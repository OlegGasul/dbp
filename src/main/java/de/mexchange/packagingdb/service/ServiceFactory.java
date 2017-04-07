package de.mexchange.packagingdb.service;

import de.mexchange.packagingdb.common.exception.NoServiceFoundException;
import de.mexchange.packagingdb.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Garik on 5/8/16.
 */
@Component
public class ServiceFactory {

	@Autowired
	private BusinessUnitService businessUnitService;

	@Autowired
	private CoatingService coatingService;

	@Autowired
	private ClosureService closureService;

	@Autowired
	private GasketService gasketService;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private ExZoneService exZoneService;

	@Autowired
	private CompanyCategoryService companyCategoryService;

	@Autowired
	private PaletteTypeService paletteTypeService;

	//todo
	@Autowired
	private UnTestMediumService unTestMediumService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AssimilationListService assimilationListService;

	@Autowired
	private VerpInstructionService verpInstructionService;

	@Autowired
	private TransportService transportService;

	@Autowired
	private CouplingService couplingService;


	public ModelService get(Class clazz) throws NoServiceFoundException {
		if (BusinessUnit.class == clazz) {
			return (ModelService) businessUnitService;
		}
		if (Closure.class == clazz) {
			return (ModelService) closureService;
		}
		if (Coating.class == clazz) {
			return (ModelService) coatingService;
		}
		if (Material.class == clazz) {
			return (ModelService) materialService;
		}
		if (Gasket.class == clazz) {
			return (ModelService) gasketService;
		}
		if (Location.class == clazz) {
			return (ModelService) locationService;
		}
		if (Country.class == clazz) {
			return (ModelService) countryService;
		}
		if (PaletteType.class == clazz) {
			return (ModelService) paletteTypeService;
		}
		if (ExZone.class == clazz) {
			return (ModelService) exZoneService;
		}
		if (CompanyCategory.class == clazz) {
			return (ModelService) companyCategoryService;
		}
		if (UnTestMedium.class == clazz) {
			return (ModelService) unTestMediumService;
		}
		if (Company.class == clazz) {
			return (ModelService) companyService;
		}
		if (AssimilationList.class == clazz) {
			return (ModelService) assimilationListService;
		}
		if (PackagingInstruction.class == clazz) {
			return (ModelService) verpInstructionService;
		}
		if (Transport.class == clazz) {
			return (ModelService) transportService;
		}
		if (Coupling.class == clazz) {
			return (ModelService) couplingService;
		}

		throw new NoServiceFoundException(clazz);
	}
}
