package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Ex Zone entity class.
 * GE: exzone
 */
@Entity
@Table(name = "ex_zone")
public class ExZoneEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy="exZone", cascade = CascadeType.ALL)
    private Set<ExZoneInfoEntity> infoList;

    @NotNull
    @Column(name = "ex_zone_number")
    private String exZoneNumber;


    @Column(name = "other")
    private String other;

    /**
     * Initializes a new instance of the class.
     */
    public ExZoneEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public ExZoneEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<ExZoneInfoEntity> getInfoList() {
        return infoList;
    }

    public ExZoneEntity setInfoList(Set<ExZoneInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    public String getExZoneNumber() {
        return exZoneNumber;
    }

    public ExZoneEntity setExZoneNumber(String exZoneNumber) {
        this.exZoneNumber = exZoneNumber;
        return this;
    }

    public String getOther() {
        return other;
    }

    public ExZoneEntity setOther(String other) {
        this.other = other;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>ExZoneEntity</code> object.
     */
    @Override
    public String toString() {
        return "ExZoneEntity{" +
                "other='" + other + '\'' +
                ", exZoneNumber='" + exZoneNumber + '\'' +
                ", infoList=" + infoList +
                ", id=" + id +
                '}';
    }
    //endregion
}
