package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Business Unit Info entity class.
 */
@Entity
@Table(name = "country_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"country_id", "language_id"})
})
public class CountryInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="country_id", foreignKey =  @ForeignKey(name="FK_country"))
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_country_lang"))
    private LanguageEntity language;

    @NotEmpty
    @Column(name = "name")
    private String name;

    /**
     * Initializes a new instance of the class.
     */
    public CountryInfoEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public CountryInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public CountryInfoEntity setCountry(CountryEntity country) {
        this.country = country;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public CountryInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryInfoEntity setName(String name) {
        this.name = name;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>CountryInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "CountryInfoEntity{" +
                "name='" + name + '\'' +
                ", language=" + language +
                ", id=" + id +
                '}';
    }
    // endregion

}
