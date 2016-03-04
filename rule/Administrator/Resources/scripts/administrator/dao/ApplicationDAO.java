package administrator.dao;

import java.util.UUID;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.scripting._Session;
import administrator.model.Application;

public class ApplicationDAO extends DAO<Application, UUID> {

	public ApplicationDAO(_Session session) {
		super(Application.class, session);
	}

}
