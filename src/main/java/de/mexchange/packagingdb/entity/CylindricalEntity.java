package de.mexchange.packagingdb.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Cylindrical Container Entity class
 * IBC_Cylindrical
 * GE: IBC_Zylindrisch
 */
@Entity
@Table(name = "ibc_cylindrical")
public class CylindricalEntity extends ContainerEntity {

    // Überlaufvolumen
    @Column(name = "overflow_volume", columnDefinition="Double(6,2)")
    private Double overflowVolume;

    // UN-Code
    @NotEmpty
    @Column(name = "un_code")
    private String unCode;

    // ExZone
    @ManyToOne
    @JoinColumn(name = "ex_zone_id", insertable = true)
    private ExZoneEntity exZone;

    //PermSperre (1=ja)
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

    //Durchmesser Behälter
    @NotNull
    @Column(name = "diameter_container")
    private Double diameterContainer;

    //Durchmesser Behälter  (+Tol)
    @Column(name = "diameter_container_max")
    private Double diameterContainerMax;

    //Durchmesser Behälter  (-Tol)
    @Column(name = "diameter_container_min")
    private Double diameterContainerMin;

    //Wandstärke Behälter
    @Column(name = "wall_thickness", columnDefinition="Double(6,2)")
    private Double wallThicknessContainer;

    //Wandstärke Behälter (+Tol)
    @Column(name = "wall_thickness_max", columnDefinition="Double(6,2)")
    private Double wallThicknessContainerMax;

    //Wandstärke Behälter (-Tol)
    @Column(name = "wall_thickness_min", columnDefinition="Double(6,2)")
    private Double wallThicknessContainerMin;

    //Deckeldurchmesser
    @Column(name = "cap_diameter")
    private Double capDiameter;

    //Deckeldurchmesser  (+Tol)
    @Column(name = "cap_diameter_max")
    private Double capDiameterMax;

    //Deckeldurchmesser  (-Tol)
    @Column(name = "cap_diameter_min")
    private Double capDiameterMin;

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

    // Behältermaterial
    @NotNull
    @ManyToOne
    @JoinColumn(name = "container_material_id", insertable = true)
    private MaterialEntity containerMaterial;

    //Beschichtung Behälter
    @ManyToOne
    @JoinColumn(name = "coating_tank_id", insertable = true)
    private CoatingEntity coatingTank;

    // Öffnungen
    @NotNull
    @ManyToOne
    @JoinColumn(name = "hole_id", insertable = true)
    private ClosureEntity hole;

    // Auslauf armatur
    @NotNull
    @ManyToOne
    @JoinColumn(name = "outlet_armatur_id", insertable = true)
    private ClosureEntity outletArmatur;

    // Auslauf kupplung
    @ManyToOne
    @JoinColumn(name = "outlet_coupling_id", insertable = true)
    private CouplingEntity outletCoupling;

    //Auslaufhöhe, max.
    @Column(name = "nozzle_height_max")
    private Double nozzleHeightMax;

    //Auslaufhöhe, min.
    @Column(name = "nozzle_height_min")
    private Double nozzleHeightMin;

    //Gewicht Rahmen
    @Column(name = "weight_frame", columnDefinition="Double(6,2)")
    private Double weightFrame;

    //Gewicht Rahmen (+Tol)
    @Column(name = "weight_frame_max", columnDefinition="Double(6,2)")
    private Double weightFrameMax;

    //Gewicht Rahmen (-Tol)
    @Column(name = "weight_frame_min", columnDefinition="Double(6,2)")
    private Double weightFrameMin;

    // Beschichtung Rahmen
    @ManyToOne
    @JoinColumn(name = "coating_frame_id", insertable = true)
    private CoatingEntity coatingFrame;

    // Rahmenmaterial
    @NotNull
    @ManyToOne
    @JoinColumn(name = "frame_material_id", insertable = true)
    private MaterialEntity frameMaterial;

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

    // Palettenmaterial
    @ManyToOne
    @JoinColumn(name = "palette_material_id", insertable = true)
    private MaterialEntity paletteMaterial;

    // Palettentype
    @ManyToOne
    @JoinColumn(name = "palette_type_id", insertable = true)
    private PaletteTypeEntity paletteType;

    //Sonstiges
    @Column(name = "other")
    private String other;

    /**
     * Initializes a new instance of the class.
     */
    public CylindricalEntity() {
    }

    // region <GET/SET>

    public Double getOverflowVolume() {
        return overflowVolume;
    }

    public CylindricalEntity setOverflowVolume(Double overflowVolume) {
        this.overflowVolume = overflowVolume;
        return this;
    }

