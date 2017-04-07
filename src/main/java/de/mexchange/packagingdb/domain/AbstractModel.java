package de.mexchange.packagingdb.domain;

import java.util.Date;

/**
 * Base class for all transfer (DTO) objects
 */
public abstract class AbstractModel {

	protected Long id;

	private Date dateCreated;

	private User createdBy;

	private Date dateModified;

	private User modifiedBy;

    /** Default formatting pattern for Date fields of DTO objects */
    protected static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:MM:00'Z'";

	public Long getId() {
		return id;
	}

	public AbstractModel setId(Long id) {
		this.id = id;
		return this;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
