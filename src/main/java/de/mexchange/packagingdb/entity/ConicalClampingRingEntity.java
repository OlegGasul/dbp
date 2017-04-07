package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Conical Clamping Ring Container Entity class
 * Spannring Konish
 */
@Entity
@Table(name = "conical_clamping_ring")
public class ConicalClampingRingEntity extends ContainerEntity {

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
+?   Beschichtung Behälter außen
+?   Beschichtung Behälter innen
+    Bemerkungen GE
+    Bemerkungen GB
+    Hersteller Datenblatt
+    Fotos
+    Zeichnungsnummer
+    Zeichnung
+    Zeichnung CAD
+    Höhe über alles
+    Höhe über alles (+Tol)
+    Höhe über alles (-Tol)
+    Höhe Rumpf
+    Höhe Rumpf (+Tol)
+    Höhe Rumpf (-Tol)
+    Innendurchmesser oben
+    Innendurchmesser oben (+Tol)
+    Innendurchmesser oben (-Tol)
+    Innendurchmesser unten
+    Innendurchmesser unten (+Tol)
+    Innendurchmesser unten (-Tol)
+    Wandstärke Rumpf
+    Wandstärke Rumpf (+Tol)
+    Wandstärke Rumpf (-Tol)
+    Wandstärke Deckel
+    Wandstärke Deckel (+Tol)
+    Wandstärke Deckel (-Tol)
+    Wandstärke Boden
+    Wandstärke Boden (+Tol)
+    Wandstärke Boden (-Tol)
+    Durchmesser Spannring
+    Durchmesser Spannring (+Tol)
+    Durchmesser Spannring (-Tol)
+    Gesamtgewicht
+    Gesamtgewicht (+Tol)
+    Gesamtgewicht (-Tol)
+    Gewicht Innenbehälter
+    Gewicht Innenbehälter (+Tol)
+    Gewicht Innenbehälter (-Tol)
+    Behältermaterial
+    Öffnungen
+    Sicken
+    Griffe
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

    // Hersteller Datenblatt
    @Lob
    @Column(name = "manufacturer_specifications")
    private byte[] manufacturerSpecifications;

    @Column(name = "manufacturer_specifications_filename")
    private String manufacturerSpecificationsFilename;

    @Column(name = "manufacturer_specifications_content_type")
    private String manufacturerSpecificationsContentType;

    //Sonstiges
    @Column(name = "other")
    private String other;

    //Sicken
    @Column(name = "sicken")
    private String sicken;

    //Höhe über alles
    @Column(name = "overall_height", columnDefinition="Double(6,2)")
    private Double overallHeight;

    //Höhe über alles (+Tol)
    @Column(name = "overall_height_max", columnDefinition="Double(6,2)")
    private Double overallHeightMax;

    //Höhe über alles (-Tol)
    @Column(name = "overall_height_min", columnDefinition="Double(6,2)")
    private Double overallHeightMin;

    //Höhe Rumpf
    @Column(name = "hull_height", columnDefinition="Double(6,2)")
    private Double hullHeight;


    //Höhe Rumpf (+Tol)
    @Column(name = "hull_height_max", columnDefinition="Double(6,2)")
    private Double hullHeightMax;


    //Höhe Rumpf (-Tol)
    @Column(name = "hull_height_min", columnDefinition="Double(6,2)")
    private Double hullHeightMin;

    //Innendurchmesser oben
    @Column(name = "inner_diameter_above", columnDefinition="Double(6,2)")
    private Double innerDiameterAbove;

    //Innendurchmesser oben (-Tol)
    @Column(name = "inner_diameter_above_min", columnDefinition="Double(6,2)")
    private Double innerDiameterAboveMin;

    //Innendurchmesser oben (+Tol)
    @Column(name = "inner_diameter_above_max", columnDefinition="Double(6,2)")
    private Double innerDiameterAboveMax;

