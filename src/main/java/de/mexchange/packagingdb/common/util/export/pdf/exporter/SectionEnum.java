package de.mexchange.packagingdb.common.util.export.pdf.exporter;

import java.util.List;

import static de.mexchange.packagingdb.common.util.export.pdf.exporter.SubSectionEnum.*;

/**
 * Created by Serozh on 6/7/2016.
 */
public enum SectionEnum {

    VPS_NUMBER                      ("export.label.vps.number", "getVPSNumber"),
    SAP_NUMBER                      ("export.label.sap.number", "getSAPNumber"),
    UN_APPROVAL_NUMBER              ("export.label.un.approval.number", "getUNApprovalNumber"),
    APPROVED_GROSS_WEIGHT           ("export.label.approved.gross.weight", "getApprovedGrossWeight"),
    APPROVED_VOLUME                 ("export.label.approved.volume", "getApprovedVolume"),
    AGGREGATION_OF_FILLING_GOOD     ("export.label.aggregation.filling.good", "getAggregationOfFillingGood"),

    WATER                           (new String[]{"export.label.water"}, new String[]{}, new SubSectionEnum[]{
            VAPOUR_PRESS_50C,
            VAPOUR_PRESS_55C,
            PACK_GRP_I,
            PACK_GRP_II,
            PACK_GRP_III,
    }),

    NOMINAL_VALUE                   ("label.export.nominal.value", "getNominalValue"),
    BRIMFUL_VOLUME                  ("label.export.brimful.volume", "getBrimfulVolume"),
    OVERALL_HEIGHT                  ("label.export.overall.height", "getOverallHeight"),
    OVERALL_WEIGHT                  ("label.export.overall.weight", "getOverallWeight"),


    WEIGHT_OF_BODY                  ("", ""),
    WALL_THICKNESS_OF_LID_MIN       ("", ""),
    WALL_THICKNESS_OF_BODY_MIN      ("", ""),
    WALL_THICKNESS_OF_BOTTOM_MIN    ("", ""),
    STIFFENING_BEAD                 ("", ""),

    TOP_OPENING_DIAMETER            ("", ""),
    TOP_OPENING_THICKNESS_OF_GASKET ("", ""),
    TOP_OPENING_PERIMETER_GASKET    ("", ""),

    // Packaging Code and User
    PACKAGING                       ("export.label.packaging", null),
    PUBLICATION                     ("export.label.publication", null),
    PACKAGING_CODE                  ("export.label.packaging.code", ""),

    // Description fields
    MATERIAL_BODY                   ("export.label.material.body", "getMaterialBody"),
    DIAMETER_BODY_AT_TOP            ("export.label.diameter.body.at.top", "getDiameterBodyAtTop"),
    HEIGHT_OF_BODY                  ("export.label.height.of.body", "getHeightOfBody"),
    DIAMETER_BODY_AT_BOTTOM         ("export.label.diameter.body.at.bottom", "getDiameterBodyAtBottom"),
    DIAMETER_CLAMP_RING_OUTSIDE     ("export.label.diameter.clamping.ring.outside", "getDiameterClampRingOutside"),
    COATING_INTERNAL                ("export.label.coating.internal", "getCoatingInternal"),
    COATING_EXTERNAL                ("export.label.coating.external", "getCoatingExternal"),
    HANDEL                          ("export.label.handle.of.metal", "getHandelOfMaterial"),

    ;

    SectionEnum(String labelKey, String labelMethod) {
        this(new String[]{labelKey}, new String[]{labelMethod}, null);
    }

    SectionEnum(String[] labelKey, String[] labelMethod, SubSectionEnum[] subSectionEna) {
        this.labelKey = labelKey;
        this.labelMethod = labelMethod;
        this.subSectionEnum = subSectionEna;
    }

    String[] labelKey;
    String[] labelMethod;
    SubSectionEnum[] subSectionEnum;
}
