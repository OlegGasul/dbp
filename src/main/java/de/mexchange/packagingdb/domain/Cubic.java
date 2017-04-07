package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.CubicEntity;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Data Transfer Object representing {@link CubicEntity}
 */
public class Cubic extends Container {

    private Double overflowVolume;

    @NotEmpty(message = "{err.msg.cubic.uncode.required}")
    private String unCode;

    private ExZone exZone;

    private String permLock;

    private String embossing;

    private String embossingPosition;

    private Coating coatingInside;

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

    private Double frameHeight;

    private Double frameHeightMax;

    private Double frameHeightMin;

    private Double frameWidth;

    private Double frameWidthMax;

    private Double frameWidthMin;

    private Double frameDepth;

    private Double frameDepthMax;

    private Double frameDepthMin;

    private Double wallThicknessContainer;

    private Double wallThicknessContainerMax;

    private Double wallThicknessContainerMin;

    @NotNull(message = "{err.msg.cubic.total.weight.required}")
    private Double totalWeight;

    private Double totalWeightMax;

    private Double totalWeightMin;

    private Double weightInnerContainer;

    private Double weightInnerContainerMax;

    private Double weightInnerContainerMin;

    private Double weightFrame;

    private Double weightFrameMax;

    private Double weightFrameMin;

    private Double palletHeight;

    private Double palletWidth;

    private Double paletteDepth;

    private Double weightPalette;

    private Double weightPaletteMax;

    private Double weightPaletteMin;

    private Double paletteInputHeight;

    private Double nozzleHeightMax;

    private Double nozzleHeightMin;

    private Material paletteMaterial;

    private PaletteType paletteType;

    private Coating coatingFrame;

    @NotNull(message = "{err.msg.cubic.frame.material.required}")
    private Material frameMaterial;

    @NotNull(message = "{err.msg.cubic.container.material.required}")
    private Material containerMaterial;

    @NotNull(message = "{err.msg.cubic.outlet.armatur.required}")
    private Closure outletArmatur;

    private Coupling outletCoupling;

    @NotNull(message = "{err.msg.cubic.hole.required}")
    private Closure hole;

    private Coating coatingTank;

    private String other;

    private List<ContainerInfo> infoList;

    private List<ContainerPhoto> photos;

    public Double getOverflowVolume() {
        return overflowVolume;
    }

    public void setOverflowVolume(Double overflowVolume) {
        this.overflowVolume = overflowVolume;
    }

    public String getUnCode() {
        return unCode;
    }

    public void setUnCode(String unCode) {
        this.unCode = unCode;
    }

    public ExZone getExZone() {
        return exZone;
    }

    public void setExZone(ExZone exZone) {
        this.exZone = exZone;
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

    public Coating getCoatingInside() {
        return coatingInside;
    }

    public void setCoatingInside(Coating coatingInside) {
        this.coatingInside = coatingInside;
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

    public Coating getCoatingFrame() {
        return coatingFrame;
    }

    public void setCoatingFrame(Coating coatingFrame) {
        this.coatingFrame = coatingFrame;
    }

    public Material getFrameMaterial() {
        return frameMaterial;
    }

    public void setFrameMaterial(Material frameMaterial) {
        this.frameMaterial = frameMaterial;
    }

    public Material getContainerMaterial() {
        return containerMaterial;
    }

    public void setContainerMaterial(Material containerMaterial) {
        this.containerMaterial = containerMaterial;
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

    public Closure getHole() {
        return hole;
    }

    public void setHole(Closure hole) {
        this.hole = hole;
    }

    public Coating getCoatingTank() {
        return coatingTank;
    }

    public void setCoatingTank(Coating coatingTank) {
        this.coatingTank = coatingTank;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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
