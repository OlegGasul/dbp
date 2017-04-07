package de.mexchange.packagingdb.entity;

import javax.persistence.*;

/**
 * Coating Info entity class.
 */
@Entity
@Table(name = "coating_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"coating_id", "language_id"})
})
public class CoatingInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="coating_id", foreignKey =  @ForeignKey(name="FK_coating"))
    private CoatingEntity coating;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_coating_lang"))
    private LanguageEntity language;

    // Beschreibung
    @Column(name = "description")
    private String description;

    /**
     * Initializes a new instance of the class.
     */
    public CoatingInfoEntity() {
    }

    // region <GET/SET>
    public Long getId() {
        return id;
    }

    public CoatingInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public CoatingEntity getCoating() {
        return coating;
    }

    public CoatingInfoEntity setCoating(CoatingEntity coating) {
        this.coating = coating;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public CoatingInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CoatingInfoEntity setDescription(String description) {
        this.description = description;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>CoatingInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "CoatingInfoEntity{" +
                "id=" + id +
                ", language=" + language +
                ", description='" + description + '\'' +
                '}';
    }
    // endregion
}
