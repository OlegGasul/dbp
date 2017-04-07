package de.mexchange.packagingdb.common.search;

import java.util.Arrays;
import java.util.List;

/**
 * User: Garik
 * Date: 6/24/16
 * Time: 12:06 AM
 */
public enum FieldType {

    OBJECT  (1, "label.field.type.object",
            Arrays.asList(
                    ValueOption.EQUAL
            )
    ),
    DOUBLE  (2, "label.field.type.double",
            Arrays.asList(
                ValueOption.EQUAL, ValueOption.GREATER, ValueOption.LESS
            )
    ),
    STRING  (3, "label.field.type.text",
            Arrays.asList(
                ValueOption.EQUAL, ValueOption.STARTS_WITH, ValueOption.ENDS_WITH, ValueOption.CONTAINS
            )
    ),
    DATE    (4, "label.field.type.date",
            Arrays.asList(
                ValueOption.EQUAL, ValueOption.GREATER, ValueOption.LESS
            )
    ),
    ENUM    (5, "label.field.type.enum",
            Arrays.asList(
                ValueOption.EQUAL
            )
    );

    FieldType(int value, String labelKey, List<ValueOption> options) {
        this.value = value;
        this.labelKey = labelKey;
        this.options = options;
    }

    public static FieldType valueOf(final int value) {
        for (FieldType e : FieldType.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }

    private final int value;
    private final String labelKey;
    private final List<ValueOption> options;

    public int getValue() {
        return value;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public List<ValueOption> getOptions() {
        return options;
    }
}
