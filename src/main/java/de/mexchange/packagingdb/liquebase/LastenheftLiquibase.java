package de.mexchange.packagingdb.liquebase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.stereotype.Component;

import de.mexchange.packagingdb.common.exception.DbSchemaException;
import de.mexchange.packagingdb.configuration.SchemaAutoRetryConfig;
import liquibase.CatalogAndSchema;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.changelog.visitor.ChangeExecListener;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.database.OfflineConnection;
import liquibase.diff.output.DiffOutputControl;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.executor.Executor;
import liquibase.executor.ExecutorService;
import liquibase.integration.commandline.CommandLineUtils;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.parser.ChangeLogParser;
import liquibase.parser.ChangeLogParserFactory;
import liquibase.resource.ResourceAccessor;
import liquibase.util.StringUtils;

/**
 * Created by Arthur on 5/16/2016.
 */
@Component
public class LastenheftLiquibase extends SpringLiquibase {

    private static Logger LOG = LoggerFactory.getLogger(LastenheftLiquibase.class);

    @Autowired
    private LiquibaseProperties liquibaseProperties;

    @Autowired
    private SchemaAutoRetryConfig autoRetryConfig;

    private ChangeExecListener changeExecListener;

    /**
     * Applies all the change sets described in the changeLog to the current database schema.
     */
    public void performSchemaUpdate() throws DbSchemaException {
        doUpdate();
    }

    /**
     * Sets all the checkSum values in the DATABASECHANGELOG table to null.  This will cause them
     * to be recalculated the next time the changeLog is processed.
     */
    public void clearCheckSums() throws DbSchemaException {
        Liquibase liquibase = null;
        try {

            liquibase = createLiquibase();
            liquibase.clearCheckSums();

        } catch (LiquibaseException e) {
            throw new DbSchemaException(e.getMessage(), e);
        } finally {
            try {
                closeLiquibaseDatabase(liquibase);
            } catch (DatabaseException e) {
                LOG.warn("Unable to close liquibase database", e);
            }
        }
    }

    /**
     * Generates the SQL that would be executed if the  changeLog were to be applied to the current
     * database schema.  Does not actually run the SQL in question.
     */
    public void generateSchemaUpdateSQL(Writer writer) throws DbSchemaException {
        doUpdate(writer);
    }

    /**
     * Creates a changeLog file that describes the current state of the database schema.
     */
    public void snapshotCurrentSchema(File changeLogFile, String author) throws DbSchemaException {

        Liquibase liquibase = null;
        try {
            liquibase = createLiquibase();
            CatalogAndSchema[] finalSchemas = new CatalogAndSchema[]{new CatalogAndSchema(null, null)};

            CommandLineUtils.doGenerateChangeLog(changeLogFile.getAbsolutePath(),
                    liquibase.getDatabase(), finalSchemas, null, author, null, null, new DiffOutputControl(false, false, false));

        } catch (Exception e) {
            throw new DbSchemaException(e);

        } finally {
            try {
                closeLiquibaseDatabase(liquibase);
            } catch (DatabaseException e) {
                LOG.warn("Unable to close liquibase database", e);
            }
        }
    }

    /**
     * Returns a list of ChangeSets that have not yet been applied to the current schema.
     */
    public List<ChangeSet> getPendingChangeSets() throws DbSchemaException {
        Liquibase liquibase = null;
        try {
            liquibase = createLiquibase();
            return liquibase.listUnrunChangeSets(new Contexts(getContexts()), new LabelExpression(getLabels()));
        } catch (LiquibaseException e) {
            throw new DbSchemaException(e);
        } finally {
            try {
                closeLiquibaseDatabase(liquibase);
            } catch (DatabaseException e) {
                LOG.warn("Unable to close liquibase database", e);
            }
        }
    }

    public List<ChangeSet> getAllChangeSets() throws DbSchemaException {
        Liquibase liquibase = null;
        try {
            liquibase = createLiquibase();
            return liquibase.getDatabaseChangeLog().getChangeSets();
        } catch (LiquibaseException e) {
            throw new DbSchemaException(e);
        } finally {
            try {
                closeLiquibaseDatabase(liquibase);
            } catch (DatabaseException e) {
                LOG.warn("Unable to close liquibase database", e);
            }
        }
    }

    /**
     * Generates the SQL that would rollback the currently pending ChangeSets, restoring the database schema
     * to its current state
     */
    public void generateFutureRollbackSQL(Writer outputWriter) throws DbSchemaException {
        Liquibase liquibase = null;
        try {
            liquibase = createLiquibase();
            liquibase.futureRollbackSQL((String) null, new Contexts(getContexts()), new LabelExpression(getLabels()), outputWriter);
        } catch (LiquibaseException e) {
            throw new DbSchemaException(e);
        } finally {
            try {
                closeLiquibaseDatabase(liquibase);
            } catch (DatabaseException e) {
                LOG.warn("Unable to close liquibase database", e);
            }
        }
    }

