package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Conical Cans Container Entity class
 * Dosen Kubish
 */
@Entity
@Table(name = "conical_cans")
public class ConicalCansEntity extends ContainerEntity {

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
+    Durchmesser Deckel
+    Durchmesser Deckel (+Tol)
+    Durchmesser Deckel (-Tol)
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

    //Durchmesser Deckel
    @Column(name = "diameter_lid", columnDefinition="Double(6,2)")
    private Double diameterLid;

    //Durchmesser Deckel (-Tol)
    @Column(name = "diameter_lid_min", columnDefinition="Double(6,2)")
    private Double diameterLidMin;

    //Durchmesser Deckel (+Tol)
    @Column(name = "diameter_lid_max", columnDefinition="Double(6,2)")
    private Double diameterLidMax;

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
    public ConicalCansEntity() {
    }

    // region <GET/SET>

    public String getUnCode() {
        return unCode;
    }

    public ConicalCansEntity setUnCode(String unCode) {
        this.unCode = unCode;
        return this;
    }

    public Double getOverflowVolume() {
        return overflowVolume;
    }

    public ConicalCansEntity setOverflowVolume(Double overflowVolume) {
        this.overflowVolume = overflowVolume;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public ConicalCansEntity setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public Double getTotalWeightMax() {
        return totalWeightMax;
    }

    public ConicalCansEntity setTotalWeightMax(Double totalWeightMax) {
        this.totalWeightMax = totalWeightMax;
        return this;
    }

    public Double getTotalWeightMin() {
        return totalWeightMin;
    }

    public ConicalCansEntity setTotalWeightMin(Double totalWeightMin) {
        this.totalWeightMin = totalWeightMin;
        return this;
    }

    public Double getWeightInnerContainer() {
        return weightInnerContainer;
    }

    public ConicalCansEntity setWeightInnerContainer(Double weightInnerContainer) {
        this.weightInnerContainer = weightInnerContainer;
        return this;
    }

    public Double getWeightInnerContainerMax() {
        return weightInnerContainerMax;
    }

    public ConicalCansEntity setWeightInnerContainerMax(Double weightInnerContainerMax) {
        this.weightInnerContainerMax = weightInnerContainerMax;
        return this;
    }

    public Double getWeightInnerContainerMin() {
        return weightInnerContainerMin;
    }

    public ConicalCansEntity setWeightInnerContainerMin(Double weightInnerContainerMin) {
        this.weightInnerContainerMin = weightInnerContainerMin;
        return this;
    }

    public String getPermLock() {
        return permLock;
    }

    public ConicalCansEntity setPermLock(String permLock) {
        this.permLock = permLock;
        return this;
    }

    public String getEmbossing() {
        return embossing;
    }

    public ConicalCansEntity setEmbossing(String embossing) {
        this.embossing = embossing;
        return this;
    }

    public String getEmbossingPosition() {
        return embossingPosition;
    }

    public ConicalCansEntity setEmbossingPosition(String embossingPosition) {
        this.embossingPosition = embossingPosition;
        return this;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public ConicalCansEntity setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
        return this;
    }

    public byte[] getDrawing() {
        return drawing;
    }

    public ConicalCansEntity setDrawing(byte[] drawing) {
        this.drawing = drawing;
        return this;
    }

    public byte[] getDrawingCAD() {
        return drawingCAD;
    }

    public ConicalCansEntity setDrawingCAD(byte[] drawingCAD) {
        this.drawingCAD = drawingCAD;
        return this;
    }

    public byte[] getManufacturerSpecifications() {
        return manufacturerSpecifications;
    }

    public ConicalCansEntity setManufacturerSpecifications(byte[] manufacturerSpecifications) {
        this.manufacturerSpecifications = manufacturerSpecifications;
        return this;
    }

    public String getOther() {
        return other;
    }

    public ConicalCansEntity setOther(String other) {
        this.other = other;
        return this;
    }

    public String getSicken() {
        return sicken;
    }

    public ConicalCansEntity setSicken(String sicken) {
        this.sicken = sicken;
        return this;
    }

    public Double getOverallHeight() {
        return overallHeight;
    }

    public ConicalCansEntity setOverallHeight(Double overallHeight) {
        this.overallHeight = overallHeight;
        return this;
    }

    public Double getOverallHeightMax() {
        return overallHeightMax;
    }

    public ConicalCansEntity setOverallHeightMax(Double overallHeightMax) {
        this.overallHeightMax = overallHeightMax;
        return this;
    }

    public Double getOverallHeightMin() {
        return overallHeightMin;
    }

    public ConicalCansEntity setOverallHeightMin(Double overallHeightMin) {
        this.overallHeightMin = overallHeightMin;
        return this;
    }

    public Double getHullHeight() {
        return hullHeight;
    }

    public ConicalCansEntity setHullHeight(Double hullHeight) {
        this.hullHeight = hullHeight;
        return this;
    }

    public Double getHullHeightMax() {
        return hullHeightMax;
    }

    public ConicalCansEntity setHullHeightMax(Double hullHeightMax) {
        this.hullHeightMax = hullHeightMax;
        return this;
    }

    public Double getHullHeightMin() {
        return hullHeightMin;
    }

    public ConicalCansEntity setHullHeightMin(Double hullHeightMin) {
        this.hullHeightMin = hullHeightMin;
        return this;
    }

    public Double getInnerDiameterAbove() {
        return innerDiameterAbove;
    }

    public ConicalCansEntity setInnerDiameterAbove(Double innerDiameterAbove) {
        this.innerDiameterAbove = innerDiameterAbove;
        return this;
    }

    public Double getInnerDiameterAboveMin() {
        return innerDiameterAboveMin;
    }

    public ConicalCansEntity setInnerDiameterAboveMin(Double innerDiameterAboveMin) {
        this.innerDiameterAboveMin = innerDiameterAboveMin;
        return this;
    }

    public Double getInnerDiameterAboveMax() {
        return innerDiameterAboveMax;
    }

    public ConicalCansEntity setInnerDiameterAboveMax(Double innerDiameterAboveMax) {
        this.innerDiameterAboveMax = innerDiameterAboveMax;
        return this;
    }

    public Double getInnerDiameterBelow() {
        return innerDiameterBelow;
    }

    public ConicalCansEntity setInnerDiameterBelow(Double innerDiameterBelow) {
        this.innerDiameterBelow = innerDiameterBelow;
        return this;
    }

    public Double getInnerDiameterBelowMin() {
        return innerDiameterBelowMin;
    }

    public ConicalCansEntity setInnerDiameterBelowMin(Double innerDiameterBelowMin) {
        this.innerDiameterBelowMin = innerDiameterBelowMin;
        return this;
    }

    public Double getInnerDiameterBelowMax() {
        return innerDiameterBelowMax;
    }

    public ConicalCansEntity setInnerDiameterBelowMax(Double innerDiameterBelowMax) {
        this.innerDiameterBelowMax = innerDiameterBelowMax;
        return this;
    }

    public Double getHullWallThickness() {
        return hullWallThickness;
    }

    public ConicalCansEntity setHullWallThickness(Double hullWallThickness) {
        this.hullWallThickness = hullWallThickness;
        return this;
    }

    public Double getHullWallThicknessMin() {
        return hullWallThicknessMin;
    }

    public ConicalCansEntity setHullWallThicknessMin(Double hullWallThicknessMin) {
        this.hullWallThicknessMin = hullWallThicknessMin;
        return this;
    }

    public Double getHullWallThicknessMax() {
        return hullWallThicknessMax;
    }

    public ConicalCansEntity setHullWallThicknessMax(Double hullWallThicknessMax) {
        this.hullWallThicknessMax = hullWallThicknessMax;
        return this;
    }

    public Double getLidWallThickness() {
        return lidWallThickness;
    }

    public ConicalCansEntity setLidWallThickness(Double lidWallThickness) {
        this.lidWallThickness = lidWallThickness;
        return this;
    }

    public Double getLidWallThicknessMin() {
        return lidWallThicknessMin;
    }

    public ConicalCansEntity setLidWallThicknessMin(Double lidWallThicknessMin) {
        this.lidWallThicknessMin = lidWallThicknessMin;
        return this;
    }

    public Double getLidWallThicknessMax() {
        return lidWallThicknessMax;
    }

    public ConicalCansEntity setLidWallThicknessMax(Double lidWallThicknessMax) {
        this.lidWallThicknessMax = lidWallThicknessMax;
        return this;
    }

    public Double getGroundWallThickness() {
        return groundWallThickness;
    }

    public ConicalCansEntity setGroundWallThickness(Double groundWallThickness) {
        this.groundWallThickness = groundWallThickness;
        return this;
    }

    public Double getGroundWallThicknessMin() {
        return groundWallThicknessMin;
    }

    public ConicalCansEntity setGroundWallThicknessMin(Double groundWallThicknessMin) {
        this.groundWallThicknessMin = groundWallThicknessMin;
        return this;
    }

    public Double getGroundWallThicknessMax() {
        return groundWallThicknessMax;
    }

    public ConicalCansEntity setGroundWallThicknessMax(Double groundWallThicknessMax) {
        this.groundWallThicknessMax = groundWallThicknessMax;
        return this;
    }

    public Double getDiameterLid() {
        return diameterLid;
    }

    public ConicalCansEntity setDiameterLid(Double diameterLid) {
        this.diameterLid = diameterLid;
        return this;
    }

    public Double getDiameterLidMin() {
        return diameterLidMin;
    }

    public ConicalCansEntity setDiameterLidMin(Double diameterLidMin) {
        this.diameterLidMin = diameterLidMin;
        return this;
    }

    public Double getDiameterLidMax() {
        return diameterLidMax;
    }

    public ConicalCansEntity setDiameterLidMax(Double diameterLidMax) {
        this.diameterLidMax = diameterLidMax;
        return this;
    }

    public MaterialEntity getContainerMaterial() {
        return containerMaterial;
    }

    public ConicalCansEntity setContainerMaterial(MaterialEntity containerMaterial) {
        this.containerMaterial = containerMaterial;
        return this;
    }

    public ExZoneEntity getExZone() {
        return exZone;
    }

    public ConicalCansEntity setExZone(ExZoneEntity exZone) {
        this.exZone = exZone;
        return this;
    }

    public CoatingEntity getCoatingInside() {
        return coatingInside;
    }

    public ConicalCansEntity setCoatingInside(CoatingEntity coatingInside) {
        this.coatingInside = coatingInside;
        return this;
    }

    public CoatingEntity getCoatingOutside() {
        return coatingOutside;
    }

    public ConicalCansEntity setCoatingOutside(CoatingEntity coatingOutside) {
        this.coatingOutside = coatingOutside;
        return this;
    }

    public CoatingEntity getCoatingTank() {
        return coatingTank;
    }

    public ConicalCansEntity setCoatingTank(CoatingEntity coatingTank) {
        this.coatingTank = coatingTank;
        return this;
    }

    public ClosureEntity getHole() {
        return hole;
    }

    public ConicalCansEntity setHole(ClosureEntity hole) {
        this.hole = hole;
        return this;
    }

    public HandleEntity getHandle() {
        return handle;
    }

    public ConicalCansEntity setHandle(HandleEntity handle) {
        this.handle = handle;
        return this;
    }

    public String getDrawingFilename() {
        return drawingFilename;
    }

    public ConicalCansEntity setDrawingFilename(String drawingFilename) {
        this.drawingFilename = drawingFilename;
        return this;
    }

    public String getDrawingContentType() {
        return drawingContentType;
    }

    public ConicalCansEntity setDrawingContentType(String drawingContentType) {
        this.drawingContentType = drawingContentType;
        return this;
    }

    public String getDrawingCADFilename() {
        return drawingCADFilename;
    }

    public ConicalCansEntity setDrawingCADFilename(String drawingCADFilename) {
        this.drawingCADFilename = drawingCADFilename;
        return this;
    }

    public String getDrawingCADContentType() {
        return drawingCADContentType;
    }

    public ConicalCansEntity setDrawingCADContentType(String drawingCADContentType) {
        this.drawingCADContentType = drawingCADContentType;
        return this;
    }

    public String getManufacturerSpecificationsFilename() {
        return manufacturerSpecificationsFilename;
    }

    public ConicalCansEntity setManufacturerSpecificationsFilename(String manufacturerSpecificationsFilename) {
        this.manufacturerSpecificationsFilename = manufacturerSpecificationsFilename;
        return this;
    }

    public String getManufacturerSpecificationsContentType() {
        return manufacturerSpecificationsContentType;
    }

    public ConicalCansEntity setManufacturerSpecificationsContentType(String manufacturerSpecificationsContentType) {
        this.manufacturerSpecificationsContentType = manufacturerSpecificationsContentType;
        return this;
    }

    //endregion
}
