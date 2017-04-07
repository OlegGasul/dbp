package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Cubic Container Entity class
 * IBC_Cubic
 * GE: IBC_Kubisch
 */
@Entity
@Table(name = "ibc_cubic")
public class CubicEntity extends ContainerEntity {

    // Überlaufvolumen
    @Column(name = "overflow_volume", columnDefinition="Double(6,2)")
    private Double overflowVolume;

    // UN-Code
    @NotEmpty
    @Column(name = "un_code")
    private String unCode;

    // ExZoneEntity
    @ManyToOne
    @JoinColumn(name = "ex_zone_id", insertable = true)
    private ExZoneEntity exZone;

    // Perm Sperre (1 = yes)
    @Column(name = "perm_lock")
    private String permLock;

    // Prägung
    @Column(name = "embossing")
    private String embossing;

    // Prägungsposition
    @Column(name = "embossing_position")
    private String embossingPosition;

    // Beschichtung Behälter innen, Coating the inside of containers
    @ManyToOne
    @JoinColumn(name = "coating_inside_id", insertable = true)
    private CoatingEntity coatingInside;

    // Hersteller Datenblatt
    @Lob
    @Column(name = "manufacturer_specifications")
    private byte[] manufacturerSpecifications;

    @Column(name = "manufacturer_specifications_filename")
    private String manufacturerSpecificationsFilename;

    @Column(name = "manufacturer_specifications_content_type")
    private String manufacturerSpecificationsContentType;

    // Zeichnungsnummer
    @Column(name = "drawing_number")
    private String drawingNumber;

    // Zeichnung
    @Lob
    @Column(name = "drawing")
    private byte[] drawing;

    @Column(name = "drawing_filename")
    private String drawingFilename;

    @Column(name = "drawing_content_type")
    private String drawingContentType;

    // Zeichnung CAD
    @Lob
    @Column(name = "drawing_CAD")
    private byte[] drawingCAD;

    @Column(name = "drawing_CAD_filename")
    private String drawingCADFilename;;

    @Column(name = "drawing_CAD_content_type")
    private String drawingCADContentType;

    //Rahmenhöhe
    @Column(name = "frame_height", columnDefinition="Double(6,2)")
    private Double frameHeight;

    //Rahmenhöhe (+Tol)
    @Column(name = "frame_height_max", columnDefinition="Double(6,2)")
    private Double frameHeightMax;

    //Rahmenhöhe (-Tol)
    @Column(name = "frame_height_min", columnDefinition="Double(6,2)")
    private Double frameHeightMin;

    //Rahmenbreite
    @Column(name = "frame_width", columnDefinition="Double(6,2)")
    private Double frameWidth;

    //Rahmenbreite (+Tol)
    @Column(name = "frame_width_max", columnDefinition="Double(6,2)")
    private Double frameWidthMax;

    //Rahmenbreite (-Tol)
    @Column(name = "frame_width_min", columnDefinition="Double(6,2)")
    private Double frameWidthMin;

    //Rahmentiefe
    @Column(name = "frame_depth", columnDefinition="Double(6,2)")
    private Double frameDepth;

    //Rahmentiefe (+Tol)
    @Column(name = "frame_depth_max", columnDefinition="Double(6,2)")
    private Double frameDepthMax;

    //Rahmentiefe (-Tol)
    @Column(name = "frame_depth_min", columnDefinition="Double(6,2)")
    private Double frameDepthMin;

    //Wandstärke Behälter
    @Column(name = "wall_thickness", columnDefinition="Double(6,2)")
    private Double wallThicknessContainer;

    //Wandstärke Behälter (+Tol)
    @Column(name = "wall_thickness_max", columnDefinition="Double(6,2)")
    private Double wallThicknessContainerMax;

    //Wandstärke Behälter (-Tol)
    @Column(name = "wall_thickness_min", columnDefinition="Double(6,2)")
    private Double wallThicknessContainerMin;

    //Gesamtgewicht
    @NotNull
    @Column(name = "total_weight", columnDefinition="Double(6,2)")
    private Double totalWeight;

