package de.mexchange.packagingdb.domain;

import javax.validation.Valid;
import java.util.List;

/**
 * User: Garik
 * Date: 5/18/16
 * Time: 12:24 AM
 */
public class PackagingForm extends AbstractModel implements Searchable {

    private String code;

    private String others;

    @Valid
    private List<PackagingFormInfo> infoList;

    private PackagingFormInfo currentInfo;

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

    public List<PackagingFormInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<PackagingFormInfo> infoList) {
        this.infoList = infoList;
    }

    public PackagingFormInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(PackagingFormInfo currentInfo) {
        this.currentInfo = currentInfo;
    }

    @Override
    public String getName() {
        if (currentInfo != null) {
            return currentInfo.getForm();
        }

        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0).getForm();
        }

        return null;
    }
}
