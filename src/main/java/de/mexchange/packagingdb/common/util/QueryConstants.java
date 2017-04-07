package de.mexchange.packagingdb.common.util;

import de.mexchange.packagingdb.common.search.ValueOption;

/**
 * Created by Arthur on 7/17/2016.
 */
public class QueryConstants {

    public static final String EQUAL = "= %s";

    public static final String CONTAINS = "IN";

    public static final String GREATETHEN = "> %s ";

    public static final String LESSTHEN = "< %s";

    public static final String CONTAINSTR = "LIKE '%%%s%%'";

    public static final String ENDSWITH = "LIKE '%%%s'";

    public static final String STARTSWITH = "LIKE '%s%%'";

    public static final String ISNULL = "IS NULL";

    public static final String ISNOTNULL = "IS NOT NULL";

    public static final String LIMIT  = "LIMIT";

    public static final String OFFSET = "OFFSET";

    public static final String ASC = "ASC";

    public static final String DESC = "DESC";


    public static String getConditionBy(String operator, Object value) {
        ValueOption option = ValueOption.valueOf(operator);
        switch (option) {
            case CONTAINS:      return String.format(CONTAINSTR, (String)value);
            case ENDS_WITH:     return String.format(ENDSWITH, (String)value);
            case STARTS_WITH:   return String.format(STARTSWITH, (String)value);
            case EQUAL:         return String.format(EQUAL, getValue(value));
            case GREATER:       return String.format(GREATETHEN, getValue(value));
            case LESS:          return String.format(LESSTHEN, getValue(value));
        }

        throw new IllegalArgumentException(String.format("no operation defined for [%s] operator", operator));
    }

    private static String getValue(Object value) {
        if (value instanceof Integer) {
            return String.valueOf(value);
        }

        if (value instanceof Double) {
            return String.valueOf(value);
        }

        if (value instanceof Long) {
            return String.valueOf(value);
        }

        if (value instanceof String) {
            return String.format("'%s'", (String)value);
        }


        return ISNOTNULL;
    }
}
