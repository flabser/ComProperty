package municipalproperty.model.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import municipalproperty.model.constants.NotificationType;

@Converter(autoApply = true)
public class NotificationTypeConverter implements AttributeConverter<NotificationType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(NotificationType issuePriority) {
		return issuePriority.getCode();
	}

	@Override
	public NotificationType convertToEntityAttribute(Integer priorityValue) {
		return NotificationType.getType(priorityValue);
	}
}
