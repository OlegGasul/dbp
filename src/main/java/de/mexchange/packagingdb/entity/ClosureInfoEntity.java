package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;

/**
 * Closure Info entity class.
 */
@Entity
@Table(name = "closure_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"closure_id", "language_id"})
})
public class ClosureInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="closure_id", foreignKey =  @ForeignKey(name="FK_closure"))
    private ClosureEntity closure;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_closure_lang"))
    private LanguageEntity language;

    @NotEmpty
    @Column(name = "name")
    private String name;

    /**
     * Initializes a new instance of the class.
     */
    public ClosureInfoEntity() {
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
     * @return updated <code>ClosureInfoEntity</code> object.
     */
    public ClosureInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the Closure Entity
     *
     * @return {@link #closure}
     */
    public ClosureEntity getClosure() {
        return closure;
    }

    /**
     * Sets the Closure Entity.
     *
     * @param closure
     * @return updated <code>ClosureInfoEntity</code> object.
     */
    public ClosureInfoEntity setClosure(ClosureEntity closure) {
        this.closure = closure;
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
     * @return updated <code>ClosureInfoEntity</code> object.
     */
    public ClosureInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    /**
     * Gets the Closure name
     *
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Closure Entity name
     *
     * @param name
     * @return updated <code>ClosureInfoEntity</code> object.
     */
    public ClosureInfoEntity setName(String name) {
        this.name = name;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>ClosureInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "ClosureInfoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
    // endregion
}
