package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Business Unit entity class.
 * Businessunit
 */
@Entity
@Table(name = "business_unit")
public class BusinessUnitEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@OneToMany(mappedBy="businessUnit", cascade = CascadeType.ALL)
    private Set<BusinessUnitInfoEntity> infoList;

    //Matchcode
    @Column(name = "match_code")
    private String matchCode;

    //Misc
    @Column(name = "misc")
    private String misc;

    /**
     * Initializes a new instance of the class.
     */
    public BusinessUnitEntity() {
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
    public BusinessUnitEntity setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the Business Unit list of info data
     *
     * @return {@link #infoList}
     */
    public Set<BusinessUnitInfoEntity> getInfoList() {
        return infoList;
    }

    /**
     * Sets the Business Unit list of info data
     *
     * @param infoList
     * @return updated <code>BusinessUnitEntity</code> object.
     */
    public BusinessUnitEntity setInfoList(Set<BusinessUnitInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    /**
     * Gets the Business Unit match code
     *
     * @return {@link #matchCode}
     */
    public String getMatchCode() {
        return matchCode;
    }

    /**
     * Sets the Business Unit match code.
     *
     * @param matchCode
     * @return updated <code>BusinessUnitEntity</code> object.
     */
    public BusinessUnitEntity setMatchCode(String matchCode) {
        this.matchCode = matchCode;
        return this;
    }

    /**
     * Gets the Business Unit match misc
     *
     * @return {@link #misc}
     */
    public String getMisc() {
        return misc;
    }

    /**
     * Sets the Business Unit Misc.
     *
     * @param misc
     * @return updated <code>BusinessUnitEntity</code> object.
     */
    public BusinessUnitEntity setMisc(String misc) {
        this.misc = misc;
        return this;
    }
    // endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>BusinessUnitEntity</code> object.
     */
    @Override
    public String toString() {
        return "BusinessUnitEntity{" +
                "id=" + id +
                ", infoList='" + infoList + '\'' +
                ", matchCode='" + matchCode + '\'' +
                ", misc='" + misc + '\'' +
                '}';
    }
    // endregion
}
