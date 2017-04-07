package de.mexchange.packagingdb.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Country entity class.
 */
@Entity
@Table(name = "country")
public class CountryEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Country multi language data
    @OneToMany(mappedBy="country", cascade = CascadeType.ALL)
    private Set<CountryInfoEntity> infoList;

    // match code
    @NotNull
    @Column(name = "match_code")
    private String matchCode;

    /**
     * Initializes a new instance of the class.
     */
    public CountryEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public CountryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<CountryInfoEntity> getInfoList() {
        return infoList;
    }

    public CountryEntity setInfoList(Set<CountryInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public CountryEntity setMatchCode(String matchCode) {
        this.matchCode = matchCode;
        return this;
    }
    // endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>CountryEntity</code> object.
     */
    @Override
    public String toString() {
        return "CountryEntity{" +
                "id=" + id +
                ", infoList=" + infoList +
                ", matchCode='" + matchCode + '\'' +
                '}';
    }
    //endregion
}
