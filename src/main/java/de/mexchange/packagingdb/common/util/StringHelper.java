package de.mexchange.packagingdb.common.util;

/**
 * Utility methods for data manipulation with Strings.
 */
public class StringHelper {

    public static boolean isBlank(String val) {
        return val == null || val.length() == 0;
    }

    public static boolean isNotBlank(String val) {
        return !isBlank(val);
    }
}
