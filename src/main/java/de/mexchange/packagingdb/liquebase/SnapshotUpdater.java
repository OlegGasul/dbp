package de.mexchange.packagingdb.liquebase;

import de.mexchange.packagingdb.common.util.StringHelper;
import org.slf4j.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;

@Component
public class SnapshotUpdater {

    private static final Logger logger = LoggerFactory.getLogger(SnapshotUpdater.class);

    Pattern idFieldPattern = Pattern.compile("\\sid=\".*?-");

    /**
     * Adds preConditions to the changeLog file, which prevents the schema upgrade from failing if the database
     * entity already exists.  This allows us to use the changeLog on an existing databases.  Also, replaces the
     * 'id' field value with the given idPrefix.
     */
    public void updateSnapshot(File changeLog, String idPrefix, Collection<SnapshotOptions> options) {

        if (options == null) options = Collections.emptyList();

        Map<ChangeType, FileContents> fileContentsByChangeType = new HashMap<>();
        if (options.contains(SnapshotOptions.splitFile)) {
            FileContents createTable = new FileContents("tables");
            FileContents constraint = new FileContents("constraints");
            FileContents index = new FileContents("indexes");

            fileContentsByChangeType.put(ChangeType.createTable, createTable);
            fileContentsByChangeType.put(ChangeType.addPrimaryKey, constraint);
            fileContentsByChangeType.put(ChangeType.addForeignKeyConstraint, constraint);
            fileContentsByChangeType.put(ChangeType.addUniqueConstraint, constraint);
            fileContentsByChangeType.put(ChangeType.createIndex, index);

        } else {
            FileContents allContent = new FileContents("");
            fileContentsByChangeType.put(ChangeType.createTable, allContent);
            fileContentsByChangeType.put(ChangeType.addPrimaryKey, allContent);
            fileContentsByChangeType.put(ChangeType.addForeignKeyConstraint, allContent);
            fileContentsByChangeType.put(ChangeType.addUniqueConstraint, allContent);
            fileContentsByChangeType.put(ChangeType.createIndex, allContent);
        }


        try (BufferedReader reader = new BufferedReader(new FileReader(changeLog))) {

            List<String> header = readHeader(reader);
            if (header == null) {
                logger.error("Unable to read file header: " + changeLog.getName());
                return;
            }

            boolean doPreConditions = options.contains(SnapshotOptions.preConditions);
            ChangeSet changeSet;
            while ((changeSet = readChangeSet(reader)) != null) {
                ChangeType changeType = changeSet.getChangeType();

                FileContents contents = fileContentsByChangeType.get(changeType);

                updateIDField(changeSet, idPrefix);
                if (doPreConditions) {
                    changeSet.insertPreConditions();
                }
                contents.addAll(changeSet.getContents());
            }

            String baseFileName = changeLog.getName();
            if (baseFileName.endsWith(".xml")) {
                baseFileName = baseFileName.substring(0, baseFileName.length() - 4);
            }

            for (FileContents fileContents : fileContentsByChangeType.values()) {
                String newFileName;
                if (StringHelper.isBlank(fileContents.getFileNameSuffix())) {
                    newFileName = baseFileName + ".xml";
                } else {
                    newFileName = baseFileName + "-" + fileContents.getFileNameSuffix() + ".xml";
                }

                File outputFile = new File(changeLog.getParent(), newFileName);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                    // write header
                    for (String line : header) {
                        writer.write(line);
                        writer.newLine();
                    }

                    // write fileContents
                    for (String line : fileContents) {
                        writer.write(line);
                        writer.newLine();
                    }

                    // write footer
                    writer.write("</databaseChangeLog>");
                    writer.newLine();

                } catch (IOException e) {
                    logger.error("Unable to write changelog file: " + outputFile.getName(), e);
                }
            }

        } catch (IOException e) {
            logger.error("Unable to read changelog file", e);
        }
    }

    private List<String> readHeader(BufferedReader reader) throws IOException {
        List<String> header = new ArrayList<>();

        String firstLine = reader.readLine();
        if (firstLine == null) return null;

        String secondLine = reader.readLine();
        if (secondLine == null) return null;

        header.add(firstLine);
        header.add(secondLine);

        return header;
    }

    private ChangeSet readChangeSet(BufferedReader reader) throws IOException {
        List<String> contents = new ArrayList<>();

        String line;
        boolean closeTag = false;
        while ((line = reader.readLine()) != null) {
            contents.add(line);
            if (line.trim().equals("</changeSet>")) {
                closeTag = true;
                break;
            }
        }

        if (!closeTag) {
            return null;
        }

        return new ChangeSet(contents);
    }

    private void updateIDField(ChangeSet changeSet, String idPrefix) {
        if (changeSet == null || changeSet.getContents() == null || changeSet.getContents().isEmpty()) {
            return;
        }
        String first = changeSet.getContents().get(0);
        first = updateIDField(first, idPrefix);

        changeSet.getContents().remove(0);
        changeSet.getContents().add(0, first);
    }

    private String updateIDField(String text, String idPrefix) {
        if (idPrefix == null || idPrefix.isEmpty()) return text;
        Matcher matcher = idFieldPattern.matcher(text);
        if (matcher.find()) {
            text = matcher.replaceAll(" id=\"" + idPrefix);
        }
        return text;
    }

    private enum ChangeType {
        createTable("<createTable\\s*tableName=\"(.*)\".*>") {
            @Override
            public List<String> generatePreConditionOutput(Matcher matcher) {
                String tableName = matcher.group(1);

                List<String> output = new ArrayList<>();
                output.add("\t\t<preConditions onFail=\"MARK_RAN\">");
                output.add("\t\t\t<not>");
                output.add("\t\t\t\t<tableExists tableName=\"" + tableName + "\"/>");
                output.add("\t\t\t</not>");
                output.add("\t\t</preConditions>");

                return output;
            }
        },

        addPrimaryKey("<addPrimaryKey\\s.*constraintName=\"(.*?)\"\\s*.*tableName=\"(.*?)\".*>") {
            @Override
            public List<String> generatePreConditionOutput(Matcher matcher) {
                String constraintName = matcher.group(1);

                List<String> output = new ArrayList<>();
                output.add("\t\t<preConditions onFail=\"MARK_RAN\">");
                output.add("\t\t\t<sqlCheck expectedResult=\"0\">");
                output.add("\t\t\t\tSELECT COUNT(1) FROM all_constraints WHERE constraint_name = '" + constraintName + "' AND constraint_type = 'P'");
                output.add("\t\t\t</sqlCheck>");
                output.add("\t\t</preConditions>");

                return output;
            }
        },

        addUniqueConstraint("<addUniqueConstraint\\s.*constraintName=\"(.*?)\".*>") {
            @Override
            public List<String> generatePreConditionOutput(Matcher matcher) {
                String constraintName = matcher.group(1);

                List<String> output = new ArrayList<>();
                output.add("\t\t<preConditions onFail=\"MARK_RAN\">");
                output.add("\t\t\t<sqlCheck expectedResult=\"0\">");
                output.add("\t\t\t\tSELECT COUNT(1) FROM all_constraints WHERE constraint_name = '" + constraintName + "' AND constraint_type = 'U'");
                output.add("\t\t\t</sqlCheck>");
                output.add("\t\t</preConditions>");

                return output;
            }
        },

        addForeignKeyConstraint("<addForeignKeyConstraint\\s.*constraintName=\"(.*?)\".*>") {
            @Override
            public List<String> generatePreConditionOutput(Matcher matcher) {
                String constraintName = matcher.group(1);

                List<String> output = new ArrayList<>();
                output.add("\t\t<preConditions onFail=\"MARK_RAN\">");
                output.add("\t\t\t<sqlCheck expectedResult=\"0\">");
                output.add("\t\t\t\tSELECT COUNT(1) FROM all_constraints WHERE constraint_name = '" + constraintName + "' AND constraint_type = 'R'");
                output.add("\t\t\t</sqlCheck>");
                output.add("\t\t</preConditions>");

                return output;
            }
        },

        createIndex("<createIndex\\s.*indexName=\"(.*?)\".*>") {
            @Override
            public List<String> generatePreConditionOutput(Matcher matcher) {
                String constraintName = matcher.group(1);

                List<String> output = new ArrayList<>();
                output.add("\t\t<preConditions onFail=\"MARK_RAN\">");
                output.add("\t\t\t<sqlCheck expectedResult=\"0\">");
                output.add("\t\t\t\tSELECT COUNT(1) FROM all_indexes WHERE index_name = '" + constraintName + "'");
                output.add("\t\t\t</sqlCheck>");
                output.add("\t\t</preConditions>");

                return output;
            }
        };

        private Pattern pattern;

        ChangeType(String regex) {
            pattern = Pattern.compile(regex);
        }

        public Matcher matches(String text) {
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                return matcher;
            }
            return null;
        }

        public abstract List<String> generatePreConditionOutput(Matcher matcher);
    }

    private class ChangeSet {
        private List<String> contents;
        private boolean hasPrecondition;
        private ChangeType type;
        private Matcher matcher;

        ChangeSet(List<String> contents) {
            this.contents = contents;
            init();
        }

        private void init() {
            for (ChangeType changeType : ChangeType.values()) {
                for (String line : contents) {
                    if (line.startsWith("<preCondition ")) {
                        this.hasPrecondition = true;
                    }

                    Matcher matcher = changeType.matches(line);
                    if (matcher != null) {
                        this.matcher = matcher;
                        this.type = changeType;
                        break;
                    }
                }
                if (this.type != null) break;
            }
        }

        public void insertPreConditions() {
            if (this.hasPrecondition) return;

            List<String> updated = new ArrayList<>();

            updated.add(this.contents.get(0));
            updated.addAll(type.generatePreConditionOutput(this.matcher));
            updated.addAll(this.contents.subList(1, this.contents.size()));

            this.hasPrecondition = true;

            this.contents = updated;
        }

        public List<String> getContents() {
            return this.contents;
        }

        public ChangeType getChangeType() {
            return this.type;
        }
    }

    private class FileContents implements Iterable<String> {
        private String fileNameSuffix;
        private List<String> contents = new ArrayList<>();

        FileContents(String fileNameSuffix) {
            this.fileNameSuffix = fileNameSuffix;
        }

        public String getFileNameSuffix() {
            return fileNameSuffix;
        }

        @Override
        public Iterator<String> iterator() {
            return contents.iterator();
        }

        public boolean addAll(Collection<String> collection) {
            return contents.addAll(collection);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof FileContents)) return false;
            FileContents fileContents = (FileContents) obj;
            if (fileContents.fileNameSuffix == null) return this.fileNameSuffix == null;
            return fileContents.fileNameSuffix.equals(this.fileNameSuffix);
        }

        @Override
        public int hashCode() {
            if (fileNameSuffix == null) return 0;
            return fileNameSuffix.hashCode();
        }
    }
}