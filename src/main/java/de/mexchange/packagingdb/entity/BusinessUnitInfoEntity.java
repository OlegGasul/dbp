package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Business Unit Info entity class.
 */
@Entity
@Table(name = "business_unit_info", uniqueConstraints = {
      @UniqueConstraint(columnNames = {"business_unit_id", "language_id"})
})
public class BusinessUnitInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@ManyToOne
	@JoinColumn(name="business_unit_id", foreignKey =  @ForeignKey(name="FK_business_unit"))
	private BusinessUnitEntity businessUnit;

    @NotNull
    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_business_unit_lang"))
    private LanguageEntity language;

    @NotEmpty
    @Column(name = "name")
    private String name;

    /**
     * Initializes a new instance of the class.
     */
    public BusinessUnitInfoEntity() {}

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
    public BusinessUnitInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the businessUnit entity.
     *
     * @return {@link #businessUnit}
     */
    public BusinessUnitEntity getBusinessUnit() {
        return businessUnit;
    }

    /**
     * Sets the businessUnit entity.
     *
     * @param businessUnit
     * @return updated <code>BusinessUnitInfoEntity</code> object.
     */
    public BusinessUnitInfoEntity setBusinessUnit(BusinessUnitEntity businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    /**
     * Gets the Business Unit language
     *
     * @return {@link #language}
     */
    public LanguageEntity getLanguage() {
        return language;
    }

    /**
     * Sets the Business Unit language
     *
     * @param language
     * @return updated <code>BusinessUnitInfoEntity</code> object.
     */
    public BusinessUnitInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    /**
     * Gets the Business Unit name
     *
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Business Unit name
     *
     * @param name
     * @return updated <code>LocationEntity</code> object.
     */
    public BusinessUnitInfoEntity setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                '}';
    }
    // endregion
}