    //Gesamtgewicht (+Tol)
    @Column(name = "total_weight_max", columnDefinition="Double(6,2)")
    private Double totalWeightMax;

    //Gesamtgewicht (-Tol)
    @Column(name = "total_weight_min", columnDefinition="Double(6,2)")
    private Double totalWeightMin;

    //Gewicht Innenbehälter
    @Column(name = "weight_inner_container", columnDefinition="Double(6,2)")
    private Double weightInnerContainer;

    //Gewicht Innenbehälter (+Tol)
    @Column(name = "weight_inner_container_max", columnDefinition="Double(6,2)")
    private Double weightInnerContainerMax;

    //Gewicht Innenbehälter (-Tol)
    @Column(name = "weight_inner_container_min", columnDefinition="Double(6,2)")
    private Double weightInnerContainerMin;

    //Gewicht Rahmen
    @Column(name = "weight_frame", columnDefinition="Double(6,2)")
    private Double weightFrame;

    //Gewicht Rahmen (+Tol)
    @Column(name = "weight_frame_max", columnDefinition="Double(6,2)")
    private Double weightFrameMax;

    //Gewicht Rahmen (-Tol)
    @Column(name = "weight_frame_min", columnDefinition="Double(6,2)")
    private Double weightFrameMin;

    //Palettenhöhe
    @Column(name = "pallet_height", columnDefinition="Double(6,2)")
    private Double palletHeight;

    //Palettenbreite
    @Column(name = "pallet_width", columnDefinition="Double(6,2)")
    private Double palletWidth;

    //Palettentiefe
    @Column(name = "palette_depth", columnDefinition="Double(6,2)")
    private Double paletteDepth;

    // Gewicht Palette
    @Column(name = "weight_palette", columnDefinition="Double(6,2)")
    private Double weightPalette;

    //Gewicht Palette (+Tol)
    @Column(name = "weight_palette_max", columnDefinition="Double(6,2)")
    private Double weightPaletteMax;

    //Gewicht Palette (-Tol)
    @Column(name = "weight_palette_min", columnDefinition="Double(6,2)")
    private Double weightPaletteMin;

    //Einfahrhöhe Palette
    @Column(name = "palette_input_height", columnDefinition="Double(6,2)")
    private Double paletteInputHeight;

    //Auslaufhöhe, max.
    @Column(name = "nozzle_height_max", columnDefinition="Double(6,2)")
    private Double nozzleHeightMax;

    //Auslaufhöhe, min.
    @Column(name = "nozzle_height_min", columnDefinition="Double(6,2)")
    private Double nozzleHeightMin;

    // Palettenmaterial
    @ManyToOne
    @JoinColumn(name = "palette_material_id", insertable = true)
    private MaterialEntity paletteMaterial;

    // Palettentype
    @ManyToOne
    @JoinColumn(name = "palette_type_id", insertable = true)
    private PaletteTypeEntity paletteType;

    // Beschichtung Rahmen
    @ManyToOne
    @JoinColumn(name = "coating_frame_id", insertable = true)
    private CoatingEntity coatingFrame;

    // Rahmenmaterial
    @NotNull
    @ManyToOne
    @JoinColumn(name = "frame_material_id", insertable = true)
    private MaterialEntity frameMaterial;

    // Behältermaterial
    @NotNull
    @ManyToOne
    @JoinColumn(name = "container_material_id")
    private MaterialEntity containerMaterial;

    // Auslauf armatur
    @NotNull
    @ManyToOne
    @JoinColumn(name = "outlet_armatur_id")
    private ClosureEntity outletArmatur;

    // Auslauf kupplung
    @ManyToOne
    @JoinColumn(name = "outlet_coupling_id")
    private CouplingEntity outletCoupling;

    // Öffnungen
    @NotNull
    @ManyToOne
    @JoinColumn(name = "hole_id", insertable = true)
    private ClosureEntity hole;

    //Beschichtung Behälter
    @ManyToOne
    @JoinColumn(name = "coating_tank_id", insertable = true)
    private CoatingEntity coatingTank;

    //Sonstiges
    @Column(name = "other")
    private String other;

