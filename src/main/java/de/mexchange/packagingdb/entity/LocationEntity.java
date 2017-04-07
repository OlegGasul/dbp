
package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Location entity class.
 * standort
 */
@Entity
@Table(name = "location")
public class LocationEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Name
    @NotEmpty
    @Column(name = "name", unique = true)
    private String name;

    //SAP-Code
    @Column(name = "sap_code")
    private String sapCode;

    //Sonstiges
    @Column(name = "others")
    private String others;

    //Land, Straße, Ort, PLZ
    //Note: address contains country ,road, place and zipCode
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", insertable = true, updatable = true, foreignKey = @ForeignKey(name = "FK_location_address"))
    private AddressEntity address;

    /**
     * Initializes a new instance of the class.
     */
    public LocationEntity() {
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
     * @return updated <code>LocationEntity</code> object.
     */
    public LocationEntity setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the sapCode of this location
     *
     * @return {@link #sapCode}
     */
    public String getSapCode() {
        return sapCode;
    }

    /**
     * Sets the SAP-CODE
     *
     * @param sapCode
     * @return updated <code>LocationEntity</code> object.
     */
    public LocationEntity setSapCode(String sapCode) {
        this.sapCode = sapCode;
        return this;
    }

    /**
     * Gets the name of this location
     *
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the location name
     *
     * @param name
     * @return updated <code>LocationEntity</code> object.
     */
    public LocationEntity setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets the other information regarding to this location
     *
     * @return {@link #others}
     */
    public String getOthers() {
        return others;
    }

    /**
     * Sets other information about location
     *
     * @param others
     * @return updated <code>LocationEntity</code> object.
     */
    public LocationEntity setOthers(String others) {
        this.others = others;
        return this;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public LocationEntity setAddress(AddressEntity address) {
        this.address = address;
        return this;
    }

    // endregion

    // region <OBJECT>

    /**
     *
     * @return string representation of <code>LocationEntity</code> object.
     */
    @Override
    public String toString() {
        return "LocationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sapCode='" + sapCode + '\'' +
                ", others='" + others + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
    // endregion
}
