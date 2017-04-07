package de.mexchange.packagingdb.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * UN Transport Entity class
 * UN-Transport
 */
@Entity
@Table(name = "un_transport")
public class UnTransportEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Verp Grp
    @NotEmpty
    @Column(name = "verp_grp")
    private String verpGrp;

    //Klasse
    @Column(name = "clazz")
    private String clazz;

    //Verp Vorschr
    @Column(name = "verp_vorschr")
    private String verpVorschr;

    //VerpSo Vorsch
    @Column(name = "verp_so_suggest")
    private String verpSoSuggest;

    //IBC Vorschr
    @Column(name = "IBC_vorschr")
    private String IBCVorschr;

    //LtdQty Vorschr
    @Column(name = "LTD_qty_vorschr")
    private String LTDQtyVorschr;

    // TaVoSW
    @Column(name = "ta_vo_sw")
    private String taVoSW;

    //IATA LQ V
    @Column(name = "IATA_LQ_V")
    private String IATA_LQ_V;

    //IATA_LQ_M
    @Column(name = "IATA_LQ_M")
    private String IATA_LQ_M;

    //IATA_LQ_BME
    @Column(name = "IATA_LQ_BME")
    private String IATA_LQ_BME;

    //IATA PASSENGER V
    @Column(name = "IATA_PASSENGER_V")
    private String IATA_PASSENGER_V;

    //IATA PASSENGER M
    @Column(name = "IATA_PASSENGER_M")
    private String IATA_PASSENGER_M;

    //IATA PASSENGER BME
    @Column(name = "IATA_PASSENGER_BME")
    private String IATA_PASSENGER_BME;

    //IATA CARGO V
    @Column(name = "IATA_CARGO_V")
    private String IATA_CARGO_V;

    //IATA CARGO M
    @Column(name = "IATA_CARGO_M")
    private String IATA_CARGO_M;

    //IATA CARGO BME
    @Column(name = "IATA_CARGO_BME")
    private String IATA_CARGO_BME;

    //Assim
    @Column(name = "Assim")
    private String Assim;

    //Permeat
    // TODO no, less, high, must be selection field
    @Column(name = "permeate")
    private String permeate;

    //Explosionsgruppe
    // TODO no, IIC or IIB MZE smaller 0.2 mj, IIA, must be selection field
    @Column(name = "explosion_group")
    private String explosionGroup;

    //Datenquelle
    @Column(name = "data_source")
    private String dataSource;

    //TODO NOT existing in new requirements but exist on old db
    @Column(name = "un")
    private String un;

    @Column(name = "un_name")
    private String unName;

    @Column(name = "cas")
    private String CAS;

    /**
     * Initializes a new instance of the class.
     */
    public UnTransportEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public UnTransportEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getVerpGrp() {
        return verpGrp;
    }

    public UnTransportEntity setVerpGrp(String verpGrp) {
        this.verpGrp = verpGrp;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public UnTransportEntity setClazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public String getVerpVorschr() {
        return verpVorschr;
    }

    public UnTransportEntity setVerpVorschr(String verpVorschr) {
        this.verpVorschr = verpVorschr;
        return this;
    }

    public String getVerpSoSuggest() {
        return verpSoSuggest;
    }

    public UnTransportEntity setVerpSoSuggest(String verpSoSuggest) {
        this.verpSoSuggest = verpSoSuggest;
        return this;
    }

    public String getIBCVorschr() {
        return IBCVorschr;
    }

    public UnTransportEntity setIBCVorschr(String IBCVorschr) {
        this.IBCVorschr = IBCVorschr;
        return this;
    }

    public String getLTDQtyVorschr() {
        return LTDQtyVorschr;
    }

    public UnTransportEntity setLTDQtyVorschr(String LTDQtyVorschr) {
        this.LTDQtyVorschr = LTDQtyVorschr;
        return this;
    }

    public String getTaVoSW() {
        return taVoSW;
    }

    public UnTransportEntity setTaVoSW(String taVoSW) {
        this.taVoSW = taVoSW;
        return this;
    }

    public String getIATA_LQ_V() {
        return IATA_LQ_V;
    }

    public UnTransportEntity setIATA_LQ_V(String IATA_LQ_V) {
        this.IATA_LQ_V = IATA_LQ_V;
        return this;
    }

    public String getIATA_LQ_M() {
        return IATA_LQ_M;
    }

    public UnTransportEntity setIATA_LQ_M(String IATA_LQ_M) {
        this.IATA_LQ_M = IATA_LQ_M;
        return this;
    }

    public String getIATA_LQ_BME() {
        return IATA_LQ_BME;
    }

    public UnTransportEntity setIATA_LQ_BME(String IATA_LQ_BME) {
        this.IATA_LQ_BME = IATA_LQ_BME;
        return this;
    }

    public String getIATA_PASSENGER_V() {
        return IATA_PASSENGER_V;
    }

    public UnTransportEntity setIATA_PASSENGER_V(String IATA_PASSENGER_V) {
        this.IATA_PASSENGER_V = IATA_PASSENGER_V;
        return this;
    }

    public String getIATA_PASSENGER_M() {
        return IATA_PASSENGER_M;
    }

    public UnTransportEntity setIATA_PASSENGER_M(String IATA_PASSENGER_M) {
        this.IATA_PASSENGER_M = IATA_PASSENGER_M;
        return this;
    }

    public String getIATA_PASSENGER_BME() {
        return IATA_PASSENGER_BME;
    }

    public UnTransportEntity setIATA_PASSENGER_BME(String IATA_PASSENGER_BME) {
        this.IATA_PASSENGER_BME = IATA_PASSENGER_BME;
        return this;
    }

    public String getIATA_CARGO_V() {
        return IATA_CARGO_V;
    }

    public UnTransportEntity setIATA_CARGO_V(String IATA_CARGO_V) {
        this.IATA_CARGO_V = IATA_CARGO_V;
        return this;
    }

    public String getIATA_CARGO_M() {
        return IATA_CARGO_M;
    }

    public UnTransportEntity setIATA_CARGO_M(String IATA_CARGO_M) {
        this.IATA_CARGO_M = IATA_CARGO_M;
        return this;
    }

    public String getIATA_CARGO_BME() {
        return IATA_CARGO_BME;
    }

    public UnTransportEntity setIATA_CARGO_BME(String IATA_CARGO_BME) {
        this.IATA_CARGO_BME = IATA_CARGO_BME;
        return this;
    }

    public String getAssim() {
        return Assim;
    }

    public UnTransportEntity setAssim(String assim) {
        Assim = assim;
        return this;
    }

    public String getPermeate() {
        return permeate;
    }

    public UnTransportEntity setPermeate(String permeate) {
        this.permeate = permeate;
        return this;
    }

    public String getExplosionGroup() {
        return explosionGroup;
    }

    public UnTransportEntity setExplosionGroup(String explosionGroup) {
        this.explosionGroup = explosionGroup;
        return this;
    }

    public String getDataSource() {
        return dataSource;
    }

    public UnTransportEntity setDataSource(String dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public String getUn() {
        return un;
    }

    public UnTransportEntity setUn(String un) {
        this.un = un;
        return this;
    }

    public String getUnName() {
        return unName;
    }

    public UnTransportEntity setUnName(String unName) {
        this.unName = unName;
        return this;
    }

    public String getCAS() {
        return CAS;
    }

    public UnTransportEntity setCAS(String CAS) {
        this.CAS = CAS;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>UnTransportEntity</code> object.
     */
    @Override
    public String toString() {
        return "UnTransportEntity{" +
                "id=" + id +
                ", verpGrp='" + verpGrp + '\'' +
                ", clazz='" + clazz + '\'' +
                ", verpVorschr='" + verpVorschr + '\'' +
                ", verpSoSuggest='" + verpSoSuggest + '\'' +
                ", IBCVorschr='" + IBCVorschr + '\'' +
                ", LTDQtyVorschr='" + LTDQtyVorschr + '\'' +
                ", taVoSW='" + taVoSW + '\'' +
                ", IATA_LQ_V='" + IATA_LQ_V + '\'' +
                ", IATA_LQ_M='" + IATA_LQ_M + '\'' +
                ", IATA_LQ_BME='" + IATA_LQ_BME + '\'' +
                ", IATA_PASSENGER_V='" + IATA_PASSENGER_V + '\'' +
                ", IATA_PASSENGER_M='" + IATA_PASSENGER_M + '\'' +
                ", IATA_PASSENGER_BME='" + IATA_PASSENGER_BME + '\'' +
                ", IATA_CARGO_V='" + IATA_CARGO_V + '\'' +
                ", IATA_CARGO_M='" + IATA_CARGO_M + '\'' +
                ", IATA_CARGO_BME='" + IATA_CARGO_BME + '\'' +
                ", Assim='" + Assim + '\'' +
                ", permeate='" + permeate + '\'' +
                ", explosionGroup='" + explosionGroup + '\'' +
                ", dataSource='" + dataSource + '\'' +
                '}';
    }
    // endregion
}