    /**
     * Initializes a new instance of the class.
     */
    public CubicEntity() {
    }

    // region <GET/SET>

    public Double getOverflowVolume() {
        return overflowVolume;
    }

    public CubicEntity setOverflowVolume(Double overflowVolume) {
        this.overflowVolume = overflowVolume;
        return this;
    }

    public String getUnCode() {
        return unCode;
    }

    public CubicEntity setUnCode(String unCode) {
        this.unCode = unCode;
        return this;
    }

    public ExZoneEntity getExZone() {
        return exZone;
    }

    public CubicEntity setExZone(ExZoneEntity exZone) {
        this.exZone = exZone;
        return this;
    }

    public String getPermLock() {
        return permLock;
    }

    public CubicEntity setPermLock(String permLock) {
        this.permLock = permLock;
        return this;
    }

    public String getEmbossing() {
        return embossing;
    }

    public CubicEntity setEmbossing(String embossing) {
        this.embossing = embossing;
        return this;
    }

    public String getEmbossingPosition() {
        return embossingPosition;
    }

    public CubicEntity setEmbossingPosition(String embossingPosition) {
        this.embossingPosition = embossingPosition;
        return this;
    }

    public CoatingEntity getCoatingInside() {
        return coatingInside;
    }

    public CubicEntity setCoatingInside(CoatingEntity coatingInside) {
        this.coatingInside = coatingInside;
        return this;
    }

    public byte[] getManufacturerSpecifications() {
        return manufacturerSpecifications;
    }

    public CubicEntity setManufacturerSpecifications(byte[] manufacturerSpecifications) {
        this.manufacturerSpecifications = manufacturerSpecifications;
        return this;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public CubicEntity setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
        return this;
    }

    public byte[] getDrawing() {
        return drawing;
    }

    public CubicEntity setDrawing(byte[] drawing) {
        this.drawing = drawing;
        return this;
    }

    public byte[] getDrawingCAD() {
        return drawingCAD;
    }

    public CubicEntity setDrawingCAD(byte[] drawingCAD) {
        this.drawingCAD = drawingCAD;
        return this;
    }

    public Double getFrameHeight() {
        return frameHeight;
    }

    public CubicEntity setFrameHeight(Double frameHeight) {
        this.frameHeight = frameHeight;
        return this;
    }

    public Double getFrameHeightMax() {
        return frameHeightMax;
    }

    public CubicEntity setFrameHeightMax(Double frameHeightMax) {
        this.frameHeightMax = frameHeightMax;
        return this;
    }

    public Double getFrameHeightMin() {
        return frameHeightMin;
    }

    public CubicEntity setFrameHeightMin(Double frameHeightMin) {
        this.frameHeightMin = frameHeightMin;
        return this;
    }

    public Double getFrameWidth() {
        return frameWidth;
    }

    public CubicEntity setFrameWidth(Double frameWidth) {
        this.frameWidth = frameWidth;
        return this;
    }

    public Double getFrameWidthMax() {
        return frameWidthMax;
    }

    public CubicEntity setFrameWidthMax(Double frameWidthMax) {
        this.frameWidthMax = frameWidthMax;
        return this;
    }

    public Double getFrameWidthMin() {
        return frameWidthMin;
    }

    public CubicEntity setFrameWidthMin(Double frameWidthMin) {
        this.frameWidthMin = frameWidthMin;
        return this;
    }

    public Double getFrameDepth() {
        return frameDepth;
    }

    public CubicEntity setFrameDepth(Double frameDepth) {
        this.frameDepth = frameDepth;
        return this;
    }

    public Double getFrameDepthMax() {
        return frameDepthMax;
    }

    public CubicEntity setFrameDepthMax(Double frameDepthMax) {
        this.frameDepthMax = frameDepthMax;
        return this;
    }

    public Double getFrameDepthMin() {
        return frameDepthMin;
    }

    public CubicEntity setFrameDepthMin(Double frameDepthMin) {
        this.frameDepthMin = frameDepthMin;
        return this;
    }

    public Double getWallThicknessContainer() {
        return wallThicknessContainer;
    }

