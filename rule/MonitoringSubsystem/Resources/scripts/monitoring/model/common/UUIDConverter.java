package monitoring.model.common;

import java.sql.Types;
import java.util.UUID;

import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.DirectCollectionMapping;
import org.eclipse.persistence.mappings.ManyToOneMapping;
import org.eclipse.persistence.mappings.OneToOneMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

public class UUIDConverter implements Converter {

	@Override
	public Object convertObjectValueToDataValue(Object objectValue, Session session) {
		return objectValue;
	}

	@Override
	public UUID convertDataValueToObjectValue(Object dataValue, Session session) {
		return (UUID) dataValue;
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public void initialize(DatabaseMapping mapping, Session session) {
		final DatabaseField Field;
		if (mapping instanceof DirectCollectionMapping) {
			Field = ((DirectCollectionMapping) mapping).getDirectField();
		} else {
			Field = mapping.getField();
		}
		Field.setSqlType(Types.OTHER);
		Field.setTypeName("uuid");
		Field.setColumnDefinition("UUID");

		for (DatabaseMapping m : mapping.getDescriptor().getMappings()) {
			assert OneToOneMapping.class.isAssignableFrom(ManyToOneMapping.class);
			if (m instanceof OneToOneMapping) {
				for (DatabaseField field : ((OneToOneMapping) m).getForeignKeyFields()) {
					field.setSqlType(Types.OTHER);
					field.setColumnDefinition("UUID");
					field.setTypeName("uuid");
				}
			}
		}
	}
}
