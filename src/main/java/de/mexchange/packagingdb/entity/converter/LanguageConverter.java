package de.mexchange.packagingdb.entity.converter;

import de.mexchange.packagingdb.domain.lcp.Language;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LanguageConverter implements AttributeConverter<Language, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Language language) {
		return language.getValue();
	}

	@Override
	public Language convertToEntityAttribute(Integer value) {
		return Language.valueOf(value);
	}
}