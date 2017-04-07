package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Company entity class.
 * Firmen
 */
@Entity
@Table(name = "company")
public class CompanyEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Firmenkategorie
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "company_category_map",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "company_category_id"))
    private Set<CompanyCategoryEntity> companyCategory;

    //Straße, Postleitzahl, Ort, Land
    //Note: address contains country ,road, place and zipCode
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", insertable = true, updatable = false, foreignKey = @ForeignKey(name = "FK_company_address"))
    private AddressEntity address;

    //Webadress
    @Column(name = "website")
    private String website;

    //Fax
    @Column(name = "fax")
    private String fax;

    //Telefon
    @Column(name = "phone")
    private String phone;

    //Kreditorennummer
    @Column(name = "vendor_number")
    private String vendorNumber;

    //company name
    @NotEmpty
    @Column(name = "name")
    private String name;

    /**
     * Initializes a new instance of the class.
     */
    public CompanyEntity() {
    }

    // region <GET/SET>
    public Long getId() {
        return id;
    }

    public CompanyEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<CompanyCategoryEntity> getCompanyCategory() {
        return companyCategory;
    }

    public CompanyEntity setCompanyCategory(Set<CompanyCategoryEntity> companyCategory) {
        this.companyCategory = companyCategory;
        return this;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public CompanyEntity setAddress(AddressEntity address) {
        this.address = address;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CompanyEntity setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getFax() {
        return fax;
    }

    public CompanyEntity setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CompanyEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getVendorNumber() {
        return vendorNumber;
    }

    public CompanyEntity setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompanyEntity setName(String name) {
        this.name = name;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>CompanyEntity</code> object.
     */
    @Override
    public String toString() {
        return "CompanyEntity{" +
                "name='" + name + '\'' +
                ", vendorNumber='" + vendorNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", website='" + website + '\'' +
                ", address=" + address +
                ", companyCategory=" + companyCategory +
                ", id=" + id +
                '}';
    }
    // endregion
}
