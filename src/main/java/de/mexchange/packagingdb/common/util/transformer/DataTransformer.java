package de.mexchange.packagingdb.common.util.transformer;

import de.mexchange.packagingdb.common.util.CollectionHelper;
import de.mexchange.packagingdb.domain.Process;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.domain.lcp.Status;
import de.mexchange.packagingdb.domain.lcp.UserRole;
import de.mexchange.packagingdb.domain.*;
import de.mexchange.packagingdb.entity.*;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.*;

/**
 * Created by Garik on 4/30/16.
 */
public class DataTransformer extends GeneralTransformer {

	/**
	 * Transforms User into UserEntity
	 * @param user
	 * @return {@link UserEntity}
	 */
	public static UserEntity transform(User user) {
		if (user == null) {
			return null;
		}

		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setStatus(user.getStatus());
		entity.setEmail(user.getEmail());
		entity.setPasswordHash(user.getPassword());
		entity.setRole(new Authority(user.getRole()));
		entity.setName(user.getName());
		entity.setSurname(user.getSurname());
		entity.setPhoneNumber(user.getPhoneNumber());
		entity.setFaxNumber(user.getFaxNumber());
		entity.setMobileNumber(user.getMobileNumber());

		Address address = user.getAddress();
		if(address != null && address.getCountry() != null && address.getCity() != null ){
			entity.setAddress(transform(user.getAddress()));
		}
		fromModelToEntity(user, entity);
		return entity;
	}

	/**
	 * Transforms UserEntity into User
	 * @param entity
	 * @return {@link User}
	 */
	public static User transform(UserEntity entity) {
		if (entity == null) {
			return null;
		}

		User user = new User();
		user.setId(entity.getId());
		user.setStatus(entity.getStatus());
		user.setEmail(entity.getEmail());
		user.setPassword(entity.getPassword());
		user.setRole(UserRole.valueOf(entity.getRole().getId()));
		user.setAuthorities(entity.getAuthorities());
		user.setName(entity.getName());
		user.setSurname(entity.getSurname());
		user.setPhoneNumber(entity.getPhoneNumber());
		user.setFaxNumber(entity.getFaxNumber());
		user.setMobileNumber(entity.getMobileNumber());
		user.setAddress(transform(entity.getAddress()));
		fromEntityToModel(entity, user);
		return user;
	}

