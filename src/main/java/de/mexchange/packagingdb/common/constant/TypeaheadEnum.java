package de.mexchange.packagingdb.common.constant;

import de.mexchange.packagingdb.domain.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Garik on 5/11/16.
 */
public enum TypeaheadEnum {

	ADDRESS			    	(1, Address.class),
	BUSINESS_UNIT 	    	(2, BusinessUnit.class),
	COUNTRY			    	(3, Country.class),
	COATING			    	(4, Coating.class),
	MATERIAL		    	(5, Material.class),
	GASKET			    	(6, Gasket.class),
	LOCATION		    	(7, Location.class),
	CLOSURE		        	(8, Closure.class),
	PALETTETYPE		    	(9, PaletteType.class),
	EXZONE		        	(10, ExZone.class),
	COMPANY_CATEGORY    	(11, CompanyCategory.class),

	UN_TEST_MEDIUM      	(12, UnTestMedium.class),
	COMPANY		        	(13, Company.class),
	ASSIMILATION_LIST		(14, AssimilationList.class),
	PACKAGING_INSTRUCTION	(15, PackagingInstruction.class),
	TRANSPORT           	(16, Transport.class),
	HANDLE           		(17, Handle.class),
	COUPLING           		(18, Coupling.class),
	;

	TypeaheadEnum(final int value, final Class<? extends AbstractModel> clazz) {
		this.value = value;
		this.clazz = clazz;
	}

	public static TypeaheadEnum valueOf(int value) {
		for (TypeaheadEnum item : values()) {
			if (item.value == value) {
				return item;
			}
		}
		return null;
	}

	public static TypeaheadEnum classOf(Class<? extends AbstractModel> clazz) {
		for (TypeaheadEnum item : values()) {
			if (item.clazz == clazz) {
				return item;
			}
		}
		return null;
	}

	public static boolean isSupported(Class<? extends AbstractModel> clazz) {
		return classes.contains(clazz);
	}

	private final int value;

	private final Class<? extends AbstractModel> clazz;

	private static Set<Class> classes = new HashSet<Class>() {{
		for (TypeaheadEnum item : values()) {
			add(item.getClazz());
		}
	}};

	public int getValue() {
		return value;
	}

	public Class<? extends AbstractModel> getClazz() {
		return clazz;
	}
}
