package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.CoatingEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * Data Transfer Object representing {@link CoatingEntity}
 */
public class Coating extends AbstractModel implements Searchable {

	@NotEmpty(message = "{err.field.coating.sw.code.required}")
    private String swCodeCoating;

    private String swCodeHierarchy;

    private byte[] certDFCF;

    private String certDFCFilename;

    private String certDFCContentType;

    private String remarks;

    private String utilisation;

    @Valid
    private List<CoatingInfo> infoList;

    private CoatingInfo currentInfo;

    public String getSwCodeCoating() {
        return swCodeCoating;
    }

    public void setSwCodeCoating(String swCodeCoating) {
        this.swCodeCoating = swCodeCoating;
    }

    public String getSwCodeHierarchy() {
        return swCodeHierarchy;
    }

    public void setSwCodeHierarchy(String swCodeHierarchy) {
        this.swCodeHierarchy = swCodeHierarchy;
    }

    public byte[] getCertDFCF() {
        return certDFCF;
    }

    public void setCertDFCF(byte[] certDFCF) {
        this.certDFCF = certDFCF;
    }

    public String getCertDFCFilename() {
        return certDFCFilename;
    }

    public void setCertDFCFilename(String certDFCFilename) {
        this.certDFCFilename = certDFCFilename;
    }

    public String getCertDFCContentType() {
        return certDFCContentType;
    }

    public void setCertDFCContentType(String certDFCContentType) {
        this.certDFCContentType = certDFCContentType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUtilisation() {
        return utilisation;
    }

    public void setUtilisation(String utilisation) {
        this.utilisation = utilisation;
    }

    public List<CoatingInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<CoatingInfo> infoList) {
        this.infoList = infoList;
    }

    public CoatingInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(CoatingInfo currentInfo) {
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
