package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.CompanyCategoryEntity;

import javax.validation.Valid;
import java.util.List;

/**
 * Data Transfer Object representing {@link CompanyCategoryEntity}
 */
public class CompanyCategory extends AbstractModel implements Searchable{

    @Valid
    private List<CompanyCategoryInfo> infoList;

    private CompanyCategoryInfo currentInfo;

    private String remarks;

    public List<CompanyCategoryInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<CompanyCategoryInfo> infoList) {
        this.infoList = infoList;
    }

    public CompanyCategoryInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(CompanyCategoryInfo currentInfo) {
        this.currentInfo = currentInfo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
