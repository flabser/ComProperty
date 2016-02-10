package municipalproperty.dao;

import java.util.UUID;

import kz.lof.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import municipalproperty.model.IntangibleAsset;

public class IntangibleAssetDAO extends DAO<IntangibleAsset, UUID> {

	public IntangibleAssetDAO(_Session session) {
		super(IntangibleAsset.class, session);
	}

}
