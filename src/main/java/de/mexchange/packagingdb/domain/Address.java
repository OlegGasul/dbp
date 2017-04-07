package de.mexchange.packagingdb.domain;

/**
 * Created by Garik on 5/8/16.
 */
public class Address extends AbstractModel {

	private Country country;

	private String street;

	private String zipCode;

	private String city;

	public Country getCountry() {
		return country;
	}

	public Address setCountry(Country country) {
		this.country = country;
		return this;
	}

	public String getStreet() {
		return street;
	}

	public Address setStreet(String street) {
		this.street = street;
		return this;
	}

	public String getZipCode() {
		return zipCode;
	}

	public Address setZipCode(String zipCode) {
		this.zipCode = zipCode;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Address setCity(String city) {
		this.city = city;
		return this;
	}
}
