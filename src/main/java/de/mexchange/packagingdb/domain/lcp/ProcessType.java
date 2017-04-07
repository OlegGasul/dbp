package de.mexchange.packagingdb.domain.lcp;

/**
 * Created by Serozh on 6/7/2016.
 */
public enum ProcessType {

    GLOBAL_PROCESS      (1, "Global Process", "label.global.process"),
    GLOBAL_INFORMATION  (2, "Global Information", "label.global.information")
    ;

    ProcessType(int value, String name, String label) {
        this.name = name;
        this.value = value;
        this.label = label;
    }

    public static ProcessType valueOf(int value) {
        for (ProcessType e : ProcessType.values()) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    private final int value;
    private final String name;
    private final String label;
}
