package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Closure Entity entity class
 * GE: verschluesse
 */
@Entity
@Table(name = "closure")
public class ClosureEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Verschluß Durchmesser
    @Column(name = "diameter", columnDefinition="Double(6,2)")
    private Double diameter;

    //Beschichtung innen
    @ManyToOne
    @JoinColumn(name = "inside_coating_id", foreignKey =  @ForeignKey(name="FK_closure_inside_coating"))
    private CoatingEntity insideCoating;

    //Beschichtung außen
    @ManyToOne
    @JoinColumn(name = "outside_coating_id", foreignKey =  @ForeignKey(name="FK_closure_outside_coating"))
    private CoatingEntity outsideCoating;

    //Material Verschluß
    @ManyToOne
    @JoinColumn(name = "material_id", foreignKey =  @ForeignKey(name="FK_closure_material"))
    private MaterialEntity material;

    //Dichtung Verschluß
    @ManyToOne
    @JoinColumn(name = "gasket_id", foreignKey =  @ForeignKey(name="FK_closure_gasket"))
    private GasketEntity gasket;

    //Bemerkungen
    @Column(name = "remarks")
    private String remarks;

    @OneToMany(mappedBy="closure", cascade = CascadeType.ALL)
    private Set<ClosureInfoEntity> infoList;

    /**
     * Initializes a new instance of the class.
     */
    public ClosureEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public ClosureEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getDiameter() {
        return diameter;
    }

    public ClosureEntity setDiameter(Double diameter) {
        this.diameter = diameter;
        return this;
    }

    public CoatingEntity getInsideCoating() {
        return insideCoating;
    }

    public ClosureEntity setInsideCoating(CoatingEntity insideCoating) {
        this.insideCoating = insideCoating;
        return this;
    }

    public CoatingEntity getOutsideCoating() {
        return outsideCoating;
    }

    public ClosureEntity setOutsideCoating(CoatingEntity outsideCoating) {
        this.outsideCoating = outsideCoating;
        return this;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public ClosureEntity setMaterial(MaterialEntity material) {
        this.material = material;
        return this;
    }

    public GasketEntity getGasket() {
        return gasket;
    }

    public ClosureEntity setGasket(GasketEntity gasket) {
        this.gasket = gasket;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public ClosureEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public Set<ClosureInfoEntity> getInfoList() {
        return infoList;
    }

    public ClosureEntity setInfoList(Set<ClosureInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>ClosureEntity</code> object.
     */
    @Override
    public String toString() {
        return "ClosureEntity{" +
                "infoList=" + infoList +
                ", remarks='" + remarks + '\'' +
                ", gasket=" + gasket +
                ", material=" + material +
                ", outsideCoating=" + outsideCoating +
                ", insideCoating=" + insideCoating +
                ", diameter=" + diameter +
                ", id=" + id +
                '}';
    }
    //endregion
}
