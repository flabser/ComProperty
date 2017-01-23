package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;

import municipalproperty.model.PersonalEstate;

public class PersonalEstateDAO extends DAO<PersonalEstate, UUID> {
	
	public PersonalEstateDAO(_Session session) throws DAOException {
		super(PersonalEstate.class, session);
	}
	
}
