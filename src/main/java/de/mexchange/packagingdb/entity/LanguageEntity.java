package de.mexchange.packagingdb.entity;

import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.domain.lcp.Status;
import de.mexchange.packagingdb.entity.converter.StatusConverter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Language Entity class
 */
@Entity
@Table(name = "language")
public class LanguageEntity {

    public static final int MAX_SIZE_NAME = 50;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = MAX_SIZE_NAME)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "status_id", nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    /**
     * Initializes a new instance of the class.
     */
    public LanguageEntity() { }

    /**
     * Initializes a new instance of the class from Enum {@link LanguageEntity}.
     */
    public LanguageEntity(Language language) {
        this.id = language.getValue();
        this.name = language.name();
        this.status = language.getStatus();
    }

    // region <GET/SET>

    /**
     * Gets the entity id (PK).
     *
     * @return {@link #id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the entity ID.
     *
     * @param id
     * @return updated <code>LanguageEntity</code> object.
     */
    public void setId(Integer id) {
        if (id == null || id == 0) {
            this.id = null;
        } else {
            this.id = id;
        }
    }

    /**
     * Gets the entity name.
     *
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the entity ID.
     *
     * @param name
     * @return updated <code>LanguageEntity</code> object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
    * Gets the status.
    *
    * @return {@link #status}
    */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status
     * @return updated <code>LanguageEntity</code> object.
     */
    public LanguageEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    // endregion

    // region <OBJECT>

    @Override
    public String toString() {
        return "LanguageEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // endregion
}
