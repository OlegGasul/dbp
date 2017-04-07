package de.mexchange.packagingdb.entity.converter;

import de.mexchange.packagingdb.domain.lcp.ContainerStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ContainerStatusConverter implements AttributeConverter<ContainerStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ContainerStatus status) {
		return status.getValue();
	}

	@Override
	public ContainerStatus convertToEntityAttribute(Integer value) {
		return ContainerStatus.valueOf(value);
	}
}