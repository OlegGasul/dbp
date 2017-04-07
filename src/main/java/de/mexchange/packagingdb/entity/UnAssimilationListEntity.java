
package de.mexchange.packagingdb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Assimilation List Entity entity class
 * un_assimilierungsliste
 */
@Entity
@Table(name = "un_assimilation_list")
public class UnAssimilationListEntity extends AbstractEntity{

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Benennung
    @NotEmpty
    @Column(name = "designation")
    private String designation;

    //Klasse
    @Column(name = "clazz")
    private String clazz;

    //Klassifizierungscode
    @Column(name = "classification_code")
    private String classificationCode;

    //Klassifizierungsgruppe
    @Column(name = "classification_group")
    private String classificationGroup;

    //Verpackungsgruppe
    @Column(name = "packaging_group")
    private String packagingGroup;

    //AssimilierungsCode
    @Column(name = "assimilation_code")
    private Integer assimilationCode;

    //Datenquelle
    @Lob
    @Column(name = "datasource")
    private byte[] dataSource;

    @Column(name = "dataSource_filename")
    private String dataSourceFilename;

    @Column(name = "dataSource_content_type")
    private String dataSourceContentType;

    //TODO NOT existing in new requirements but exist on old db
    @Column(name = "un_nr")
    private Integer unnr;

    @Column(name = "page_assim_list")
    private Integer pageAssimList;

    @Column(name = "cas")
    private String CAS;

    /**
     * Initializes a new instance of the class.
     */
    public UnAssimilationListEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public UnAssimilationListEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDesignation() {
        return designation;
    }

    public UnAssimilationListEntity setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public UnAssimilationListEntity setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public String getClassificationCode() {
        return classificationCode;
    }

    public UnAssimilationListEntity setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
        return this;
    }

    public String getClassificationGroup() {
        return classificationGroup;
    }

    public UnAssimilationListEntity setClassificationGroup(String classificationGroup) {
        this.classificationGroup = classificationGroup;
        return this;
    }

    public String getPackagingGroup() {
        return packagingGroup;
    }

    public UnAssimilationListEntity setPackagingGroup(String packagingGroup) {
        this.packagingGroup = packagingGroup;
        return this;
    }

    public Integer getAssimilationCode() {
        return assimilationCode;
    }

    public UnAssimilationListEntity setAssimilationCode(Integer assimilationCode) {
        this.assimilationCode = assimilationCode;
        return this;
    }

    public byte[] getDataSource() {
        return dataSource;
    }

    public UnAssimilationListEntity setDataSource(byte[] dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public String getDataSourceFilename() {
        return dataSourceFilename;
    }

    public UnAssimilationListEntity setDataSourceFilename(String dataSourceFilename) {
        this.dataSourceFilename = dataSourceFilename;
        return this;
    }

    public String getDataSourceContentType() {
        return dataSourceContentType;
    }

    public UnAssimilationListEntity setDataSourceContentType(String dataSourceContentType) {
        this.dataSourceContentType = dataSourceContentType;
        return this;
    }

    public Integer getUnnr() {
        return unnr;
    }

    public UnAssimilationListEntity setUnnr(Integer unnr) {
        this.unnr = unnr;
        return this;
    }

    public Integer getPageAssimList() {
        return pageAssimList;
    }

    public UnAssimilationListEntity setPageAssimList(Integer pageAssimList) {
        this.pageAssimList = pageAssimList;
        return this;
    }

    public String getCAS() {
        return CAS;
    }

    public UnAssimilationListEntity setCAS(String CAS) {
        this.CAS = CAS;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>UnAssimilationListEntity</code> object.
     */
    @Override
    public String toString() {
        return "UnAssimilationListEntity{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", clazz='" + clazz + '\'' +
                ", classificationCode='" + classificationCode + '\'' +
                ", classificationGroup='" + classificationGroup + '\'' +
                ", packagingGroup='" + packagingGroup + '\'' +
                ", assimilationCode=" + assimilationCode +
                ", dataSource.length='" + (dataSource != null ? dataSource.length : null) + '\''
                +
                ", dataSourceFilename='" + dataSourceFilename + '\'' +
                ", dataSourceContentType='" + dataSourceContentType + '\'' +
                '}';
    }
    // endregion


}
