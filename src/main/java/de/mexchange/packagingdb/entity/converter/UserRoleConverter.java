package de.mexchange.packagingdb.entity.converter;

import de.mexchange.packagingdb.domain.lcp.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {

	@Override
	public Integer convertToDatabaseColumn(UserRole role) {
		return role.getValue();
	}

	@Override
	public UserRole convertToEntityAttribute(Integer value) {
		return UserRole.valueOf(value);
	}
}