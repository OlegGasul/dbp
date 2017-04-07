package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Test Medium Entity class
 * Prüfmedium
 */
@Entity
@Table(name = "test_medium")
public class UnTestMediumEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy="testMedium", cascade = CascadeType.ALL)
    private Set<UnTestMediumInfoEntity> infoList;

    //Code
    @Column(name = "code")
    private String code;

    //Sonstiges
    @Column(name = "others")
    private String others;

    /**
     * Initializes a new instance of the class.
     */
    public UnTestMediumEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public UnTestMediumEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<UnTestMediumInfoEntity> getInfoList() {
        return infoList;
    }

    public UnTestMediumEntity setInfoList(Set<UnTestMediumInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    public String getCode() {
        return code;
    }

    public UnTestMediumEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getOthers() {
        return others;
    }

    public UnTestMediumEntity setOthers(String others) {
        this.others = others;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>UnTestMediumEntity</code> object.
     */
    @Override
    public String toString() {
        return "UnTestMediumEntity{" +
                "id=" + id +
                ", infoList=" + infoList +
                ", code=" + code +
                ", others=" + others +
                '}';
    }
    // endregion
}
