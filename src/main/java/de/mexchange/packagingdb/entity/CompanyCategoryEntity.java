package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Company Category entity class.
 */
@Entity
@Table(name = "company_category")
public class CompanyCategoryEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // //Bemerkungen
    @Column(name = "remarks")
    private String remarks;

    @OneToMany(mappedBy="companyCategory", cascade = CascadeType.ALL)
    private Set<CompanyCategoryInfoEntity> infoList;

    /**
     * Initializes a new instance of the class.
     */
    public CompanyCategoryEntity() {
    }

    // region <GET/SET>
    public Long getId() {
        return id;
    }

    public CompanyCategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public CompanyCategoryEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public Set<CompanyCategoryInfoEntity> getInfoList() {
        return infoList;
    }

    public CompanyCategoryEntity setInfoList(Set<CompanyCategoryInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>CompanyCategoryEntity</code> object.
     */
    @Override
    public String toString() {
        return "CompanyCategoryEntity{" +
                "id=" + id +
                ", remarks='" + remarks+ '\'' +
                ", infoList='" + infoList + '\'' +
                '}';
    }
    //endregion
}
