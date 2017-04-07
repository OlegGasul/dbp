package de.mexchange.packagingdb.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Coupling Entity entity class
 * GE: Kupplungen
 */
@Entity
@Table(name = "coupling")
public class CouplingEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Rohrdurchmesser
    @Column(name = "pipe_diameter")
    private Integer pipeDiameter;

    //Bild
    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_filename")
    private String imageFilename;

    @Column(name = "image_content_type")
    private String imageContentType;

    //Zeichnung
    @Lob
    @Column(name = "drawing")
    private byte[] drawing;

    @Column(name = "drawing_filename")
    private String drawingFilename;

    @Column(name = "drawing_content_type")
    private String drawingContentType;

    //Sonstiges
    @Column(name = "others")
    private String others;

    //Material
    @ManyToOne
    @JoinColumn(name = "material_id", insertable = true, updatable = false, foreignKey =  @ForeignKey(name="FK_coupling_material"))
    private MaterialEntity material;

    //Dichtungsmaterial
    @ManyToOne
    @JoinColumn(name = "gasket_id", insertable = true, updatable = false, foreignKey =  @ForeignKey(name="FK_coupling_gasket"))
    private GasketEntity gasket;

    @OneToMany(mappedBy="coupling", cascade = CascadeType.ALL)
    private Set<CouplingInfoEntity> infoList;

    /**
     * Initializes a new instance of the class.
     */
    public CouplingEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public CouplingEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPipeDiameter() {
        return pipeDiameter;
    }

    public CouplingEntity setPipeDiameter(Integer pipeDiameter) {
        this.pipeDiameter = pipeDiameter;
        return this;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
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

    public String getOthers() {
        return others;
    }

    public CouplingEntity setOthers(String others) {
        this.others = others;
        return this;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public CouplingEntity setMaterial(MaterialEntity material) {
        this.material = material;
        return this;
    }

    public GasketEntity getGasket() {
        return gasket;
    }

    public CouplingEntity setGasket(GasketEntity gasket) {
        this.gasket = gasket;
        return this;
    }

    public Set<CouplingInfoEntity> getInfoList() {
        return infoList;
    }

    public CouplingEntity setInfoList(Set<CouplingInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>CouplingEntity</code> object.
     */
    @Override
    public String toString() {
        return "CouplingEntity{" +
                "infoList=" + infoList +
                ", gasket=" + gasket +
                ", material=" + material +
                ", others='" + others + '\'' +
                ", drawing.length='" + (drawing != null ? drawing.length : null) + '\'' + ", image.length='"
                + (image != null ? image.length : null) + '\'' +
                ", pipeDiameter=" + pipeDiameter +
                ", id=" + id +
                '}';
    }
    // endregion
}
