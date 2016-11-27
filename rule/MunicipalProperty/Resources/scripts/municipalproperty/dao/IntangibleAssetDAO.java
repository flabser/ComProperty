package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;

import municipalproperty.model.IntangibleAsset;

public class IntangibleAssetDAO extends DAO<IntangibleAsset, UUID> {
	
	public IntangibleAssetDAO(_Session session) throws DAOException {
		super(IntangibleAsset.class, session);
	}
	
}
