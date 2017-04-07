package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Material Info Entity entity class
 * GE: Werkstoffe
 */
@Entity
@Table(name = "material_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"material_id", "language_id"})
})
public class MaterialInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="material_id", foreignKey =  @ForeignKey(name="FK_material"))
    private MaterialEntity material;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_material_lang"))
    private LanguageEntity language;

    //Material Name
    @NotEmpty
    @Column(name = "name")
    private String name;

    //Kurzbezeichnung
    @NotEmpty
    @Column(name = "short_name")
    private String shortName;


    /**
     * Initializes a new instance of the class.
     */
    public MaterialInfoEntity() {}

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
     * @return updated <code>MaterialInfoEntity</code> object.
     */
    public MaterialInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the Material entity.
     *
     * @return {@link #material}
     */
    public MaterialEntity getMaterial() {
        return material;
    }

    /**
     * Sets the Material entity.
     *
     * @return {@link #material}
     */
    public MaterialInfoEntity setMaterial(MaterialEntity material) {
        this.material = material;
        return this;
    }

    /**
     * Gets the Material Info language
     *
     * @return {@link #language}
     */
    public LanguageEntity getLanguage() {
        return language;
    }

    /**
     * Sets the Material Info language
     *
     * @param language
     * @return updated <code>MaterialInfoEntity</code> object.
     */
    public MaterialInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    /**
     * Gets the Material Info name
     *
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Material Info name
     *
     * @return {@link #name}
     */
    public MaterialInfoEntity setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets the Material Info shortName
     *
     * @return {@link #shortName}
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the Material Info shortName
     *
     * @return {@link #shortName}
     */
    public MaterialInfoEntity setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }


    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>MaterialInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "MaterialInfoEntity{" +
                "id=" + id +
                ", name ='" + name + '\'' +
                ", shortName ='" + shortName + '\'' +
                ", language='" + language+ '\'' +
                '}';
    }
    // endregion
}
