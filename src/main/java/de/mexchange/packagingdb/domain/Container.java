package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.ContainerStatus;
import de.mexchange.packagingdb.domain.lcp.Language;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Created by Arthur on 5/7/2016.
 */
public abstract class Container extends AbstractModel {

	protected String sapCode;

    protected String localCode;

    protected String globalCode;

    protected ContainerStatus status;

    protected Location location;

    protected BusinessUnit businessUnit;

	private Company company;

    @NotEmpty(message = "{err.msg.container.nominal.volume.required}")
    protected String nominalVolume;

    @NotEmpty(message = "{err.msg.container.designation.required}")
    protected String designation;

	protected List<ContainerInfo> infoList;

	protected List<ContainerPhoto> photos;

	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getLocalCode() {
		return localCode;
	}

	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}

	public String getGlobalCode() {
		return globalCode;
	}

	public void setGlobalCode(String globalCode) {
		this.globalCode = globalCode;
	}

	public ContainerStatus getStatus() {
		return status;
	}

	public void setStatus(ContainerStatus status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getNominalVolume() {
		return nominalVolume;
	}

	public void setNominalVolume(String nominalVolume) {
		this.nominalVolume = nominalVolume;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMaterialBody() {
		return "Material Body";
	}

	public String getDiameterBodyAtTop() {
		return "Diameter body at top";
	}

	public String getDiameterBodyAtBottom() {
		return "Diameter body at Bottom";
	}

	public String getHeightOfBody() {
		return "Height Of Body";
	}

	public String getDiameterClampRingOutside() {
		return "Diameter Clamp Ring Outside";
	}

	public String getCoatingInternal() {
		return "Coating Internal";
	}

	public String getCoatingExternal() {
		return "Coating External";
	}

	public String getHandelOfMaterial() {
		return "Handel Of Material";
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<ContainerInfo> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<ContainerInfo> infoList) {
		this.infoList = infoList;
	}

	public ContainerInfo getInfo(Language language) {
		if (CollectionUtils.isNotEmpty(infoList)) {
			for (ContainerInfo e : infoList) {
				if (e.getLanguage() == language) {
					return e;
				}
			}
		}
		return null;
	}

	public List<ContainerPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<ContainerPhoto> photos) {
		this.photos = photos;
	}

}
