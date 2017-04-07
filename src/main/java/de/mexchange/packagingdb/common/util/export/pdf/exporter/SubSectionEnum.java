package de.mexchange.packagingdb.common.util.export.pdf.exporter;

/**
 * Created by Serozh on 6/7/2016.
 */
public enum SubSectionEnum {

    VAPOUR_PRESS_50C                ("", ""),
    VAPOUR_PRESS_55C                ("", ""),
    PACK_GRP_I                      ("", ""),
    PACK_GRP_II                     ("", ""),
    PACK_GRP_III                    ("", ""),
    ;

    SubSectionEnum(String labelKey, String labelMethod) {
        this.labelKey = labelKey;
        this.labelMethod = labelMethod;
    }

    String labelKey;
    String labelMethod;
}