    /**
     * Tags the current database schema.  This tag can be used later to perform a rollbackToTag command.
     */
    public void tagSchema(String tag) throws DbSchemaException {
        Liquibase liquibase = null;
        try {
            liquibase = createLiquibase();
            liquibase.tag(tag);
        } catch (LiquibaseException e) {
            throw new DbSchemaException(e);
        } finally {
            try {
                closeLiquibaseDatabase(liquibase);
            } catch (DatabaseException e) {
                LOG.warn("Unable to close liquibase database", e);
            }
        }
    }

    /**
     * Rolls back the current database schema to the specified tag
     */
    public void rollbackToTag(String tag) throws DbSchemaException {
        Liquibase liquibase = null;
        try {
            liquibase = createLiquibase();
            liquibase.rollback(tag, new Contexts(getContexts()), new LabelExpression(getLabels()));
        } catch (LiquibaseException e) {
            throw new DbSchemaException(e);
        } finally {
            try {
                closeLiquibaseDatabase(liquibase);
            } catch (DatabaseException e) {
                LOG.warn("Unable to close liquibase database", e);
            }
        }
    }

    /**
     * Rolls back the current database schema to the specified date
     */
    public void rollbackToDate(Date date) throws DbSchemaException {
        Liquibase liquibase = null;
        try {
            liquibase = createLiquibase();
            liquibase.rollback(date, new Contexts(getContexts()), new LabelExpression(getLabels()));
        } catch (LiquibaseException e) {
            throw new DbSchemaException(e);
        } finally {
            try {
                closeLiquibaseDatabase(liquibase);
            } catch (DatabaseException e) {
                LOG.warn("Unable to close liquibase database", e);
            }
        }
    }

    /**
     * Processes the changeLog file and ensures that it is properly formed xml that conforms to the liquibase standard
     */
    public void validate() throws DbSchemaException {
        Liquibase liquibase = null;
        try {
            liquibase = createLiquibase();
            liquibase.validate();
        } catch (LiquibaseException e) {
            throw new DbSchemaException(e);
        } finally {
            try {
                closeLiquibaseDatabase(liquibase);
            } catch (DatabaseException e) {
                LOG.warn("Unable to close liquibase database", e);
            }
        }
    }

    /**
     * Generates checkSums for all the changeSets contained in the changeLog.
     * Format:
     * 		// Header
     * 		ID<tab>CheckSum
     * 		// Footer
     */
    public void generateCheckSumInfo(Writer outputWriter) throws DbSchemaException {
        String warning = "// WARNING: DO NOT CHANGE THIS FILE MANUALLY!  IT IS GENERATED AUTOMATICALLY AND IS NECESSARY TO ENFORCE CHANGELOG INTEGRITY.";

        PrintWriter printWriter = new PrintWriter(outputWriter);

        // header
        for (int i = 0; i < 5; i++) {
            printWriter.println(warning);
        }
        printWriter.println();

        // contents
        Map<String, String> checkSums = getCurrentCheckSums();
        for (Map.Entry<String, String> entry : checkSums.entrySet()) {
            String id = entry.getKey();
            String checkSum = entry.getValue();

            printWriter.println(id + "\t" + checkSum);
        }

        // footer
        printWriter.println();
        for (int i = 0; i < 5; i++) {
            printWriter.println(warning);
        }
    }

