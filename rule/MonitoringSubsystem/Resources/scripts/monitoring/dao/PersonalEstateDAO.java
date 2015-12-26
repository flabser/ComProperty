package monitoring.dao;

import java.util.UUID;

import kz.nextbase.script._Session;
import monitoring.model.PersonalEstate;

public class PersonalEstateDAO extends PropertyDAO<PersonalEstate, UUID> {

	public PersonalEstateDAO(_Session session) {
		super(session);
	}

}
