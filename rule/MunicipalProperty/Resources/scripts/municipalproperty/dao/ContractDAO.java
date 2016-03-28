package municipalproperty.dao;

import java.util.UUID;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.scripting._Session;
import municipalproperty.model.Contract;

public class ContractDAO extends DAO<Contract, UUID> {

	public ContractDAO(_Session session) {
		super(Contract.class, session);
	}

}
