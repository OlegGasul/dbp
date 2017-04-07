package de.mexchange.packagingdb.entity.converter;

import de.mexchange.packagingdb.domain.lcp.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Status status) {
		return status.getValue();
	}

	@Override
	public Status convertToEntityAttribute(Integer value) {
		return Status.valueOf(value);
	}
}