package de.mexchange.packagingdb.entity;

import de.mexchange.packagingdb.domain.lcp.ContainerStatus;
import de.mexchange.packagingdb.entity.converter.ContainerStatusConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class for containers
 */
@Entity
@Table(name="container")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ContainerEntity extends AbstractEntity {

	/*
	CREATE TABLE `packmittel_data` (
	  `ID_PM` int(100) NOT NULL AUTO_INCREMENT,
	  `ID_Gebindetyp` int(100) DEFAULT NULL,
	  `ID_Packm_Form` int(10) NOT NULL DEFAULT '0',
	  `Bezeichnung` varchar(255) DEFAULT NULL,
	  `ID_ReferenzVerpackung` int(1) DEFAULT NULL,
	  `Nennvolumen` float(7,2) DEFAULT NULL,
	  `Ueberlaufvolumen` float(7,2) DEFAULT NULL,
	  `ID_UN_Zulassung` int(100) DEFAULT NULL,
	  `ID_WHG_Zulassung` int(10) DEFAULT NULL,
	  `ID_ExZone` int(10) DEFAULT NULL,
	  `ID_PermSperre` int(2) DEFAULT '0',
	  `ID_Dokumente` int(100) DEFAULT NULL,
	  `Praegungen` varchar(255) DEFAULT NULL,
	  `Praegungsposition` varchar(255) DEFAULT NULL,
	  `Markierungen_GE` blob,
	  `Markierungen_GB` blob,
	  `Markierungen_PT` blob,
	  `Markierungen_PL` blob,
	  `ID_Hersteller` int(100) DEFAULT NULL,
	  `SAP_Nummer` varchar(20) DEFAULT NULL,
	  `Herst_Nummer` varchar(25) DEFAULT NULL,
	  `Herst_Datenblatt` varchar(255) DEFAULT NULL,
	  `Foto1` varchar(255) DEFAULT NULL,
	  `Foto2` varchar(255) DEFAULT NULL,
	  `Foto3` varchar(255) DEFAULT NULL,
	  `Zeichnungsnummer` varchar(255) DEFAULT NULL,
	  `Zeichnung_pdf` varchar(255) DEFAULT NULL,
	  `Zeichnung_CAD` varchar(255) DEFAULT NULL,
	  `TIMESTAMP_PM` timestamp NULL DEFAULT '0000-00-00 00:00:00',
	  `USER_PM` varchar(255) DEFAULT NULL,
	  `Hoehe_Ueberalles` float(6,2) DEFAULT NULL,
	  `Hoehe_Ueberalles_maxTol` float(6,2) DEFAULT NULL,
	  `Hoehe_Ueberalles_minTol` float(6,2) DEFAULT NULL,
	  `Breite_Rahmen` float(6,2) DEFAULT NULL,
	  `Breite_Rahmen_maxTol` float(6,2) DEFAULT NULL,
	  `Breite_Rahmen_minTol` float(6,2) DEFAULT NULL,
	  `Tiefe_Rahmen` float(6,2) DEFAULT NULL,
	  `Tiefe_Rahmen_maxTol` float(6,2) DEFAULT NULL,
	  `Tiefe_Rahmen_minTol` float(6,2) DEFAULT NULL,
	  `Hoehe_Behaelter` float(6,2) DEFAULT NULL,
	  `Hoehe_Behaelter_maxTol` float(6,2) DEFAULT NULL,
	  `Hoehe_Behaelter_minTol` float(6,2) DEFAULT NULL,
	  `Durchmesser_Behaelter_innen_oben` float(6,2) DEFAULT NULL,
	  `Durchmesser_Behaelter_innen_oben_maxTol` float(6,2) DEFAULT NULL,
	  `Durchmesser_Behaelter_innen_oben_minTol` float(6,2) DEFAULT NULL,
	  `Durchmesser_Behaelter_innen_unten` float(6,2) DEFAULT NULL,
	  `Durchmesser_Behaelter_innen_unten_maxTol` float(6,2) DEFAULT NULL,
	  `Durchmesser_Behaelter_innen_unten_minTol` float(6,2) DEFAULT NULL,
	  `Behaelter_Schuettwinkel_breit` float(6,2) DEFAULT NULL,
	  `Behaelter_Schuettwinkel_lang` float(6,2) DEFAULT NULL,
	  `Wandstaerke_mitte` float(4,2) DEFAULT NULL,
	  `Wandstaerke_mitte_maxTol` float(4,2) DEFAULT NULL,
	  `Wandstaerke_mitte_minTol` float(4,2) DEFAULT NULL,
	  `Wandstaerke_oben` float(4,2) DEFAULT NULL,
	  `Wandstaerke_oben_maxTol` float(4,2) DEFAULT NULL,
	  `Wandstaerke_oben_minTol` float(4,2) DEFAULT NULL,
	  `Wandstaerke_unten` float(4,2) DEFAULT NULL,
	  `Wandstaerke_unten_maxTol` float(4,2) DEFAULT NULL,
	  `Wandstaerke_unten_minTol` float(4,2) DEFAULT NULL,
	  `Durchmesser_Spannring` float(6,2) DEFAULT NULL,
	  `Durchmesser_Spannring_maxTol` float(6,2) DEFAULT NULL,
	  `Durchmesser_Spannring_minTol` float(6,2) DEFAULT NULL,
	  `Gewicht_Gesamt` float(6,2) DEFAULT NULL,
	  `Gewicht_Gesamt_maxTol` float(6,2) DEFAULT NULL,
	  `Gewicht_Gesamt_minTol` float(6,2) DEFAULT NULL,
	  `Gewicht_Behaelter` float(6,2) DEFAULT NULL,
	  `Gewicht_Behaelter_maxTol` float(6,2) DEFAULT NULL,
	  `Gewicht_Behaelter_minTol` float(6,2) DEFAULT NULL,
	  `ID_werkstoffe_Behaelter` int(10) DEFAULT NULL,
	  `ID_beschichtungen_Behaelter_aussen` int(10) DEFAULT NULL,
	  `ID_beschichtungen_Behaelter_innen` int(10) DEFAULT NULL,
	  `ID_verschluesse_Einfuell` int(10) DEFAULT NULL,
	  `ID_verschluesse_Auslauf1` int(10) DEFAULT NULL,
	  `ID_kupplungen_Kuppl1` int(10) DEFAULT NULL,
	  `Hoehe_Auslauf1_max` float(6,2) DEFAULT NULL,
	  `Hoehe_Auslauf1_min` float(6,2) DEFAULT NULL,
	  `ID_verschluesse_Auslauf2` int(10) DEFAULT NULL,
	  `ID_kupplungen_Kuppl2` int(10) DEFAULT NULL,
	  `Hoehe_Auslauf2_max` float(6,2) DEFAULT NULL,
	  `Hoehe_Auslauf2_min` float(6,2) DEFAULT NULL,
	  `ID_verschluesse_Auslauf3` int(10) DEFAULT NULL,
	  `ID_kupplungen_Kuppl3` int(10) DEFAULT NULL,
	  `Hoehe_Auslauf3_max` float(6,2) DEFAULT NULL,
	  `Hoehe_Auslauf3_min` float(6,2) DEFAULT NULL,
	  `Gewicht_Rahmen` float(6,2) DEFAULT NULL,
	  `Gewicht_Rahmen_maxTol` float(6,2) DEFAULT NULL,
	  `Gewicht_Rahmen_minTol` float(6,2) DEFAULT NULL,
	  `ID_werkstoffe_Rahmen` int(10) DEFAULT NULL,
	  `ID_beschichtungen_Rahmen` int(10) DEFAULT NULL,
	  `Gewicht_Palette` float(6,2) DEFAULT NULL,
	  `Gewicht_Palette_maxTol` float(6,2) DEFAULT NULL,
	  `Gewicht_Palette_minTol` float(6,2) DEFAULT NULL,
	  `Hoehe_Palette` float(6,2) DEFAULT NULL,
	  `Breite_Palette` float(6,2) DEFAULT NULL,
	  `Tiefe_Palette` float(6,2) DEFAULT NULL,
	  `Hoehe_Paletteneinfahrt` float(6,2) DEFAULT NULL,
	  `ID_werkstoffe_Palette` int(10) DEFAULT NULL,
	  `ID_Palettentyp` int(10) DEFAULT NULL,
	  `Verstaerkungssicken` varchar(255) DEFAULT NULL,
	  `ID_griffe` int(10) DEFAULT NULL,
	  `Sonstiges` varchar(255) DEFAULT NULL,
	  `ADMIN_PM` varchar(255) DEFAULT NULL,
	  `TS_PM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	  PRIMARY KEY (`ID_PM`),
	  KEY `ID_UN_Zulassung` (`ID_UN_Zulassung`),
	  KEY `ID_Hersteller` (`ID_Hersteller`),
	  KEY `ID_ReferenzVerpackung` (`ID_ReferenzVerpackung`)
	) ENGINE=MyISAM AUTO_INCREMENT=247 DEFAULT CHARSET=latin1

	 */

    //VPS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    //VPS_SAP_Code
    @Column(name = "sap_code")
    private String sapCode;

    //VPS_Local_Code
    @Column(name = "local_code")
    private String localCode;

    // VPS_Global_Code
    @Column(name = "global_code")
    private String globalCode;

    //VPS_Status
	@NotNull
    @Column(name = "status_id", nullable = false)
    @Convert(converter = ContainerStatusConverter.class)
    private ContainerStatus status;

    // Standort
    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    // BU
    @ManyToOne
    @JoinColumn(name = "business_unit_id")
    private BusinessUnitEntity businessUnit;

    // Nennvolumen
    @NotNull
    @Column(name = "nominal_volume")
    private String nominalVolume;

    // Bezeichnung
    @NotNull
    @Column(name = "designation")
    private String designation;

	//Hersteller
	@ManyToOne
	@JoinColumn(name = "company_id")
	private CompanyEntity company;

	//Packaging Form
	@ManyToOne
	@JoinColumn(name = "packaging_form_id")
	private PackagingFormEntity packagingFormEntity;

	@OneToMany(mappedBy="container", cascade = CascadeType.ALL)
	private Set<ContainerInfoEntity> infoList;

	@OneToMany(mappedBy="container", cascade = CascadeType.ALL)
	private Set<ContainerPhotoEntity> photos;

	public Long getId() {
		return id;
	}

	public ContainerEntity setId(Long id) {
		this.id = id;
		return this;
	}

	public String getSapCode() {
		return sapCode;
	}

	public ContainerEntity setSapCode(String sapCode) {
		this.sapCode = sapCode;
		return this;
	}

	public String getLocalCode() {
		return localCode;
	}

	public ContainerEntity setLocalCode(String localCode) {
		this.localCode = localCode;
		return this;
	}

	public String getGlobalCode() {
		return globalCode;
	}

	public ContainerEntity setGlobalCode(String globalCode) {
		this.globalCode = globalCode;
		return this;
	}

	public ContainerStatus getStatus() {
		return status;
	}

	public ContainerEntity setStatus(ContainerStatus status) {
		this.status = status;
		return this;
	}

	public LocationEntity getLocation() {
		return location;
	}

	public ContainerEntity setLocation(LocationEntity location) {
		this.location = location;
		return this;
	}

	public BusinessUnitEntity getBusinessUnit() {
		return businessUnit;
	}

	public ContainerEntity setBusinessUnit(BusinessUnitEntity businessUnit) {
		this.businessUnit = businessUnit;
		return this;
	}

	public String getNominalVolume() {
		return nominalVolume;
	}

	public ContainerEntity setNominalVolume(String nominalVolume) {
		this.nominalVolume = nominalVolume;
		return this;
	}

	public String getDesignation() {
		return designation;
	}

	public ContainerEntity setDesignation(String designation) {
		this.designation = designation;
		return this;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public ContainerEntity setCompany(CompanyEntity company) {
		this.company = company;
		return this;
	}

	public PackagingFormEntity getPackagingFormEntity() {
		return packagingFormEntity;
	}

	public ContainerEntity setPackagingFormEntity(PackagingFormEntity packagingFormEntity) {
		this.packagingFormEntity = packagingFormEntity;
		return this;
	}

	public Set<ContainerInfoEntity> getInfoList() {
		return infoList;
	}

	public ContainerEntity setInfoList(Set<ContainerInfoEntity> infoList) {
		this.infoList = infoList;
		return this;
	}

	public Set<ContainerPhotoEntity> getPhotos() {
		return photos;
	}

	public ContainerEntity setPhotos(Set<ContainerPhotoEntity> photos) {
		this.photos = photos;
		return this;
	}

	public void addPhoto(ContainerPhotoEntity photo) {
		if (photos == null) {
			photos = new HashSet<>();
		}
		photos.add(photo);
		photo.setContainer(this);
	}
}
