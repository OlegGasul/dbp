package de.mexchange.packagingdb.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

public class ExZone extends AbstractModel implements Searchable {

    @Valid
    private List<ExZoneInfo> infoList;

    private ExZoneInfo currentInfo;

    @NotEmpty(message = "{err.field.exzone.number.required}")
    private String exZoneNumber;

    private String other;

    public List<ExZoneInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<ExZoneInfo> infoList) {
        this.infoList = infoList;
    }

    public ExZoneInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(ExZoneInfo currentInfo) {
        this.currentInfo = currentInfo;
    }

    public String getExZoneNumber() {
        return exZoneNumber;
    }

    public void setExZoneNumber(String exZoneNumber) {
        this.exZoneNumber = exZoneNumber;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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
