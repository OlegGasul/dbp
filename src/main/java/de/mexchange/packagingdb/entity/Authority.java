package de.mexchange.packagingdb.entity;

import de.mexchange.packagingdb.domain.lcp.UserRole;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Authority Entity class
 */
@Entity
@Table(name = "role")
public class Authority {
    public static final int MAX_SIZE_NAME = 50;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = MAX_SIZE_NAME)
    @Column(name = "name")
    private String name;

    /**
     * Initializes a new instance of the class.
     */
    public Authority() { }

    /**
     * Initializes a new instance of the class from Enum {@link UserRole}.
     */
    public Authority(UserRole role) {
        this.id = role.getValue();
        this.name = role.name();
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
     * @return updated <code>Authority</code> object.
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
     * @return updated <code>Authority</code> object.
     */
    public void setName(String name) {
        this.name = name;
    }

    // endregion

    // region <OBJECT>

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // endregion

}