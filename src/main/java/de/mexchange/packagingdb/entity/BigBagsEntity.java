package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * BigBags Container Entity class
 * Big Bag
 */
@Entity
@Table(name = "big_bags")
public class BigBagsEntity extends ContainerEntity {

    /*
+    VPS
+    VPS_SAP_Code
+    VPS_Local_Code
+    VPS_Global_Code
+    VPS_Status
+    Standort
+    BU
+    Nennvolumen
+    Bezeichnung
+    Überlaufvolumen
+    UN-Code
+    ExZone
+    PermSperre (1=ja)
+    Prägung
+    Prägungsposition
+?   Beschichtung Behälter innen
+?   Beschichtung Innen
+?   Beschichtung Außen
+    Bemerkungen GE
+    Bemerkungen GB
+    Hersteller Datenblatt
+    Fotos
+    Zeichnungsnummer
+    Zeichnung
+    Zeichnung CAD
+    Rahmenhöhe
+    Rahmenhöhe (+Tol)
+    Rahmenhöhe (-Tol)
+    Rahmenbreite
+    Rahmenbreite (+Tol)
+    Rahmenbreite (-Tol)
+    Rahmentiefe
+    Rahmentiefe (+Tol)
+    Rahmentiefe (-Tol)
+    Wandstärke Behälter
+    Gesamtgewicht
+    Gesamtgewicht (+Tol)
+    Gesamtgewicht (-Tol)
+    Gewicht Innenbehälter
+    Gewicht Innenbehälter (+Tol)
+    Gewicht Innenbehälter (-Tol)
+    Behältermaterial
+    Öffnungen
+    Auslauf armatur
+    Auslauf kupplung
+    Auslaufhöhe, max.
+    Auslaufhöhe, min.
+    Gewicht Rahmen
+    Gewicht Rahmen (+Tol)
+    Gewicht Rahmen (-Tol)
+    Rahmenmaterial
+    Gewicht Palette
+    Gewicht Palette (+Tol)
+    Gewicht Palette (-Tol)
+    Palettenhöhe
+    Palettenbreite
+    Palettentiefe
+    Einfahrhöhe Palette
+    Palettenmaterial
+    Palettentype
+    Sonstiges
+    Create_User
+    Create_Date
+    Update_User
+    Update_Date
     */

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

    // Prägung
    @Column(name = "embossing")
    private String embossing;

    // Prägungsposition
    @Column(name = "embossing_position")
    private String embossingPosition;

    // Zeichnungsnummer
    @Column(name = "drawing_number")
    private String drawingNumber;

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

    //Palettentiefe
    @Column(name = "palette_depth", columnDefinition="Double(6,2)")
    private Double paletteDepth;

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

    // Rahmenmaterial
    @NotNull
    @ManyToOne
    @JoinColumn(name = "frame_material_id")
    private MaterialEntity frameMaterial;

    // ExZone
    @ManyToOne
    @JoinColumn(name = "ex_zone_id")
    private ExZoneEntity exZone;

    // Beschichtung Behälter innen
    @ManyToOne
    @JoinColumn(name = "coating_inside_id")
    private CoatingEntity coatingInside;

    // Beschichtung Behälter außen
    @ManyToOne
    @JoinColumn(name = "coating_outside_id")
    private CoatingEntity coatingOutside;

    //Beschichtung Behälter
    @ManyToOne
    @JoinColumn(name = "coating_tank_id")
    private CoatingEntity coatingTank;

    // Öffnungen
    @NotNull
    @ManyToOne
    @JoinColumn(name = "hole_id")
    private ClosureEntity hole;

    /**
     * Initializes a new instance of the class.
     */
    public BigBagsEntity() {
    }

    // region <GET/SET>

    public String getUnCode() {
        return unCode;
    }

    public BigBagsEntity setUnCode(String unCode) {
        this.unCode = unCode;
        return this;
    }

