
package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Aggregate State entity class
 */
@Entity
@Table(name = "aggregate_state")
public class AggregateStateEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy="aggregateState", cascade = CascadeType.ALL)
    private Set<AggregateStateInfoEntity> infoList;

    /**
     * Initializes a new instance of the class.
     */
    public AggregateStateEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public AggregateStateEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<AggregateStateInfoEntity> getInfoList() {
        return infoList;
    }

    public AggregateStateEntity setInfoList(Set<AggregateStateInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>AggregateStateEntity</code> object.
     */
    @Override
    public String toString() {
        return "AggregateStateEntity{" +
                "id=" + id +
                ", infoList=" + infoList +
                '}';
    }
    // endregion
}
