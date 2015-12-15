package monitoring.model.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import monitoring.model.constants.FuelType;

@Converter(autoApply = true)
public class FuelTypeConverter implements AttributeConverter<FuelType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(FuelType issuePriority) {
		return issuePriority.getCode();
	}

	@Override
	public FuelType convertToEntityAttribute(Integer priorityValue) {
		return FuelType.getType(priorityValue);
	}
}