    public Double getFrameHeight() {
        return frameHeight;
    }

    public BigBagsEntity setFrameHeight(Double frameHeight) {
        this.frameHeight = frameHeight;
        return this;
    }

    public Double getFrameHeightMax() {
        return frameHeightMax;
    }

    public BigBagsEntity setFrameHeightMax(Double frameHeightMax) {
        this.frameHeightMax = frameHeightMax;
        return this;
    }

    public Double getFrameHeightMin() {
        return frameHeightMin;
    }

    public BigBagsEntity setFrameHeightMin(Double frameHeightMin) {
        this.frameHeightMin = frameHeightMin;
        return this;
    }

    public Double getFrameWidth() {
        return frameWidth;
    }

    public BigBagsEntity setFrameWidth(Double frameWidth) {
        this.frameWidth = frameWidth;
        return this;
    }

    public Double getFrameWidthMax() {
        return frameWidthMax;
    }

    public BigBagsEntity setFrameWidthMax(Double frameWidthMax) {
        this.frameWidthMax = frameWidthMax;
        return this;
    }

    public Double getFrameWidthMin() {
        return frameWidthMin;
    }

    public BigBagsEntity setFrameWidthMin(Double frameWidthMin) {
        this.frameWidthMin = frameWidthMin;
        return this;
    }

    public Double getFrameDepth() {
        return frameDepth;
    }

    public BigBagsEntity setFrameDepth(Double frameDepth) {
        this.frameDepth = frameDepth;
        return this;
    }

    public Double getFrameDepthMax() {
        return frameDepthMax;
    }

    public BigBagsEntity setFrameDepthMax(Double frameDepthMax) {
        this.frameDepthMax = frameDepthMax;
        return this;
    }

    public Double getFrameDepthMin() {
        return frameDepthMin;
    }

    public BigBagsEntity setFrameDepthMin(Double frameDepthMin) {
        this.frameDepthMin = frameDepthMin;
        return this;
    }

    public Double getOverflowVolume() {
        return overflowVolume;
    }

