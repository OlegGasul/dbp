package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.CouplingEntity;

import javax.validation.Valid;
import java.util.List;

/**
 * Data Transfer Object representing {@link CouplingEntity}
 */
public class Coupling extends AbstractModel implements Searchable{

    private Integer pipeDiameter;

    private byte[] image;

    private String imageFilename;

    private String imageContentType;

    private byte[] drawing;

    private String drawingFilename;

    private String drawingContentType;

    private String others;

    private Gasket gasket;

    private Material material;

    @Valid
    private List<CouplingInfo> infoList;

    private CouplingInfo currentInfo;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getPipeDiameter() {
        return pipeDiameter;
    }

    public void setPipeDiameter(Integer pipeDiameter) {
        this.pipeDiameter = pipeDiameter;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public byte[] getDrawing() {
        return drawing;
    }

    public void setDrawing(byte[] drawing) {
        this.drawing = drawing;
    }

    public String getDrawingFilename() {
        return drawingFilename;
    }

    public void setDrawingFilename(String drawingFilename) {
        this.drawingFilename = drawingFilename;
    }

    public String getDrawingContentType() {
        return drawingContentType;
    }

    public void setDrawingContentType(String drawingContentType) {
        this.drawingContentType = drawingContentType;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Gasket getGasket() {
        return gasket;
    }

    public void setGasket(Gasket gasket) {
        this.gasket = gasket;
    }

    public List<CouplingInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<CouplingInfo> infoList) {
        this.infoList = infoList;
    }

    public CouplingInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(CouplingInfo currentInfo) {
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
