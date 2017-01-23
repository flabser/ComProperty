package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;

import municipalproperty.model.StrategicObject;

public class StrategicObjectDAO extends DAO<StrategicObject, UUID> {
	
	public StrategicObjectDAO(_Session session) throws DAOException {
		super(StrategicObject.class, session);
	}
	
}
