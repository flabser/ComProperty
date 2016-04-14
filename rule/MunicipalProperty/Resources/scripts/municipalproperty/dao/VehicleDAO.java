package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;
import municipalproperty.model.Vehicle;

public class VehicleDAO extends DAO<Vehicle, UUID> {

	public VehicleDAO(_Session session) {
		super(Vehicle.class, session);
	}

}
