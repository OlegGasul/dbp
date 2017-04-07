package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Coating entity class.
 * GE: beschichtung
 */
@Entity
@Table(name = "coating")
public class CoatingEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // SW code coating
    @NotNull
    @Column(name = "sw_code_coating")
    private String swCodeCoating;

    //SW code hierarchy
    @Column(name = "sw_code_hierarchy")
    private String swCodeHierarchy;

    //Zertifikat DFC
    @Lob
    @Column(name = "cert_dfc")
    private byte[] certDFC;

    @Column(name = "cert_dfc_filename")
    private String certDFCFilename;

    @Column(name = "cert_dfc_content_type")
    private String certDFCContentType;

    // Bemerkungen
    @Column(name = "remarks")
    private String remarks;

    //Verwendung
    @Column(name = "utilisation")
    private String utilisation;

    @OneToMany(mappedBy="coating", cascade = CascadeType.ALL)
    private Set<CoatingInfoEntity> infoList;

    /**
     * Initializes a new instance of the class.
     */
    public CoatingEntity() {
    }

    // region <GET/SET>
    public Long getId() {
        return id;
    }

    public CoatingEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSwCodeCoating() {
        return swCodeCoating;
    }

    public CoatingEntity setSwCodeCoating(String swCodeCoating) {
        this.swCodeCoating = swCodeCoating;
        return this;
    }

    public String getSwCodeHierarchy() {
        return swCodeHierarchy;
    }

    public CoatingEntity setSwCodeHierarchy(String swCodeHierarchy) {
        this.swCodeHierarchy = swCodeHierarchy;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public CoatingEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getUtilisation() {
        return utilisation;
    }

    public CoatingEntity setUtilisation(String utilisation) {
        this.utilisation = utilisation;
        return this;
    }

    public byte[] getCertDFC() {
        return certDFC;
    }

    public void setCertDFC(byte[] certDFC) {
        this.certDFC = certDFC;
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

    public Set<CoatingInfoEntity> getInfoList() {
        return infoList;
    }

    public CoatingEntity setInfoList(Set<CoatingInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }
    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>CoatingEntity</code> object.
     */

    //endregion
}
