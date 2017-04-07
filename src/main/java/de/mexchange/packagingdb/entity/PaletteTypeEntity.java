
package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Palette Type Entity entity class
 * Plattentypen
 */
@Entity
@Table(name = "palette_type")
public class PaletteTypeEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy="paletteType", cascade = CascadeType.ALL)
    private Set<PaletteTypeInfoEntity> infoList;

    /**
     * Initializes a new instance of the class.
     */
    public PaletteTypeEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public PaletteTypeEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<PaletteTypeInfoEntity> getInfoList() {
        return infoList;
    }

    public PaletteTypeEntity setInfoList(Set<PaletteTypeInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>PaletteTypeEntity</code> object.
     */
    @Override
    public String toString() {
        return "PaletteTypeEntity{" +
                "id=" + id +
                ", infoList=" + infoList +
                '}';
    }
    // endregion
}
