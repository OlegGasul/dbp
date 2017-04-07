package de.mexchange.packagingdb.liquebase;

import liquibase.database.Database;
import liquibase.exception.DatabaseException;
import liquibase.executor.jvm.JdbcExecutor;
import liquibase.sql.visitor.SqlVisitor;
import liquibase.statement.SqlStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Arthur on 5/16/2016.
 */
public class AutoRetryExecutor extends JdbcExecutor {

    private static Logger LOG = LoggerFactory.getLogger(AutoRetryExecutor.class);

    private int retryAttempts;
    private long retryIntervalMillis;
    private boolean retryAll;

    public AutoRetryExecutor(Database database, int retryAttempts, long retryIntervalMillis, boolean retryAll) {
        if (retryAttempts < 0 || retryAttempts > 15) {
            throw new RuntimeException("Invalid retryAttempts value: " + retryAttempts + ". Use a value between 0 and 15");
        }
        if (retryIntervalMillis < 0 || retryIntervalMillis > TimeUnit.MINUTES.toMillis(5)) {
            throw new RuntimeException("Invalid retryIntervalMillis value: " + retryIntervalMillis + ". Use a value between 0 and 5 minutes");
        }

        this.retryAttempts = retryAttempts;
        this.retryIntervalMillis = retryIntervalMillis;
        this.retryAll = retryAll;
        setDatabase(database);
    }

    @Override
    public void execute(SqlStatement sql, List<SqlVisitor> sqlVisitors) throws DatabaseException {
        execute(sql, sqlVisitors, 1);
    }

    private void execute(SqlStatement sql, List<SqlVisitor> sqlVisitors, int retryCount) throws DatabaseException {
        try {
            super.execute(sql, sqlVisitors);
        } catch (DatabaseException e) {
            if (isRetryException(e) && retryCount < retryAttempts) {
                LOG.info(e.getMessage());
                LOG.info("Attempt " + (retryCount) + " of " + retryAttempts + " failed. Waiting " + retryIntervalMillis + "ms before trying again.");
                try {
                    Thread.sleep(retryIntervalMillis);
                } catch (InterruptedException e1) { /* do nothing */ }

                execute(sql, sqlVisitors, ++retryCount);
            } else {
                throw e;
            }
        }
    }

    protected boolean isRetryException(DatabaseException e) {
        if (retryAll) return true;

        if (e == null || e.getMessage() == null) return false;

        // ORA-00054 -> Resource busy error
        return e.getMessage().startsWith("ORA-00054");
    }
}
