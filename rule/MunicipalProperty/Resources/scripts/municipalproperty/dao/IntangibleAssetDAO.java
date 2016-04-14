package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;
import municipalproperty.model.IntangibleAsset;

public class IntangibleAssetDAO extends DAO<IntangibleAsset, UUID> {

	public IntangibleAssetDAO(_Session session) {
		super(IntangibleAsset.class, session);
	}

}
