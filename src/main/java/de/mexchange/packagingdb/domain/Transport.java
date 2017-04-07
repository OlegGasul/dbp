package de.mexchange.packagingdb.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Data Transfer Object representing {@link de.mexchange.packagingdb.entity.UnTransportEntity}
 */
public class Transport extends AbstractModel{

    @NotEmpty(message = "{err.field.transport.verp.grp}")
    private String verpGrp;

    private String clazz;

    private String verpVorschr;

    private String verpSoSuggest;

    private String IBCVorschr;

    private String LTDQtyVorschr;

    private String taVoSW;

    private String IATA_LQ_V;

    private String IATA_LQ_M;

    private String IATA_LQ_BME;

    private String IATA_PASSENGER_V;

    private String IATA_PASSENGER_M;

    private String IATA_PASSENGER_BME;

    private String IATA_CARGO_V;

    private String IATA_CARGO_M;

    private String IATA_CARGO_BME;

    private String Assim;

    private String permeate;

    private String explosionGroup;

    private String dataSource;

    public String getVerpGrp() {
        return verpGrp;
    }

    public void setVerpGrp(String verpGrp) {
        this.verpGrp = verpGrp;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getVerpVorschr() {
        return verpVorschr;
    }

    public void setVerpVorschr(String verpVorschr) {
        this.verpVorschr = verpVorschr;
    }

    public String getVerpSoSuggest() {
        return verpSoSuggest;
    }

    public void setVerpSoSuggest(String verpSoSuggest) {
        this.verpSoSuggest = verpSoSuggest;
    }

    public String getIBCVorschr() {
        return IBCVorschr;
    }

    public void setIBCVorschr(String IBCVorschr) {
        this.IBCVorschr = IBCVorschr;
    }

    public String getLTDQtyVorschr() {
        return LTDQtyVorschr;
    }

    public void setLTDQtyVorschr(String LTDQtyVorschr) {
        this.LTDQtyVorschr = LTDQtyVorschr;
    }

    public String getTaVoSW() {
        return taVoSW;
    }

    public void setTaVoSW(String taVoSW) {
        this.taVoSW = taVoSW;
    }

    public String getIATA_LQ_V() {
        return IATA_LQ_V;
    }

    public void setIATA_LQ_V(String IATA_LQ_V) {
        this.IATA_LQ_V = IATA_LQ_V;
    }

    public String getIATA_LQ_M() {
        return IATA_LQ_M;
    }

    public void setIATA_LQ_M(String IATA_LQ_M) {
        this.IATA_LQ_M = IATA_LQ_M;
    }

    public String getIATA_LQ_BME() {
        return IATA_LQ_BME;
    }

    public void setIATA_LQ_BME(String IATA_LQ_BME) {
        this.IATA_LQ_BME = IATA_LQ_BME;
    }

    public String getIATA_PASSENGER_V() {
        return IATA_PASSENGER_V;
    }

    public void setIATA_PASSENGER_V(String IATA_PASSENGER_V) {
        this.IATA_PASSENGER_V = IATA_PASSENGER_V;
    }

    public String getIATA_PASSENGER_M() {
        return IATA_PASSENGER_M;
    }

    public void setIATA_PASSENGER_M(String IATA_PASSENGER_M) {
        this.IATA_PASSENGER_M = IATA_PASSENGER_M;
    }

    public String getIATA_PASSENGER_BME() {
        return IATA_PASSENGER_BME;
    }

    public void setIATA_PASSENGER_BME(String IATA_PASSENGER_BME) {
        this.IATA_PASSENGER_BME = IATA_PASSENGER_BME;
    }

    public String getIATA_CARGO_V() {
        return IATA_CARGO_V;
    }

    public void setIATA_CARGO_V(String IATA_CARGO_V) {
        this.IATA_CARGO_V = IATA_CARGO_V;
    }

    public String getIATA_CARGO_M() {
        return IATA_CARGO_M;
    }

    public void setIATA_CARGO_M(String IATA_CARGO_M) {
        this.IATA_CARGO_M = IATA_CARGO_M;
    }

    public String getIATA_CARGO_BME() {
        return IATA_CARGO_BME;
    }

    public void setIATA_CARGO_BME(String IATA_CARGO_BME) {
        this.IATA_CARGO_BME = IATA_CARGO_BME;
    }

    public String getAssim() {
        return Assim;
    }

    public void setAssim(String assim) {
        Assim = assim;
    }

    public String getPermeate() {
        return permeate;
    }

    public void setPermeate(String permeate) {
        this.permeate = permeate;
    }

    public String getExplosionGroup() {
        return explosionGroup;
    }

    public void setExplosionGroup(String explosionGroup) {
        this.explosionGroup = explosionGroup;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}
