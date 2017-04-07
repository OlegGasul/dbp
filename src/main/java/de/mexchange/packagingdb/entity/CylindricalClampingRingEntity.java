package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Cylindrical Clamping Ring Container Entity class
 * Cylindrical Clamping Ring
 * GE: Spannring Zylindrish
 */
@Entity
@Table(name = "cylindrical_clamping_ring")
public class CylindricalClampingRingEntity extends ContainerEntity {

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
+    Bemerkungen GE
+    Bemerkungen GB
+    Hersteller Datenblatt
+    Fotos
+    Zeichnungsnummer
+    Zeichnung
+    Zeichnung CAD
+    Gesamthähe (Gesamthöhe, Höhe über alles)
+    Gesamthähe (+Tol)
+    Gesamthähe (-Tol)
+    Hähe Rumpf
+    Hähe Rumpf (+Tol)
+    Hähe Rumpf (-Tol)
+    Innendurchmesser
+    Innendurchmesser (+Tol)
+    Innendurchmesser (-Tol)
+    Wandstärke Behälter
+    Wandstärke Behälter (+Tol)
+    Wandstärke Behälter (-Tol)
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

    //Innendurchmesser
    @Column(name = "inner_diameter", columnDefinition="Double(6,2)")
    private Double innerDiameter;

    //Innendurchmesser (-Tol)
    @Column(name = "inner_diameter_min", columnDefinition="Double(6,2)")
    private Double innerDiameterMin;

    //Innendurchmesser (+Tol)
    @Column(name = "inner_diameter_max", columnDefinition="Double(6,2)")
    private Double innerDiameterMax;

    //Wandstärke Behälter
    @Column(name = "wall_thickness", columnDefinition="Double(6,2)")
    private Double wallThicknessContainer;

    //Wandstärke Behälter (+Tol)
    @Column(name = "wall_thickness_max", columnDefinition="Double(6,2)")
    private Double wallThicknessContainerMax;

    //Wandstärke Behälter (-Tol)
    @Column(name = "wall_thickness_min", columnDefinition="Double(6,2)")
    private Double wallThicknessContainerMin;

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
    public CylindricalClampingRingEntity() {
    }

    // region <GET/SET>

    public String getUnCode() {
        return unCode;
    }

    public CylindricalClampingRingEntity setUnCode(String unCode) {
        this.unCode = unCode;
        return this;
    }

    public Double getOverflowVolume() {
        return overflowVolume;
    }

