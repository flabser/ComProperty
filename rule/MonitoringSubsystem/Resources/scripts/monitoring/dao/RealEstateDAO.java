package monitoring.dao;

import java.util.UUID;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import monitoring.model.RealEstate;

public class RealEstateDAO extends DAO<RealEstate, UUID> {

	public RealEstateDAO(_Session session) {
		super(RealEstate.class, session);
	}

}
