package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Coupling Info entity class.
 */
@Entity
@Table(name = "coupling_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"coupling_id", "language_id"})
})
public class CouplingInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="coupling_id", foreignKey =  @ForeignKey(name="FK_coupling"))
    private CouplingEntity coupling;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_coupling_lang"))
    private LanguageEntity language;

    // Kupplungsbeschreibung
    @NotEmpty
    @Column(name = "description")
    private String description;

    /**
     * Initializes a new instance of the class.
     */
    public CouplingInfoEntity() {}

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
     * @return updated <code>CouplingInfoEntity</code> object.
     */
    public CouplingInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the Coupling Info language
     *
     * @return {@link #language}
     */
    public LanguageEntity getLanguage() {
        return language;
    }

    /**
     * Sets the Coupling Info language
     *
     * @param language
     * @return updated <code>CouplingInfoEntity</code> object.
     */
    public CouplingInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public CouplingEntity getCoupling() {
        return coupling;
    }

    public void setCoupling(CouplingEntity coupling) {
        this.coupling = coupling;
    }

    /**
     * Gets the Coupling Info description
     *
     * @return {@link #description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the Coupling Info description
     *
     * @return {@link #description}
     */
    public CouplingInfoEntity setDescription(String description) {
        this.description = description;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>CouplingInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "CouplingInfoEntity{" +
                "id=" + id +
                ", description='" + description+ '\'' +
                ", language='" + language+ '\'' +
                '}';
    }
    // endregion
}
