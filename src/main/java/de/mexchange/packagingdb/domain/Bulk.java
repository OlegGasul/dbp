package de.mexchange.packagingdb.domain;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.BulkEntity;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Data Transfer Object representing {@link BulkEntity}
 */
public class Bulk extends Container {

    @NotEmpty(message = "{err.msg.bulk.uncode.required}")
    private String unCode;

    private Double frameHeight;
    
    private Double frameHeightMax;
    
    private Double frameHeightMin;
    
    private Double frameWidth;
    
    private Double frameWidthMax;
    
    private Double frameWidthMin;
    
    private Double frameDepth;
    
    private Double frameDepthMax;
    
    private Double frameDepthMin;
    
    private Double angleReposeWide;
    
    private Double angleReposeDeep;

    private Double overflowVolume;

    @NotNull(message = "{err.msg.bulk.total.weight.required}")
    private Double totalWeight;

    private Double totalWeightMax;

    private Double totalWeightMin;

    private Double weightInnerContainer;

    private Double weightInnerContainerMax;

    private Double weightInnerContainerMin;

    private Double weightFrame;

    private Double weightFrameMax;

    private Double weightFrameMin;

    private Double weightPalette;

    private Double weightPaletteMax;

    private Double weightPaletteMin;

    private String permLock;

    private Closure fillingOpenings;

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

    private Double palletHeight;

    private Double palletWidth;

    private Double paletteDepth;

    private Double paletteInputHeight;

    private Double nozzleHeightMax;

    private Double nozzleHeightMin;

    private Double wallThicknessContainer;

    private String other;

    private Material paletteMaterial;

    private PaletteType paletteType;

    private Closure outletArmatur;

    private Coupling outletCoupling;

    private Material containerMaterial;

    private Coating coatingTank;

    private Material frameMaterial;

    private ExZone exZone;

    private Coating coatingInside;

    private Coating containerCoating;

    private Coating coatingFrame;

    private Closure fittingHorizontally1;

    private Closure fittingHorizontally2;

    private Coupling clutchHorizontally1;

    private Coupling clutchHorizontally2;

    private Double distanceOutletLevel1Max;

    private Double distanceOutletLevel1Min;

    private Double distanceOutletLevel2Max;

    private Double distanceOutletLevel2Min;

    private Closure fittingVertically;

    private Coupling couplingPerpendicular;

    private Double heightVerticalOutletMax;

    private Double heightVerticalOutletMin;

    private Closure hole;

    private List<ContainerInfo> infoList;

    private List<ContainerPhoto> photos;


    public Bulk() {
    }

    // region <GET/SET>

    public String getUnCode() {
        return unCode;
    }

    public void setUnCode(String unCode) {
        this.unCode = unCode;
    }

    public Double getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(Double frameHeight) {
        this.frameHeight = frameHeight;
    }

    public Double getFrameHeightMax() {
        return frameHeightMax;
    }

    public void setFrameHeightMax(Double frameHeightMax) {
        this.frameHeightMax = frameHeightMax;
    }

    public Double getFrameHeightMin() {
        return frameHeightMin;
    }

    public void setFrameHeightMin(Double frameHeightMin) {
        this.frameHeightMin = frameHeightMin;
    }

    public Double getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(Double frameWidth) {
        this.frameWidth = frameWidth;
    }

    public Double getFrameWidthMax() {
        return frameWidthMax;
    }

    public void setFrameWidthMax(Double frameWidthMax) {
        this.frameWidthMax = frameWidthMax;
    }

    public Double getFrameWidthMin() {
        return frameWidthMin;
    }

    public void setFrameWidthMin(Double frameWidthMin) {
        this.frameWidthMin = frameWidthMin;
    }

    public Double getFrameDepth() {
        return frameDepth;
    }

    public void setFrameDepth(Double frameDepth) {
        this.frameDepth = frameDepth;
    }

    public Double getFrameDepthMax() {
        return frameDepthMax;
    }

    public void setFrameDepthMax(Double frameDepthMax) {
        this.frameDepthMax = frameDepthMax;
    }

    public Double getFrameDepthMin() {
        return frameDepthMin;
    }

