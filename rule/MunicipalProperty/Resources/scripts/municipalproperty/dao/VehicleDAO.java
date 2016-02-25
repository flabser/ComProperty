package municipalproperty.dao;

import java.util.UUID;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.scripting._Session;
import municipalproperty.model.Vehicle;

public class VehicleDAO extends DAO<Vehicle, UUID> {

	public VehicleDAO(_Session session) {
		super(Vehicle.class, session);
	}

}
