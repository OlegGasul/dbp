package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Conical Canister Container Entity class
 * ConicalCanister
 * GE: Kanister Kubish
 */
@Entity
@Table(name = "conical_canister")
public class ConicalCanisterEntity extends ContainerEntity {

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
+?   Beschichtung Behälter
+    Bemerkungen GE
+    Bemerkungen GB
+    Hersteller Datenblatt
+    Fotos
+    Zeichnungsnummer
+    Zeichnung
+    Zeichnung CAD
+    Hähe äber alles
+    Hähe äber alles (+Tol)
+    Hähe äber alles (-Tol)
+    Breite
+    Breite (+Tol)
+    Breite (-Tol)
+    Tiefe
+    Tiefe (+Tol)
+    Tiefe (-Tol)
+    Wandstärke Behälter
+    Wandstärke Behälter (+Tol)
+    Wandstärke Behälter (-Tol)
+    Wandstärke Deckel
+    Wandstärke Deckel (+Tol)
+    Wandstärke Deckel (-Tol)
+    Wandstärke Boden
+    Wandstärke Boden (+Tol)
+    Wandstärke Boden (-Tol)
+    Gesamtgewicht
+    Gesamtgewicht (+Tol)
+    Gesamtgewicht (-Tol)
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
    @Column(name = "overflow_volume", columnDefinition = "Double(6,2)")
    private Double overflowVolume;

    //Gesamtgewicht
    @NotNull
    @Column(name = "total_weight", columnDefinition = "Double(6,2)")
    private Double totalWeight;

    //Gesamtgewicht (+Tol)
    @Column(name = "total_weight_max", columnDefinition = "Double(6,2)")
    private Double totalWeightMax;

    //Gesamtgewicht (-Tol)
    @Column(name = "total_weight_min", columnDefinition = "Double(6,2)")
    private Double totalWeightMin;

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
    @Column(name = "overall_height", columnDefinition = "Double(6,2)")
    private Double overallHeight;

    //Höhe über alles (+Tol)
    @Column(name = "overall_height_max", columnDefinition = "Double(6,2)")
    private Double overallHeightMax;

    //Höhe über alles (-Tol)
    @Column(name = "overall_height_min", columnDefinition = "Double(6,2)")
    private Double overallHeightMin;

    //Wandstärke Behälter
    @Column(name = "wall_thickness", columnDefinition = "Double(6,2)")
    private Double wallThicknessContainer;

    //Wandstärke Behälter (+Tol)
    @Column(name = "wall_thickness_max", columnDefinition = "Double(6,2)")
    private Double wallThicknessContainerMax;

    //Wandstärke Behälter (-Tol)
    @Column(name = "wall_thickness_min", columnDefinition = "Double(6,2)")
    private Double wallThicknessContainerMin;

    //Wandstärke Deckel
    @Column(name = "lid_wall_thickness", columnDefinition = "Double(6,2)")
    private Double lidWallThickness;

    //Wandstärke Deckel (-Tol)
    @Column(name = "lid_wall_thickness_min", columnDefinition = "Double(6,2)")
    private Double lidWallThicknessMin;

    //Wandstärke Deckel (+Tol)
    @Column(name = "lid_wall_thickness_max", columnDefinition = "Double(6,2)")
    private Double lidWallThicknessMax;

    //Wandstärke Boden
    @Column(name = "ground_wall_thickness", columnDefinition = "Double(6,2)")
    private Double groundWallThickness;

    //Wandstärke Boden (-Tol)
    @Column(name = "ground_wall_thickness_min", columnDefinition = "Double(6,2)")
    private Double groundWallThicknessMin;

    //Wandstärke Boden (+Tol)
    @Column(name = "ground_wall_thickness_max", columnDefinition = "Double(6,2)")
    private Double groundWallThicknessMax;

    //Breite
    @Column(name = "width", columnDefinition = "Double(6,2)")
    private Double width;

    //Breite (+Tol)
    @Column(name = "width_max", columnDefinition = "Double(6,2)")
    private Double widthMax;

    //Breite (-Tol)
    @Column(name = "width_min", columnDefinition = "Double(6,2)")
    private Double widthMin;

    //Tiefe
    @Column(name = "depth", columnDefinition = "Double(6,2)")
    private Double depth;

    //Tiefe (+Tol)
    @Column(name = "depth_max", columnDefinition = "Double(6,2)")
    private Double depthMax;

    //Tiefe (-Tol)
    @Column(name = "depth_min", columnDefinition = "Double(6,2)")
    private Double depthMin;

    // Behältermaterial
    @NotNull
    @ManyToOne
    @JoinColumn(name = "container_material_id")
    private MaterialEntity containerMaterial;

    // Beschichtung Behälter innen
    @ManyToOne
    @JoinColumn(name = "coating_inside_id")
    private CoatingEntity coatingInside;

