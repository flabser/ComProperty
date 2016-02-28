package municipalproperty.dao;

import java.util.UUID;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.scripting._Session;
import municipalproperty.model.EngineeringInfrastructure;

public class EngInfrastructureDAO extends DAO<EngineeringInfrastructure, UUID> {

	public EngInfrastructureDAO(_Session session) {
		super(EngineeringInfrastructure.class, session);
	}

}
