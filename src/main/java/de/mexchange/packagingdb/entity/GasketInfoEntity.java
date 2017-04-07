package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Gasket Info entity class.
 */
@Entity
@Table(name = "gasket_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"gasket_id", "language_id"})
})
public class GasketInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="gasket_id", foreignKey =  @ForeignKey(name="FK_gasket"))
    private GasketEntity gasket;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_gasket_lang"))
    private LanguageEntity language;

    // Beschreibung
    @NotEmpty
    @Column(name = "description")
    private String description;

    /**
     * Initializes a new instance of the class.
     */
    public GasketInfoEntity() {}

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
     * @return updated <code>GasketInfoEntity</code> object.
     */
    public GasketInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the Gasket entity.
     *
     * @return {@link #gasket}
     */
    public GasketEntity getGasket() {
        return gasket;
    }

    /**
     * Sets the Gasket entity.
     *
     * @return {@link #gasket}
     */
    public GasketInfoEntity setGasket(GasketEntity gasket) {
        this.gasket = gasket;
        return this;
    }

    /**
     * Gets the Gasket Info language
     *
     * @return {@link #language}
     */
    public LanguageEntity getLanguage() {
        return language;
    }

    /**
     * Sets the Gasket Info language
     *
     * @param language
     * @return updated <code>GasketInfoEntity</code> object.
     */
    public GasketInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    /**
     * Gets the Gasket Info description
     *
     * @return {@link #description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the Gasket Info description
     *
     * @return {@link #description}
     */
    public GasketInfoEntity setDescription(String description) {
        this.description = description;
        return this;
    }


    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>GasketInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "GasketInfoEntity{" +
                "id=" + id +
                ", description='" + description+ '\'' +
                ", language='" + language+ '\'' +
                '}';
    }
    // endregion
}
