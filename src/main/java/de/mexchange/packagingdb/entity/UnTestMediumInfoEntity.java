package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Test Medium Info Entity class
 */
@Entity
@Table(name = "test_medium_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"test_medium_id", "language_id"})
})
public class UnTestMediumInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="test_medium_id", foreignKey =  @ForeignKey(name="FK_test_medium"))
    private UnTestMediumEntity testMedium;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_test_medium_lang"))
    private LanguageEntity language;

    //Medium
    @NotEmpty
    @Column(name = "medium")
    private String medium;

    /**
     * Initializes a new instance of the class.
     */
    public UnTestMediumInfoEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public UnTestMediumInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UnTestMediumEntity getTestMedium() {
        return testMedium;
    }

    public UnTestMediumInfoEntity setTestMedium(UnTestMediumEntity testMedium) {
        this.testMedium = testMedium;
        return this;
    }

    public String getMedium() {
        return medium;
    }

    public UnTestMediumInfoEntity setMedium(String medium) {
        this.medium = medium;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public UnTestMediumInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>UnTestMediumInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "UnTestMediumInfoEntity{" +
                "id=" + id +
                ", medium='" + medium + '\'' +
                ", language='" + language+ '\'' +
                '}';
    }
    // endregion
}