    //Innendurchmesser unten
    @Column(name = "inner_diameter_below", columnDefinition="Double(6,2)")
    private Double innerDiameterBelow;

    //Innendurchmesser unten (-Tol)
    @Column(name = "inner_diameter_below_min", columnDefinition="Double(6,2)")
    private Double innerDiameterBelowMin;

    //Innendurchmesser unten (+Tol)
    @Column(name = "inner_diameter_below_max", columnDefinition="Double(6,2)")
    private Double innerDiameterBelowMax;

    //Wandstärke Rumpf
    @Column(name = "hull_wall_thickness", columnDefinition="Double(6,2)")
    private Double hullWallThickness;

    //Wandstärke Rumpf (-Tol)
    @Column(name = "hull_wall_thickness_min", columnDefinition="Double(6,2)")
    private Double hullWallThicknessMin;

    //Wandstärke Rumpf (+Tol)
    @Column(name = "hull_wall_thickness_max", columnDefinition="Double(6,2)")
    private Double hullWallThicknessMax;

    //Wandstärke Deckel
    @Column(name = "lid_wall_thickness", columnDefinition="Double(6,2)")
    private Double lidWallThickness;

    //Wandstärke Deckel (-Tol)
    @Column(name = "lid_wall_thickness_min", columnDefinition="Double(6,2)")
    private Double lidWallThicknessMin;

    //Wandstärke Deckel (+Tol)
    @Column(name = "lid_wall_thickness_max", columnDefinition="Double(6,2)")
    private Double lidWallThicknessMax;

    //Wandstärke Boden
    @Column(name = "ground_wall_thickness", columnDefinition="Double(6,2)")
    private Double groundWallThickness;

    //Wandstärke Boden (-Tol)
    @Column(name = "ground_wall_thickness_min", columnDefinition="Double(6,2)")
    private Double groundWallThicknessMin;

    //Wandstärke Boden (+Tol)
    @Column(name = "ground_wall_thickness_max", columnDefinition="Double(6,2)")
    private Double groundWallThicknessMax;

    //Durchmesser Spannring
    @Column(name = "diameter_clamping_ring", columnDefinition="Double(6,2)")
    private Double diameterClampingRing;

    //Durchmesser Spannring (-Tol)
    @Column(name = "diameter_clamping_ring_min", columnDefinition="Double(6,2)")
    private Double diameterClampingRingMin;

    //Durchmesser Spannring (+Tol)
    @Column(name = "diameter_clamping_ring_max", columnDefinition="Double(6,2)")
    private Double diameterClampingRingMax;

    // Behältermaterial
    @NotNull
    @ManyToOne
    @JoinColumn(name = "container_material_id")
    private MaterialEntity containerMaterial;

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

    // Griffe
    @ManyToOne
    @JoinColumn(name = "handle_id")
    private HandleEntity handle;

    /**
     * Initializes a new instance of the class.
     */
    public ConicalClampingRingEntity() {
    }

    // region <GET/SET>

    public String getUnCode() {
        return unCode;
    }

    public ConicalClampingRingEntity setUnCode(String unCode) {
        this.unCode = unCode;
        return this;
    }

    public Double getOverflowVolume() {
        return overflowVolume;
    }

