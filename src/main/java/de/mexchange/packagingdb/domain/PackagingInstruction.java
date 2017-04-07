package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.entity.UnPackagingInstructionEntity;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Artur on 5/29/2016.
 * Data PackagingInstruction Object representing {@link UnPackagingInstructionEntity}
 */
public class PackagingInstruction extends AbstractModel {

    @NotEmpty(message = "{err.field.packaging.instruction.transportlaw}")
    private String transportLaw;
    private String verpProvision;
    private String packOutside;
    private String packInside;
    private Float volVerGrpII;
    private Float volVerGrpIII;
    private String BME;

    public String getTransportLaw() {
        return transportLaw;
    }

    public void setTransportLaw(String transportLaw) {
        this.transportLaw = transportLaw;
    }

    public String getVerpProvision() {
        return verpProvision;
    }

    public void setVerpProvision(String verpProvision) {
        this.verpProvision = verpProvision;
    }

    public String getPackOutside() {
        return packOutside;
    }

    public void setPackOutside(String packOutside) {
        this.packOutside = packOutside;
    }

    public String getPackInside() {
        return packInside;
    }

    public void setPackInside(String packInside) {
        this.packInside = packInside;
    }

    public Float getVolVerGrpII() {
        return volVerGrpII;
    }

    public void setVolVerGrpII(Float volVerGrpII) {
        this.volVerGrpII = volVerGrpII;
    }

    public Float getVolVerGrpIII() {
        return volVerGrpIII;
    }

    public void setVolVerGrpIII(Float volVerGrpIII) {
        this.volVerGrpIII = volVerGrpIII;
    }

    public String getBME() {
        return BME;
    }

    public void setBME(String BME) {
        this.BME = BME;
    }
}
