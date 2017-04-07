package de.mexchange.packagingdb.liquebase;


import liquibase.change.Change;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.changelog.visitor.ChangeExecListener;
import liquibase.database.Database;
import liquibase.exception.PreconditionErrorException;
import liquibase.exception.PreconditionFailedException;
import liquibase.precondition.core.PreconditionContainer;
import liquibase.sql.Sql;
import liquibase.sqlgenerator.SqlGeneratorFactory;

import java.util.*;

public class LiquibaseStatusMonitor implements ChangeExecListener {

    private Map<String, Object> currentChangeSet = null;

    private Map<String, Object> lastExceptionChangeSet = null;
    private Exception lastException = null;

    private Set<String> pendingChangeSetIDs;

    private boolean isUpgrading = false;
    private boolean autoUpgrade = false;

    private int totalPendingChangeSetCount = 0;
    private int exceptionCounter = 0;

    private long currentChangeSetStartMillis = 0;

    private long upgradeStartMillis = 0;
    private long upgradeStopMillis = 0;

    @Override
    public void willRun(ChangeSet changeSet, DatabaseChangeLog databaseChangeLog, Database database, ChangeSet.RunStatus runStatus) {
        currentChangeSet = getMapFromChangeSet(changeSet, database);
        currentChangeSetStartMillis = System.currentTimeMillis();
    }

    @Override
    public void ran(ChangeSet changeSet, DatabaseChangeLog databaseChangeLog, Database database, ChangeSet.ExecType execType) {
        pendingChangeSetIDs.remove(changeSet.getId());

        if (changeSetsEqual(changeSet, lastExceptionChangeSet)) {
            lastExceptionChangeSet = null;
            lastException = null;
        }

        currentChangeSet = null;
        exceptionCounter = 0;
        currentChangeSetStartMillis = 0;
    }

    @Override
    public void runFailed(ChangeSet changeSet, DatabaseChangeLog databaseChangeLog, Database database, Exception exception) {
        lastException = exception;
        lastExceptionChangeSet = getMapFromChangeSet(changeSet, database);

        currentChangeSet = null;
        currentChangeSetStartMillis = 0;

        exceptionCounter++;
    }

    public void externalException(Exception exception) {
        // This is used to remember an exception that occurred outside the liquibase framework.
        if (lastException == null) {
            lastException = exception;
            exceptionCounter++;
        }
    }

    private List<String> getSqlFromChangeSet(ChangeSet changeSet, Database database) {
        List<String> sqlList = new ArrayList<>();

        for (Change change : changeSet.getChanges()) {
            if (change == null) continue;
            Sql[] sqlArray = SqlGeneratorFactory.getInstance().generateSql(change, database);
            if (sqlArray == null) continue;

            for (Sql sql : sqlArray) {
                sqlList.add(sql.toSql());
            }
        }
        return sqlList;
    }

    private Map<String, Object> getMapFromChangeSet(ChangeSet changeSet, Database database) {
        Map<String, Object> changeSetMap = new LinkedHashMap<>();

        changeSetMap.put("id", changeSet.getId());
        changeSetMap.put("author", changeSet.getAuthor());
        changeSetMap.put("file", changeSet.getFilePath());
        changeSetMap.put("sql", getSqlFromChangeSet(changeSet, database));

        return changeSetMap;
    }

    private boolean changeSetsEqual(ChangeSet changeSet, Map<String, Object> changeSetMap) {
        if (changeSet == null && changeSetMap == null) return true;
        if (changeSet == null || changeSetMap == null) return false;

        return changeSet.getId().equals(changeSetMap.get("id"));
    }

    @Override
    public void willRun(Change change, ChangeSet changeSet, DatabaseChangeLog changeLog, Database database) {
        // do nothing
    }

    @Override
    public void ran(Change change, ChangeSet changeSet, DatabaseChangeLog changeLog, Database database) {
        // do nothing
    }

    @Override
    public void rolledBack(ChangeSet changeSet, DatabaseChangeLog databaseChangeLog, Database database) {
        // do nothing
    }

    @Override
    public void preconditionFailed(PreconditionFailedException error, PreconditionContainer.FailOption onFail) {
        // do nothing
    }

    @Override
    public void preconditionErrored(PreconditionErrorException error, PreconditionContainer.ErrorOption onError) {
        // do nothing
    }

    public Map<String, Object> getCurrentChangeSet() {
        return currentChangeSet;
    }

    public Exception getLastException() {
        return lastException;
    }

    public Map<String, Object> getLastExceptionChangeSet() {
        return lastExceptionChangeSet;
    }

    public Set<String> getPendingChangeSetIDs() {
        return pendingChangeSetIDs;
    }

    public void setPendingChangeSetIDs(Set<String> pendingChangeSetIDs) {
        if (totalPendingChangeSetCount == 0 && pendingChangeSetIDs != null) {
            totalPendingChangeSetCount = pendingChangeSetIDs.size();
        }
        this.pendingChangeSetIDs = pendingChangeSetIDs;
    }

    public int getTotalPendingChangeSetCount() {
        return totalPendingChangeSetCount;
    }

    public boolean isUpgrading() {
        return isUpgrading;
    }

    public void setUpgrading(boolean upgrading) {
        if (upgrading) {
            upgradeStartMillis = System.currentTimeMillis();
            upgradeStopMillis = 0;
        } else {
            if (upgradeStartMillis != 0) {
                upgradeStopMillis = System.currentTimeMillis();
            }
        }
        isUpgrading = upgrading;
    }

    public int getExceptionCounter() {
        return exceptionCounter;
    }

    public boolean isAutoUpgrade() {
        return autoUpgrade;
    }

    public void setAutoUpgrade(boolean autoUpgrade) {
        this.autoUpgrade = autoUpgrade;
    }

    public long getCurrentStepMillis() {
        if (currentChangeSet == null) {
            return 0;
        }
        return System.currentTimeMillis() - currentChangeSetStartMillis;
    }

    public long getUpgradeMillis() {
        if (isUpgrading) {
            return System.currentTimeMillis() - upgradeStartMillis;
        }
        return upgradeStopMillis - upgradeStartMillis;
    }

}