	/**
	 * Transforms BusinessUnitEntity into BusinessUnit
	 * @param entity
	 * @return {@link BusinessUnit}
	 */
	public static BusinessUnit transform(BusinessUnitEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms BusinessUnitEntity into BusinessUnit
	 * @param entity
	 * @return {@link BusinessUnit}
	 */
	public static BusinessUnit transform(BusinessUnitEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		BusinessUnit dto = new BusinessUnit();
		dto.setId(entity.getId());
		dto.setMatchCode(entity.getMatchCode());
		dto.setMisc(entity.getMisc());

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
            Locale locale = LocaleContextHolder.getLocale();
			List<BusinessUnitInfo> infoList = new ArrayList<>();
			for (BusinessUnitInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				BusinessUnitInfo info = new BusinessUnitInfo();
                info.setId(e.getId());
                info.setLanguage(lang);
                info.setName(e.getName());

				if (language != null) {
					if (language.equals(lang)) {
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				} else if (lang.getLocale().getLanguage().equals(locale.getLanguage())) {
                    dto.setCurrentInfo(info);
                }

				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms BusinessUnit into BusinessUnitEntity
	 * @param businessUnit
	 * @return {@link BusinessUnitEntity}
	 */
	public static BusinessUnitEntity transform(BusinessUnit businessUnit) {
		if (businessUnit == null) {
			return null;
		}

		BusinessUnitEntity entity = new BusinessUnitEntity();
		entity.setId(businessUnit.getId());
		entity.setMatchCode(businessUnit.getMatchCode());
		entity.setMisc(businessUnit.getMisc());

		if (CollectionHelper.isNotEmpty(businessUnit.getInfoList())) {
            Set<BusinessUnitInfoEntity> infoList = new HashSet<>();
			for (BusinessUnitInfo e : businessUnit.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				BusinessUnitInfoEntity info = new BusinessUnitInfoEntity();
				info.setBusinessUnit(entity);
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setName(e.getName());
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(businessUnit, entity);
		return entity;
	}

	/**
	 * Transforms AssimilationList into UnAssimilationListEntity
	 * @param assimilationList
	 * @return {@link UnAssimilationListEntity}
	 */
	public static UnAssimilationListEntity transform(final AssimilationList assimilationList) {
		if (assimilationList == null) {
			return null;
		}

		UnAssimilationListEntity entity = new UnAssimilationListEntity();
		entity.setId(assimilationList.getId());
		entity.setDesignation(assimilationList.getDesignation());
		entity.setClazz(assimilationList.getClazz());
		entity.setClassificationCode(assimilationList.getClassificationCode());
		entity.setClassificationGroup(assimilationList.getClassificationGroup());
		entity.setPackagingGroup(assimilationList.getPackagingGroup());
		entity.setAssimilationCode(assimilationList.getAssimilationCode());
		entity.setDataSource(assimilationList.getDataSource());
		entity.setDataSourceFilename(assimilationList.getDataSourceFilename());
		entity.setDataSourceContentType(assimilationList.getDataSourceContentType());

		fromModelToEntity(assimilationList, entity);
		return entity;
	}

	/**
	 * Transforms UnTransportEntity into Transport
	 * @param entity
	 * @return {@link Transport}
	 */
	public static AssimilationList transform(final UnAssimilationListEntity entity) {
		if (entity == null) {
			return null;
		}

		AssimilationList dto = new AssimilationList();
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		dto.setClazz(entity.getClazz());
		dto.setClassificationCode(entity.getClassificationCode());
		dto.setClassificationGroup(entity.getClassificationGroup());
		dto.setPackagingGroup(entity.getPackagingGroup());
		dto.setAssimilationCode(entity.getAssimilationCode());
		dto.setDataSource(entity.getDataSource());
		dto.setDataSourceFilename(entity.getDataSourceFilename());
		dto.setDataSourceContentType(entity.getDataSourceContentType());

		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Transport into UnTransportEntity
	 * @param transport
	 * @return {@link UnTransportEntity}
	 */
	public static UnTransportEntity transform(final Transport transport) {
		if (transport == null) {
			return null;
		}

		UnTransportEntity entity = new UnTransportEntity();
		entity.setId(transport.getId());
		entity.setVerpGrp(transport.getVerpGrp());
		entity.setClazz(transport.getClazz());
		entity.setVerpVorschr(transport.getVerpVorschr());
		entity.setVerpSoSuggest(transport.getVerpSoSuggest());
		entity.setIBCVorschr(transport.getIBCVorschr());
		entity.setLTDQtyVorschr(transport.getLTDQtyVorschr());
		entity.setTaVoSW(transport.getTaVoSW());
		entity.setIATA_LQ_V(transport.getIATA_LQ_V());
		entity.setIATA_LQ_M(transport.getIATA_LQ_M());
		entity.setIATA_LQ_BME(transport.getIATA_LQ_BME());
		entity.setIATA_PASSENGER_V(transport.getIATA_PASSENGER_V());
		entity.setIATA_PASSENGER_M(transport.getIATA_PASSENGER_M());
		entity.setIATA_PASSENGER_BME(transport.getIATA_PASSENGER_BME());
		entity.setIATA_CARGO_V(transport.getIATA_CARGO_V());
		entity.setIATA_CARGO_M(transport.getIATA_CARGO_M());
		entity.setIATA_CARGO_BME(transport.getIATA_CARGO_BME());
		entity.setAssim(transport.getAssim());
		entity.setPermeate(transport.getPermeate());
		entity.setExplosionGroup(transport.getExplosionGroup());
		entity.setDataSource(transport.getDataSource());

		fromModelToEntity(transport, entity);
		return entity;
	}

	/**
	 * Transforms UnTransportEntity into Transport
	 * @param entity
	 * @return {@link Transport}
	 */
	public static Transport transform(final UnTransportEntity entity) {
		if (entity == null) {
			return null;
		}

		Transport dto = new Transport();
		dto.setId(entity.getId());
		dto.setVerpGrp(entity.getVerpGrp());
		dto.setClazz(entity.getClazz());
		dto.setVerpVorschr(entity.getVerpVorschr());
		dto.setVerpSoSuggest(entity.getVerpSoSuggest());
		dto.setIBCVorschr(entity.getIBCVorschr());
		dto.setLTDQtyVorschr(entity.getLTDQtyVorschr());
		dto.setTaVoSW(entity.getTaVoSW());
		dto.setIATA_LQ_V(entity.getIATA_LQ_V());
		dto.setIATA_LQ_M(entity.getIATA_LQ_M());
		dto.setIATA_LQ_BME(entity.getIATA_LQ_BME());
		dto.setIATA_PASSENGER_V(entity.getIATA_PASSENGER_V());
		dto.setIATA_PASSENGER_M(entity.getIATA_PASSENGER_M());
		dto.setIATA_PASSENGER_BME(entity.getIATA_PASSENGER_BME());
		dto.setIATA_CARGO_V(entity.getIATA_CARGO_V());
		dto.setIATA_CARGO_M(entity.getIATA_CARGO_M());
		dto.setIATA_CARGO_BME(entity.getIATA_CARGO_BME());
		dto.setAssim(entity.getAssim());
		dto.setPermeate(entity.getPermeate());
		dto.setExplosionGroup(entity.getExplosionGroup());
		dto.setDataSource(entity.getDataSource());

		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms PackagingInstruction into UnPackagingInstructionEntity
	 * @param packagingInstruction
	 * @return {@link UnPackagingInstructionEntity}
	 */
	public static UnPackagingInstructionEntity transform(final PackagingInstruction packagingInstruction) {
		if (packagingInstruction == null) {
			return null;
		}

		UnPackagingInstructionEntity entity = new UnPackagingInstructionEntity();
		entity.setId(packagingInstruction.getId());
		entity.setTransportLaw(packagingInstruction.getTransportLaw());
		entity.setVerpProvision(packagingInstruction.getVerpProvision());
		entity.setPackOutside(packagingInstruction.getPackOutside());
		entity.setPackInside(packagingInstruction.getPackInside());
		entity.setVolVerGrpII(packagingInstruction.getVolVerGrpII());
		entity.setVolVerGrpIII(packagingInstruction.getVolVerGrpIII());
		entity.setBME(packagingInstruction.getBME());

		fromModelToEntity(packagingInstruction, entity);
		return entity;
	}

	/**
	 * Transforms UnPackagingInstructionEntity into PackagingInstruction
	 * @param entity
	 * @return {@link PackagingInstruction}
	 */
	public static PackagingInstruction transform(final UnPackagingInstructionEntity entity) {
		if (entity == null) {
			return null;
		}

		PackagingInstruction dto = new PackagingInstruction();
		dto.setId(entity.getId());
		dto.setTransportLaw(entity.getTransportLaw());
		dto.setVerpProvision(entity.getVerpProvision());
		dto.setPackOutside(entity.getPackOutside());
		dto.setPackInside(entity.getPackInside());
		dto.setVolVerGrpII(entity.getVolVerGrpII());
		dto.setVolVerGrpIII(entity.getVolVerGrpIII());
		dto.setBME(entity.getBME());

		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Handle into HandleEntity
	 * @param handle
	 * @return {@link HandleEntity}
	 */
	public static HandleEntity transform(Handle handle) {
		if (handle == null) {
			return null;
		}

		HandleEntity entity = new HandleEntity();
		entity.setId(handle.getId());
		entity.setRemark(handle.getRemark());

		if (CollectionHelper.isNotEmpty(handle.getInfoList())) {
            Set<HandleInfoEntity> infoList = new HashSet<>();
			for (HandleInfo e : handle.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				HandleInfoEntity info = new HandleInfoEntity();
				info.setHandle(entity);
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setName(e.getName());
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(handle, entity);
		return entity;
	}

	/**
	 * Transforms HandleEntity into Handle
	 * @param entity
	 * @return {@link Handle}
	 */
	public static Handle transform(HandleEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms HandleEntity into Handle
	 * @param entity
	 * @return {@link Handle}
	 */
	public static Handle transform(HandleEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		Handle dto = new Handle();
		dto.setId(entity.getId());
		dto.setRemark(entity.getRemark());

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
			List<HandleInfo> infoList = new ArrayList<>();
			for (HandleInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				if (language != null) {
					if (language.equals(lang)) {
						HandleInfo info = new HandleInfo();
						info.setId(e.getId());
						info.setLanguage(Language.valueOf(e.getLanguage().getId()));
						info.setName(e.getName());
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				}

				HandleInfo info = new HandleInfo();
				info.setId(e.getId());
				info.setLanguage(Language.valueOf(e.getLanguage().getId()));
				info.setName(e.getName());
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Gasket into GasketEntity
	 * @param gasket
	 * @return {@link GasketEntity}
	 */
	public static GasketEntity transform(Gasket gasket) {
		if (gasket == null) {
			return null;
		}

		GasketEntity entity = new GasketEntity();
		entity.setId(gasket.getId());
		entity.setCircumference(gasket.getCircumference());
        if (gasket.getMaterial() != null && gasket.getMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(gasket.getMaterial().getId());
            entity.setMaterial(materialEntity);
        }
		entity.setThickness(gasket.getThickness());
		entity.setUtilisation(gasket.getUtilisation());

		if (CollectionHelper.isNotEmpty(gasket.getInfoList())) {
            Set<GasketInfoEntity> infoList = new HashSet<>();
			for (GasketInfo e : gasket.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				GasketInfoEntity info = new GasketInfoEntity();
				info.setGasket(entity);
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setDescription(e.getDescription());
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(gasket, entity);
		return entity;
	}

	/**
	 * Transforms GasketEntity into Gasket
	 * @param entity
	 * @return {@link Gasket}
	 */
	public static Gasket transform(GasketEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms GasketEntity into Gasket
	 * @param entity
	 * @return {@link Gasket}
	 */
	public static Gasket transform(GasketEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		Gasket dto = new Gasket();
		dto.setId(entity.getId());
		dto.setMaterial(transform(entity.getMaterial()));
		dto.setThickness(entity.getThickness());
		dto.setUtilisation(entity.getUtilisation());
		dto.setCircumference(entity.getCircumference());

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
			List<GasketInfo> infoList = new ArrayList<>();
			for (GasketInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				if (language != null) {
					if (language.equals(lang)) {
						GasketInfo info = new GasketInfo();
						info.setId(e.getId());
						info.setLanguage(Language.valueOf(e.getLanguage().getId()));
						info.setDescription(e.getDescription());
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				}

				GasketInfo info = new GasketInfo();
				info.setId(e.getId());
				info.setLanguage(Language.valueOf(e.getLanguage().getId()));
				info.setDescription(e.getDescription());
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Material into MaterialEntity
	 * @param dto
	 * @return {@link MaterialEntity}
	 */
	public static MaterialEntity transform(Material dto) {
		if (dto == null) {
			return null;
		}

		MaterialEntity entity = new MaterialEntity();
		entity.setId(dto.getId());
		entity.setRemarks(dto.getRemarks());
		entity.setAggregateState(transform(dto.getAggregateState()));
		if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<MaterialInfoEntity> infoList = new HashSet<>();
			for (MaterialInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				MaterialInfoEntity info = new MaterialInfoEntity();
				info.setName(e.getName());
				info.setShortName(e.getShortName());
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setMaterial(entity);
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms MaterialEntity into Material
	 * @param entity
	 * @return {@link Material}
	 */
	public static Material transform(MaterialEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms MaterialEntity into Material
	 * @param entity
	 * @return {@link Material}
	 */
	public static Material transform(MaterialEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		Material dto = new Material();
		dto.setId(entity.getId());
		dto.setAggregateState(transform(entity.getAggregateState()));
		dto.setRemarks(entity.getRemarks());

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
            Locale locale = LocaleContextHolder.getLocale();
			List<MaterialInfo> infoList = new ArrayList<>();
			for (MaterialInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

                MaterialInfo info = new MaterialInfo();
                info.setId(e.getId());
                info.setLanguage(lang);
                info.setName(e.getName());
                info.setShortName(e.getShortName());

				if (language != null) {
					if (language.equals(lang)) {
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				} else if (lang.getLocale().getLanguage().equals(locale.getLanguage())) {
                    dto.setCurrentInfo(info);
                }
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Coating with certificate data into CoatingEntity including certificate
	 * @param dto
	 * @return {@link CoatingEntity}
	 */
	public static CoatingEntity transform(Coating dto) {
		if (dto == null) {
			return null;
		}

		CoatingEntity entity = new CoatingEntity();
		entity.setId(dto.getId());
		entity.setSwCodeHierarchy(dto.getSwCodeHierarchy());
		entity.setSwCodeCoating(dto.getSwCodeCoating());
		entity.setUtilisation(dto.getUtilisation());
		entity.setRemarks(dto.getRemarks());
        entity.setCertDFC(dto.getCertDFCF());
		entity.setCertDFCFilename(dto.getCertDFCFilename());
        entity.setCertDFCContentType(dto.getCertDFCContentType());

		if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<CoatingInfoEntity> infoList = new HashSet<>();
			for (CoatingInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				CoatingInfoEntity info = new CoatingInfoEntity();
				info.setCoating(entity);
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setDescription(e.getDescription());
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms CoatingEntity into Coating
	 * @param entity
	 * @return {@link Coating}
	 */
	public static Coating transform(CoatingEntity entity) {
		return transform(entity, null, false);
	}
	/**
	 * Transforms CoatingEntity into Coating
	 * @param entity
	 * @return {@link Coating}
	 */
	public static Coating transform(CoatingEntity entity, boolean includeCertificate) {
		return transform(entity, null, includeCertificate);
	}

	/**
	 * Transforms CoatingEntity into Coating
	 * @param entity
	 * @return {@link Coating}
	 */
	public static Coating transform(CoatingEntity entity, Language language) {
        return transform(entity, language, false);
    }

    /**
     * Transforms CoatingEntity into Coating
	 * @param entity
	 * @return {@link Coating}
	 */
	public static Coating transform(CoatingEntity entity, Language language, boolean includedCert) {
		if (entity == null) {
			return null;
		}

		Coating dto = new Coating();
		dto.setId(entity.getId());
		dto.setSwCodeCoating(entity.getSwCodeCoating());
		dto.setSwCodeHierarchy(entity.getSwCodeHierarchy());
		dto.setRemarks(entity.getRemarks());
		dto.setUtilisation(entity.getUtilisation());
        dto.setCertDFCFilename(entity.getCertDFCFilename());

        if (includedCert) {
            dto.setCertDFCF(entity.getCertDFC());
            dto.setCertDFCContentType(entity.getCertDFCContentType());
        }

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
            Locale locale = LocaleContextHolder.getLocale();
			List<CoatingInfo> infoList = new ArrayList<>();
			for (CoatingInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

                CoatingInfo info = new CoatingInfo();
                info.setId(e.getId());
                info.setLanguage(lang);
                info.setDescription(e.getDescription());

				if (language != null) {
					if (language.equals(lang)) {
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				} else if (lang.getLocale().getLanguage().equals(locale.getLanguage())) {
                    dto.setCurrentInfo(info);
                }
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Coupling into CouplingEntity
	 * @param dto
	 * @return {@link CouplingEntity}
	 */
	public static CouplingEntity transform(Coupling dto) {
		if (dto == null) {
			return null;
		}

		CouplingEntity entity = new CouplingEntity();
		entity.setId(dto.getId());
		entity.setDrawing(dto.getDrawing());
		entity.setDrawingContentType(dto.getDrawingContentType());
		entity.setDrawingFilename(dto.getDrawingFilename());
		entity.setImage(dto.getImage());
		entity.setImageContentType(dto.getImageContentType());
		entity.setImageFilename(dto.getImageFilename());
		entity.setPipeDiameter(dto.getPipeDiameter());
		entity.setOthers(dto.getOthers());
		entity.setGasket(transform(dto.getGasket()));
		entity.setMaterial(transform(dto.getMaterial()));

		if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
			Set<CouplingInfoEntity> infoList = new HashSet<>();
			for (CouplingInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				CouplingInfoEntity info = new CouplingInfoEntity();
				info.setCoupling(entity);
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setDescription(e.getDescription());
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms CouplingEntity into Coupling
	 * @param entity
	 * @return {@link Coupling}
	 */
	public static Coupling transform(CouplingEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms CouplingEntity into Coupling
	 * @param entity
	 * @return {@link Coupling}
	 */
	public static Coupling transform(CouplingEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		Coupling dto = new Coupling();
		dto.setId(entity.getId());
		dto.setDrawing(entity.getDrawing());
		dto.setDrawingContentType(entity.getDrawingContentType());
		dto.setDrawingFilename(entity.getDrawingFilename());
		dto.setImage(entity.getImage());
		dto.setImageContentType(entity.getImageContentType());
		dto.setImageFilename(entity.getImageFilename());
		dto.setPipeDiameter(entity.getPipeDiameter());
		dto.setOthers(entity.getOthers());
		dto.setGasket(transform(entity.getGasket()));
		dto.setMaterial(transform(entity.getMaterial()));

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
			List<CouplingInfo> infoList = new ArrayList<>();
			for (CouplingInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				if (language != null) {
					if (language.equals(lang)) {
						CouplingInfo info = new CouplingInfo();
						info.setId(e.getId());
						info.setLanguage(Language.valueOf(e.getLanguage().getId()));
						info.setDescription(e.getDescription());
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				}

				CouplingInfo info = new CouplingInfo();
				info.setId(e.getId());
				info.setLanguage(Language.valueOf(e.getLanguage().getId()));
				info.setDescription(e.getDescription());
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Closure into ClosureEntity
	 * @param dto
	 * @return {@link ClosureEntity}
	 */
	public static ClosureEntity transform(Closure dto) {
		if (dto == null) {
			return null;
		}

		ClosureEntity entity = new ClosureEntity();
		entity.setId(dto.getId());

        if (dto.getGasket() != null && dto.getGasket().getId() != null) {
            GasketEntity gasketEntity = new GasketEntity();
            gasketEntity.setId(dto.getGasket().getId());
            entity.setGasket(gasketEntity);
        }

        if (dto.getMaterial() != null && dto.getMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getMaterial().getId());
            entity.setMaterial(materialEntity);
        }

        if (dto.getInsideCoating() != null && dto.getInsideCoating().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getInsideCoating().getId());
            entity.setInsideCoating(coatingEntity);
        }

        if (dto.getOutsideCoating() != null && dto.getOutsideCoating().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getOutsideCoating().getId());
            entity.setOutsideCoating(coatingEntity);
        }

		entity.setRemarks(dto.getRemarks());
		entity.setDiameter(dto.getDiameter());

		if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<ClosureInfoEntity> infoList = new HashSet<>();
			for (ClosureInfo e : dto.getInfoList()) {// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				ClosureInfoEntity info = new ClosureInfoEntity();
				info.setClosure(entity);
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setName(e.getName());
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms ClosureEntity into Closure
	 * @param entity
	 * @return {@link Closure}
	 */
	public static Closure transform(ClosureEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms ClosureEntity into Closure
	 * @param entity
	 * @return {@link Closure}
	 */
	public static Closure transform(ClosureEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		Closure dto = new Closure();
		dto.setId(entity.getId());
		dto.setGasket(transform(entity.getGasket()));
		dto.setMaterial(transform(entity.getMaterial()));
		dto.setInsideCoating(transform(entity.getInsideCoating()));
		dto.setOutsideCoating(transform(entity.getOutsideCoating()));
		dto.setRemarks(entity.getRemarks());
		dto.setDiameter(entity.getDiameter());

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
            Locale locale = LocaleContextHolder.getLocale();
			List<ClosureInfo> infoList = new ArrayList<>();
			for (ClosureInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				ClosureInfo info = new ClosureInfo();
                info.setId(e.getId());
                info.setLanguage(lang);
                info.setName(e.getName());
				if (language != null){
					if (language.equals(lang)) {
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				} else if (lang.getLocale().getLanguage().equals(locale.getLanguage())) {
                    dto.setCurrentInfo(info);
                }
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

    /**
     * Transforms PaletteType into PaletteTypeEntity
     * @param dto
     * @return {@link PaletteTypeEntity}
     */
    public static PaletteTypeEntity transform(PaletteType dto) {
        if (dto == null) {
            return null;
        }

        PaletteTypeEntity entity = new PaletteTypeEntity();
        entity.setId(dto.getId());

        if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<PaletteTypeInfoEntity> infoList = new HashSet<>();
            for (PaletteTypeInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

                PaletteTypeInfoEntity info = new PaletteTypeInfoEntity();
                info.setId(e.getId());
                info.setPaletteType(entity);
                info.setLanguage(new LanguageEntity(e.getLanguage()));
                info.setType(e.getType());
                infoList.add(info);
            }
            entity.setInfoList(infoList);
        }
        fromModelToEntity(dto, entity);
        return entity;
    }

    /**
     * Transforms PaletteTypeEntity into PaletteType
     * @param entity
     * @return {@link PaletteType}
     */
    public static PaletteType transform(PaletteTypeEntity entity) {
       	return transform(entity, null);
    }

    /**
     * Transforms PaletteTypeEntity into PaletteType
     * @param entity
     * @return {@link PaletteType}
     */
    public static PaletteType transform(PaletteTypeEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        PaletteType dto = new PaletteType();
        dto.setId(entity.getId());

        if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
            Locale locale = LocaleContextHolder.getLocale();
            List<PaletteTypeInfo> infoList = new ArrayList<>();
            for (PaletteTypeInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				PaletteTypeInfo info = new PaletteTypeInfo();
                info.setId(e.getId());
                info.setLanguage(lang);
                info.setType(e.getType());

				if (language != null) {
					if (language.equals(lang)) {
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				} else if (lang.getLocale().getLanguage().equals(locale.getLanguage())) {
                    dto.setCurrentInfo(info);
                }

                infoList.add(info);
            }
            dto.setInfoList(infoList);
        }
        fromEntityToModel(entity, dto);
        return dto;
    }

    /**
	 * Transforms AggregateState into AggregateStateEntity
	 * @param dto
	 * @return {@link AggregateStateEntity}
	 */
	public static AggregateStateEntity transform(AggregateState dto) {
		if (dto == null) {
			return null;
		}

		AggregateStateEntity entity = new AggregateStateEntity();
		entity.setId(dto.getId());
		if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<AggregateStateInfoEntity> infoList = new HashSet<>();
			for (AggregateStateInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				AggregateStateInfoEntity info = new AggregateStateInfoEntity();
				info.setState(e.getState());
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setAggregateState(entity);
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms AggregateStateEntity into AggregateState
	 * @param entity
	 * @return {@link AggregateState}
	 */
	public static AggregateState transform(AggregateStateEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms AggregateStateEntity into AggregateState
	 * @param entity
	 * @return {@link AggregateState}
	 */
	public static AggregateState transform(AggregateStateEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		AggregateState dto = new AggregateState();
		dto.setId(entity.getId());

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
			List<AggregateStateInfo> infoList = new ArrayList<>();
			for (AggregateStateInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				if (language != null ) {
					if (language.equals(lang)) {
						AggregateStateInfo info = new AggregateStateInfo();
						info.setId(e.getId());
						info.setLanguage(lang);
						info.setState(e.getState());
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				}

				AggregateStateInfo info = new AggregateStateInfo();
				info.setId(e.getId());
				info.setLanguage(Language.valueOf(e.getLanguage().getId()));
				info.setState(e.getState());
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

    /**
     * Transforms PackagingForm into PackagingFormEntity
     * @param dto
     * @return {@link PackagingFormEntity}
     */
    public static PackagingFormEntity transform(PackagingForm dto) {
        if (dto == null) {
            return null;
        }

        PackagingFormEntity entity = new PackagingFormEntity();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setOthers(dto.getOthers());
        if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<PackagingFormInfoEntity> infoList = new HashSet<>();
            for (PackagingFormInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

                PackagingFormInfoEntity info = new PackagingFormInfoEntity();
                info.setId(e.getId());
                info.setPackagingForm(entity);
                info.setLanguage(new LanguageEntity(e.getLanguage()));
                info.setForm(e.getForm());
                infoList.add(info);
            }
            entity.setInfoList(infoList);
        }
        fromModelToEntity(dto, entity);
        return entity;
    }

    /**
     * Transforms PackagingFormEntity into PackagingForm
     * @param entity
     * @return {@link PackagingForm}
     */
    public static PackagingForm transform(PackagingFormEntity entity) {
        return transform(entity, null);
    }

    /**
     * Transforms PackagingFormEntity into PackagingForm
     * @param entity
     * @return {@link PackagingForm}
     */
    public static PackagingForm transform(PackagingFormEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        PackagingForm dto = new PackagingForm();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setOthers(entity.getOthers());

        if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
            List<PackagingFormInfo> infoList = new ArrayList<>();
            for (PackagingFormInfoEntity e : entity.getInfoList()) {
                // if language is specified we need to transform only for specified language
                Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

                if (language != null) {
                    if (language.equals(lang))  {
                        PackagingFormInfo info = new PackagingFormInfo();
                        info.setId(e.getId());
                        info.setLanguage(lang);
                        info.setForm(e.getForm());
                        dto.setCurrentInfo(info);
                        break;
                    }
                    continue;
                }

                PackagingFormInfo info = new PackagingFormInfo();
                info.setId(e.getId());
                info.setLanguage(Language.valueOf(e.getLanguage().getId()));
                info.setForm(e.getForm());
                infoList.add(info);
            }
            dto.setInfoList(infoList);
        }
        fromEntityToModel(entity, dto);
        return dto;
    }

    /**
     * Transforms ExZone into ExZoneEntity
     * @param dto
     * @return {@link ExZoneEntity}
     */
    public static ExZoneEntity transform(ExZone dto) {
        if (dto == null) {
            return null;
        }

        ExZoneEntity entity = new ExZoneEntity();
        entity.setId(dto.getId());
        entity.setExZoneNumber(dto.getExZoneNumber());
        entity.setOther(dto.getOther());
        if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<ExZoneInfoEntity> infoList = new HashSet<>();
            for (ExZoneInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

                ExZoneInfoEntity info = new ExZoneInfoEntity();
                info.setId(e.getId());
                info.setExZone(entity);
                info.setLanguage(new LanguageEntity(e.getLanguage()));
                info.setName(e.getName());
                infoList.add(info);
            }
            entity.setInfoList(infoList);
        }
        fromModelToEntity(dto, entity);
        return entity;
    }

    /**
     * Transforms ExZoneEntity into ExZone
     * @param entity
     * @return {@link ExZone}
     */
    public static ExZone transform(ExZoneEntity entity) {
        return transform(entity, null);
    }

    /**
     * Transforms ExZoneEntity into ExZone
     * @param entity
     * @return {@link ExZone}
     */
    public static ExZone transform(ExZoneEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        ExZone dto = new ExZone();
        dto.setId(entity.getId());
        dto.setExZoneNumber(entity.getExZoneNumber());
        dto.setOther(entity.getOther());

        if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
            Locale locale = LocaleContextHolder.getLocale();
            List<ExZoneInfo> infoList = new ArrayList<>();
            for (ExZoneInfoEntity e : entity.getInfoList()) {
                // if language is specified we need to transform only for specified language
                Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

                ExZoneInfo info = new ExZoneInfo();
                info.setId(e.getId());
                info.setLanguage(lang);
                info.setName(e.getName());

                if (language != null) {
                    if (language.equals(lang))  {
                        dto.setCurrentInfo(info);
                        break;
                    }
                    continue;
                } else if (lang.getLocale().getLanguage().equals(locale.getLanguage())) {
                    dto.setCurrentInfo(info);
                }
                infoList.add(info);
            }
            dto.setInfoList(infoList);
        }
        fromEntityToModel(entity, dto);
        return dto;
    }

	/**
	 * Transforms Location into LocationEntity
	 * @param dto
	 * @return {@link LocationEntity}
	 */
	public static LocationEntity transform(Location dto) {
		if (dto == null) {
			return null;
		}

		LocationEntity entity = new LocationEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setSapCode(dto.getSapCode());
		entity.setOthers(dto.getOthers());
		entity.setAddress(transform(dto.getAddress()));
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms LocationEntity into Location
	 * @param entity
	 * @return {@link Location}
	 */
	public static Location transform(LocationEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms LocationEntity into Location
	 * @param entity
	 * @return {@link Location}
	 */
	public static Location transform(LocationEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		Location dto = new Location();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSapCode(entity.getSapCode());
		dto.setOthers(entity.getOthers());
		dto.setAddress(transform(entity.getAddress(), language));
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Address into AddressEntity
	 * @param dto
	 * @return {@link AddressEntity}
	 */
	public static AddressEntity transform(Address dto) {
		if (dto == null) {
			return null;
		}

		AddressEntity entity = new AddressEntity();
		entity.setId(dto.getId());
		entity.setCity(dto.getCity());
		entity.setStreet(dto.getStreet());
		entity.setZipCode(dto.getZipCode());
		entity.setCountry(transform(dto.getCountry()));
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms AddressEntity into Address
	 * @param entity
	 * @return {@link Address}
	 */
	public static Address transform(AddressEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms AddressEntity into Address
	 * @param entity
	 * @return {@link Address}
	 */
	public static Address transform(AddressEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		Address dto = new Address();
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setCity(entity.getCity());
		dto.setStreet(entity.getStreet());
		dto.setZipCode(entity.getZipCode());
		dto.setCountry(transform(entity.getCountry(), language));
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Country into CountryEntity
	 * @param dto
	 * @return {@link CountryEntity}
	 */
	public static CountryEntity transform(Country dto) {
		if (dto == null) {
			return null;
		}

		CountryEntity entity = new CountryEntity();
		entity.setId(dto.getId());
		entity.setMatchCode(dto.getMatchCode());
		if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
			Set<CountryInfoEntity> infoList = new HashSet<>();
			for (CountryInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				CountryInfoEntity info = new CountryInfoEntity();
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setName(e.getName());
				info.setCountry(entity);
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms CountryEntity into Country
	 * @param entity
	 * @return {@link Country}
	 */
	public static Country transform(CountryEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms CountryEntity into Country
	 * @param entity
	 * @return {@link Country}
	 */
	public static Country transform(CountryEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		Country dto = new Country();
		dto.setId(entity.getId());
		dto.setMatchCode(entity.getMatchCode());
		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
			List<CountryInfo> infoList = new ArrayList<>();
			for (CountryInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				if (language != null) {
					if (language.equals(lang))  {
						CountryInfo info = new CountryInfo();
						info.setId(e.getId());
						info.setLanguage(lang);
						info.setName(e.getName());
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				}

				CountryInfo info = new CountryInfo();
				info.setId(e.getId());
				info.setLanguage(lang);
				info.setName(e.getName());
				if (Language.getDefault() == lang) {
					dto.setCurrentInfo(info);
				}
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms CompanyCategory into CompanyCategoryEntity
	 * @param dto
	 * @return {@link CompanyCategoryEntity}
	 */
	public static CompanyCategoryEntity transform(CompanyCategory dto) {
		if (dto == null) {
			return null;
		}

		CompanyCategoryEntity entity = new CompanyCategoryEntity();
		entity.setId(dto.getId());
		if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<CompanyCategoryInfoEntity> infoList = new HashSet<>();
			for (CompanyCategoryInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				CompanyCategoryInfoEntity info = new CompanyCategoryInfoEntity();
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setName(e.getName());
				info.setCompanyCategory(entity);
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		entity.setRemarks(dto.getRemarks());
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms CompanyCategoryEntity into CompanyCategory
	 * @param entity
	 * @return {@link CompanyCategory}
	 */
	public static CompanyCategory transform(CompanyCategoryEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms CompanyCategoryEntity into CompanyCategory
	 * @param entity
	 * @return {@link CompanyCategory}
	 */
	public static CompanyCategory transform(CompanyCategoryEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		CompanyCategory dto = new CompanyCategory();
		dto.setId(entity.getId());
		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
            Locale locale = LocaleContextHolder.getLocale();
			List<CompanyCategoryInfo> infoList = new ArrayList<>();
			for (CompanyCategoryInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				CompanyCategoryInfo info = new CompanyCategoryInfo();
				info.setId(e.getId());
				info.setLanguage(lang);
				info.setName(e.getName());

                if (language != null) {
                    if (language.equals(lang)) {
                        dto.setCurrentInfo(info);
                        break;
                    }
                    continue;
                } else if (lang.getLocale().getLanguage().equals(locale.getLanguage())) {
                    dto.setCurrentInfo(info);
                }
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		dto.setRemarks(entity.getRemarks());
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Company into CompanyEntity
	 * @param dto
	 * @return {@link CompanyEntity}
	 */
	public static CompanyEntity transform(Company dto) {
		if (dto == null) {
			return null;
		}

		CompanyEntity entity = new CompanyEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setAddress(transform(dto.getAddress()));
		if (CollectionHelper.isNotEmpty(dto.getCompanyCategories())) {
            Set<CompanyCategoryEntity> categoryEntities = new HashSet<>();
			for (CompanyCategory e : dto.getCompanyCategories()) {
				CompanyCategoryEntity categoryEntity = new CompanyCategoryEntity();
                categoryEntity.setId(e.getId());
				categoryEntities.add(categoryEntity);
			}
			entity.setCompanyCategory(categoryEntities);
		}
		entity.setFax(dto.getFax());
		entity.setPhone(dto.getPhone());
		entity.setVendorNumber(dto.getVendorNumber());
		entity.setWebsite(dto.getWebsite());
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms CompanyEntity into Company
	 * @param entity
	 * @return {@link Company}
	 */
	public static Company transform(CompanyEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms CompanyEntity into Company
	 * @param entity
	 * @return {@link Company}
	 */
	public static Company transform(CompanyEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		Company dto = new Company();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setAddress(transform(entity.getAddress()));
		if (CollectionHelper.isNotEmpty(entity.getCompanyCategory())) {
			List<CompanyCategory> categories = new ArrayList<>();
			for (CompanyCategoryEntity e : entity.getCompanyCategory()) {
				CompanyCategory category = transform(e);
				categories.add(category);
			}
			dto.setCompanyCategories(categories);
		}
		dto.setFax(entity.getFax());
		dto.setPhone(entity.getPhone());
		dto.setVendorNumber(entity.getVendorNumber());
		dto.setWebsite(entity.getWebsite());
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms Process into ProcessEntity
	 * @param dto
	 * @return {@link ProcessEntity}
	 */
	public static ProcessEntity transform(Process dto) {
		if (dto == null) {
			return null;
		}

		ProcessEntity entity = new ProcessEntity();
		entity.setId(dto.getId());
		entity.setRemarks(dto.getRemarks());
		entity.setProcessType(dto.getProcessType());
		entity.setProcessFile(dto.getProcessFile());
		entity.setProcessFilename(dto.getProcessFilename());
		entity.setProcessFileContentType(dto.getProcessFileContentType());
		if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<ProcessInfoEntity> infoList = new HashSet<>();
			for (ProcessInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				ProcessInfoEntity info = new ProcessInfoEntity();
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setName(e.getName());
				info.setProcess(entity);
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms ProcessEntity into Process
	 * @param entity
	 * @return {@link Process}
	 */
	public static Process transform(ProcessEntity entity, boolean includeCertificate) {
		return transform(entity, null, includeCertificate);
	}

	/**
	 * Transforms ProcessEntity into Process
	 * @param entity
	 * @return {@link Process}
	 */
	public static Process transform(ProcessEntity entity) {
		return transform(entity, null, false);
	}

	/**
	 * Transforms ProcessEntity into Process
	 * @param entity
	 * @return {@link Process}
	 */
	public static Process transform(ProcessEntity entity, Language language) {
		return transform(entity, language, false);
	}

	/**
	 * Transforms ProcessEntity into Process
	 * @param entity
	 * @return {@link Process}
	 */
	public static Process transform(ProcessEntity entity, Language language, boolean includedCert) {
		if (entity == null) {
			return null;
		}

		Process dto = new Process();
		dto.setId(entity.getId());
		dto.setRemarks(entity.getRemarks());
		dto.setProcessType(entity.getProcessType());
		dto.setProcessFilename(entity.getProcessFilename());

		if (includedCert) {
			dto.setProcessFile(entity.getProcessFile());
			dto.setProcessFileContentType(entity.getProcessFileContentType());
		}

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
            Locale locale = LocaleContextHolder.getLocale();
			List<ProcessInfo> infoList = new ArrayList<>();
			for (ProcessInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;


				ProcessInfo info = new ProcessInfo();
				info.setId(e.getId());
				info.setLanguage(lang);
				info.setName(e.getName());
                if (language != null) {
                    if (language.equals(lang)) {
                        dto.setCurrentInfo(info);
                        break;
                    }
                    continue;
                } else if (lang.getLocale().getLanguage().equals(locale.getLanguage())) {
                    dto.setCurrentInfo(info);
                }
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms News with files data into NewsEntity including files
	 * @param dto
	 * @return {@link NewsEntity}
	 */
	public static NewsEntity transform(News dto) {
		if (dto == null) {
			return null;
		}

		NewsEntity entity = new NewsEntity();
		entity.setId(dto.getId());
		entity.setExpirationDate(dto.getExpirationDate());
		entity.setNewsDate(dto.getNewsDate());
		entity.setRemarks(dto.getRemarks());
		entity.setNewsFilename(dto.getNewsFilename());
		entity.setNewsFile(dto.getNewsFile());
		entity.setNewsFileContentType(dto.getNewsFileContentType());
		entity.setNewsLinkFilename(dto.getNewsLinkFilename());
		entity.setNewsLink(dto.getNewsLink());
		entity.setNewsLinkFileContentType(dto.getNewsLinkFileContentType());

		if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
			Set<NewsInfoEntity> infoList = new HashSet<>();
			for (NewsInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				NewsInfoEntity info = new NewsInfoEntity();
				info.setNews(entity);
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setTitle(e.getTitle());
				info.setText(e.getText());
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms NewsEntity into News
	 * @param entity
	 * @return {@link News}
	 */
	public static News transform(NewsEntity entity) {
		return transform(entity, null, false);
	}
	/**
	 * Transforms NewsEntity into News
	 * @param entity
	 * @return {@link News}
	 */
	public static News transform(NewsEntity entity, boolean includeFiles) {
		return transform(entity, null, includeFiles);
	}

	/**
	 * Transforms NewsEntity into News
	 * @param entity
	 * @return {@link News}
	 */
	public static News transform(NewsEntity entity, Language language) {
		return transform(entity, language, false);
	}

	/**
	 * Transforms NewsEntity into News
	 * @param entity
	 * @return {@link News}
	 */
	public static News transform(NewsEntity entity, Language language, boolean includedFiles) {
		if (entity == null) {
			return null;
		}

		News dto = new News();
		dto.setId(entity.getId());
		dto.setExpirationDate(entity.getExpirationDate());
		dto.setNewsDate(entity.getNewsDate());
		dto.setRemarks(entity.getRemarks());
		dto.setNewsFilename(entity.getNewsFilename());
		dto.setNewsLinkFilename(entity.getNewsLinkFilename());

		if (includedFiles) {
			dto.setNewsFile(entity.getNewsFile());
			dto.setNewsFileContentType(entity.getNewsFileContentType());
			dto.setNewsLink(entity.getNewsLink());
			dto.setNewsLinkFileContentType(entity.getNewsLinkFileContentType());
		}

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
			Locale locale = LocaleContextHolder.getLocale();
			List<NewsInfo> infoList = new ArrayList<>();
			for (NewsInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				NewsInfo info = new NewsInfo();
				info.setId(e.getId());
				info.setLanguage(lang);
				info.setTitle(e.getTitle());
				info.setText(e.getText());

				if (language != null) {
					if (language.equals(lang)) {
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				} else if (lang.getLocale().getLanguage().equals(locale.getLanguage())) {
					dto.setCurrentInfo(info);
				}
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}


	//endregion

	/**
	 * Transforms UnTestMedium into UnTestMediumEntity
	 * @param dto
	 * @return {@link UnTestMediumEntity}
	 */
	public static UnTestMediumEntity transform(UnTestMedium dto) {
		if (dto == null) {
			return null;
		}

		UnTestMediumEntity entity = new UnTestMediumEntity();
		entity.setId(dto.getId());
		entity.setCode(dto.getCode());
		entity.setOthers(dto.getOthers());

		if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<UnTestMediumInfoEntity> infoList = new HashSet<>();
			for (UnTestMediumInfo e : dto.getInfoList()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				UnTestMediumInfoEntity info = new UnTestMediumInfoEntity();
				info.setTestMedium(entity);
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setMedium(e.getMedium());
				infoList.add(info);
			}
			entity.setInfoList(infoList);
		}
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms UnTestMediumEntity into UnTestMedium
	 * @param entity
	 * @return {@link UnTestMedium}
	 */
	public static UnTestMedium transform(UnTestMediumEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms UnTestMediumEntity into UnTestMedium
	 * @param entity
	 * @return {@link UnTestMedium}
	 */
	public static UnTestMedium transform(UnTestMediumEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		UnTestMedium dto = new UnTestMedium();
		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setOthers(entity.getOthers());

		if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
			List<UnTestMediumInfo> infoList = new ArrayList<>();
			for (UnTestMediumInfoEntity e : entity.getInfoList()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				if (language != null) {
					if (language.equals(lang)) {
						UnTestMediumInfo info = new UnTestMediumInfo();
						info.setId(e.getId());
						info.setLanguage(Language.valueOf(e.getLanguage().getId()));
						info.setMedium(e.getMedium());
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				}

				UnTestMediumInfo info = new UnTestMediumInfo();
				info.setId(e.getId());
				info.setLanguage(Language.valueOf(e.getLanguage().getId()));
				info.setMedium(e.getMedium());
				infoList.add(info);
			}
			dto.setInfoList(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}

	/**
	 * Transforms UnApproval into UnApprovalEntity
	 * @param dto
	 * @return {@link UnApprovalEntity}
	 */
	public static UnApprovalEntity transform(UnApproval dto) {
		if (dto == null) {
			return null;
		}

		UnApprovalEntity entity = new UnApprovalEntity();
		entity.setId(dto.getId());
		if (dto.getTestMedium() != null && dto.getTestMedium().getId() != null) {
			UnTestMediumEntity testMediumEntity = new UnTestMediumEntity();
			testMediumEntity.setId(dto.getTestMedium().getId());
			entity.setTestMedium(testMediumEntity);
		}

		if (dto.getCompany() != null && dto.getCompany().getId() != null) {
			CompanyEntity companyEntity = new CompanyEntity();
			companyEntity.setId(dto.getCompany().getId());
			entity.setCompany(companyEntity);
		}

		if (dto.getAssimilationList() != null && dto.getAssimilationList().getId() != null) {
			UnAssimilationListEntity unAssimilationListEntity = new UnAssimilationListEntity();
			unAssimilationListEntity.setId(dto.getAssimilationList().getId());
			entity.setAssimilationList(unAssimilationListEntity);
		}

		if (dto.getPackagingInstruction() != null && dto.getPackagingInstruction().getId() != null) {
			UnPackagingInstructionEntity unPackagingInstructionEntity = new UnPackagingInstructionEntity();
			unPackagingInstructionEntity.setId(dto.getPackagingInstruction().getId());
			entity.setVerpInstruction(unPackagingInstructionEntity);
		}

		if (dto.getTransport() != null && dto.getTransport().getId() != null) {
			UnTransportEntity transportEntity = new UnTransportEntity();
			transportEntity.setId(dto.getTransport().getId());
			entity.setTransport(transportEntity);
		}

		entity.setDensityX(dto.getDensityX());
		entity.setDensityY(dto.getDensityY());
		entity.setDensityZ(dto.getDensityZ());
		entity.setUnCode(dto.getUnCode());
		entity.setVaporPressure50(dto.getVaporPressure50());
		entity.setVaporPressure55(dto.getVaporPressure55());
		entity.setDateOfIssue(dto.getDateOfIssue());
		entity.setUnVersion(dto.getUnVersion());
		entity.setUnit(dto.getUnit());
		entity.setMaxGrossWeight(dto.getMaxGrossWeight());
		entity.setSleeps(dto.getSleeps());
		entity.setPermeation(dto.getPermeation());
		entity.setAggregateState(dto.getAggregateState());
		entity.setFile(dto.getFile());
		entity.setFileFilename(dto.getFileFilename());
		entity.setFileContentType(dto.getFileContentType());

		if (CollectionHelper.isNotEmpty(dto.getAuthorizationRooms())) {
            Set<UnApprovalInfoEntity> infoList = new HashSet<>();
			for (UnApprovalInfo e : dto.getAuthorizationRooms()) {
				// if language is disabled continue
				if (e.getLanguage() == null || e.getLanguage().getStatus() != Status.ACTIVE) continue;

				UnApprovalInfoEntity info = new UnApprovalInfoEntity();
				info.setAuthorization(entity);
				info.setId(e.getId());
				info.setLanguage(new LanguageEntity(e.getLanguage()));
				info.setRoomName(e.getRoomName());
				infoList.add(info);
			}
			entity.setAuthorizationRooms(infoList);
		}
		fromModelToEntity(dto, entity);
		return entity;
	}

	/**
	 * Transforms UnApprovalEntity into UnApproval
	 * @param entity
	 * @return {@link UnApproval}
	 */
	public static UnApproval transform(UnApprovalEntity entity) {
		return transform(entity, null);
	}

	/**
	 * Transforms UnApprovalEntity into UnApproval
	 * @param entity
	 * @return {@link UnApproval}
	 */
	public static UnApproval transform(UnApprovalEntity entity, Language language) {
		if (entity == null) {
			return null;
		}

		UnApproval dto = new UnApproval();
		dto.setId(entity.getId());

		dto.setTestMedium(transform(entity.getTestMedium()));
		dto.setCompany(transform(entity.getCompany()));
		dto.setAssimilationList(transform(entity.getAssimilationList()));
		dto.setPackagingInstruction(transform(entity.getVerpInstruction()));
		dto.setTransport(transform(entity.getTransport()));

		dto.setDensityX(entity.getDensityX());
		dto.setDensityY(entity.getDensityY());
		dto.setDensityZ(entity.getDensityZ());
		dto.setUnCode(entity.getUnCode());
		dto.setVaporPressure50(entity.getVaporPressure50());
		dto.setVaporPressure55(entity.getVaporPressure55());
		dto.setDateOfIssue(entity.getDateOfIssue());
		dto.setUnVersion(entity.getUnVersion());
		dto.setUnit(entity.getUnit());
		dto.setMaxGrossWeight(entity.getMaxGrossWeight());
		dto.setSleeps(entity.getSleeps());
		dto.setPermeation(entity.getPermeation());
		dto.setAggregateState(entity.getAggregateState());
		dto.setFile(entity.getFile());
		dto.setFileFilename(entity.getFileFilename());
		dto.setFileContentType(entity.getFileContentType());

		if (CollectionHelper.isNotEmpty(entity.getAuthorizationRooms())) {
			List<UnApprovalInfo> infoList = new ArrayList<>();
			for (UnApprovalInfoEntity e : entity.getAuthorizationRooms()) {
				// if language is specified we need to transform only for specified language
				Language lang = Language.valueOf(e.getLanguage().getId());
				if (lang == null || lang.getStatus() != Status.ACTIVE) continue;

				if (language != null) {
					if (language.equals(lang)) {
						UnApprovalInfo info = new UnApprovalInfo();
						info.setId(e.getId());
						info.setLanguage(Language.valueOf(e.getLanguage().getId()));
						info.setRoomName(e.getRoomName());
						dto.setCurrentInfo(info);
						break;
					}
					continue;
				}

				UnApprovalInfo info = new UnApprovalInfo();
				info.setId(e.getId());
				info.setLanguage(Language.valueOf(e.getLanguage().getId()));
				info.setRoomName(e.getRoomName());
				infoList.add(info);
			}
			dto.setAuthorizationRooms(infoList);
		}
		fromEntityToModel(entity, dto);
		return dto;
	}
}