    public CubicEntity setWallThicknessContainer(Double wallThicknessContainer) {
        this.wallThicknessContainer = wallThicknessContainer;
        return this;
    }

    public Double getWallThicknessContainerMax() {
        return wallThicknessContainerMax;
    }

    public CubicEntity setWallThicknessContainerMax(Double wallThicknessContainerMax) {
        this.wallThicknessContainerMax = wallThicknessContainerMax;
        return this;
    }

    public Double getWallThicknessContainerMin() {
        return wallThicknessContainerMin;
    }

    public CubicEntity setWallThicknessContainerMin(Double wallThicknessContainerMin) {
        this.wallThicknessContainerMin = wallThicknessContainerMin;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public CubicEntity setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public Double getTotalWeightMax() {
        return totalWeightMax;
    }

    public CubicEntity setTotalWeightMax(Double totalWeightMax) {
        this.totalWeightMax = totalWeightMax;
        return this;
    }

    public Double getTotalWeightMin() {
        return totalWeightMin;
    }

    public CubicEntity setTotalWeightMin(Double totalWeightMin) {
        this.totalWeightMin = totalWeightMin;
        return this;
    }

    public Double getWeightInnerContainer() {
        return weightInnerContainer;
    }

    public CubicEntity setWeightInnerContainer(Double weightInnerContainer) {
        this.weightInnerContainer = weightInnerContainer;
        return this;
    }

    public Double getWeightInnerContainerMax() {
        return weightInnerContainerMax;
    }

    public CubicEntity setWeightInnerContainerMax(Double weightInnerContainerMax) {
        this.weightInnerContainerMax = weightInnerContainerMax;
        return this;
    }

    public Double getWeightInnerContainerMin() {
        return weightInnerContainerMin;
    }

    public CubicEntity setWeightInnerContainerMin(Double weightInnerContainerMin) {
        this.weightInnerContainerMin = weightInnerContainerMin;
        return this;
    }

    public Double getWeightFrame() {
        return weightFrame;
    }

    public CubicEntity setWeightFrame(Double weightFrame) {
        this.weightFrame = weightFrame;
        return this;
    }

    public Double getWeightFrameMax() {
        return weightFrameMax;
    }

    public CubicEntity setWeightFrameMax(Double weightFrameMax) {
        this.weightFrameMax = weightFrameMax;
        return this;
    }

    public Double getWeightFrameMin() {
        return weightFrameMin;
    }

    public CubicEntity setWeightFrameMin(Double weightFrameMin) {
        this.weightFrameMin = weightFrameMin;
        return this;
    }

    public Double getPalletHeight() {
        return palletHeight;
    }

    public CubicEntity setPalletHeight(Double palletHeight) {
        this.palletHeight = palletHeight;
        return this;
    }

    public Double getPalletWidth() {
        return palletWidth;
    }

    public CubicEntity setPalletWidth(Double palletWidth) {
        this.palletWidth = palletWidth;
        return this;
    }

    public Double getPaletteDepth() {
        return paletteDepth;
    }

    public CubicEntity setPaletteDepth(Double paletteDepth) {
        this.paletteDepth = paletteDepth;
        return this;
    }

    public Double getWeightPalette() {
        return weightPalette;
    }

    public CubicEntity setWeightPalette(Double weightPalette) {
        this.weightPalette = weightPalette;
        return this;
    }

    public Double getWeightPaletteMax() {
        return weightPaletteMax;
    }

    public CubicEntity setWeightPaletteMax(Double weightPaletteMax) {
        this.weightPaletteMax = weightPaletteMax;
        return this;
    }

    public Double getWeightPaletteMin() {
        return weightPaletteMin;
    }

    public CubicEntity setWeightPaletteMin(Double weightPaletteMin) {
        this.weightPaletteMin = weightPaletteMin;
        return this;
    }

    public Double getPaletteInputHeight() {
        return paletteInputHeight;
    }

    public CubicEntity setPaletteInputHeight(Double paletteInputHeight) {
        this.paletteInputHeight = paletteInputHeight;
        return this;
    }

    public Double getNozzleHeightMax() {
        return nozzleHeightMax;
    }

    public CubicEntity setNozzleHeightMax(Double nozzleHeightMax) {
        this.nozzleHeightMax = nozzleHeightMax;
        return this;
    }

    public Double getNozzleHeightMin() {
        return nozzleHeightMin;
    }

    public CubicEntity setNozzleHeightMin(Double nozzleHeightMin) {
        this.nozzleHeightMin = nozzleHeightMin;
        return this;
    }

    public MaterialEntity getPaletteMaterial() {
        return paletteMaterial;
    }

    public CubicEntity setPaletteMaterial(MaterialEntity paletteMaterial) {
        this.paletteMaterial = paletteMaterial;
        return this;
    }

    public PaletteTypeEntity getPaletteType() {
        return paletteType;
    }

    public CubicEntity setPaletteType(PaletteTypeEntity paletteType) {
        this.paletteType = paletteType;
        return this;
    }

    public CoatingEntity getCoatingFrame() {
        return coatingFrame;
    }

    public CubicEntity setCoatingFrame(CoatingEntity coatingFrame) {
        this.coatingFrame = coatingFrame;
        return this;
    }

    public MaterialEntity getFrameMaterial() {
        return frameMaterial;
    }

    public CubicEntity setFrameMaterial(MaterialEntity frameMaterial) {
        this.frameMaterial = frameMaterial;
        return this;
    }

    public MaterialEntity getContainerMaterial() {
        return containerMaterial;
    }

    public CubicEntity setContainerMaterial(MaterialEntity containerMaterial) {
        this.containerMaterial = containerMaterial;
        return this;
    }

    public ClosureEntity getOutletArmatur() {
        return outletArmatur;
    }

    public CubicEntity setOutletArmatur(ClosureEntity outletArmatur) {
        this.outletArmatur = outletArmatur;
        return this;
    }

    public CouplingEntity getOutletCoupling() {
        return outletCoupling;
    }

    public CubicEntity setOutletCoupling(CouplingEntity outletCoupling) {
        this.outletCoupling = outletCoupling;
        return this;
    }

    public ClosureEntity getHole() {
        return hole;
    }

    public CubicEntity setHole(ClosureEntity hole) {
        this.hole = hole;
        return this;
    }

    public CoatingEntity getCoatingTank() {
        return coatingTank;
    }

    public CubicEntity setCoatingTank(CoatingEntity coatingTank) {
        this.coatingTank = coatingTank;
        return this;
    }

    public String getOther() {
        return other;
    }

    public CubicEntity setOther(String other) {
        this.other = other;
        return this;
    }

    public String getManufacturerSpecificationsFilename() {
        return manufacturerSpecificationsFilename;
    }

    public CubicEntity setManufacturerSpecificationsFilename(String manufacturerSpecificationsFilename) {
        this.manufacturerSpecificationsFilename = manufacturerSpecificationsFilename;
        return this;
    }

    public String getManufacturerSpecificationsContentType() {
        return manufacturerSpecificationsContentType;
    }

    public CubicEntity setManufacturerSpecificationsContentType(String manufacturerSpecificationsContentType) {
        this.manufacturerSpecificationsContentType = manufacturerSpecificationsContentType;
        return this;
    }

    public String getDrawingFilename() {
        return drawingFilename;
    }

    public CubicEntity setDrawingFilename(String drawingFilename) {
        this.drawingFilename = drawingFilename;
        return this;
    }

    public String getDrawingContentType() {
        return drawingContentType;
    }

    public CubicEntity setDrawingContentType(String drawingContentType) {
        this.drawingContentType = drawingContentType;
        return this;
    }

    public String getDrawingCADFilename() {
        return drawingCADFilename;
    }

    public CubicEntity setDrawingCADFilename(String drawingCADFilename) {
        this.drawingCADFilename = drawingCADFilename;
        return this;
    }

    public String getDrawingCADContentType() {
        return drawingCADContentType;
    }

    public CubicEntity setDrawingCADContentType(String drawingCADContentType) {
        this.drawingCADContentType = drawingCADContentType;
        return this;
    }

    //endregion



}
