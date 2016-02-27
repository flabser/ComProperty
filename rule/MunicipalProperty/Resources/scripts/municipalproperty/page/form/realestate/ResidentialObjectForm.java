package municipalproperty.page.form.realestate;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.RealEstate;
import municipalproperty.model.constants.KufType;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;

public class ResidentialObjectForm extends RealEstateAbstractForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		RealEstate entity;
		if (!id.isEmpty()) {
			entity = getEntity(id, session);
		} else {
			entity = getDefaultEntity(user, KufType.RESIDENTIAL_OBJECTS, session);
		}
		addContent(entity);
		addContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll(), session));
		addContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll(), session));
		addContent(getActionBar(session, lang, entity));
		startSaveFormTransact(entity);
	}
}