    public ConicalClampingRingEntity setOverflowVolume(Double overflowVolume) {
        this.overflowVolume = overflowVolume;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public ConicalClampingRingEntity setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public Double getTotalWeightMax() {
        return totalWeightMax;
    }

    public ConicalClampingRingEntity setTotalWeightMax(Double totalWeightMax) {
        this.totalWeightMax = totalWeightMax;
        return this;
    }

    public Double getTotalWeightMin() {
        return totalWeightMin;
    }

    public ConicalClampingRingEntity setTotalWeightMin(Double totalWeightMin) {
        this.totalWeightMin = totalWeightMin;
        return this;
    }

    public Double getWeightInnerContainer() {
        return weightInnerContainer;
    }

    public ConicalClampingRingEntity setWeightInnerContainer(Double weightInnerContainer) {
        this.weightInnerContainer = weightInnerContainer;
        return this;
    }

    public Double getWeightInnerContainerMax() {
        return weightInnerContainerMax;
    }

    public ConicalClampingRingEntity setWeightInnerContainerMax(Double weightInnerContainerMax) {
        this.weightInnerContainerMax = weightInnerContainerMax;
        return this;
    }

    public Double getWeightInnerContainerMin() {
        return weightInnerContainerMin;
    }

    public ConicalClampingRingEntity setWeightInnerContainerMin(Double weightInnerContainerMin) {
        this.weightInnerContainerMin = weightInnerContainerMin;
        return this;
    }

    public String getPermLock() {
        return permLock;
    }

    public ConicalClampingRingEntity setPermLock(String permLock) {
        this.permLock = permLock;
        return this;
    }

    public String getEmbossing() {
        return embossing;
    }

    public ConicalClampingRingEntity setEmbossing(String embossing) {
        this.embossing = embossing;
        return this;
    }

    public String getEmbossingPosition() {
        return embossingPosition;
    }

    public ConicalClampingRingEntity setEmbossingPosition(String embossingPosition) {
        this.embossingPosition = embossingPosition;
        return this;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public ConicalClampingRingEntity setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
        return this;
    }

    public  byte[] getDrawing() {
        return drawing;
    }

    public ConicalClampingRingEntity setDrawing( byte[] drawing) {
        this.drawing = drawing;
        return this;
    }

    public  byte[] getDrawingCAD() {
        return drawingCAD;
    }

    public ConicalClampingRingEntity setDrawingCAD( byte[] drawingCAD) {
        this.drawingCAD = drawingCAD;
        return this;
    }

    public  byte[] getManufacturerSpecifications() {
        return manufacturerSpecifications;
    }

    public ConicalClampingRingEntity setManufacturerSpecifications( byte[] manufacturerSpecifications) {
        this.manufacturerSpecifications = manufacturerSpecifications;
        return this;
    }

    public String getOther() {
        return other;
    }

    public ConicalClampingRingEntity setOther(String other) {
        this.other = other;
        return this;
    }

    public String getSicken() {
        return sicken;
    }

    public ConicalClampingRingEntity setSicken(String sicken) {
        this.sicken = sicken;
        return this;
    }

    public Double getOverallHeight() {
        return overallHeight;
    }

    public ConicalClampingRingEntity setOverallHeight(Double overallHeight) {
        this.overallHeight = overallHeight;
        return this;
    }

    public Double getOverallHeightMax() {
        return overallHeightMax;
    }

    public ConicalClampingRingEntity setOverallHeightMax(Double overallHeightMax) {
        this.overallHeightMax = overallHeightMax;
        return this;
    }

    public Double getOverallHeightMin() {
        return overallHeightMin;
    }

    public ConicalClampingRingEntity setOverallHeightMin(Double overallHeightMin) {
        this.overallHeightMin = overallHeightMin;
        return this;
    }

    public Double getHullHeight() {
        return hullHeight;
    }

    public ConicalClampingRingEntity setHullHeight(Double hullHeight) {
        this.hullHeight = hullHeight;
        return this;
    }

    public Double getHullHeightMax() {
        return hullHeightMax;
    }

    public ConicalClampingRingEntity setHullHeightMax(Double hullHeightMax) {
        this.hullHeightMax = hullHeightMax;
        return this;
    }

    public Double getHullHeightMin() {
        return hullHeightMin;
    }

    public ConicalClampingRingEntity setHullHeightMin(Double hullHeightMin) {
        this.hullHeightMin = hullHeightMin;
        return this;
    }

    public Double getInnerDiameterAbove() {
        return innerDiameterAbove;
    }

    public ConicalClampingRingEntity setInnerDiameterAbove(Double innerDiameterAbove) {
        this.innerDiameterAbove = innerDiameterAbove;
        return this;
    }

    public Double getInnerDiameterAboveMin() {
        return innerDiameterAboveMin;
    }

    public ConicalClampingRingEntity setInnerDiameterAboveMin(Double innerDiameterAboveMin) {
        this.innerDiameterAboveMin = innerDiameterAboveMin;
        return this;
    }

    public Double getInnerDiameterAboveMax() {
        return innerDiameterAboveMax;
    }

    public ConicalClampingRingEntity setInnerDiameterAboveMax(Double innerDiameterAboveMax) {
        this.innerDiameterAboveMax = innerDiameterAboveMax;
        return this;
    }

    public Double getInnerDiameterBelow() {
        return innerDiameterBelow;
    }

    public ConicalClampingRingEntity setInnerDiameterBelow(Double innerDiameterBelow) {
        this.innerDiameterBelow = innerDiameterBelow;
        return this;
    }

    public Double getInnerDiameterBelowMin() {
        return innerDiameterBelowMin;
    }

    public ConicalClampingRingEntity setInnerDiameterBelowMin(Double innerDiameterBelowMin) {
        this.innerDiameterBelowMin = innerDiameterBelowMin;
        return this;
    }

    public Double getInnerDiameterBelowMax() {
        return innerDiameterBelowMax;
    }

    public ConicalClampingRingEntity setInnerDiameterBelowMax(Double innerDiameterBelowMax) {
        this.innerDiameterBelowMax = innerDiameterBelowMax;
        return this;
    }

    public Double getHullWallThickness() {
        return hullWallThickness;
    }

    public ConicalClampingRingEntity setHullWallThickness(Double hullWallThickness) {
        this.hullWallThickness = hullWallThickness;
        return this;
    }

    public Double getHullWallThicknessMin() {
        return hullWallThicknessMin;
    }

    public ConicalClampingRingEntity setHullWallThicknessMin(Double hullWallThicknessMin) {
        this.hullWallThicknessMin = hullWallThicknessMin;
        return this;
    }

    public Double getHullWallThicknessMax() {
        return hullWallThicknessMax;
    }

    public ConicalClampingRingEntity setHullWallThicknessMax(Double hullWallThicknessMax) {
        this.hullWallThicknessMax = hullWallThicknessMax;
        return this;
    }

    public Double getLidWallThickness() {
        return lidWallThickness;
    }

    public ConicalClampingRingEntity setLidWallThickness(Double lidWallThickness) {
        this.lidWallThickness = lidWallThickness;
        return this;
    }

    public Double getLidWallThicknessMin() {
        return lidWallThicknessMin;
    }

    public ConicalClampingRingEntity setLidWallThicknessMin(Double lidWallThicknessMin) {
        this.lidWallThicknessMin = lidWallThicknessMin;
        return this;
    }

    public Double getLidWallThicknessMax() {
        return lidWallThicknessMax;
    }

    public ConicalClampingRingEntity setLidWallThicknessMax(Double lidWallThicknessMax) {
        this.lidWallThicknessMax = lidWallThicknessMax;
        return this;
    }

    public Double getGroundWallThickness() {
        return groundWallThickness;
    }

    public ConicalClampingRingEntity setGroundWallThickness(Double groundWallThickness) {
        this.groundWallThickness = groundWallThickness;
        return this;
    }

    public Double getGroundWallThicknessMin() {
        return groundWallThicknessMin;
    }

    public ConicalClampingRingEntity setGroundWallThicknessMin(Double groundWallThicknessMin) {
        this.groundWallThicknessMin = groundWallThicknessMin;
        return this;
    }

    public Double getGroundWallThicknessMax() {
        return groundWallThicknessMax;
    }

    public ConicalClampingRingEntity setGroundWallThicknessMax(Double groundWallThicknessMax) {
        this.groundWallThicknessMax = groundWallThicknessMax;
        return this;
    }

    public Double getDiameterClampingRing() {
        return diameterClampingRing;
    }

    public ConicalClampingRingEntity setDiameterClampingRing(Double diameterClampingRing) {
        this.diameterClampingRing = diameterClampingRing;
        return this;
    }

    public Double getDiameterClampingRingMin() {
        return diameterClampingRingMin;
    }

    public ConicalClampingRingEntity setDiameterClampingRingMin(Double diameterClampingRingMin) {
        this.diameterClampingRingMin = diameterClampingRingMin;
        return this;
    }

    public Double getDiameterClampingRingMax() {
        return diameterClampingRingMax;
    }

    public ConicalClampingRingEntity setDiameterClampingRingMax(Double diameterClampingRingMax) {
        this.diameterClampingRingMax = diameterClampingRingMax;
        return this;
    }

    public MaterialEntity getContainerMaterial() {
        return containerMaterial;
    }

    public ConicalClampingRingEntity setContainerMaterial(MaterialEntity containerMaterial) {
        this.containerMaterial = containerMaterial;
        return this;
    }

    public ExZoneEntity getExZone() {
        return exZone;
    }

    public ConicalClampingRingEntity setExZone(ExZoneEntity exZone) {
        this.exZone = exZone;
        return this;
    }

    public CoatingEntity getCoatingInside() {
        return coatingInside;
    }

    public ConicalClampingRingEntity setCoatingInside(CoatingEntity coatingInside) {
        this.coatingInside = coatingInside;
        return this;
    }

    public CoatingEntity getCoatingOutside() {
        return coatingOutside;
    }

    public ConicalClampingRingEntity setCoatingOutside(CoatingEntity coatingOutside) {
        this.coatingOutside = coatingOutside;
        return this;
    }

    public CoatingEntity getCoatingTank() {
        return coatingTank;
    }

    public ConicalClampingRingEntity setCoatingTank(CoatingEntity coatingTank) {
        this.coatingTank = coatingTank;
        return this;
    }

    public ClosureEntity getHole() {
        return hole;
    }

    public ConicalClampingRingEntity setHole(ClosureEntity hole) {
        this.hole = hole;
        return this;
    }

    public HandleEntity getHandle() {
        return handle;
    }

    public ConicalClampingRingEntity setHandle(HandleEntity handle) {
        this.handle = handle;
        return this;
    }

    public String getDrawingFilename() {
        return drawingFilename;
    }

    public ConicalClampingRingEntity setDrawingFilename(String drawingFilename) {
        this.drawingFilename = drawingFilename;
        return this;
    }

    public String getDrawingContentType() {
        return drawingContentType;
    }

    public ConicalClampingRingEntity setDrawingContentType(String drawingContentType) {
        this.drawingContentType = drawingContentType;
        return this;
    }

    public String getDrawingCADFilename() {
        return drawingCADFilename;
    }

    public ConicalClampingRingEntity setDrawingCADFilename(String drawingCADFilename) {
        this.drawingCADFilename = drawingCADFilename;
        return this;
    }

    public String getDrawingCADContentType() {
        return drawingCADContentType;
    }

    public ConicalClampingRingEntity setDrawingCADContentType(String drawingCADContentType) {
        this.drawingCADContentType = drawingCADContentType;
        return this;
    }

    public String getManufacturerSpecificationsFilename() {
        return manufacturerSpecificationsFilename;
    }

    public ConicalClampingRingEntity setManufacturerSpecificationsFilename(String manufacturerSpecificationsFilename) {
        this.manufacturerSpecificationsFilename = manufacturerSpecificationsFilename;
        return this;
    }

    public String getManufacturerSpecificationsContentType() {
        return manufacturerSpecificationsContentType;
    }

    public ConicalClampingRingEntity setManufacturerSpecificationsContentType(String manufacturerSpecificationsContentType) {
        this.manufacturerSpecificationsContentType = manufacturerSpecificationsContentType;
        return this;
    }

    //endregion
}