    public String getUnCode() {
        return unCode;
    }

    public CylindricalEntity setUnCode(String unCode) {
        this.unCode = unCode;
        return this;
    }

    public ExZoneEntity getExZone() {
        return exZone;
    }

    public CylindricalEntity setExZone(ExZoneEntity exZone) {
        this.exZone = exZone;
        return this;
    }

    public String getPermLock() {
        return permLock;
    }

    public CylindricalEntity setPermLock(String permLock) {
        this.permLock = permLock;
        return this;
    }

    public String getEmbossing() {
        return embossing;
    }

    public CylindricalEntity setEmbossing(String embossing) {
        this.embossing = embossing;
        return this;
    }

    public String getEmbossingPosition() {
        return embossingPosition;
    }

    public CylindricalEntity setEmbossingPosition(String embossingPosition) {
        this.embossingPosition = embossingPosition;
        return this;
    }

    public CoatingEntity getCoatingInside() {
        return coatingInside;
    }

    public CylindricalEntity setCoatingInside(CoatingEntity coatingInside) {
        this.coatingInside = coatingInside;
        return this;
    }

    public byte[] getManufacturerSpecifications() {
        return manufacturerSpecifications;
    }

    public CylindricalEntity setManufacturerSpecifications(byte[] manufacturerSpecifications) {
        this.manufacturerSpecifications = manufacturerSpecifications;
        return this;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public CylindricalEntity setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
        return this;
    }

    public byte[] getDrawing() {
        return drawing;
    }

    public CylindricalEntity setDrawing(byte[] drawing) {
        this.drawing = drawing;
        return this;
    }

    public byte[] getDrawingCAD() {
        return drawingCAD;
    }

    public CylindricalEntity setDrawingCAD(byte[] drawingCAD) {
        this.drawingCAD = drawingCAD;
        return this;
    }

    public Double getFrameHeight() {
        return frameHeight;
    }

    public CylindricalEntity setFrameHeight(Double frameHeight) {
        this.frameHeight = frameHeight;
        return this;
    }

    public Double getFrameHeightMax() {
        return frameHeightMax;
    }

    public CylindricalEntity setFrameHeightMax(Double frameHeightMax) {
        this.frameHeightMax = frameHeightMax;
        return this;
    }

    public Double getFrameHeightMin() {
        return frameHeightMin;
    }

    public CylindricalEntity setFrameHeightMin(Double frameHeightMin) {
        this.frameHeightMin = frameHeightMin;
        return this;
    }

    public Double getFrameWidth() {
        return frameWidth;
    }

    public CylindricalEntity setFrameWidth(Double frameWidth) {
        this.frameWidth = frameWidth;
        return this;
    }

    public Double getFrameWidthMax() {
        return frameWidthMax;
    }

    public CylindricalEntity setFrameWidthMax(Double frameWidthMax) {
        this.frameWidthMax = frameWidthMax;
        return this;
    }

    public Double getFrameWidthMin() {
        return frameWidthMin;
    }

    public CylindricalEntity setFrameWidthMin(Double frameWidthMin) {
        this.frameWidthMin = frameWidthMin;
        return this;
    }

    public Double getFrameDepth() {
        return frameDepth;
    }

    public CylindricalEntity setFrameDepth(Double frameDepth) {
        this.frameDepth = frameDepth;
        return this;
    }

    public Double getFrameDepthMax() {
        return frameDepthMax;
    }

    public CylindricalEntity setFrameDepthMax(Double frameDepthMax) {
        this.frameDepthMax = frameDepthMax;
        return this;
    }

    public Double getFrameDepthMin() {
        return frameDepthMin;
    }

    public CylindricalEntity setFrameDepthMin(Double frameDepthMin) {
        this.frameDepthMin = frameDepthMin;
        return this;
    }

    public Double getDiameterContainer() {
        return diameterContainer;
    }

    public CylindricalEntity setDiameterContainer(Double diameterContainer) {
        this.diameterContainer = diameterContainer;
        return this;
    }

    public Double getDiameterContainerMax() {
        return diameterContainerMax;
    }

    public CylindricalEntity setDiameterContainerMax(Double diameterContainerMax) {
        this.diameterContainerMax = diameterContainerMax;
        return this;
    }

    public Double getDiameterContainerMin() {
        return diameterContainerMin;
    }

    public CylindricalEntity setDiameterContainerMin(Double diameterContainerMin) {
        this.diameterContainerMin = diameterContainerMin;
        return this;
    }

