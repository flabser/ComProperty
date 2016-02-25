package municipalproperty.page.form.intangibleasset;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.dao.IntangibleAssetDAO;
import municipalproperty.model.IntangibleAsset;
import municipalproperty.model.constants.KufType;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;

public class ShareBlockForm extends IntangibleAssetAbstarctForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		IntangibleAsset entity;
		if (!id.isEmpty()) {
			IntangibleAssetDAO dao = new IntangibleAssetDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = getDefaultEntity(user, KufType.SHARE_BLOCK, session);
		}
		addContent(entity);
		addContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll(), lang));
		addContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll(), lang));
		addContent(getActionBar(session, lang, entity));
		startSaveFormTransact(entity);
	}
}
