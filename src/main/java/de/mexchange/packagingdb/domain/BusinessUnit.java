package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.BusinessUnitEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * Data Transfer Object representing {@link BusinessUnitEntity}
 */
public class BusinessUnit extends AbstractModel implements Searchable {

    @Valid
	private List<BusinessUnitInfo> infoList;

	private BusinessUnitInfo currentInfo;

	@NotEmpty(message = "{err.field.business.unit.match.code.required}")
	private String matchCode;

	private String misc;

	public List<BusinessUnitInfo> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<BusinessUnitInfo> infoList) {
		this.infoList = infoList;
	}

	public BusinessUnitInfo getCurrentInfo() {
		return currentInfo;
	}

	public void setCurrentInfo(BusinessUnitInfo currentInfo) {
		this.currentInfo = currentInfo;
	}

	public String getMatchCode() {
		return matchCode;
	}

	public void setMatchCode(String matchCode) {
		this.matchCode = matchCode;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	@Override
	public String getName() {
		if (currentInfo != null) {
			return currentInfo.getName();
		}

		if (infoList != null && infoList.size() > 0) {
			return infoList.get(0).getName();
		}

		return null;
	}
}
