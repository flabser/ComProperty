package municipalproperty.page.form.realestate;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.dao.RealEstateDAO;
import municipalproperty.model.RealEstate;
import municipalproperty.model.constants.KufType;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;

public class MonumentForm extends RealEstateAbstractForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		RealEstate entity;
		if (!id.isEmpty()) {
			RealEstateDAO dao = new RealEstateDAO(session);
			entity = dao.findById(UUID.fromString(id));
			if (entity.getAddress().getStreet().getName().equalsIgnoreCase("unknown")) {
				entity.getAddress().getStreet().setName("");
			}
		} else {
			entity = getDefaultEntity(user, KufType.MONUMENT, session);
		}
		addContent(entity);
		addContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll(), lang));
		addContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll(), lang));
		addContent(getActionBar(session, lang, entity));
		startSaveFormTransact(entity);
	}
}
