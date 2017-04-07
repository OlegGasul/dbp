package de.mexchange.packagingdb.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Bulk Container Entity class
 * IBC_Bulk
 * GE: IBC_Schüttgut
 */
@Entity
@Table(name = "ibc_bulk")
public class BulkEntity extends ContainerEntity {

    // UN-Code
    @NotNull
    @Column(name = "un_code")
    private String unCode;

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

    // Schüttwinkel breite (Angle of repose wide)
    @Column(name = "repose_angle_wide", columnDefinition="Double(6,2)")
    private Double angleReposeWide;

    //Schüttwinkel tiefe (Angle of repose deep)
    @Column(name = "repose_angle_deep", columnDefinition="Double(6,2)")
    private Double angleReposeDeep;

    // Überlaufvolumen
    @Column(name = "overflow_volume", columnDefinition="Double(6,2)")
    private Double overflowVolume;

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

    // Gewicht Palette
    @Column(name = "weight_palette", columnDefinition="Double(6,2)")
    private Double weightPalette;

    //Gewicht Palette (+Tol)
    @Column(name = "weight_palette_max", columnDefinition="Double(6,2)")
    private Double weightPaletteMax;

    //Gewicht Palette (-Tol)
    @Column(name = "weight_palette_min", columnDefinition="Double(6,2)")
    private Double weightPaletteMin;

    //PermSperre (1=ja)
    @Column(name = "perm_lock")
    private String permLock;

    //Einfüllöffnungen
    @ManyToOne
    @JoinColumn(name = "filling_openings_id")
    private ClosureEntity fillingOpenings;

    // Prägung
    @Column(name = "embossing")
    private String embossing;

    // Prägungsposition
    @Column(name = "embossing_position")
    private String embossingPosition;

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

    //Palettenhöhe
    @Column(name = "pallet_height", columnDefinition="Double(6,2)")
    private Double palletHeight;

    //Palettenbreite
    @Column(name = "pallet_width", columnDefinition="Double(6,2)")
    private Double palletWidth;

    //Armatur waagerecht 1
    @ManyToOne
    @JoinColumn(name = "fitting_horizontally1_id")
    private ClosureEntity fittingHorizontally1;

    //Armatur waagerecht 2
    @ManyToOne
    @JoinColumn(name = "fitting_horizontally2_id")
    private ClosureEntity fittingHorizontally2;

    //Kupplung waagerecht 1
    @ManyToOne
    @JoinColumn(name = "clutch_horizontally1_id")
    private CouplingEntity clutchHorizontally1;

    //Kupplung waagerecht 2
    @ManyToOne
    @JoinColumn(name = "clutch_horizontally2_id")
    private CouplingEntity clutchHorizontally2;

    //Höhe Auslauf waagerecht 1 (max.)
    @Column(name = "distance_outlet_level1_max", columnDefinition="Double(6,2)")
    private Double distanceOutletLevel1Max;

    //Höhe Auslauf waagerecht 1 (min.)
    @Column(name = "distance_outlet_level1_min", columnDefinition="Double(6,2)")
    private Double distanceOutletLevel1Min;

    //Höhe Auslauf waagerecht 2 (max.)
    @Column(name = "distance_outlet_level2_max", columnDefinition="Double(6,2)")
    private Double distanceOutletLevel2Max;

    //Höhe Auslauf waagerecht 2 (min.)
    @Column(name = "distance_outlet_level2_min", columnDefinition="Double(6,2)")
    private Double distanceOutletLevel2Min;

    //Armatur senkrecht
    @ManyToOne
    @JoinColumn(name = "fitting_vertically_id")
    private ClosureEntity fittingVertically;

    //Kupplung senkrecht
    @ManyToOne
    @JoinColumn(name = "coupling_perpendicular_id")
    private CouplingEntity couplingPerpendicular;

    //Höhe Auslauf senkrecht (max.)
    @Column(name = "height_vertical_outlet_max", columnDefinition="Double(6,2)")
    private Double heightVerticalOutletMax;

    //Höhe Auslauf senkrecht (min.)
    @Column(name = "height_vertical_outlet_min", columnDefinition="Double(6,2)")
    private Double heightVerticalOutletMin;

    //Palettentiefe
    @Column(name = "palette_depth", columnDefinition="Double(6,2)")
    private Double paletteDepth;

    // Hersteller Datenblatt
    @Lob
    @Column(name = "manufacturer_specifications")
    private byte[] manufacturerSpecifications;

    @Column(name = "manufacturer_specifications_filename")
    private String manufacturerSpecificationsFilename;

    @Column(name = "manufacturer_specifications_content_type")
    private String manufacturerSpecificationsContentType;

    //Einfahrhöhe Palette
    @Column(name = "palette_input_height", columnDefinition="Double(6,2)")
    private Double paletteInputHeight;

    //Auslaufhöhe, max.
    @Column(name = "nozzle_height_max", columnDefinition="Double(6,2)")
    private Double nozzleHeightMax;

    //Auslaufhöhe, min.
    @Column(name = "nozzle_height_min", columnDefinition="Double(6,2)")
    private Double nozzleHeightMin;

    //Wandstärke Behälter
    @Column(name = "wall_thickness", columnDefinition="Double(6,2)")
    private Double wallThicknessContainer;

    //Sonstiges
    @Column(name = "other")
    private String other;

    // Palettenmaterial
    @ManyToOne
    @JoinColumn(name = "palette_material_id")
    private MaterialEntity paletteMaterial;

    // Palettentype
    @ManyToOne
    @JoinColumn(name = "palette_type_id")
    private PaletteTypeEntity paletteType;

    // Auslauf armatur
    @NotNull
    @ManyToOne
    @JoinColumn(name = "outlet_armatur_id")
    private ClosureEntity outletArmatur;

