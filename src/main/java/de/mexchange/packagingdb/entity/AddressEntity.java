package de.mexchange.packagingdb.entity;

import javax.persistence.*;

/**
 * Company entity class.
 */
@Entity
@Table(name = "address")
public class AddressEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id", insertable = true, updatable = true, foreignKey =  @ForeignKey(name="FK_address_country"))
    private CountryEntity country;

    @Column(name = "street")
    private String street;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    /**
     * Initializes a new instance of the class.
     */
    public AddressEntity() {
    }

    // region <GET/SET>
    public Long getId() {
        return id;
    }

    public AddressEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public AddressEntity setCountry(CountryEntity country) {
        this.country = country;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public AddressEntity setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressEntity setCity(String city) {
        this.city = city;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>AddressEntity</code> object.
     */
    @Override
    public String toString() {
        return "AddressEntity{" +
                "city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", country=" + country +
                ", id=" + id +
                '}';
    }
    // endregion
}
