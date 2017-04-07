package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Handle entity class.
 * GE: Griffe
 */
@Entity
@Table(name = "handle")
public class HandleEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy="handle", cascade = CascadeType.ALL)
    private Set<HandleInfoEntity> infoList;

    //Bemerkung
    @Column(name = "remark")
    private String remark;

    /**
     * Initializes a new instance of the class.
     */
    public HandleEntity() {
    }

    // region <GET/SET>
    /**
     * Gets the entity id (PK).
     *
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the entity ID.
     *
     * @param id
     * @return updated <code>BusinessUnitEntity</code> object.
     */
    public HandleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the HandleInfo list of info data
     *
     * @return {@link #infoList}
     */
    public Set<HandleInfoEntity> getInfoList() {
        return infoList;
    }

    /**
     * Sets the HandleInfo list of info data
     *
     * @param infoList
     * @return updated <code>BusinessUnitEntity</code> object.
     */
    public HandleEntity setInfoList(Set<HandleInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    /**
     * Gets the Handle remark
     *
     * @return {@link #remark}
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the Handle remark.
     *
     * @param remark
     * @return updated <code>HandleEntity</code> object.
     */
    public HandleEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    // endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>HandleEntity</code> object.
     */
    @Override
    public String toString() {
        return "HandleEntity{" +
                "id=" + id +
                ", infoList=" + infoList +
                ", remark='" + remark + '\'' +
                '}';
    }
    //endregion
}
