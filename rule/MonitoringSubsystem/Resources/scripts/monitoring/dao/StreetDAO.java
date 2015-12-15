package monitoring.dao;

import java.util.UUID;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import monitoring.model.common.Street;

public class StreetDAO extends DAO<Street, UUID> {

	public StreetDAO(_Session session) {
		super(Street.class, session);
	}

}