    /**
     * Reads checkSums from the provided file.  Throws an exception if the provided checkSums
     * differ from the current changeLog checkSum set.
     */
    public void verifyCheckSums(File referenceFile) throws DbSchemaException {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(referenceFile);
        } catch (FileNotFoundException e) {
            throw new DbSchemaException(e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new DbSchemaException("Could not close file reader", e);
                }
            }
        }

        compareCheckSums(readCheckSumFile(fileReader), getCurrentCheckSums());
    }

    public void registerChangeLogListener(ChangeExecListener changeExecListener) {
        this.changeExecListener = changeExecListener;
    }

    private void compareCheckSums(Map<String, String> oldCheckSums, Map<String, String> newCheckSums) throws DbSchemaException {
        for (Map.Entry<String, String> newEntry : newCheckSums.entrySet()) {
            String id = newEntry.getKey();
            String newSum = newEntry.getValue();

            String oldSum = oldCheckSums.get(id);
            if (oldSum != null && !oldSum.equals(newSum)) {
                throw new DbSchemaException("ChangeSet has been altered!  CheckSum mismatch for changeSet: " + id);
            }
        }
    }

    private Map<String, String> readCheckSumFile(Reader inputReader) throws DbSchemaException {
        Map<String, String> checkSums = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(inputReader)) {
            String line;
            while((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.trim().startsWith("//")) {
                    continue;
                }
                String[] parts = line.split("\\t", 2);
                checkSums.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            throw new DbSchemaException(e);
        }

        return checkSums;
    }

    private Map<String, String> getCurrentCheckSums() throws DbSchemaException {
        Map<String, String> checkSums = new LinkedHashMap<>();

        try {
            // read changeSets from changeLog, store in checkSums map
            SpringResourceOpener resourceAccessor = createResourceOpener();
            ChangeLogParameters changeLogParameters = new ChangeLogParameters(createDatabase(null, resourceAccessor));

            ChangeLogParser parser = ChangeLogParserFactory.getInstance().getParser(getChangeLog(), resourceAccessor);
            DatabaseChangeLog databaseChangeLog = parser.parse(getChangeLog(), changeLogParameters, resourceAccessor);

            for (ChangeSet changeSet : databaseChangeLog.getChangeSets()) {
                checkSums.put(changeSet.getId(), changeSet.generateCheckSum().toString());
            }
        } catch (LiquibaseException e) {
            throw new DbSchemaException(e);
        }

        return checkSums;
    }

    private void closeLiquibaseDatabase(Liquibase liquibase) throws DatabaseException {
        Database database = null;
        if (liquibase != null) {
            database = liquibase.getDatabase();
        }
        if (database != null) {
            database.close();
        }
    }

    private void doUpdate() throws DbSchemaException {
        doUpdate(null);
    }

    private void doUpdate(Writer outputWriter) throws DbSchemaException {
        Liquibase liquibase = null;
        try {

            liquibase = createLiquibase();
            if (changeExecListener != null) {
                liquibase.setChangeExecListener(changeExecListener);
            }

            generateRollbackFile(liquibase);

            if (outputWriter == null) {
                liquibase.update(new Contexts(getContexts()), new LabelExpression(getLabels()));
            } else {
                liquibase.update(new Contexts(getContexts()), new LabelExpression(getLabels()), outputWriter);
            }

        } catch (LiquibaseException e) {
            throw new DbSchemaException(e);
        } finally {
            try {
                closeLiquibaseDatabase(liquibase);
            } catch (DatabaseException e) {
                LOG.warn("Unable to close liquibase database", e);
            }
        }
    }

    private void generateRollbackFile(Liquibase liquibase) throws LiquibaseException {
        if (rollbackFile != null) {
            FileWriter output = null;
            try {
                output = new FileWriter(rollbackFile);
                liquibase.futureRollbackSQL((String)null, new Contexts(getContexts()), new LabelExpression(getLabels()), output);
            } catch (IOException e) {
                throw new LiquibaseException("Unable to generate rollback file.", e);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    log.severe("Error closing output", e);
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws LiquibaseException {
        // do nothing
        // Overridden to prevent SpringLiquibase.afterPropertiesSet() from being run automatically on startup.
    }

    /**
     * This method is overridden to add offline:oracle connection functionality when a null connection is passed in.  Otherwise,
     * this method behaves exactly the same as its parent.
     */
    @Override
    protected Database createDatabase(Connection c, ResourceAccessor resourceAccessor) throws DatabaseException {
        if (c != null) {
            return super.createDatabase(c, resourceAccessor);
        }

        // Create offline database
        String url = liquibaseProperties.getUrl();
        if (url == null || url.isEmpty()) {
            url = "offline:oracle";
        }
        if (url.startsWith("jdbc:")) {
            url = url.substring(5);
        }
        DatabaseConnection liquibaseConnection = new OfflineConnection(url, resourceAccessor);

        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(liquibaseConnection);
        if (StringUtils.trimToNull(defaultSchema) != null) {
            database.setDefaultSchemaName(defaultSchema);
        }
        return database;
    }

    private Liquibase createLiquibase() throws DbSchemaException {
        Connection connection;
        Liquibase liquibase;
        try {
            String url = liquibaseProperties.getUrl();
            if (url != null && url.startsWith("jdbc:offline")) {
                connection = null;
            } else {
                connection = getDataSource().getConnection();
            }

            liquibase = createLiquibase(connection);
        } catch (SQLException | LiquibaseException e) {
            throw new DbSchemaException(e.getMessage(), e);
        }

        Executor retryExecutor = new AutoRetryExecutor(
                liquibase.getDatabase(),
                autoRetryConfig.getAttempts(),
                autoRetryConfig.getInterval(),
                autoRetryConfig.getRetryAll()
                );

        ExecutorService.getInstance().setExecutor(liquibase.getDatabase(), retryExecutor);

        return liquibase;
    }
}
