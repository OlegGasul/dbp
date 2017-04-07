package de.mexchange.packagingdb.domain;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Garik on 5/8/16.
 */
public class Country extends AbstractModel implements Searchable {

    @Valid
	private List<CountryInfo> infoList;

	private String matchCode;

	private CountryInfo currentInfo;

	public List<CountryInfo> getInfoList() {
		return infoList;
	}

	public Country setInfoList(List<CountryInfo> infoList) {
		this.infoList = infoList;
		return this;
	}

	public String getMatchCode() {
		return matchCode;
	}

	public Country setMatchCode(String matchCode) {
		this.matchCode = matchCode;
		return this;
	}

	public CountryInfo getCurrentInfo() {
		return currentInfo;
	}

	public void setCurrentInfo(CountryInfo currentInfo) {
		this.currentInfo = currentInfo;
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
