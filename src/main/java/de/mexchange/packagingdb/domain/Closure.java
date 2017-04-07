package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.ClosureEntity;

import javax.validation.Valid;
import java.util.List;

/**
 * Data Transfer Object representing {@link ClosureEntity}
 */
public class Closure extends AbstractModel implements Searchable{

    private Double diameter;

    private String remarks;

    @Valid
    private List<ClosureInfo> infoList;

    private ClosureInfo currentInfo;

    private Coating insideCoating;

    private Coating outsideCoating;

    private Material material;

    private Gasket gasket;

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<ClosureInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<ClosureInfo> infoList) {
        this.infoList = infoList;
    }

    public Coating getInsideCoating() {
        return insideCoating;
    }

    public void setInsideCoating(Coating insideCoating) {
        this.insideCoating = insideCoating;
    }

    public Coating getOutsideCoating() {
        return outsideCoating;
    }

    public void setOutsideCoating(Coating outsideCoating) {
        this.outsideCoating = outsideCoating;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Gasket getGasket() {
        return gasket;
    }

    public void setGasket(Gasket gasket) {
        this.gasket = gasket;
    }

    public ClosureInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(ClosureInfo currentInfo) {
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
