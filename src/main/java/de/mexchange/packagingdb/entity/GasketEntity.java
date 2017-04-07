package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Gasket Entity entity class
 * GE: Dichtungen
 */
@Entity
@Table(name = "gasket")
public class GasketEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Umfang
    @Column(name = "circumference", columnDefinition="Double(6,2)")
    private Double circumference;

    //Dicke
    @Column(name = "thickness", columnDefinition="Double(6,2)")
    private Double thickness;

    //Material
    @ManyToOne
    @JoinColumn(name = "material_id", foreignKey =  @ForeignKey(name="FK_gasket_material"))
    private MaterialEntity material;

    //Verwendung
    @Column(name = "utilisation")
    private String utilisation;

    @OneToMany(mappedBy="gasket", cascade = CascadeType.ALL)
    private Set<GasketInfoEntity> infoList;


    /**
     * Initializes a new instance of the class.
     */
    public GasketEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public GasketEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getCircumference() {
        return circumference;
    }

    public GasketEntity setCircumference(Double circumference) {
        this.circumference = circumference;
        return this;
    }

    public Double getThickness() {
        return thickness;
    }

    public GasketEntity setThickness(Double thickness) {
        this.thickness = thickness;
        return this;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public GasketEntity setMaterial(MaterialEntity material) {
        this.material = material;
        return this;
    }

    public String getUtilisation() {
        return utilisation;
    }

    public GasketEntity setUtilisation(String utilisation) {
        this.utilisation = utilisation;
        return this;
    }

    public Set<GasketInfoEntity> getInfoList() {
        return infoList;
    }

    public GasketEntity setInfoList(Set<GasketInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>GasketEntity</code> object.
     */
    @Override
    public String toString() {
        return "GasketEntity{" +
                "id=" + id +
                ", circumference='" + circumference + '\'' +
                ", thickness='" + thickness + '\'' +
                ", material='" + material + '\'' +
                ", utilisation='" + utilisation + '\'' +
                ", infoList=" + infoList +
                '}';
    }
    // endregion
}
