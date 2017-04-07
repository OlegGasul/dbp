package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.Language;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Garik on 5/8/16.
 */
public class CountryInfo extends AbstractModel {


    @NotNull(message = "{err.field.country.language.not.specified}")
    private Language language;

    @NotEmpty(message = "{err.field.country.name.required}")
	private String name;

	public Language getLanguage() {
		return language;
	}

	public CountryInfo setLanguage(Language language) {
		this.language = language;
		return this;
	}

	public String getName() {
		return name;
	}

	public CountryInfo setName(String name) {
		this.name = name;
		return this;
	}
}
