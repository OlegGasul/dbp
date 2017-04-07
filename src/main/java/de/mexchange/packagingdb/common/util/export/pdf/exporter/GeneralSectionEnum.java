package de.mexchange.packagingdb.common.util.export.pdf.exporter;

import de.mexchange.packagingdb.common.util.export.ExportType;

import java.util.ArrayList;
import java.util.List;

import static de.mexchange.packagingdb.common.util.export.pdf.exporter.SectionEnum.*;

/**
 * Created by Serozh on 6/7/2016.
 */
public enum GeneralSectionEnum {

    PACKAGING_KEY               ("export.label.packaging.key", ExportType.PDF_PORTRAIT, new SectionEnum[]{
            VPS_NUMBER
    }),

    SAP_MATERIAL_NUMBER         ("export.label.sap.material.number", ExportType.PDF_PORTRAIT, new SectionEnum[]{
            SAP_NUMBER
    }),

    DANGEROUS_GOODS_APPROVAL    ("export.label.goods.approval", ExportType.PDF_PORTRAIT, new SectionEnum[]{
            UN_APPROVAL_NUMBER,
            APPROVED_GROSS_WEIGHT,
            APPROVED_VOLUME,
            AGGREGATION_OF_FILLING_GOOD,
            WATER
    }),

    BASIC_DATA                  ("export.label.basic.data", ExportType.PDF_PORTRAIT, new SectionEnum[]{
            NOMINAL_VALUE,
            BRIMFUL_VOLUME,
            OVERALL_HEIGHT,
            OVERALL_WEIGHT
    }),

    BODY                        ("export.label.body", ExportType.PDF_PORTRAIT, new SectionEnum[]{
            MATERIAL_BODY,
            WEIGHT_OF_BODY,
            WALL_THICKNESS_OF_LID_MIN,
            WALL_THICKNESS_OF_BODY_MIN,
            WALL_THICKNESS_OF_BOTTOM_MIN,
            COATING_INTERNAL,
            COATING_EXTERNAL,
            DIAMETER_BODY_AT_TOP,
            HEIGHT_OF_BODY,
            DIAMETER_BODY_AT_BOTTOM,
            HANDEL,
            STIFFENING_BEAD,

    }),

    FILLER_OPENING              ("export.label.filler.opening", ExportType.PDF_PORTRAIT, new SectionEnum[]{

    }),


    HISTORY                     ("export.label.history", ExportType.PDF_PORTRAIT, new SectionEnum[]{

    }),



    PACKAGING_CODE_AND_USER     ("export.label.packaging.code.and.user", ExportType.PDF_LANDSCAPE, new SectionEnum[]{
            PACKAGING,
            PUBLICATION,
            PACKAGING_CODE
    }),

    PRODUCT_APPROVAL            ("export.label.product.approval", ExportType.PDF_LANDSCAPE, new SectionEnum[]{

    }),

    DESCRIPTION                 ("export.label.description", ExportType.PDF_LANDSCAPE, new SectionEnum[]{
            MATERIAL_BODY,
            DIAMETER_BODY_AT_TOP,
            HEIGHT_OF_BODY,
            DIAMETER_BODY_AT_BOTTOM,
            DIAMETER_CLAMP_RING_OUTSIDE,
            COATING_INTERNAL,
            COATING_EXTERNAL,
            HANDEL
    }),


    ;

    GeneralSectionEnum(String labelKey, ExportType exportType) {
        this(labelKey, exportType, null);
    }

    GeneralSectionEnum(String labelKey, ExportType exportType, SectionEnum[] sectionEnums) {
        this.labelKey = labelKey;
        this.sectionEnums = sectionEnums;
        this.exportType = exportType;
    }

    public static List<GeneralSectionEnum> getByExportType(ExportType exportType) {
        List<GeneralSectionEnum> result = new ArrayList<>();
        for (GeneralSectionEnum generalSectionEnum : GeneralSectionEnum.values()) {
            if (exportType == generalSectionEnum.exportType) {
                result.add(generalSectionEnum);
            }
        }
        return result;
    }

    String labelKey;
    SectionEnum[] sectionEnums;
    ExportType exportType;
}
