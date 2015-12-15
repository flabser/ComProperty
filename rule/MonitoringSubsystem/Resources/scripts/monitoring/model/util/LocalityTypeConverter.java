package monitoring.model.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import monitoring.model.constants.LocalityType;

@Converter(autoApply = true)
public class LocalityTypeConverter implements AttributeConverter<LocalityType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(LocalityType lt) {
		return lt.getCode();
	}

	@Override
	public LocalityType convertToEntityAttribute(Integer lt) {
		return LocalityType.getType(lt);
	}
}
