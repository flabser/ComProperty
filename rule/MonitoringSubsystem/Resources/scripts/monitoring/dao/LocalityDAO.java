package monitoring.dao;

import java.util.UUID;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import monitoring.model.common.Locality;

public class LocalityDAO extends DAO<Locality, UUID> {

	public LocalityDAO(_Session session) {
		super(Locality.class, session);
	}

}
