package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;

import municipalproperty.model.PrevBalanceHolder;

public class PrevBalanceHolderDAO extends DAO<PrevBalanceHolder, UUID> {
	
	public PrevBalanceHolderDAO(_Session session) throws DAOException {
		super(PrevBalanceHolder.class, session);
	}
	
}
