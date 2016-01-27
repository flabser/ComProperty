package municipalproperty.model.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import municipalproperty.model.constants.StatusType;

@Converter(autoApply = true)
public class StatusTypeConverter implements AttributeConverter<StatusType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(StatusType issuePriority) {
		return issuePriority.getCode();
	}

	@Override
	public StatusType convertToEntityAttribute(Integer priorityValue) {
		return StatusType.getType(priorityValue);
	}
}
