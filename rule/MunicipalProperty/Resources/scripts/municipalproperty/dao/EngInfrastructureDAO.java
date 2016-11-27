package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;

import municipalproperty.model.EngineeringInfrastructure;

public class EngInfrastructureDAO extends DAO<EngineeringInfrastructure, UUID> {
	
	public EngInfrastructureDAO(_Session session) throws DAOException {
		super(EngineeringInfrastructure.class, session);
	}
	
}
