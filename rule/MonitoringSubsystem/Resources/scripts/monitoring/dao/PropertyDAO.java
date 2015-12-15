package monitoring.dao;

import java.util.UUID;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import monitoring.model.Property;

public class PropertyDAO extends DAO<Property, UUID> {

	public PropertyDAO(_Session session) {
		super(Property.class, session);
	}

}
