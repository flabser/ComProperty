package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;
import municipalproperty.model.RealEstate;

public class RealEstateDAO extends DAO<RealEstate, UUID> {

	public RealEstateDAO(_Session session) {
		super(RealEstate.class, session);
	}

}
