package de.mexchange.packagingdb.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * UN Verp Instruction Entity class
 * This class has not any references and has not multi language fields
 * UN-VerpAnweis
 */
@Entity
@Table(name = "un_packaging_instruction")
public class UnPackagingInstructionEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Transportrecht
    @NotNull
    @Column(name = "transport_law")
    private String transportLaw;

    // Verp Vorschrift
    @Column(name = "verp_provision")
    private String verpProvision;

    // Verp Aussen
    @Column(name = "pack_outside")
    private String packOutside;

    //Verp Innen
    @Column(name = "pack_inside")
    private String packInside;

    //Vol VerGrp II
    @Column(name = "vol_verGrp_II")
    private Float volVerGrpII;

    //Vol VerGrp III
    @Column(name = "vol_verGrp_III")
    private Float volVerGrpIII;

    //BME
    @Column(name = "BME")
    private String BME;

    /**
     * Initializes a new instance of the class.
     */
    public UnPackagingInstructionEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public UnPackagingInstructionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTransportLaw() {
        return transportLaw;
    }

    public UnPackagingInstructionEntity setTransportLaw(String transportLaw) {
        this.transportLaw = transportLaw;
        return this;
    }

    public String getVerpProvision() {
        return verpProvision;
    }

    public UnPackagingInstructionEntity setVerpProvision(String verpProvision) {
        this.verpProvision = verpProvision;
        return this;
    }

    public String getPackOutside() {
        return packOutside;
    }

    public UnPackagingInstructionEntity setPackOutside(String packOutside) {
        this.packOutside = packOutside;
        return this;
    }

    public String getPackInside() {
        return packInside;
    }

    public UnPackagingInstructionEntity setPackInside(String packInside) {
        this.packInside = packInside;
        return this;
    }

    public Float getVolVerGrpII() {
        return volVerGrpII;
    }

    public UnPackagingInstructionEntity setVolVerGrpII(Float volVerGrpII) {
        this.volVerGrpII = volVerGrpII;
        return this;
    }

    public Float getVolVerGrpIII() {
        return volVerGrpIII;
    }

    public UnPackagingInstructionEntity setVolVerGrpIII(Float volVerGrpIII) {
        this.volVerGrpIII = volVerGrpIII;
        return this;
    }

    public String getBME() {
        return BME;
    }

    public UnPackagingInstructionEntity setBME(String BME) {
        this.BME = BME;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>UnPackagingInstructionEntity</code> object.
     */
    @Override
    public String toString() {
        return "UnPackagingInstructionEntity{" +
                "id=" + id +
                ", transportLaw='" + transportLaw + '\'' +
                ", verpProvision='" + verpProvision + '\'' +
                ", packOutside='" + packOutside + '\'' +
                ", packInside='" + packInside + '\'' +
                ", volVerGrpII='" + volVerGrpII + '\'' +
                ", volVerGrpIII='" + volVerGrpIII + '\'' +
                ", BME='" + BME + '\'' +
                '}';
    }
    //endregion

}
