package de.mexchange.packagingdb.domain;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Data UnApproval Object representing {@link de.mexchange.packagingdb.entity.UnApprovalEntity}
 */
public class UnApproval extends AbstractModel implements Searchable {

    private Double densityX;

    private Double densityY;

    private Double densityZ;

    private String unCode;

    private Double vaporPressure50;

    private Double vaporPressure55;

    @DateTimeFormat(pattern="MM/dd/YYYY")
    private Date dateOfIssue;

    private String unVersion;

    private String unit;

    private Double maxGrossWeight;

    private Double sleeps;

    private String permeation;

    private String aggregateState;

    private byte[] file;

    private String fileFilename;

    private String fileContentType;

    @Valid
    private List<UnApprovalInfo> authorizationRooms;

    private UnApprovalInfo currentInfo;

    private UnTestMedium testMedium;

    @NotNull(message = "{err.field.approval.company.required}")
    private Company company;

    private AssimilationList assimilationList;

    private PackagingInstruction packagingInstruction;

    private Transport transport;

    // region <GET/SET>


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

    public List<UnApprovalInfo> getAuthorizationRooms() {
        return authorizationRooms;
    }

    public void setAuthorizationRooms(List<UnApprovalInfo> authorizationRooms) {
        this.authorizationRooms = authorizationRooms;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public AssimilationList getAssimilationList() {
        return assimilationList;
    }

    public void setAssimilationList(AssimilationList assimilationList) {
        this.assimilationList = assimilationList;
    }

    public PackagingInstruction getPackagingInstruction() {
        return packagingInstruction;
    }

    public void setPackagingInstruction(PackagingInstruction packagingInstruction) {
        this.packagingInstruction = packagingInstruction;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public UnTestMedium getTestMedium() {
        return testMedium;
    }

    public void setTestMedium(UnTestMedium testMedium) {
        this.testMedium = testMedium;
    }

    public UnApprovalInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(UnApprovalInfo currentInfo) {
        this.currentInfo = currentInfo;
    }

    @Override
    public String getName() {
        if (currentInfo != null) {
            return currentInfo.getRoomName();
        }

        if (authorizationRooms != null && authorizationRooms.size() > 0) {
            return authorizationRooms.get(0).getRoomName();
        }

        return null;
    }
}