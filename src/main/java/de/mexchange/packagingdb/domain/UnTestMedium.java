package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.UnTestMediumEntity;

import javax.validation.Valid;
import java.util.List;

/**
 * Data UnTestMedium Object representing {@link UnTestMediumEntity}
 */
public class UnTestMedium extends AbstractModel implements Searchable {

    @Valid
    private List<UnTestMediumInfo> infoList;

    private UnTestMediumInfo currentInfo;

    private String code;

    private String others;

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public List<UnTestMediumInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<UnTestMediumInfo> infoList) {
        this.infoList = infoList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public UnTestMediumInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(UnTestMediumInfo currentInfo) {
        this.currentInfo = currentInfo;
    }

    @Override
    public String getName() {
        if (currentInfo != null) {
            return currentInfo.getMedium();
        }

        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0).getMedium();
        }

        return null;
    }
}