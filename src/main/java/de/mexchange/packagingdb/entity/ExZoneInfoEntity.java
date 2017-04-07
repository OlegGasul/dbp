package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * ExZone Info entity class.
 */
@Entity
@Table(name = "ex_zone_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ex_zone_id", "language_id"})
})
public class ExZoneInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="ex_zone_id", foreignKey =  @ForeignKey(name="FK_ex_zone"))
    private ExZoneEntity exZone;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_ex_zone_lang"))
    private LanguageEntity language;

    @NotEmpty
    @Column(name = "name")
    private String name;

    /**
     * Initializes a new instance of the class.
     */
    public ExZoneInfoEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public ExZoneInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ExZoneEntity getExZone() {
        return exZone;
    }

    public ExZoneInfoEntity setExZone(ExZoneEntity exZone) {
        this.exZone = exZone;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public ExZoneInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExZoneInfoEntity setName(String name) {
        this.name = name;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>ExZoneInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "ExZoneInfoEntity{" +
                "id=" + id +
                ", language=" + language +
                ", name='" + name + '\'' +
                '}';
    }
    //endregion
}
