package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.CompanyEntity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Data Transfer Object representing {@link CompanyEntity}
 */
public class Company extends AbstractModel implements Searchable {

    private List<CompanyCategory> companyCategories;

    private Address address;

    private String website;

    private String fax;

    private String phone;

    private String vendorNumber;

    @NotEmpty(message = "{err.field.company.name.required}")
    private String name;

    public List<CompanyCategory> getCompanyCategories() {
        return companyCategories;
    }

    public void setCompanyCategories(List<CompanyCategory> companyCategories) {
        this.companyCategories = companyCategories;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