    public Double getWallThicknessContainer() {
        return wallThicknessContainer;
    }

    public CylindricalEntity setWallThicknessContainer(Double wallThicknessContainer) {
        this.wallThicknessContainer = wallThicknessContainer;
        return this;
    }

    public Double getWallThicknessContainerMax() {
        return wallThicknessContainerMax;
    }

    public CylindricalEntity setWallThicknessContainerMax(Double wallThicknessContainerMax) {
        this.wallThicknessContainerMax = wallThicknessContainerMax;
        return this;
    }

    public Double getWallThicknessContainerMin() {
        return wallThicknessContainerMin;
    }

    public CylindricalEntity setWallThicknessContainerMin(Double wallThicknessContainerMin) {
        this.wallThicknessContainerMin = wallThicknessContainerMin;
        return this;
    }

    public Double getCapDiameter() {
        return capDiameter;
    }

    public CylindricalEntity setCapDiameter(Double capDiameter) {
        this.capDiameter = capDiameter;
        return this;
    }

    public Double getCapDiameterMax() {
        return capDiameterMax;
    }

    public CylindricalEntity setCapDiameterMax(Double capDiameterMax) {
        this.capDiameterMax = capDiameterMax;
        return this;
    }

    public Double getCapDiameterMin() {
        return capDiameterMin;
    }

