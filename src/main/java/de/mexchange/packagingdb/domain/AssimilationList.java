package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.UnAssimilationListEntity;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Artur on 5/28/2016.
 * Data Transfer Object representing {@link UnAssimilationListEntity}
 */
public class AssimilationList extends AbstractModel{

    @NotEmpty(message = "{err.field.assimilation.list.designation.required}")
    private String designation;

    //Klasse
    private String clazz;

    //Klassifizierungscode
    private String classificationCode;

    //Klassifizierungsgruppe
    private String classificationGroup;

    //Verpackungsgruppe
    private String packagingGroup;

    //AssimilierungsCode
    private Integer assimilationCode;

    //Datenquelle
    private byte[] dataSource;

    private String dataSourceFilename;

    private String dataSourceContentType;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getClassificationCode() {
        return classificationCode;
    }

    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
    }

    public String getClassificationGroup() {
        return classificationGroup;
    }

    public void setClassificationGroup(String classificationGroup) {
        this.classificationGroup = classificationGroup;
    }

    public String getPackagingGroup() {
        return packagingGroup;
    }

    public void setPackagingGroup(String packagingGroup) {
        this.packagingGroup = packagingGroup;
    }

    public Integer getAssimilationCode() {
        return assimilationCode;
    }

    public void setAssimilationCode(Integer assimilationCode) {
        this.assimilationCode = assimilationCode;
    }

    public byte[] getDataSource() {
        return dataSource;
    }

    public void setDataSource(byte[] dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataSourceFilename() {
        return dataSourceFilename;
    }

    public void setDataSourceFilename(String dataSourceFilename) {
        this.dataSourceFilename = dataSourceFilename;
    }

    public String getDataSourceContentType() {
        return dataSourceContentType;
    }

    public void setDataSourceContentType(String dataSourceContentType) {
        this.dataSourceContentType = dataSourceContentType;
    }
}
