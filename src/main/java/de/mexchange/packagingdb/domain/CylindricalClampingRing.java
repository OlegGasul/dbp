package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.CylindricalClampingRingEntity;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Data Transfer Object representing {@link CylindricalClampingRingEntity}
 */
public class CylindricalClampingRing extends Container {

    @NotEmpty(message = "{err.msg.cylindrical.clamping.ring.uncode.required}")
    private String unCode;

    private Double overflowVolume;

    @NotNull(message = "{err.msg.cylindrical.clamping.ring.total.weight.required}")
    private Double totalWeight;

    private Double totalWeightMax;

    private Double totalWeightMin;

    private Double weightInnerContainer;

    private Double weightInnerContainerMax;

    private Double weightInnerContainerMin;

    private String permLock;

    private String embossing;

    private String embossingPosition;

    private String drawingNumber;

    private byte[] drawing;

    private String drawingFilename;

    private String drawingContentType;

    private  byte[] drawingCAD;

    private String drawingCADFilename;

    private String drawingCADContentType;

    private byte[] manufacturerSpecifications;

    private String manufacturerSpecificationsFilename;

    private String manufacturerSpecificationsContentType;

    private String other;

    private String sicken;

    private Double overallHeight;

    private Double overallHeightMax;

    private Double overallHeightMin;

    private Double hullHeight;

    private Double hullHeightMax;

    private Double hullHeightMin;

    private Double innerDiameter;

    private Double innerDiameterMin;

    private Double innerDiameterMax;

    private Double wallThicknessContainer;

    private Double wallThicknessContainerMax;

    private Double wallThicknessContainerMin;

    private Double lidWallThickness;

    private Double lidWallThicknessMin;

    private Double lidWallThicknessMax;

    private Double groundWallThickness;

    private Double groundWallThicknessMin;

    private Double groundWallThicknessMax;

    private Double diameterClampingRing;

    private Double diameterClampingRingMin;

    private Double diameterClampingRingMax;

    @NotNull(message = "{err.msg.cylindrical.clamping.ring.container.material.required}")
    private Material containerMaterial;

    private ExZone exZone;

    private Coating coatingInside;

    private Coating coatingOutside;

    @NotNull(message = "{err.msg.cylindrical.clamping.ring.hole.required}")
    private Closure hole;

    private Handle handle;

    private List<ContainerInfo> infoList;

    private List<ContainerPhoto> photos;

    public CylindricalClampingRing() {
    }

    public String getUnCode() {
        return unCode;
    }

    public void setUnCode(String unCode) {
        this.unCode = unCode;
    }

    public Double getOverflowVolume() {
        return overflowVolume;
    }

