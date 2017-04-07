package de.mexchange.packagingdb.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Material Entity entity class
 * GE: un_zulassung
 */
@Entity
@Table(name = "un_approval")
public class UnApprovalEntity extends AbstractEntity {

    /**
     * ID                           +
     * authorization (room)         +
     * Test medium                  +
     * UN Code                      +
     * Vapor pressure at 50 C       +
     * Vapor pressure at 55 C       +
     * density X                    +
     * density Y                    +
     * density Z                    +
     * container type               - TODO
     * UNPR confirmation.           - TODO
     * Company                      +
     * Sleeps                       +
     * unit                         +
     * Filling aggregate state      +
     * Max. Gross Weight            +
     * means of transport           - TODO
     * authorization                - TODO
     * version                      +
     * date of issue                +
     * Permeation f? R PE           +
     * un assimilation              +
     * UN packing instructions      +
     * UN transport                 +
     */

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Dichte X
    @Column(name = "density_x", columnDefinition="Double(6,2)")
    private Double densityX;

    //Dichte Y
    @Column(name = "density_y", columnDefinition="Double(6,2)")
    private Double densityY;

    //Dichte Z
    @Column(name = "density_z", columnDefinition="Double(6,2)")
    private Double densityZ;

    //UN Code
    @Column(name = "un_code")
    private String unCode;

    //Dampfdruck bei 50 ?C
    @Column(name = "vapor_pressure_50", columnDefinition="Double(6,2)")
    private Double vaporPressure50;

    //Dampfdruck bei 55 ?C
    @Column(name = "vapor_pressure_55", columnDefinition="Double(6,2)")
    private Double vaporPressure55;

    //Ausstellungsdatum
    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    //Version
    @Column(name = "un_version")
    private String unVersion;

    //Einheit
    @Column(name = "unit")
    private String unit;

    //Max. Bruttogewicht
    @Column(name = "max_gross_weight", columnDefinition="Double(6,2)")
    private Double maxGrossWeight;

    //Fassungsraum
    @Column(name = "sleeps", columnDefinition="Double(6,2)")
    private Double sleeps;

    //Permeationstest fär PE
    @Column(name = "PE_permeation")
    private String permeation;

