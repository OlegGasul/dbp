package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Superclass for all entities.
 */
@MappedSuperclass
public abstract class AbstractEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datecreated")
    private Date dateCreated;

    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", insertable = true, updatable = false)
    private UserEntity createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datemodified")
    private Date dateModified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by", insertable = true, updatable = true)
    private UserEntity modifiedBy;

    @Version
    @Column(name = "version")
    private int version;

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public UserEntity getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserEntity modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @PrePersist
    protected void prePersist() {
        dateCreated = new Date();
        dateModified = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        dateModified = new Date();
    }
}
