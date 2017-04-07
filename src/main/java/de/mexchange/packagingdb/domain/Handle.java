package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.HandleEntity;

import javax.validation.Valid;
import java.util.List;

/**
 * Data Transfer Object representing {@link HandleEntity}
 */
public class Handle extends AbstractModel implements Searchable {

    private String remark;

    @Valid
    private List<HandleInfo> infoList;

    private HandleInfo currentInfo;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<HandleInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<HandleInfo> infoList) {
        this.infoList = infoList;
    }

    public HandleInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(HandleInfo currentInfo) {
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