    // Auslauf kupplung
    @ManyToOne
    @JoinColumn(name = "outlet_coupling_id")
    private CouplingEntity outletCoupling;

    // Behältermaterial
    @NotNull
    @ManyToOne
    @JoinColumn(name = "container_material_id")
    private MaterialEntity containerMaterial;

    //Beschichtung Behälter
    @ManyToOne
    @JoinColumn(name = "coating_tank_id")
    private CoatingEntity coatingTank;

    // Rahmenmaterial
    @NotNull
    @ManyToOne
    @JoinColumn(name = "frame_material_id")
    private MaterialEntity frameMaterial;

    // ExZone
    @ManyToOne
    @JoinColumn(name = "ex_zone_id")
    private ExZoneEntity exZone;

    // Beschichtung Behälter innen, Coating the inside of containers
    @ManyToOne
    @JoinColumn(name = "coating_inside_id")
    private CoatingEntity coatingInside;

    // Beschichtung Behälter innen, Coating the inside of containers
    @ManyToOne
    @JoinColumn(name = "container_coating_id")
    private CoatingEntity containerCoating;

    // Beschichtung Rahmen
    @ManyToOne
    @JoinColumn(name = "coating_frame_id")
    private CoatingEntity coatingFrame;

    // Öffnungen
    @NotNull
    @ManyToOne
    @JoinColumn(name = "hole_id")
    private ClosureEntity hole;

    /**
     * Initializes a new instance of the class.
     */
    public BulkEntity() {
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

    public ClosureEntity getFillingOpenings() {
        return fillingOpenings;
    }

    public void setFillingOpenings(ClosureEntity fillingOpenings) {
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

    public String getDrawingCADContentType() {
        return drawingCADContentType;
    }

    public void setDrawingCADContentType(String drawingCADContentType) {
        this.drawingCADContentType = drawingCADContentType;
    }

    public String getDrawingCADFilename() {
        return drawingCADFilename;
    }

    public void setDrawingCADFilename(String drawingCADFilename) {
        this.drawingCADFilename = drawingCADFilename;
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

    public MaterialEntity getPaletteMaterial() {
        return paletteMaterial;
    }

    public void setPaletteMaterial(MaterialEntity paletteMaterial) {
        this.paletteMaterial = paletteMaterial;
    }

    public PaletteTypeEntity getPaletteType() {
        return paletteType;
    }

    public void setPaletteType(PaletteTypeEntity paletteType) {
        this.paletteType = paletteType;
    }

    public ClosureEntity getOutletArmatur() {
        return outletArmatur;
    }

    public void setOutletArmatur(ClosureEntity outletArmatur) {
        this.outletArmatur = outletArmatur;
    }

    public CouplingEntity getOutletCoupling() {
        return outletCoupling;
    }

    public void setOutletCoupling(CouplingEntity outletCoupling) {
        this.outletCoupling = outletCoupling;
    }

    public MaterialEntity getContainerMaterial() {
        return containerMaterial;
    }

    public void setContainerMaterial(MaterialEntity containerMaterial) {
        this.containerMaterial = containerMaterial;
    }

    public CoatingEntity getCoatingTank() {
        return coatingTank;
    }

    public void setCoatingTank(CoatingEntity coatingTank) {
        this.coatingTank = coatingTank;
    }

    public MaterialEntity getFrameMaterial() {
        return frameMaterial;
    }

    public void setFrameMaterial(MaterialEntity frameMaterial) {
        this.frameMaterial = frameMaterial;
    }

    public ExZoneEntity getExZone() {
        return exZone;
    }

    public void setExZone(ExZoneEntity exZone) {
        this.exZone = exZone;
    }

    public CoatingEntity getCoatingInside() {
        return coatingInside;
    }

    public void setCoatingInside(CoatingEntity coatingInside) {
        this.coatingInside = coatingInside;
    }

    public CoatingEntity getContainerCoating() {
        return containerCoating;
    }

    public void setContainerCoating(CoatingEntity containerCoating) {
        this.containerCoating = containerCoating;
    }

    public CoatingEntity getCoatingFrame() {
        return coatingFrame;
    }

    public void setCoatingFrame(CoatingEntity coatingFrame) {
        this.coatingFrame = coatingFrame;
    }

    public ClosureEntity getFittingHorizontally1() {
        return fittingHorizontally1;
    }

    public void setFittingHorizontally1(ClosureEntity fittingHorizontally1) {
        this.fittingHorizontally1 = fittingHorizontally1;
    }

    public ClosureEntity getFittingHorizontally2() {
        return fittingHorizontally2;
    }

    public void setFittingHorizontally2(ClosureEntity fittingHorizontally2) {
        this.fittingHorizontally2 = fittingHorizontally2;
    }

    public CouplingEntity getClutchHorizontally1() {
        return clutchHorizontally1;
    }

    public void setClutchHorizontally1(CouplingEntity clutchHorizontally1) {
        this.clutchHorizontally1 = clutchHorizontally1;
    }

    public CouplingEntity getClutchHorizontally2() {
        return clutchHorizontally2;
    }

    public void setClutchHorizontally2(CouplingEntity clutchHorizontally2) {
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

    public ClosureEntity getFittingVertically() {
        return fittingVertically;
    }

    public void setFittingVertically(ClosureEntity fittingVertically) {
        this.fittingVertically = fittingVertically;
    }

    public CouplingEntity getCouplingPerpendicular() {
        return couplingPerpendicular;
    }

    public void setCouplingPerpendicular(CouplingEntity couplingPerpendicular) {
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

    public ClosureEntity getHole() {
        return hole;
    }

    public void setHole(ClosureEntity hole) {
        this.hole = hole;
    }
    //endregion

}