    public CylindricalEntity setCapDiameterMin(Double capDiameterMin) {
        this.capDiameterMin = capDiameterMin;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public CylindricalEntity setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public Double getTotalWeightMax() {
        return totalWeightMax;
    }

    public CylindricalEntity setTotalWeightMax(Double totalWeightMax) {
        this.totalWeightMax = totalWeightMax;
        return this;
    }

    public Double getTotalWeightMin() {
        return totalWeightMin;
    }

    public CylindricalEntity setTotalWeightMin(Double totalWeightMin) {
        this.totalWeightMin = totalWeightMin;
        return this;
    }

    public Double getWeightInnerContainer() {
        return weightInnerContainer;
    }

    public CylindricalEntity setWeightInnerContainer(Double weightInnerContainer) {
        this.weightInnerContainer = weightInnerContainer;
        return this;
    }

    public Double getWeightInnerContainerMax() {
        return weightInnerContainerMax;
    }

    public CylindricalEntity setWeightInnerContainerMax(Double weightInnerContainerMax) {
        this.weightInnerContainerMax = weightInnerContainerMax;
        return this;
    }

    public Double getWeightInnerContainerMin() {
        return weightInnerContainerMin;
    }

    public CylindricalEntity setWeightInnerContainerMin(Double weightInnerContainerMin) {
        this.weightInnerContainerMin = weightInnerContainerMin;
        return this;
    }

    public MaterialEntity getContainerMaterial() {
        return containerMaterial;
    }

    public CylindricalEntity setContainerMaterial(MaterialEntity containerMaterial) {
        this.containerMaterial = containerMaterial;
        return this;
    }

    public CoatingEntity getCoatingTank() {
        return coatingTank;
    }

    public CylindricalEntity setCoatingTank(CoatingEntity coatingTank) {
        this.coatingTank = coatingTank;
        return this;
    }

    public ClosureEntity getHole() {
        return hole;
    }

    public CylindricalEntity setHole(ClosureEntity hole) {
        this.hole = hole;
        return this;
    }

    public ClosureEntity getOutletArmatur() {
        return outletArmatur;
    }

    public CylindricalEntity setOutletArmatur(ClosureEntity outletArmatur) {
        this.outletArmatur = outletArmatur;
        return this;
    }

    public CouplingEntity getOutletCoupling() {
        return outletCoupling;
    }

    public CylindricalEntity setOutletCoupling(CouplingEntity outletCoupling) {
        this.outletCoupling = outletCoupling;
        return this;
    }

    public Double getNozzleHeightMax() {
        return nozzleHeightMax;
    }

    public CylindricalEntity setNozzleHeightMax(Double nozzleHeightMax) {
        this.nozzleHeightMax = nozzleHeightMax;
        return this;
    }

    public Double getNozzleHeightMin() {
        return nozzleHeightMin;
    }

    public CylindricalEntity setNozzleHeightMin(Double nozzleHeightMin) {
        this.nozzleHeightMin = nozzleHeightMin;
        return this;
    }

    public Double getWeightFrame() {
        return weightFrame;
    }

    public CylindricalEntity setWeightFrame(Double weightFrame) {
        this.weightFrame = weightFrame;
        return this;
    }

    public Double getWeightFrameMax() {
        return weightFrameMax;
    }

    public CylindricalEntity setWeightFrameMax(Double weightFrameMax) {
        this.weightFrameMax = weightFrameMax;
        return this;
    }

    public Double getWeightFrameMin() {
        return weightFrameMin;
    }

    public CylindricalEntity setWeightFrameMin(Double weightFrameMin) {
        this.weightFrameMin = weightFrameMin;
        return this;
    }

    public CoatingEntity getCoatingFrame() {
        return coatingFrame;
    }

    public CylindricalEntity setCoatingFrame(CoatingEntity coatingFrame) {
        this.coatingFrame = coatingFrame;
        return this;
    }

    public MaterialEntity getFrameMaterial() {
        return frameMaterial;
    }

    public CylindricalEntity setFrameMaterial(MaterialEntity frameMaterial) {
        this.frameMaterial = frameMaterial;
        return this;
    }

    public Double getPalletHeight() {
        return palletHeight;
    }

    public CylindricalEntity setPalletHeight(Double palletHeight) {
        this.palletHeight = palletHeight;
        return this;
    }

    public Double getPalletWidth() {
        return palletWidth;
    }

    public CylindricalEntity setPalletWidth(Double palletWidth) {
        this.palletWidth = palletWidth;
        return this;
    }

    public Double getPaletteDepth() {
        return paletteDepth;
    }

    public CylindricalEntity setPaletteDepth(Double paletteDepth) {
        this.paletteDepth = paletteDepth;
        return this;
    }

    public Double getWeightPalette() {
        return weightPalette;
    }

    public CylindricalEntity setWeightPalette(Double weightPalette) {
        this.weightPalette = weightPalette;
        return this;
    }

    public Double getWeightPaletteMax() {
        return weightPaletteMax;
    }

    public CylindricalEntity setWeightPaletteMax(Double weightPaletteMax) {
        this.weightPaletteMax = weightPaletteMax;
        return this;
    }

    public Double getWeightPaletteMin() {
        return weightPaletteMin;
    }

    public CylindricalEntity setWeightPaletteMin(Double weightPaletteMin) {
        this.weightPaletteMin = weightPaletteMin;
        return this;
    }

    public Double getPaletteInputHeight() {
        return paletteInputHeight;
    }

    public CylindricalEntity setPaletteInputHeight(Double paletteInputHeight) {
        this.paletteInputHeight = paletteInputHeight;
        return this;
    }

    public MaterialEntity getPaletteMaterial() {
        return paletteMaterial;
    }

    public CylindricalEntity setPaletteMaterial(MaterialEntity paletteMaterial) {
        this.paletteMaterial = paletteMaterial;
        return this;
    }

    public PaletteTypeEntity getPaletteType() {
        return paletteType;
    }

    public CylindricalEntity setPaletteType(PaletteTypeEntity paletteType) {
        this.paletteType = paletteType;
        return this;
    }

    public String getOther() {
        return other;
    }

    public CylindricalEntity setOther(String other) {
        this.other = other;
        return this;
    }

    public String getManufacturerSpecificationsFilename() {
        return manufacturerSpecificationsFilename;
    }

    public CylindricalEntity setManufacturerSpecificationsFilename(String manufacturerSpecificationsFilename) {
        this.manufacturerSpecificationsFilename = manufacturerSpecificationsFilename;
        return this;
    }

    public String getManufacturerSpecificationsContentType() {
        return manufacturerSpecificationsContentType;
    }

    public CylindricalEntity setManufacturerSpecificationsContentType(String manufacturerSpecificationsContentType) {
        this.manufacturerSpecificationsContentType = manufacturerSpecificationsContentType;
        return this;
    }

    public String getDrawingFilename() {
        return drawingFilename;
    }

    public CylindricalEntity setDrawingFilename(String drawingFilename) {
        this.drawingFilename = drawingFilename;
        return this;
    }

    public String getDrawingContentType() {
        return drawingContentType;
    }

    public CylindricalEntity setDrawingContentType(String drawingContentType) {
        this.drawingContentType = drawingContentType;
        return this;
    }

    public String getDrawingCADFilename() {
        return drawingCADFilename;
    }

    public CylindricalEntity setDrawingCADFilename(String drawingCADFilename) {
        this.drawingCADFilename = drawingCADFilename;
        return this;
    }

    public String getDrawingCADContentType() {
        return drawingCADContentType;
    }

    public CylindricalEntity setDrawingCADContentType(String drawingCADContentType) {
        this.drawingCADContentType = drawingCADContentType;
        return this;
    }

    //endregion
}
