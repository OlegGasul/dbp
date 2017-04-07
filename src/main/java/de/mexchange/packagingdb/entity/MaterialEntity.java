
package de.mexchange.packagingdb.entity;


import javax.persistence.*;
import java.util.Set;

/**
 * Material Entity entity class
 * GE: Werkstoffe
 */
@Entity
@Table(name = "material")
public class MaterialEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Bemerkungen
    @Column(name = "remarks")
    private String remarks;

    //Bemerkungen
    @Column(name = "match_code")
    private String matchCode;

    @OneToMany(mappedBy="material", cascade = CascadeType.ALL)
    private Set<MaterialInfoEntity> infoList;

    @ManyToOne
    @JoinColumn(name = "aggregate_state_id", foreignKey =  @ForeignKey(name="FK_material_aggregate_state"))
    private AggregateStateEntity aggregateState;

    /**
     * Initializes a new instance of the class.
     */
    public MaterialEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public MaterialEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public MaterialEntity setMatchCode(String matchCode) {
        this.matchCode = matchCode;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public MaterialEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public Set<MaterialInfoEntity> getInfoList() {
        return infoList;
    }

    public MaterialEntity setInfoList(Set<MaterialInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    public AggregateStateEntity getAggregateState() {
        return aggregateState;
    }

    public MaterialEntity setAggregateState(AggregateStateEntity aggregateState) {
        this.aggregateState = aggregateState;
        return this;
    }

    // endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>MaterialEntity</code> object.
     */
    @Override
    public String toString() {
        return "MaterialEntity{" +
                "id=" + id +
                ", remarks='" + remarks + '\'' +
                ", aggregateState='" + aggregateState + '\'' +
                ", infoList=" + infoList +
                '}';
    }
    // endregion

}