    // Beschichtung Behälter außen
    @ManyToOne
    @JoinColumn(name = "coating_outside_id")
    private CoatingEntity coatingOutside;

    // ExZone
    @ManyToOne
    @JoinColumn(name = "ex_zone_id")
    private ExZoneEntity exZone;

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
    public ConicalCanisterEntity() {
    }

    // region <GET/SET>

    public String getUnCode() {
        return unCode;
    }

    public ConicalCanisterEntity setUnCode(String unCode) {
        this.unCode = unCode;
        return this;
    }

    public Double getOverflowVolume() {
        return overflowVolume;
    }

    public ConicalCanisterEntity setOverflowVolume(Double overflowVolume) {
        this.overflowVolume = overflowVolume;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public ConicalCanisterEntity setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public Double getTotalWeightMax() {
        return totalWeightMax;
    }

    public ConicalCanisterEntity setTotalWeightMax(Double totalWeightMax) {
        this.totalWeightMax = totalWeightMax;
        return this;
    }

    public Double getTotalWeightMin() {
        return totalWeightMin;
    }

    public ConicalCanisterEntity setTotalWeightMin(Double totalWeightMin) {
        this.totalWeightMin = totalWeightMin;
        return this;
    }

    public String getPermLock() {
        return permLock;
    }

    public ConicalCanisterEntity setPermLock(String permLock) {
        this.permLock = permLock;
        return this;
    }

    public String getEmbossing() {
        return embossing;
    }

    public ConicalCanisterEntity setEmbossing(String embossing) {
        this.embossing = embossing;
        return this;
    }

    public String getEmbossingPosition() {
        return embossingPosition;
    }

    public ConicalCanisterEntity setEmbossingPosition(String embossingPosition) {
        this.embossingPosition = embossingPosition;
        return this;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public ConicalCanisterEntity setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
        return this;
    }

    public byte[] getDrawing() {
        return drawing;
    }

    public ConicalCanisterEntity setDrawing(byte[] drawing) {
        this.drawing = drawing;
        return this;
    }

    public byte[] getDrawingCAD() {
        return drawingCAD;
    }

    public ConicalCanisterEntity setDrawingCAD(byte[] drawingCAD) {
        this.drawingCAD = drawingCAD;
        return this;
    }

    public byte[] getManufacturerSpecifications() {
        return manufacturerSpecifications;
    }

    public ConicalCanisterEntity setManufacturerSpecifications(byte[] manufacturerSpecifications) {
        this.manufacturerSpecifications = manufacturerSpecifications;
        return this;
    }

    public String getOther() {
        return other;
    }

    public ConicalCanisterEntity setOther(String other) {
        this.other = other;
        return this;
    }

    public String getSicken() {
        return sicken;
    }

    public ConicalCanisterEntity setSicken(String sicken) {
        this.sicken = sicken;
        return this;
    }

    public Double getOverallHeight() {
        return overallHeight;
    }

    public ConicalCanisterEntity setOverallHeight(Double overallHeight) {
        this.overallHeight = overallHeight;
        return this;
    }

    public Double getOverallHeightMax() {
        return overallHeightMax;
    }

    public ConicalCanisterEntity setOverallHeightMax(Double overallHeightMax) {
        this.overallHeightMax = overallHeightMax;
        return this;
    }

    public Double getOverallHeightMin() {
        return overallHeightMin;
    }

    public ConicalCanisterEntity setOverallHeightMin(Double overallHeightMin) {
        this.overallHeightMin = overallHeightMin;
        return this;
    }

    public Double getWallThicknessContainer() {
        return wallThicknessContainer;
    }

    public ConicalCanisterEntity setWallThicknessContainer(Double wallThicknessContainer) {
        this.wallThicknessContainer = wallThicknessContainer;
        return this;
    }

    public Double getWallThicknessContainerMax() {
        return wallThicknessContainerMax;
    }

    public ConicalCanisterEntity setWallThicknessContainerMax(Double wallThicknessContainerMax) {
        this.wallThicknessContainerMax = wallThicknessContainerMax;
        return this;
    }

    public Double getWallThicknessContainerMin() {
        return wallThicknessContainerMin;
    }

    public ConicalCanisterEntity setWallThicknessContainerMin(Double wallThicknessContainerMin) {
        this.wallThicknessContainerMin = wallThicknessContainerMin;
        return this;
    }

    public Double getLidWallThickness() {
        return lidWallThickness;
    }

    public ConicalCanisterEntity setLidWallThickness(Double lidWallThickness) {
        this.lidWallThickness = lidWallThickness;
        return this;
    }

    public Double getLidWallThicknessMin() {
        return lidWallThicknessMin;
    }

    public ConicalCanisterEntity setLidWallThicknessMin(Double lidWallThicknessMin) {
        this.lidWallThicknessMin = lidWallThicknessMin;
        return this;
    }

    public Double getLidWallThicknessMax() {
        return lidWallThicknessMax;
    }

    public ConicalCanisterEntity setLidWallThicknessMax(Double lidWallThicknessMax) {
        this.lidWallThicknessMax = lidWallThicknessMax;
        return this;
    }

    public Double getGroundWallThickness() {
        return groundWallThickness;
    }

    public ConicalCanisterEntity setGroundWallThickness(Double groundWallThickness) {
        this.groundWallThickness = groundWallThickness;
        return this;
    }

    public Double getGroundWallThicknessMin() {
        return groundWallThicknessMin;
    }

    public ConicalCanisterEntity setGroundWallThicknessMin(Double groundWallThicknessMin) {
        this.groundWallThicknessMin = groundWallThicknessMin;
        return this;
    }

    public Double getGroundWallThicknessMax() {
        return groundWallThicknessMax;
    }

    public ConicalCanisterEntity setGroundWallThicknessMax(Double groundWallThicknessMax) {
        this.groundWallThicknessMax = groundWallThicknessMax;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public ConicalCanisterEntity setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getWidthMax() {
        return widthMax;
    }

    public ConicalCanisterEntity setWidthMax(Double widthMax) {
        this.widthMax = widthMax;
        return this;
    }

    public Double getWidthMin() {
        return widthMin;
    }

    public ConicalCanisterEntity setWidthMin(Double widthMin) {
        this.widthMin = widthMin;
        return this;
    }

    public Double getDepth() {
        return depth;
    }

    public ConicalCanisterEntity setDepth(Double depth) {
        this.depth = depth;
        return this;
    }

    public Double getDepthMax() {
        return depthMax;
    }

    public ConicalCanisterEntity setDepthMax(Double depthMax) {
        this.depthMax = depthMax;
        return this;
    }

    public Double getDepthMin() {
        return depthMin;
    }

    public ConicalCanisterEntity setDepthMin(Double depthMin) {
        this.depthMin = depthMin;
        return this;
    }

    public MaterialEntity getContainerMaterial() {
        return containerMaterial;
    }

    public ConicalCanisterEntity setContainerMaterial(MaterialEntity containerMaterial) {
        this.containerMaterial = containerMaterial;
        return this;
    }

    public CoatingEntity getCoatingInside() {
        return coatingInside;
    }

    public ConicalCanisterEntity setCoatingInside(CoatingEntity coatingInside) {
        this.coatingInside = coatingInside;
        return this;
    }

    public CoatingEntity getCoatingOutside() {
        return coatingOutside;
    }

    public ConicalCanisterEntity setCoatingOutside(CoatingEntity coatingOutside) {
        this.coatingOutside = coatingOutside;
        return this;
    }

    public ExZoneEntity getExZone() {
        return exZone;
    }

    public ConicalCanisterEntity setExZone(ExZoneEntity exZone) {
        this.exZone = exZone;
        return this;
    }

    public ClosureEntity getHole() {
        return hole;
    }

    public ConicalCanisterEntity setHole(ClosureEntity hole) {
        this.hole = hole;
        return this;
    }

    public HandleEntity getHandle() {
        return handle;
    }

    public ConicalCanisterEntity setHandle(HandleEntity handle) {
        this.handle = handle;
        return this;
    }

    public String getDrawingFilename() {
        return drawingFilename;
    }

    public ConicalCanisterEntity setDrawingFilename(String drawingFilename) {
        this.drawingFilename = drawingFilename;
        return this;
    }

    public String getDrawingContentType() {
        return drawingContentType;
    }

    public ConicalCanisterEntity setDrawingContentType(String drawingContentType) {
        this.drawingContentType = drawingContentType;
        return this;
    }

    public String getDrawingCADFilename() {
        return drawingCADFilename;
    }

    public ConicalCanisterEntity setDrawingCADFilename(String drawingCADFilename) {
        this.drawingCADFilename = drawingCADFilename;
        return this;
    }

    public String getDrawingCADContentType() {
        return drawingCADContentType;
    }

    public ConicalCanisterEntity setDrawingCADContentType(String drawingCADContentType) {
        this.drawingCADContentType = drawingCADContentType;
        return this;
    }

    public String getManufacturerSpecificationsFilename() {
        return manufacturerSpecificationsFilename;
    }

    public ConicalCanisterEntity setManufacturerSpecificationsFilename(String manufacturerSpecificationsFilename) {
        this.manufacturerSpecificationsFilename = manufacturerSpecificationsFilename;
        return this;
    }

    public String getManufacturerSpecificationsContentType() {
        return manufacturerSpecificationsContentType;
    }

    public ConicalCanisterEntity setManufacturerSpecificationsContentType(String manufacturerSpecificationsContentType) {
        this.manufacturerSpecificationsContentType = manufacturerSpecificationsContentType;
        return this;
    }

    //endregion
}