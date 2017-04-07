package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Palette Type Info Entity class
 */
@Entity
@Table(name = "palette_type_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"palette_type_id", "language_id"})
})
public class PaletteTypeInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="palette_type_id", foreignKey =  @ForeignKey(name="FK_palette_type"))
    private PaletteTypeEntity paletteType;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_palette_type_lang"))
    private LanguageEntity language;

    //Palettentyp
    @NotEmpty
    @Column(name = "type")
    private String type;

    /**
     * Initializes a new instance of the class.
     */
    public PaletteTypeInfoEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public PaletteTypeInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public PaletteTypeEntity getPaletteType() {
        return paletteType;
    }

    public PaletteTypeInfoEntity setPaletteType(PaletteTypeEntity paletteType) {
        this.paletteType = paletteType;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public PaletteTypeInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public String getType() {
        return type;
    }

    public PaletteTypeInfoEntity setType(String type) {
        this.type = type;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>PaletteTypeInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "PaletteTypeInfoEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", language='" + language+ '\'' +
                '}';
    }
    // endregion
}
