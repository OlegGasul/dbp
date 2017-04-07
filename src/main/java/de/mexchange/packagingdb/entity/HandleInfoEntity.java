package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Handle Info entity class.
 */
@Entity
@Table(name = "handle_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"handle_id", "language_id"})
})
public class HandleInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="handle_id", foreignKey =  @ForeignKey(name="FK_handle"))
    private HandleEntity handle;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_handle_lang"))
    private LanguageEntity language;

    // Name
    @NotEmpty
    @Column(name = "name")
    private String name;

    /**
     * Initializes a new instance of the class.
     */
    public HandleInfoEntity() {
    }

    // region <GET/SET>
    /**
     * Gets the entity id (PK).
     *
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the entity ID.
     *
     * @param id
     * @return updated <code>BusinessUnitEntity</code> object.
     */
    public HandleInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the Handle Entity
     *
     * @return {@link #handle}
     */
    public HandleEntity getHandle() {
        return handle;
    }


    /**
     * Sets the Handle Enityt.
     *
     * @param handle
     * @return updated <code>HandleInfoEntity</code> object.
     */
    public HandleInfoEntity setHandle(HandleEntity handle) {
        this.handle = handle;
        return this;
    }

    /**
     * Gets the language
     *
     * @return {@link #language}
     */
    public LanguageEntity getLanguage() {
        return language;
    }

    /**
     * Sets the language
     *
     * @param language
     * @return updated <code>HandleInfoEntity</code> object.
     */
    public HandleInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    /**
     * Gets the Handle name
     *
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Handle Entity name
     *
     * @param name
     * @return updated <code>HandleInfoEntity</code> object.
     */
    public HandleInfoEntity setName(String name) {
        this.name = name;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>HandleInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "HandleInfoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
    // endregion
}
