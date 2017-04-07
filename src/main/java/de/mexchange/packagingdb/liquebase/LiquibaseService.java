package de.mexchange.packagingdb.liquebase;

import de.mexchange.packagingdb.common.exception.DbSchemaException;
import liquibase.change.AddColumnConfig;
import liquibase.change.Change;
import liquibase.change.ColumnConfig;
import liquibase.change.core.AddColumnChange;
import liquibase.change.core.CreateTableChange;
import liquibase.change.core.RenameColumnChange;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.visitor.ChangeExecListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LiquibaseService {

    private static final Logger logger = LoggerFactory.getLogger(SnapshotUpdater.class);

    @Autowired
    private LastenheftLiquibase liquibase;

    @Autowired
    private SnapshotUpdater snapshotUpdater;

    /**
     * Applies all the change sets described in the changeLog to the current database schema
     */
    public void performSchemaUpdate() throws DbSchemaException {
        logger.info("Performing schema update");
        validateSQLFileRollback(liquibase.getPendingChangeSets());
        liquibase.performSchemaUpdate();
    }

    public void clearChecksums() throws DbSchemaException {
        logger.info("Clearing Checksums");
        liquibase.clearCheckSums();
    }

    /**
     * Generates the SQL that would be executed if the  changeLog were to be applied to the current
     * database schema.  Does not actually run the SQL in question.  Writes result to specified file.
     */
    public void generateSchemaUpdateSQL(File file) throws DbSchemaException {
        try (FileWriter writer = new FileWriter(file)) {
            generateSchemaUpdateSQL(writer);
        } catch (IOException e) {
            throw new DbSchemaException(e);
        }
    }

    /**
     * Generates the SQL that would be executed if the  changeLog were to be applied to the current
     * database schema.  Does not actually run the SQL in question.  Writes result to specified writer.
     */
    public void generateSchemaUpdateSQL(Writer writer) throws DbSchemaException {
        logger.info("Generating schema update SQL");

        try {
            validateSQLFileRollback(liquibase.getPendingChangeSets());
        } catch (DbSchemaException e) {
            logger.warn("Missing rollback statement", e);
        }

        liquibase.generateSchemaUpdateSQL(writer);
    }

    /**
     * Creates a changeLog file that describes the current state of the database schema.
     */
    public void snapshotCurrentSchema(File changeLog, String author, String idPrefix, Collection<SnapshotOptions> options) throws DbSchemaException {
        if (changeLog.exists()) {
            if (!changeLog.delete()) {
                throw new DbSchemaException("Unable to delete changelog file: " + changeLog.getAbsolutePath());
            }
        }

        logger.info("Generating snapshot...");
        liquibase.snapshotCurrentSchema(changeLog, author);

        String prefix = "";
        if (idPrefix != null && !idPrefix.isEmpty()) {
            prefix = idPrefix + "_";
        }

        // always include date as part of the prefix
        prefix = prefix + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "_";

        snapshotUpdater.updateSnapshot(changeLog, prefix, options);
    }

    /**
     * Generates the SQL that would restore the database schema to its current state if the current
     * changeLog were to be applied. Writes result to specified file.
     */
    public void generateFutureRollbackSQL(File file) throws DbSchemaException {
        try (FileWriter writer = new FileWriter(file)) {
            generateSchemaUpdateSQL(writer);
        } catch (IOException e) {
            throw new DbSchemaException(e);
        }
    }

    /**
     * Generates the SQL that would restore the database schema to its current state if the current
     * changeLog were to be applied.  Writes result to specified writer.
     */
    public void generateFutureRollbackSQL(Writer writer) throws DbSchemaException {
        logger.info("Generating future rollback SQL");
        liquibase.generateFutureRollbackSQL(writer);
    }

    /**
     * Tags the current database schema.  This tag can be used later to perform a rollbackToTag command.
     */
    public void tagSchema(String tag) throws DbSchemaException {
        liquibase.tagSchema(tag);
    }

    /**
     * Rolls back the current database schema to the specified tag
     */
    public void rollbackSchema(String tag) throws DbSchemaException {
        liquibase.rollbackToTag(tag);

    }

    /**
     * Rolls back the current database schema to the specified date
     */
    public void rollbackSchema(Date date) throws DbSchemaException {
        liquibase.rollbackToDate(date);
    }

    public void validate() throws DbSchemaException {
        logger.info("Validating ChangeLog");
        liquibase.validate();

        // custom rule validation
        List<ChangeSet> changeSets = liquibase.getAllChangeSets();
        validateSQLFileRollback(changeSets);
        validateColumnLengths(changeSets);
    }

    public void generateCheckSumFile(Writer writer) throws DbSchemaException {
        logger.info("Generating CheckSums from changelog");
        liquibase.generateCheckSumInfo(writer);
    }

    public void generateCheckSumFile(File file) throws DbSchemaException {
        logger.info("Generating CheckSums from changelog");
        try (FileWriter writer = new FileWriter(file)) {
            liquibase.generateCheckSumInfo(writer);
        } catch (IOException e) {
            throw new DbSchemaException(e);
        }
    }

    public void verifyCheckSums(File referenceFile) throws DbSchemaException {
        logger.info("Verifying checksums...");
        liquibase.verifyCheckSums(referenceFile);
        logger.info("Checksums verified.  Everything looks great.");
    }

    /**
     * Returns true if the changeLog contains changeSets that have not been run on this database.
     */
    public boolean hasPendingChanges() throws DbSchemaException {
        return !liquibase.getPendingChangeSets().isEmpty();
    }

    /**
     * Returns the ID of all pending ChangeSets
     */
    @SuppressWarnings("unused")
    public List<String> getPendingChangeIDs() throws DbSchemaException {
        List<String> changeIDs = new ArrayList<>();
        for (ChangeSet changeSet : liquibase.getPendingChangeSets()) {
            changeIDs.add(changeSet.getId());
        }
        return changeIDs;
    }

    /**
     * Sets the provided logger as the 'liquibase' logger.  All logging activity from the liquibase module
     * will be routed through this logger.
     */
    @SuppressWarnings("unused")
    public void registerLogger(org.slf4j.Logger logger) {
        //SchemaLogFactory.getInstance().registerLogger("liquibase", logger);
    }

    /**
     * Sets the changeExecListener.  This allows the schema upgrade process to be monitored.
     */
    @SuppressWarnings("unused")
    public void registerChangeLogListener(ChangeExecListener changeExecListener) {
        liquibase.registerChangeLogListener(changeExecListener);
    }

    /**
     * Ensures that any pending changes that use a SQL file have a rollback clause defined.  Does not check
     * the validity of the rollback; it only checks that it exists.
     */
    private void validateSQLFileRollback(List<ChangeSet> changeSets) throws DbSchemaException {

        if (changeSets.isEmpty()) return;

        for (ChangeSet changeSet : changeSets) {
            validateSQLFileRollback(changeSet);
        }
    }

    private void validateSQLFileRollback(ChangeSet changeSet) throws DbSchemaException {
        for (Change change : changeSet.getChanges()) {
            if (!change.supportsRollback(null) && changeSet.getRollback() != null && changeSet.getRollback().getChanges().isEmpty()) {
                throw new DbSchemaException("Invalid ChangeSet. Rollback MUST be specified for '" +
                        change.getClass().getSimpleName() + "'. ID: \"" + changeSet.getId() + "\"" + " FILE: \"" + changeSet.getFilePath() + "\"");
            }
        }
    }

    private void validateColumnLengths(List<ChangeSet> changeSets) throws DbSchemaException {
        for (ChangeSet changeSet : changeSets) {
            validateColumnLengths(changeSet);
        }
    }

    private void validateColumnLengths(ChangeSet changeSet) throws DbSchemaException {
        for (Change change : changeSet.getChanges()) {

            String tableName = null;
            Set<String> columnNames = new LinkedHashSet<>();

            if (change instanceof CreateTableChange) {
                tableName = ((CreateTableChange) change).getTableName();
                for (ColumnConfig columnConfig : ((CreateTableChange) change).getColumns()) {
                    columnNames.add(columnConfig.getName());
                }

            } else if (change instanceof AddColumnChange) {
                tableName = ((AddColumnChange) change).getTableName();
                for (AddColumnConfig columnConfig : ((AddColumnChange) change).getColumns()) {
                    columnNames.add(columnConfig.getName());
                }

            } else if (change instanceof RenameColumnChange) {
                tableName = ((RenameColumnChange)change).getTableName();
                columnNames.add(((RenameColumnChange) change).getNewColumnName());
            }

            // verify that the column names conform to length standards
            for (String columnName : columnNames) {
                if (columnName != null && columnName.trim().length() > 27) {
                    throw new DbSchemaException("Column name is too long. Must be <= 27 characters. Column: " + tableName + "." + columnName);
                }
            }
        }

    }
}
