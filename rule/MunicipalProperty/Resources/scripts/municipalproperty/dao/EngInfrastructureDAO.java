package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;
import municipalproperty.model.EngineeringInfrastructure;

public class EngInfrastructureDAO extends DAO<EngineeringInfrastructure, UUID> {

	public EngInfrastructureDAO(_Session session) {
		super(EngineeringInfrastructure.class, session);
	}

}
