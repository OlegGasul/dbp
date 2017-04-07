package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.ContainerStatus;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.CoatingInfoEntity;
import de.mexchange.packagingdb.entity.ContainerEntity;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Created by Arthur on 5/7/2016.
 *  Data Transfer Object representing {@link ContainerEntity}
 */
public class CommonContainer {

	//VPS Number (container ID)
	protected Long vpsNumber;

    protected String nominalVolume;

	// Container Name (N/A)
    protected String name;

	// Container Type
	protected String packagingForm;

	// filename (first Photo id)
	protected Long fileId;

	// filename (first Photo filename)
	protected String fileName;

	// approval (N/A)
	protected String approval;

	// company Name
	protected String manufacturer;

	public Long getVpsNumber() {
		return vpsNumber;
	}

	public void setVpsNumber(Long vpsNumber) {
		this.vpsNumber = vpsNumber;
	}

	public String getNominalVolume() {
		return nominalVolume;
	}

	public void setNominalVolume(String nominalVolume) {
		this.nominalVolume = nominalVolume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackagingForm() {
		return packagingForm;
	}

	public void setPackagingForm(String packagingForm) {
		this.packagingForm = packagingForm;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


}
