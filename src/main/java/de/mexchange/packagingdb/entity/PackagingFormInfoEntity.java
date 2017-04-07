package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;

/**
 * Packaging Form Info Entity class
 */
@Entity
@Table(name = "packaging_form_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"packaging_form_id", "language_id"})
})
public class PackagingFormInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="packaging_form_id", foreignKey =  @ForeignKey(name="FK_packaging_form"))
    private PackagingFormEntity packagingForm;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_packaging_form_lang"))
    private LanguageEntity language;

    @NotEmpty
    @Column(name = "form")
    private String form;

    /**
     * Initializes a new instance of the class.
     */
    public PackagingFormInfoEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public PackagingFormInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public PackagingFormEntity getPackagingForm() {
        return packagingForm;
    }

    public PackagingFormInfoEntity setPackagingForm(PackagingFormEntity packagingForm) {
        this.packagingForm = packagingForm;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public PackagingFormInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public String getForm() {
        return form;
    }

    public PackagingFormInfoEntity setForm(String form) {
        this.form = form;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>PackagingFormInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "PackagingFormInfoEntity{" +
                "id=" + id +
                ", form='" + form + '\'' +
                ", language='" + language+ '\'' +
                '}';
    }
    // endregion
}
