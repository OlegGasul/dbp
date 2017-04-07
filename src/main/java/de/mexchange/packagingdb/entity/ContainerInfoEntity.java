package de.mexchange.packagingdb.entity;

import javax.persistence.*;

/**
 * Cubic Info Container Entity class
 * IBC_Cubic
 */
@Entity
@Table(name = "container_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"container_id", "language_id"})
})
public class ContainerInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="container_id")
    private ContainerEntity container;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_cubic_lang"))
    private LanguageEntity language;

    //Bemerkungen
    @Column(name = "comments")
    private String comments;

    /**
     * Initializes a new instance of the class.
     */
    public ContainerInfoEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public ContainerInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ContainerEntity getContainer() {
        return container;
    }

    public ContainerInfoEntity setContainer(ContainerEntity container) {
        this.container = container;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public ContainerInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public ContainerInfoEntity setComments(String comments) {
        this.comments = comments;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>CubicInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "CubicInfoEntity{" +
                "comments='" + comments + '\'' +
                ", language=" + language +
                ", id=" + id +
                '}';
    }
    //endregion

}
