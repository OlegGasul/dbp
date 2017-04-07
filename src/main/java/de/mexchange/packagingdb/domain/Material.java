package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.MaterialEntity;

import javax.validation.Valid;
import java.util.List;


/**
 * Data Transfer Object representing {@link MaterialEntity}
 */
public class Material extends AbstractModel implements Searchable{

    private String remarks;

    private AggregateState aggregateState;

    private MaterialInfo currentInfo;

    @Valid
    private List<MaterialInfo> infoList;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<MaterialInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<MaterialInfo> infoList) {
        this.infoList = infoList;
    }

    public AggregateState getAggregateState() {
        return aggregateState;
    }

    public void setAggregateState(AggregateState aggregateState) {
        this.aggregateState = aggregateState;
    }

    public MaterialInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(MaterialInfo currentInfo) {
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