    public CylindricalClampingRingEntity setOverflowVolume(Double overflowVolume) {
        this.overflowVolume = overflowVolume;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public CylindricalClampingRingEntity setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public Double getTotalWeightMax() {
        return totalWeightMax;
    }

    public CylindricalClampingRingEntity setTotalWeightMax(Double totalWeightMax) {
        this.totalWeightMax = totalWeightMax;
        return this;
    }

    public Double getTotalWeightMin() {
        return totalWeightMin;
    }

    public CylindricalClampingRingEntity setTotalWeightMin(Double totalWeightMin) {
        this.totalWeightMin = totalWeightMin;
        return this;
    }

    public Double getWeightInnerContainer() {
        return weightInnerContainer;
    }

    public CylindricalClampingRingEntity setWeightInnerContainer(Double weightInnerContainer) {
        this.weightInnerContainer = weightInnerContainer;
        return this;
    }

    public Double getWeightInnerContainerMax() {
        return weightInnerContainerMax;
    }

    public CylindricalClampingRingEntity setWeightInnerContainerMax(Double weightInnerContainerMax) {
        this.weightInnerContainerMax = weightInnerContainerMax;
        return this;
    }

    public Double getWeightInnerContainerMin() {
        return weightInnerContainerMin;
    }

    public CylindricalClampingRingEntity setWeightInnerContainerMin(Double weightInnerContainerMin) {
        this.weightInnerContainerMin = weightInnerContainerMin;
        return this;
    }

    public String getPermLock() {
        return permLock;
    }

    public CylindricalClampingRingEntity setPermLock(String permLock) {
        this.permLock = permLock;
        return this;
    }

    public String getEmbossing() {
        return embossing;
    }

    public CylindricalClampingRingEntity setEmbossing(String embossing) {
        this.embossing = embossing;
        return this;
    }

    public String getEmbossingPosition() {
        return embossingPosition;
    }

    public CylindricalClampingRingEntity setEmbossingPosition(String embossingPosition) {
        this.embossingPosition = embossingPosition;
        return this;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public CylindricalClampingRingEntity setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
        return this;
    }

    public byte[] getDrawing() {
        return drawing;
    }

    public CylindricalClampingRingEntity setDrawing(byte[] drawing) {
        this.drawing = drawing;
        return this;
    }

    public byte[] getDrawingCAD() {
        return drawingCAD;
    }

    public CylindricalClampingRingEntity setDrawingCAD(byte[] drawingCAD) {
        this.drawingCAD = drawingCAD;
        return this;
    }

    public byte[] getManufacturerSpecifications() {
        return manufacturerSpecifications;
    }

    public CylindricalClampingRingEntity setManufacturerSpecifications(byte[] manufacturerSpecifications) {
        this.manufacturerSpecifications = manufacturerSpecifications;
        return this;
    }

    public String getOther() {
        return other;
    }

    public CylindricalClampingRingEntity setOther(String other) {
        this.other = other;
        return this;
    }

    public String getSicken() {
        return sicken;
    }

    public CylindricalClampingRingEntity setSicken(String sicken) {
        this.sicken = sicken;
        return this;
    }

    public Double getOverallHeight() {
        return overallHeight;
    }

    public CylindricalClampingRingEntity setOverallHeight(Double overallHeight) {
        this.overallHeight = overallHeight;
        return this;
    }

    public Double getOverallHeightMax() {
        return overallHeightMax;
    }

    public CylindricalClampingRingEntity setOverallHeightMax(Double overallHeightMax) {
        this.overallHeightMax = overallHeightMax;
        return this;
    }

    public Double getOverallHeightMin() {
        return overallHeightMin;
    }

    public CylindricalClampingRingEntity setOverallHeightMin(Double overallHeightMin) {
        this.overallHeightMin = overallHeightMin;
        return this;
    }

    public Double getHullHeight() {
        return hullHeight;
    }

    public CylindricalClampingRingEntity setHullHeight(Double hullHeight) {
        this.hullHeight = hullHeight;
        return this;
    }

    public Double getHullHeightMax() {
        return hullHeightMax;
    }

    public CylindricalClampingRingEntity setHullHeightMax(Double hullHeightMax) {
        this.hullHeightMax = hullHeightMax;
        return this;
    }

    public Double getHullHeightMin() {
        return hullHeightMin;
    }

    public CylindricalClampingRingEntity setHullHeightMin(Double hullHeightMin) {
        this.hullHeightMin = hullHeightMin;
        return this;
    }

    public Double getInnerDiameter() {
        return innerDiameter;
    }

    public CylindricalClampingRingEntity setInnerDiameter(Double innerDiameter) {
        this.innerDiameter = innerDiameter;
        return this;
    }

    public Double getInnerDiameterMin() {
        return innerDiameterMin;
    }

    public CylindricalClampingRingEntity setInnerDiameterMin(Double innerDiameterMin) {
        this.innerDiameterMin = innerDiameterMin;
        return this;
    }

    public Double getInnerDiameterMax() {
        return innerDiameterMax;
    }

    public CylindricalClampingRingEntity setInnerDiameterMax(Double innerDiameterMax) {
        this.innerDiameterMax = innerDiameterMax;
        return this;
    }

    public Double getWallThicknessContainer() {
        return wallThicknessContainer;
    }

    public CylindricalClampingRingEntity setWallThicknessContainer(Double wallThicknessContainer) {
        this.wallThicknessContainer = wallThicknessContainer;
        return this;
    }

    public Double getWallThicknessContainerMax() {
        return wallThicknessContainerMax;
    }

    public CylindricalClampingRingEntity setWallThicknessContainerMax(Double wallThicknessContainerMax) {
        this.wallThicknessContainerMax = wallThicknessContainerMax;
        return this;
    }

    public Double getWallThicknessContainerMin() {
        return wallThicknessContainerMin;
    }

    public CylindricalClampingRingEntity setWallThicknessContainerMin(Double wallThicknessContainerMin) {
        this.wallThicknessContainerMin = wallThicknessContainerMin;
        return this;
    }

    public Double getLidWallThickness() {
        return lidWallThickness;
    }

    public CylindricalClampingRingEntity setLidWallThickness(Double lidWallThickness) {
        this.lidWallThickness = lidWallThickness;
        return this;
    }

    public Double getLidWallThicknessMin() {
        return lidWallThicknessMin;
    }

    public CylindricalClampingRingEntity setLidWallThicknessMin(Double lidWallThicknessMin) {
        this.lidWallThicknessMin = lidWallThicknessMin;
        return this;
    }

    public Double getLidWallThicknessMax() {
        return lidWallThicknessMax;
    }

    public CylindricalClampingRingEntity setLidWallThicknessMax(Double lidWallThicknessMax) {
        this.lidWallThicknessMax = lidWallThicknessMax;
        return this;
    }

    public Double getGroundWallThickness() {
        return groundWallThickness;
    }

    public CylindricalClampingRingEntity setGroundWallThickness(Double groundWallThickness) {
        this.groundWallThickness = groundWallThickness;
        return this;
    }

    public Double getGroundWallThicknessMin() {
        return groundWallThicknessMin;
    }

    public CylindricalClampingRingEntity setGroundWallThicknessMin(Double groundWallThicknessMin) {
        this.groundWallThicknessMin = groundWallThicknessMin;
        return this;
    }

    public Double getGroundWallThicknessMax() {
        return groundWallThicknessMax;
    }

    public CylindricalClampingRingEntity setGroundWallThicknessMax(Double groundWallThicknessMax) {
        this.groundWallThicknessMax = groundWallThicknessMax;
        return this;
    }

    public Double getDiameterClampingRing() {
        return diameterClampingRing;
    }

    public CylindricalClampingRingEntity setDiameterClampingRing(Double diameterClampingRing) {
        this.diameterClampingRing = diameterClampingRing;
        return this;
    }

    public Double getDiameterClampingRingMin() {
        return diameterClampingRingMin;
    }

    public CylindricalClampingRingEntity setDiameterClampingRingMin(Double diameterClampingRingMin) {
        this.diameterClampingRingMin = diameterClampingRingMin;
        return this;
    }

    public Double getDiameterClampingRingMax() {
        return diameterClampingRingMax;
    }

    public CylindricalClampingRingEntity setDiameterClampingRingMax(Double diameterClampingRingMax) {
        this.diameterClampingRingMax = diameterClampingRingMax;
        return this;
    }

    public MaterialEntity getContainerMaterial() {
        return containerMaterial;
    }

    public CylindricalClampingRingEntity setContainerMaterial(MaterialEntity containerMaterial) {
        this.containerMaterial = containerMaterial;
        return this;
    }

    public ExZoneEntity getExZone() {
        return exZone;
    }

    public CylindricalClampingRingEntity setExZone(ExZoneEntity exZone) {
        this.exZone = exZone;
        return this;
    }

    public CoatingEntity getCoatingInside() {
        return coatingInside;
    }

    public CylindricalClampingRingEntity setCoatingInside(CoatingEntity coatingInside) {
        this.coatingInside = coatingInside;
        return this;
    }

    public CoatingEntity getCoatingOutside() {
        return coatingOutside;
    }

    public CylindricalClampingRingEntity setCoatingOutside(CoatingEntity coatingOutside) {
        this.coatingOutside = coatingOutside;
        return this;
    }

    public ClosureEntity getHole() {
        return hole;
    }

    public CylindricalClampingRingEntity setHole(ClosureEntity hole) {
        this.hole = hole;
        return this;
    }

    public HandleEntity getHandle() {
        return handle;
    }

    public CylindricalClampingRingEntity setHandle(HandleEntity handle) {
        this.handle = handle;
        return this;
    }

    public String getDrawingFilename() {
        return drawingFilename;
    }

    public CylindricalClampingRingEntity setDrawingFilename(String drawingFilename) {
        this.drawingFilename = drawingFilename;
        return this;
    }

    public String getDrawingContentType() {
        return drawingContentType;
    }

    public CylindricalClampingRingEntity setDrawingContentType(String drawingContentType) {
        this.drawingContentType = drawingContentType;
        return this;
    }

    public String getDrawingCADFilename() {
        return drawingCADFilename;
    }

    public CylindricalClampingRingEntity setDrawingCADFilename(String drawingCADFilename) {
        this.drawingCADFilename = drawingCADFilename;
        return this;
    }

    public String getDrawingCADContentType() {
        return drawingCADContentType;
    }

    public CylindricalClampingRingEntity setDrawingCADContentType(String drawingCADContentType) {
        this.drawingCADContentType = drawingCADContentType;
        return this;
    }

    public String getManufacturerSpecificationsFilename() {
        return manufacturerSpecificationsFilename;
    }

    public CylindricalClampingRingEntity setManufacturerSpecificationsFilename(String manufacturerSpecificationsFilename) {
        this.manufacturerSpecificationsFilename = manufacturerSpecificationsFilename;
        return this;
    }

    public String getManufacturerSpecificationsContentType() {
        return manufacturerSpecificationsContentType;
    }

    public CylindricalClampingRingEntity setManufacturerSpecificationsContentType(String manufacturerSpecificationsContentType) {
        this.manufacturerSpecificationsContentType = manufacturerSpecificationsContentType;
        return this;
    }

    //endregion

}