    public void setFrameDepthMin(Double frameDepthMin) {
        this.frameDepthMin = frameDepthMin;
    }

    public Double getAngleReposeWide() {
        return angleReposeWide;
    }

    public void setAngleReposeWide(Double angleReposeWide) {
        this.angleReposeWide = angleReposeWide;
    }

    public Double getAngleReposeDeep() {
        return angleReposeDeep;
    }

    public void setAngleReposeDeep(Double angleReposeDeep) {
        this.angleReposeDeep = angleReposeDeep;
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

    public Double getWeightFrame() {
        return weightFrame;
    }

    public void setWeightFrame(Double weightFrame) {
        this.weightFrame = weightFrame;
    }

    public Double getWeightFrameMax() {
        return weightFrameMax;
    }

    public void setWeightFrameMax(Double weightFrameMax) {
        this.weightFrameMax = weightFrameMax;
    }

    public Double getWeightFrameMin() {
        return weightFrameMin;
    }

    public void setWeightFrameMin(Double weightFrameMin) {
        this.weightFrameMin = weightFrameMin;
    }

    public Double getWeightPalette() {
        return weightPalette;
    }

    public void setWeightPalette(Double weightPalette) {
        this.weightPalette = weightPalette;
    }

    public Double getWeightPaletteMax() {
        return weightPaletteMax;
    }

    public void setWeightPaletteMax(Double weightPaletteMax) {
        this.weightPaletteMax = weightPaletteMax;
    }

    public Double getWeightPaletteMin() {
        return weightPaletteMin;
    }

    public void setWeightPaletteMin(Double weightPaletteMin) {
        this.weightPaletteMin = weightPaletteMin;
    }

    public String getPermLock() {
        return permLock;
    }

    public void setPermLock(String permLock) {
        this.permLock = permLock;
    }

    public Closure getFillingOpenings() {
        return fillingOpenings;
    }

    public void setFillingOpenings(Closure fillingOpenings) {
        this.fillingOpenings = fillingOpenings;
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

    public Double getPalletHeight() {
        return palletHeight;
    }

    public void setPalletHeight(Double palletHeight) {
        this.palletHeight = palletHeight;
    }

    public Double getPalletWidth() {
        return palletWidth;
    }

    public void setPalletWidth(Double palletWidth) {
        this.palletWidth = palletWidth;
    }

    public Double getPaletteDepth() {
        return paletteDepth;
    }

    public void setPaletteDepth(Double paletteDepth) {
        this.paletteDepth = paletteDepth;
    }

    public byte[] getManufacturerSpecifications() {
        return manufacturerSpecifications;
    }

    public void setManufacturerSpecifications(byte[] manufacturerSpecifications) {
        this.manufacturerSpecifications = manufacturerSpecifications;
    }

    public Double getPaletteInputHeight() {
        return paletteInputHeight;
    }

    public void setPaletteInputHeight(Double paletteInputHeight) {
        this.paletteInputHeight = paletteInputHeight;
    }

    public Double getNozzleHeightMax() {
        return nozzleHeightMax;
    }

    public void setNozzleHeightMax(Double nozzleHeightMax) {
        this.nozzleHeightMax = nozzleHeightMax;
    }

    public Double getNozzleHeightMin() {
        return nozzleHeightMin;
    }

    public void setNozzleHeightMin(Double nozzleHeightMin) {
        this.nozzleHeightMin = nozzleHeightMin;
    }

    public Double getWallThicknessContainer() {
        return wallThicknessContainer;
    }

    public void setWallThicknessContainer(Double wallThicknessContainer) {
        this.wallThicknessContainer = wallThicknessContainer;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Material getPaletteMaterial() {
        return paletteMaterial;
    }

    public void setPaletteMaterial(Material paletteMaterial) {
        this.paletteMaterial = paletteMaterial;
    }

    public PaletteType getPaletteType() {
        return paletteType;
    }

    public void setPaletteType(PaletteType paletteType) {
        this.paletteType = paletteType;
    }

    public Closure getOutletArmatur() {
        return outletArmatur;
    }

    public void setOutletArmatur(Closure outletArmatur) {
        this.outletArmatur = outletArmatur;
    }

    public Coupling getOutletCoupling() {
        return outletCoupling;
    }

    public void setOutletCoupling(Coupling outletCoupling) {
        this.outletCoupling = outletCoupling;
    }

    public Material getContainerMaterial() {
        return containerMaterial;
    }

    public void setContainerMaterial(Material containerMaterial) {
        this.containerMaterial = containerMaterial;
    }

    public Coating getCoatingTank() {
        return coatingTank;
    }

    public void setCoatingTank(Coating coatingTank) {
        this.coatingTank = coatingTank;
    }

    public Material getFrameMaterial() {
        return frameMaterial;
    }

    public void setFrameMaterial(Material frameMaterial) {
        this.frameMaterial = frameMaterial;
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

    public Coating getContainerCoating() {
        return containerCoating;
    }

    public void setContainerCoating(Coating containerCoating) {
        this.containerCoating = containerCoating;
    }

    public Coating getCoatingFrame() {
        return coatingFrame;
    }

    public void setCoatingFrame(Coating coatingFrame) {
        this.coatingFrame = coatingFrame;
    }

    public Closure getFittingHorizontally1() {
        return fittingHorizontally1;
    }

    public void setFittingHorizontally1(Closure fittingHorizontally1) {
        this.fittingHorizontally1 = fittingHorizontally1;
    }

    public Closure getFittingHorizontally2() {
        return fittingHorizontally2;
    }

    public void setFittingHorizontally2(Closure fittingHorizontally2) {
        this.fittingHorizontally2 = fittingHorizontally2;
    }

    public Coupling getClutchHorizontally1() {
        return clutchHorizontally1;
    }

    public void setClutchHorizontally1(Coupling clutchHorizontally1) {
        this.clutchHorizontally1 = clutchHorizontally1;
    }

    public Coupling getClutchHorizontally2() {
        return clutchHorizontally2;
    }

    public void setClutchHorizontally2(Coupling clutchHorizontally2) {
        this.clutchHorizontally2 = clutchHorizontally2;
    }

    public Double getDistanceOutletLevel1Max() {
        return distanceOutletLevel1Max;
    }

    public void setDistanceOutletLevel1Max(Double distanceOutletLevel1Max) {
        this.distanceOutletLevel1Max = distanceOutletLevel1Max;
    }

    public Double getDistanceOutletLevel1Min() {
        return distanceOutletLevel1Min;
    }

    public void setDistanceOutletLevel1Min(Double distanceOutletLevel1Min) {
        this.distanceOutletLevel1Min = distanceOutletLevel1Min;
    }

    public Double getDistanceOutletLevel2Max() {
        return distanceOutletLevel2Max;
    }

    public void setDistanceOutletLevel2Max(Double distanceOutletLevel2Max) {
        this.distanceOutletLevel2Max = distanceOutletLevel2Max;
    }

    public Double getDistanceOutletLevel2Min() {
        return distanceOutletLevel2Min;
    }

    public void setDistanceOutletLevel2Min(Double distanceOutletLevel2Min) {
        this.distanceOutletLevel2Min = distanceOutletLevel2Min;
    }

    public Closure getFittingVertically() {
        return fittingVertically;
    }

    public void setFittingVertically(Closure fittingVertically) {
        this.fittingVertically = fittingVertically;
    }

    public Coupling getCouplingPerpendicular() {
        return couplingPerpendicular;
    }

    public void setCouplingPerpendicular(Coupling couplingPerpendicular) {
        this.couplingPerpendicular = couplingPerpendicular;
    }

    public Double getHeightVerticalOutletMax() {
        return heightVerticalOutletMax;
    }

    public void setHeightVerticalOutletMax(Double heightVerticalOutletMax) {
        this.heightVerticalOutletMax = heightVerticalOutletMax;
    }

    public Double getHeightVerticalOutletMin() {
        return heightVerticalOutletMin;
    }

    public void setHeightVerticalOutletMin(Double heightVerticalOutletMin) {
        this.heightVerticalOutletMin = heightVerticalOutletMin;
    }

    public Closure getHole() {
        return hole;
    }

    public void setHole(Closure hole) {
        this.hole = hole;
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

    //endregion

}
