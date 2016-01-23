package reference.dao;

import java.util.UUID;

import kz.nextbase.script._Session;
import reference.model.Street;

public class StreetDAO extends ReferenceDAO<Street, UUID> {

	public StreetDAO(_Session session) {
		super(Street.class, session);
	}

}