    public BigBagsEntity setOverflowVolume(Double overflowVolume) {
        this.overflowVolume = overflowVolume;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public BigBagsEntity setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public Double getTotalWeightMax() {
        return totalWeightMax;
    }

    public BigBagsEntity setTotalWeightMax(Double totalWeightMax) {
        this.totalWeightMax = totalWeightMax;
        return this;
    }

    public Double getTotalWeightMin() {
        return totalWeightMin;
    }

    public BigBagsEntity setTotalWeightMin(Double totalWeightMin) {
        this.totalWeightMin = totalWeightMin;
        return this;
    }

    public Double getWeightInnerContainer() {
        return weightInnerContainer;
    }

    public BigBagsEntity setWeightInnerContainer(Double weightInnerContainer) {
        this.weightInnerContainer = weightInnerContainer;
        return this;
    }

    public Double getWeightInnerContainerMax() {
        return weightInnerContainerMax;
    }

    public BigBagsEntity setWeightInnerContainerMax(Double weightInnerContainerMax) {
        this.weightInnerContainerMax = weightInnerContainerMax;
        return this;
    }

    public Double getWeightInnerContainerMin() {
        return weightInnerContainerMin;
    }

    public BigBagsEntity setWeightInnerContainerMin(Double weightInnerContainerMin) {
        this.weightInnerContainerMin = weightInnerContainerMin;
        return this;
    }

    public Double getWeightFrame() {
        return weightFrame;
    }

    public BigBagsEntity setWeightFrame(Double weightFrame) {
        this.weightFrame = weightFrame;
        return this;
    }

    public Double getWeightFrameMax() {
        return weightFrameMax;
    }

    public BigBagsEntity setWeightFrameMax(Double weightFrameMax) {
        this.weightFrameMax = weightFrameMax;
        return this;
    }

    public Double getWeightFrameMin() {
        return weightFrameMin;
    }

    public BigBagsEntity setWeightFrameMin(Double weightFrameMin) {
        this.weightFrameMin = weightFrameMin;
        return this;
    }

    public Double getWeightPalette() {
        return weightPalette;
    }

    public BigBagsEntity setWeightPalette(Double weightPalette) {
        this.weightPalette = weightPalette;
        return this;
    }

    public Double getWeightPaletteMax() {
        return weightPaletteMax;
    }

    public BigBagsEntity setWeightPaletteMax(Double weightPaletteMax) {
        this.weightPaletteMax = weightPaletteMax;
        return this;
    }

    public Double getWeightPaletteMin() {
        return weightPaletteMin;
    }

    public BigBagsEntity setWeightPaletteMin(Double weightPaletteMin) {
        this.weightPaletteMin = weightPaletteMin;
        return this;
    }

    public String getPermLock() {
        return permLock;
    }

    public BigBagsEntity setPermLock(String permLock) {
        this.permLock = permLock;
        return this;
    }

    public String getEmbossing() {
        return embossing;
    }

    public BigBagsEntity setEmbossing(String embossing) {
        this.embossing = embossing;
        return this;
    }

    public String getEmbossingPosition() {
        return embossingPosition;
    }

    public BigBagsEntity setEmbossingPosition(String embossingPosition) {
        this.embossingPosition = embossingPosition;
        return this;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public BigBagsEntity setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
        return this;
    }

    public byte[] getDrawing() {
        return drawing;
    }

    public BigBagsEntity setDrawing(byte[] drawing) {
        this.drawing = drawing;
        return this;
    }

    public byte[] getDrawingCAD() {
        return drawingCAD;
    }

    public BigBagsEntity setDrawingCAD(byte[] drawingCAD) {
        this.drawingCAD = drawingCAD;
        return this;
    }

    public Double getPalletHeight() {
        return palletHeight;
    }

    public BigBagsEntity setPalletHeight(Double palletHeight) {
        this.palletHeight = palletHeight;
        return this;
    }

    public Double getPalletWidth() {
        return palletWidth;
    }

    public BigBagsEntity setPalletWidth(Double palletWidth) {
        this.palletWidth = palletWidth;
        return this;
    }

    public Double getPaletteDepth() {
        return paletteDepth;
    }

    public BigBagsEntity setPaletteDepth(Double paletteDepth) {
        this.paletteDepth = paletteDepth;
        return this;
    }

    public byte[] getManufacturerSpecifications() {
        return manufacturerSpecifications;
    }

    public BigBagsEntity setManufacturerSpecifications(byte[] manufacturerSpecifications) {
        this.manufacturerSpecifications = manufacturerSpecifications;
        return this;
    }

    public Double getPaletteInputHeight() {
        return paletteInputHeight;
    }

    public BigBagsEntity setPaletteInputHeight(Double paletteInputHeight) {
        this.paletteInputHeight = paletteInputHeight;
        return this;
    }

    public Double getNozzleHeightMax() {
        return nozzleHeightMax;
    }

    public BigBagsEntity setNozzleHeightMax(Double nozzleHeightMax) {
        this.nozzleHeightMax = nozzleHeightMax;
        return this;
    }

    public Double getNozzleHeightMin() {
        return nozzleHeightMin;
    }

    public BigBagsEntity setNozzleHeightMin(Double nozzleHeightMin) {
        this.nozzleHeightMin = nozzleHeightMin;
        return this;
    }

    public Double getWallThicknessContainer() {
        return wallThicknessContainer;
    }

    public BigBagsEntity setWallThicknessContainer(Double wallThicknessContainer) {
        this.wallThicknessContainer = wallThicknessContainer;
        return this;
    }

    public String getOther() {
        return other;
    }

    public BigBagsEntity setOther(String other) {
        this.other = other;
        return this;
    }

    public MaterialEntity getPaletteMaterial() {
        return paletteMaterial;
    }

    public BigBagsEntity setPaletteMaterial(MaterialEntity paletteMaterial) {
        this.paletteMaterial = paletteMaterial;
        return this;
    }

    public PaletteTypeEntity getPaletteType() {
        return paletteType;
    }

    public BigBagsEntity setPaletteType(PaletteTypeEntity paletteType) {
        this.paletteType = paletteType;
        return this;
    }

    public ClosureEntity getOutletArmatur() {
        return outletArmatur;
    }

    public BigBagsEntity setOutletArmatur(ClosureEntity outletArmatur) {
        this.outletArmatur = outletArmatur;
        return this;
    }

    public CouplingEntity getOutletCoupling() {
        return outletCoupling;
    }

    public BigBagsEntity setOutletCoupling(CouplingEntity outletCoupling) {
        this.outletCoupling = outletCoupling;
        return this;
    }

    public MaterialEntity getContainerMaterial() {
        return containerMaterial;
    }

    public BigBagsEntity setContainerMaterial(MaterialEntity containerMaterial) {
        this.containerMaterial = containerMaterial;
        return this;
    }

    public MaterialEntity getFrameMaterial() {
        return frameMaterial;
    }

    public BigBagsEntity setFrameMaterial(MaterialEntity frameMaterial) {
        this.frameMaterial = frameMaterial;
        return this;
    }

    public ExZoneEntity getExZone() {
        return exZone;
    }

    public BigBagsEntity setExZone(ExZoneEntity exZone) {
        this.exZone = exZone;
        return this;
    }

    public CoatingEntity getCoatingInside() {
        return coatingInside;
    }

    public BigBagsEntity setCoatingInside(CoatingEntity coatingInside) {
        this.coatingInside = coatingInside;
        return this;
    }

    public CoatingEntity getCoatingOutside() {
        return coatingOutside;
    }

    public BigBagsEntity setCoatingOutside(CoatingEntity coatingOutside) {
        this.coatingOutside = coatingOutside;
        return this;
    }

    public CoatingEntity getCoatingTank() {
        return coatingTank;
    }

    public BigBagsEntity setCoatingTank(CoatingEntity coatingTank) {
        this.coatingTank = coatingTank;
        return this;
    }

    public ClosureEntity getHole() {
        return hole;
    }

    public BigBagsEntity setHole(ClosureEntity hole) {
        this.hole = hole;
        return this;
    }

    public String getDrawingFilename() {
        return drawingFilename;
    }

    public BigBagsEntity setDrawingFilename(String drawingFilename) {
        this.drawingFilename = drawingFilename;
        return this;
    }

    public String getDrawingContentType() {
        return drawingContentType;
    }

    public BigBagsEntity setDrawingContentType(String drawingContentType) {
        this.drawingContentType = drawingContentType;
        return this;
    }

    public String getDrawingCADFilename() {
        return drawingCADFilename;
    }

    public BigBagsEntity setDrawingCADFilename(String drawingCADFilename) {
        this.drawingCADFilename = drawingCADFilename;
        return this;
    }

    public String getDrawingCADContentType() {
        return drawingCADContentType;
    }

    public BigBagsEntity setDrawingCADContentType(String drawingCADContentType) {
        this.drawingCADContentType = drawingCADContentType;
        return this;
    }

    public String getManufacturerSpecificationsFilename() {
        return manufacturerSpecificationsFilename;
    }

    public BigBagsEntity setManufacturerSpecificationsFilename(String manufacturerSpecificationsFilename) {
        this.manufacturerSpecificationsFilename = manufacturerSpecificationsFilename;
        return this;
    }

    public String getManufacturerSpecificationsContentType() {
        return manufacturerSpecificationsContentType;
    }

    public BigBagsEntity setManufacturerSpecificationsContentType(String manufacturerSpecificationsContentType) {
        this.manufacturerSpecificationsContentType = manufacturerSpecificationsContentType;
        return this;
    }

    //endregion
}
