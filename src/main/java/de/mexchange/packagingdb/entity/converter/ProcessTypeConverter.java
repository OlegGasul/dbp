package de.mexchange.packagingdb.entity.converter;

import de.mexchange.packagingdb.domain.lcp.ProcessType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Serozh on 6/7/2016.
 */
@Converter(autoApply = true)
public class ProcessTypeConverter implements AttributeConverter<ProcessType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProcessType process) {
        return process.getValue();
    }

    @Override
    public ProcessType convertToEntityAttribute(Integer value) {
        return ProcessType.valueOf(value);
    }
}