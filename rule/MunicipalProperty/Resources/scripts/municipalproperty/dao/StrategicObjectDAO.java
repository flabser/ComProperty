package municipalproperty.dao;

import java.util.UUID;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.scripting._Session;
import municipalproperty.model.StrategicObject;

public class StrategicObjectDAO extends DAO<StrategicObject, UUID> {

	public StrategicObjectDAO(_Session session) {
		super(StrategicObject.class, session);
	}

}
