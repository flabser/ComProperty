package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;

import municipalproperty.model.Equipment;

public class EquipmentDAO extends DAO<Equipment, UUID> {
	
	public EquipmentDAO(_Session session) throws DAOException {
		super(Equipment.class, session);
	}
	
}
