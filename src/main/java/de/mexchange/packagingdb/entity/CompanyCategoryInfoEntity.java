package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Company Category Info entity class.
 */
@Entity
@Table(name = "company_category_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"company_category_id", "language_id"})
})
public class CompanyCategoryInfoEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="company_category_id", foreignKey =  @ForeignKey(name="FK_company_category"))
    private CompanyCategoryEntity companyCategory;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_company_category_lang"))
    private LanguageEntity language;

    // company category name
    @NotEmpty
    @Column(name = "name")
    private String name;

    /**
     * Initializes a new instance of the class.
     */
    public CompanyCategoryInfoEntity() {
    }

    // region <GET/SET>
    public Long getId() {
        return id;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public CompanyCategoryEntity getCompanyCategory() {
        return companyCategory;
    }

    public CompanyCategoryInfoEntity setCompanyCategory(CompanyCategoryEntity companyCategory) {
        this.companyCategory = companyCategory;
        return this;
    }

    public CompanyCategoryInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public CompanyCategoryInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompanyCategoryInfoEntity setName(String name) {
        this.name = name;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>CompanyCategoryInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "CompanyCategoryInfoEntity{" +
                "id=" + id +
                ", language=" + language +
                ", name='" + name+ '\'' +
                '}';
    }
    //endregion
}
