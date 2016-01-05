package municipalproperty.dao;

import java.util.UUID;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import municipalproperty.model.RealEstate;

public class RealEstateDAO extends DAO<RealEstate, UUID> {

	public RealEstateDAO(_Session session) {
		super(RealEstate.class, session);
	}

}
