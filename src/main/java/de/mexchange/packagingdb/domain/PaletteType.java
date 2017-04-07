
package de.mexchange.packagingdb.domain;

import javax.validation.Valid;
import java.util.List;

public class PaletteType extends AbstractModel implements Searchable {

    @Valid
    private List<PaletteTypeInfo> infoList;

    private PaletteTypeInfo currentInfo;

    public List<PaletteTypeInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<PaletteTypeInfo> infoList) {
        this.infoList = infoList;
    }

    public PaletteTypeInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(PaletteTypeInfo currentInfo) {
        this.currentInfo = currentInfo;
    }

    @Override
    public String getName() {
        if (currentInfo != null) {
            return currentInfo.getType();
        }

        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0).getType();
        }

        return null;
    }
}
