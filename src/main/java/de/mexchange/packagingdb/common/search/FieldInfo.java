package de.mexchange.packagingdb.common.search;

import java.util.List;

/**
 * User: Garik
 * Date: 6/24/16
 * Time: 12:25 AM
 */
public class FieldInfo {

    private String name;

    private String url;

    private FieldType type;

    private List<ValueOption> options;

    public FieldInfo() {}

    public FieldInfo(String name, FieldType type) {
        this(name, name, type);
    }

    public FieldInfo(String name, String url, FieldType type) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.options = type.getOptions();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public List<ValueOption> getOptions() {
        return options;
    }

    public void setOptions(List<ValueOption> options) {
        this.options = options;
    }
}
