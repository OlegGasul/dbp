package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Packaging Form Entity class
 */
@Entity
@Table(name = "packaging_form")
public class PackagingFormEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy="packagingForm", cascade = CascadeType.ALL)
    private Set<PackagingFormInfoEntity> infoList;

    //Code
    @Column(name = "code")
    private String code;

    //Sonstiges
    @Column(name = "others")
    private String others;

    /**
     * Initializes a new instance of the class.
     */
    public PackagingFormEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public PackagingFormEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<PackagingFormInfoEntity> getInfoList() {
        return infoList;
    }

    public PackagingFormEntity setInfoList(Set<PackagingFormInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    public String getCode() {
        return code;
    }

    public PackagingFormEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getOthers() {
        return others;
    }

    public PackagingFormEntity setOthers(String others) {
        this.others = others;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>PackagingFormEntity</code> object.
     */
    @Override
    public String toString() {
        return "PackagingFormEntity{" +
                "id=" + id +
                ", infoList=" + infoList +
                ", code=" + code +
                ", others=" + others +
                '}';
    }
    // endregion
}
