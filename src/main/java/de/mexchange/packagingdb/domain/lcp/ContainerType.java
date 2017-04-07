package de.mexchange.packagingdb.domain.lcp;

/**
 * Container Types
 */
public enum ContainerType implements Labelable {

    Cylindrical_Clamping_Ring   (2, "Cylindrical Clamping Ring", "label.container.type.cylindrical.clamping.ring"),
    Conical_Clamping_Ring       (3, "Conical Clamping Ring", "label.container.type.conical.clamping.ring"),
    IBC_Cubic                   (4, "IBC Cubic", "label.container.type.cubic"),
    IBC_Cylindrical             (5, "IBC Cylindrical", "label.container.type.cylindrical"),
    IBC_Bulk                    (6, "IBC Bulk", "label.container.type.bulk"),
    Canister_Cubic              (7, "Canister CubicEntity", "label.container.type.canister.cubic"),
    Big_Bags                    (8, "Big Bags", "label.container.type.big.bags"),
    Conical_Cans                (9, "Doses", "label.container.type.doses"),
    ;

    ContainerType(final int value, final String name, final String labelKey) {
        this.value = value;
        this.name = name;
        this.labelKey = labelKey;
    }

    private int value;

    private String name;

    private String labelKey;

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getLabelKey() {
       return labelKey;
    }

    public static ContainerType valueOf(Long packaging_form_id) {
        for (ContainerType type : ContainerType.values()) {
            if (type.getValue() == packaging_form_id.intValue()) {
                return type;
            }
        }
        return null;
    }
}
