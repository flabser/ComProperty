package municipalproperty.dao;

import java.util.UUID;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import municipalproperty.model.Equipment;

public class EquipmentDAO extends DAO<Equipment, UUID> {

	public EquipmentDAO(_Session session) {
		super(Equipment.class, session);
	}

}
