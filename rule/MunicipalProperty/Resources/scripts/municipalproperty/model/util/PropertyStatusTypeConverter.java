package municipalproperty.model.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import municipalproperty.model.constants.PropertyStatusType;

@Converter(autoApply = true)
public class PropertyStatusTypeConverter implements AttributeConverter<PropertyStatusType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(PropertyStatusType issuePriority) {
		return issuePriority.getCode();
	}

	@Override
	public PropertyStatusType convertToEntityAttribute(Integer priorityValue) {
		return PropertyStatusType.getType(priorityValue);
	}
}
