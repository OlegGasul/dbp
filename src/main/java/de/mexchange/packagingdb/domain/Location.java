package de.mexchange.packagingdb.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Garik on 5/8/16.
 */
public class Location extends AbstractModel implements Searchable {

    @NotEmpty(message = "{err.field.location.name.required}")
	private String name;

	private String sapCode;

	private String others;

	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSapCode() {
		return sapCode;
	}

	public Location setSapCode(String sapCode) {
		this.sapCode = sapCode;
		return this;
	}

	public String getOthers() {
		return others;
	}

	public Location setOthers(String others) {
		this.others = others;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public Location setAddress(Address address) {
		this.address = address;
		return this;
	}
}
