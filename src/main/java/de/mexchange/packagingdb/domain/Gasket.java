package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.GasketEntity;

import javax.validation.Valid;
import java.util.List;

/**
 * Data Transfer Object representing {@link GasketEntity}
 */
public class Gasket extends AbstractModel implements Searchable {

    private Double circumference;

    private Double thickness;

    private Material material;

    private String utilisation;

    @Valid
    private List<GasketInfo> infoList;

    private GasketInfo currentInfo;

    public Double getCircumference() {
        return circumference;
    }

    public void setCircumference(Double circumference) {
        this.circumference = circumference;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getUtilisation() {
        return utilisation;
    }

    public void setUtilisation(String utilisation) {
        this.utilisation = utilisation;
    }

    public List<GasketInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<GasketInfo> infoList) {
        this.infoList = infoList;
    }

    public GasketInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(GasketInfo currentInfo) {
        this.currentInfo = currentInfo;
    }

    @Override
    public String getName() {
        if (currentInfo != null) {
            return currentInfo.getDescription();
        }

        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0).getDescription();
        }

        return null;
    }
}