    public void setOverflowVolume(Double overflowVolume) {
        this.overflowVolume = overflowVolume;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getTotalWeightMax() {
        return totalWeightMax;
    }

    public void setTotalWeightMax(Double totalWeightMax) {
        this.totalWeightMax = totalWeightMax;
    }

    public Double getTotalWeightMin() {
        return totalWeightMin;
    }

    public void setTotalWeightMin(Double totalWeightMin) {
        this.totalWeightMin = totalWeightMin;
    }

    public Double getWeightInnerContainer() {
        return weightInnerContainer;
    }

    public void setWeightInnerContainer(Double weightInnerContainer) {
        this.weightInnerContainer = weightInnerContainer;
    }

    public Double getWeightInnerContainerMax() {
        return weightInnerContainerMax;
    }

    public void setWeightInnerContainerMax(Double weightInnerContainerMax) {
        this.weightInnerContainerMax = weightInnerContainerMax;
    }

    public Double getWeightInnerContainerMin() {
        return weightInnerContainerMin;
    }

    public void setWeightInnerContainerMin(Double weightInnerContainerMin) {
        this.weightInnerContainerMin = weightInnerContainerMin;
    }

    public String getPermLock() {
        return permLock;
    }

    public void setPermLock(String permLock) {
        this.permLock = permLock;
    }

    public String getEmbossing() {
        return embossing;
    }

    public void setEmbossing(String embossing) {
        this.embossing = embossing;
    }

    public String getEmbossingPosition() {
        return embossingPosition;
    }

    public void setEmbossingPosition(String embossingPosition) {
        this.embossingPosition = embossingPosition;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public void setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
    }

    public byte[] getDrawing() {
        return drawing;
    }

    public void setDrawing(byte[] drawing) {
        this.drawing = drawing;
    }

    public String getDrawingFilename() {
        return drawingFilename;
    }

    public void setDrawingFilename(String drawingFilename) {
        this.drawingFilename = drawingFilename;
    }

    public String getDrawingContentType() {
        return drawingContentType;
    }

    public void setDrawingContentType(String drawingContentType) {
        this.drawingContentType = drawingContentType;
    }

    public byte[] getDrawingCAD() {
        return drawingCAD;
    }

    public void setDrawingCAD(byte[] drawingCAD) {
        this.drawingCAD = drawingCAD;
    }

    public String getDrawingCADFilename() {
        return drawingCADFilename;
    }

    public void setDrawingCADFilename(String drawingCADFilename) {
        this.drawingCADFilename = drawingCADFilename;
    }

    public String getDrawingCADContentType() {
        return drawingCADContentType;
    }

    public void setDrawingCADContentType(String drawingCADContentType) {
        this.drawingCADContentType = drawingCADContentType;
    }

    public byte[] getManufacturerSpecifications() {
        return manufacturerSpecifications;
    }

    public void setManufacturerSpecifications(byte[] manufacturerSpecifications) {
        this.manufacturerSpecifications = manufacturerSpecifications;
    }

    public String getManufacturerSpecificationsFilename() {
        return manufacturerSpecificationsFilename;
    }

    public void setManufacturerSpecificationsFilename(String manufacturerSpecificationsFilename) {
        this.manufacturerSpecificationsFilename = manufacturerSpecificationsFilename;
    }

    public String getManufacturerSpecificationsContentType() {
        return manufacturerSpecificationsContentType;
    }

    public void setManufacturerSpecificationsContentType(String manufacturerSpecificationsContentType) {
        this.manufacturerSpecificationsContentType = manufacturerSpecificationsContentType;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getSicken() {
        return sicken;
    }

    public void setSicken(String sicken) {
        this.sicken = sicken;
    }

    public Double getOverallHeight() {
        return overallHeight;
    }

    public void setOverallHeight(Double overallHeight) {
        this.overallHeight = overallHeight;
    }

    public Double getOverallHeightMax() {
        return overallHeightMax;
    }

    public void setOverallHeightMax(Double overallHeightMax) {
        this.overallHeightMax = overallHeightMax;
    }

    public Double getOverallHeightMin() {
        return overallHeightMin;
    }

    public void setOverallHeightMin(Double overallHeightMin) {
        this.overallHeightMin = overallHeightMin;
    }

    public Double getHullHeight() {
        return hullHeight;
    }

    public void setHullHeight(Double hullHeight) {
        this.hullHeight = hullHeight;
    }

    public Double getHullHeightMax() {
        return hullHeightMax;
    }

    public void setHullHeightMax(Double hullHeightMax) {
        this.hullHeightMax = hullHeightMax;
    }

    public Double getHullHeightMin() {
        return hullHeightMin;
    }

    public void setHullHeightMin(Double hullHeightMin) {
        this.hullHeightMin = hullHeightMin;
    }

    public Double getInnerDiameter() {
        return innerDiameter;
    }

    public void setInnerDiameter(Double innerDiameter) {
        this.innerDiameter = innerDiameter;
    }

    public Double getInnerDiameterMin() {
        return innerDiameterMin;
    }

    public void setInnerDiameterMin(Double innerDiameterMin) {
        this.innerDiameterMin = innerDiameterMin;
    }

    public Double getInnerDiameterMax() {
        return innerDiameterMax;
    }

    public void setInnerDiameterMax(Double innerDiameterMax) {
        this.innerDiameterMax = innerDiameterMax;
    }

    public Double getWallThicknessContainer() {
        return wallThicknessContainer;
    }

    public void setWallThicknessContainer(Double wallThicknessContainer) {
        this.wallThicknessContainer = wallThicknessContainer;
    }

    public Double getWallThicknessContainerMax() {
        return wallThicknessContainerMax;
    }

    public void setWallThicknessContainerMax(Double wallThicknessContainerMax) {
        this.wallThicknessContainerMax = wallThicknessContainerMax;
    }

    public Double getWallThicknessContainerMin() {
        return wallThicknessContainerMin;
    }

    public void setWallThicknessContainerMin(Double wallThicknessContainerMin) {
        this.wallThicknessContainerMin = wallThicknessContainerMin;
    }

    public Double getLidWallThickness() {
        return lidWallThickness;
    }

    public void setLidWallThickness(Double lidWallThickness) {
        this.lidWallThickness = lidWallThickness;
    }

    public Double getLidWallThicknessMin() {
        return lidWallThicknessMin;
    }

    public void setLidWallThicknessMin(Double lidWallThicknessMin) {
        this.lidWallThicknessMin = lidWallThicknessMin;
    }

    public Double getLidWallThicknessMax() {
        return lidWallThicknessMax;
    }

    public void setLidWallThicknessMax(Double lidWallThicknessMax) {
        this.lidWallThicknessMax = lidWallThicknessMax;
    }

    public Double getGroundWallThickness() {
        return groundWallThickness;
    }

    public void setGroundWallThickness(Double groundWallThickness) {
        this.groundWallThickness = groundWallThickness;
    }

    public Double getGroundWallThicknessMin() {
        return groundWallThicknessMin;
    }

    public void setGroundWallThicknessMin(Double groundWallThicknessMin) {
        this.groundWallThicknessMin = groundWallThicknessMin;
    }

    public Double getGroundWallThicknessMax() {
        return groundWallThicknessMax;
    }

    public void setGroundWallThicknessMax(Double groundWallThicknessMax) {
        this.groundWallThicknessMax = groundWallThicknessMax;
    }

    public Double getDiameterClampingRing() {
        return diameterClampingRing;
    }

    public void setDiameterClampingRing(Double diameterClampingRing) {
        this.diameterClampingRing = diameterClampingRing;
    }

    public Double getDiameterClampingRingMin() {
        return diameterClampingRingMin;
    }

    public void setDiameterClampingRingMin(Double diameterClampingRingMin) {
        this.diameterClampingRingMin = diameterClampingRingMin;
    }

    public Double getDiameterClampingRingMax() {
        return diameterClampingRingMax;
    }

    public void setDiameterClampingRingMax(Double diameterClampingRingMax) {
        this.diameterClampingRingMax = diameterClampingRingMax;
    }

    public Material getContainerMaterial() {
        return containerMaterial;
    }

    public void setContainerMaterial(Material containerMaterial) {
        this.containerMaterial = containerMaterial;
    }

    public ExZone getExZone() {
        return exZone;
    }

    public void setExZone(ExZone exZone) {
        this.exZone = exZone;
    }

    public Coating getCoatingInside() {
        return coatingInside;
    }

    public void setCoatingInside(Coating coatingInside) {
        this.coatingInside = coatingInside;
    }

    public Coating getCoatingOutside() {
        return coatingOutside;
    }

    public void setCoatingOutside(Coating coatingOutside) {
        this.coatingOutside = coatingOutside;
    }

    public Closure getHole() {
        return hole;
    }

    public void setHole(Closure hole) {
        this.hole = hole;
    }

    public Handle getHandle() {
        return handle;
    }

    public void setHandle(Handle handle) {
        this.handle = handle;
    }

    public List<ContainerInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<ContainerInfo> infoList) {
        this.infoList = infoList;
    }

    public ContainerInfo getInfo(Language language) {
        if (CollectionUtils.isNotEmpty(infoList)) {
            for (ContainerInfo e : infoList) {
                if (e.getLanguage() == language) {
                    return e;
                }
            }
        }
        return null;
    }

    public List<ContainerPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<ContainerPhoto> photos) {
        this.photos = photos;
    }
}
