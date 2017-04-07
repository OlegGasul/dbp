package de.mexchange.packagingdb.common.search;

/**
 * User: Garik
 * Date: 6/24/16
 * Time: 12:06 AM
 */
public enum ValueOption {

    GREATER         (1, "label.greater"),
    LESS            (2, "label.less"),
    EQUAL           (3, "label.equal"),
    STARTS_WITH     (4, "label.starts.with"),
    ENDS_WITH       (5, "label.ends.with"),
    CONTAINS        (6, "label.containes");

    ValueOption(int value, String labelKey) {
        this.value = value;
        this.labelKey = labelKey;
    }

    public static ValueOption valueOf(final int value) {
        for (ValueOption e : ValueOption.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }

    private final int value;
    private final String labelKey;

    public int getValue() {
        return value;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public String getName() {
        return name();
    }
}
