package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.AggregateStateEntity;

import javax.validation.Valid;
import java.util.List;

/**
 * Data Transfer Object representing {@link AggregateStateEntity}
 */
public class AggregateState extends AbstractModel implements Searchable{

    @Valid
    private List<AggregateStateInfo> infoList;

    private AggregateStateInfo currentInfo;

    public List<AggregateStateInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<AggregateStateInfo> infoList) {
        this.infoList = infoList;
    }

    public AggregateStateInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(AggregateStateInfo currentInfo) {
        this.currentInfo = currentInfo;
    }

    @Override
    public String getName() {
        if (currentInfo != null) {
            return currentInfo.getState();
        }

        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0).getState();
        }

        return null;
    }
}
