package de.mexchange.packagingdb.domain.lcp;

/**
 * Created by Serozh on 6/15/2016.
 */
public enum Status {

    ACTIVE      (1, "Active"),
    DISABLED    (2, "Disabled"),
    DELETED     (3, "Deleted");

    Status(int value, String title) {
        this.value = value;
        this.title = title;
    }

    public static Status valueOf(int value) {
        for (Status e : Status.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }

    private final int value;

    private final String title;
}
