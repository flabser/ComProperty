package municipalproperty.dao;

import java.util.UUID;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import municipalproperty.model.PersonalEstate;

public class PersonalEstateDAO extends DAO<PersonalEstate, UUID> {

	public PersonalEstateDAO(_Session session) {
		super(PersonalEstate.class, session);
	}

}