    //Füllgutaggregatzustand
    @Column(name = "filling_aggregate_state")
    private String aggregateState;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "file_filename")
    private String fileFilename;

    @Column(name = "file_content_type")
    private String fileContentType;

    @OneToMany(mappedBy="authorization", cascade = CascadeType.ALL)
    private Set<UnApprovalInfoEntity> authorizationRooms;

    //Prüfmedium
    @ManyToOne
    @JoinColumn(name = "test_medium_id", foreignKey =  @ForeignKey(name="FK_authorization_testMedium"))
    private UnTestMediumEntity testMedium;

    //Zulassungsinhaber
    @NotNull
    @ManyToOne
    @JoinColumn(name = "company_id", foreignKey =  @ForeignKey(name="FK_authorization_company"))
    private CompanyEntity company;

    //un assimilierungsliste
    @ManyToOne
    @JoinColumn(name = "assimilation_list_id", foreignKey =  @ForeignKey(name="FK_authorization_assimilationList"))
    private UnAssimilationListEntity assimilationList;

    //UN Verpackungsanweisung
    @ManyToOne
    @JoinColumn(name = "verp_instruction_id", foreignKey =  @ForeignKey(name="FK_authorization_verpInstruction"))
    private UnPackagingInstructionEntity verpInstruction;

    //UN Transport
    @ManyToOne
    @JoinColumn(name = "transport_id", foreignKey =  @ForeignKey(name="FK_authorization_transport"))
    private UnTransportEntity transport;

    /**
     * Initializes a new instance of the class.
     */
    public UnApprovalEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnCode() {
        return unCode;
    }

    public void setUnCode(String unCode) {
        this.unCode = unCode;
    }

    public Double getVaporPressure50() {
        return vaporPressure50;
    }

    public void setVaporPressure50(Double vaporPressure50) {
        this.vaporPressure50 = vaporPressure50;
    }

    public Double getVaporPressure55() {
        return vaporPressure55;
    }

    public void setVaporPressure55(Double vaporPressure55) {
        this.vaporPressure55 = vaporPressure55;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getUnVersion() {
        return unVersion;
    }

    public void setUnVersion(String unVersion) {
        this.unVersion = unVersion;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getMaxGrossWeight() {
        return maxGrossWeight;
    }

    public void setMaxGrossWeight(Double maxGrossWeight) {
        this.maxGrossWeight = maxGrossWeight;
    }

    public Double getSleeps() {
        return sleeps;
    }

    public void setSleeps(Double sleeps) {
        this.sleeps = sleeps;
    }

    public String getPermeation() {
        return permeation;
    }

    public void setPermeation(String permeation) {
        this.permeation = permeation;
    }

    public String getAggregateState() {
        return aggregateState;
    }

    public void setAggregateState(String aggregateState) {
        this.aggregateState = aggregateState;
    }

    public Set<UnApprovalInfoEntity> getAuthorizationRooms() {
        return authorizationRooms;
    }

    public void setAuthorizationRooms(Set<UnApprovalInfoEntity> authorizationRooms) {
        this.authorizationRooms = authorizationRooms;
    }

    public UnTestMediumEntity getTestMedium() {
        return testMedium;
    }

    public void setTestMedium(UnTestMediumEntity testMedium) {
        this.testMedium = testMedium;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public UnAssimilationListEntity getAssimilationList() {
        return assimilationList;
    }

    public void setAssimilationList(UnAssimilationListEntity assimilationList) {
        this.assimilationList = assimilationList;
    }

    public UnPackagingInstructionEntity getVerpInstruction() {
        return verpInstruction;
    }

    public void setVerpInstruction(UnPackagingInstructionEntity verpInstruction) {
        this.verpInstruction = verpInstruction;
    }

    public UnTransportEntity getTransport() {
        return transport;
    }

    public void setTransport(UnTransportEntity transport) {
        this.transport = transport;
    }

    public Double getDensityX() {
        return densityX;
    }

    public void setDensityX(Double densityX) {
        this.densityX = densityX;
    }

    public Double getDensityY() {
        return densityY;
    }

    public void setDensityY(Double densityY) {
        this.densityY = densityY;
    }

    public Double getDensityZ() {
        return densityZ;
    }

    public void setDensityZ(Double densityZ) {
        this.densityZ = densityZ;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileFilename() {
        return fileFilename;
    }

    public void setFileFilename(String fileFilename) {
        this.fileFilename = fileFilename;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>UnApprovalEntity</code> object.
     */
    @Override
    public String toString() {
        return "UnApprovalEntity{" +
                "transport=" + transport +
                ", id=" + id +
                ", densityX='" + densityX + '\'' +
                ", densityY='" + densityY + '\'' +
                ", densityZ='" + densityZ + '\'' +
                ", unCode='" + unCode + '\'' +
                ", vaporPressure50='" + vaporPressure50 + '\'' +
                ", vaporPressure55='" + vaporPressure55 + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", unVersion='" + unVersion + '\'' +
                ", unit='" + unit + '\'' +
                ", maxGrossWeight=" + maxGrossWeight +
                ", sleeps=" + sleeps +
                ", permeation='" + permeation + '\'' +
                ", aggregateState='" + aggregateState + '\'' +
                ", file.length='" + (file != null ? file.length : null) + '\'' +
                ", fileContentType='" + fileContentType + '\'' +
                ", fileFilename='" + fileFilename + '\'' +
                ", authorizationRooms=" + authorizationRooms +
                ", testMedium=" + testMedium +
                ", company=" + company +
                ", assimilationList=" + assimilationList +
                ", verpInstruction=" + verpInstruction +
                '}';
    }
    //endregion
}
