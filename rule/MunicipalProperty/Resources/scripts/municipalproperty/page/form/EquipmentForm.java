package municipalproperty.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import municipalproperty.dao.EquipmentDAO;
import municipalproperty.model.Equipment;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;

public class EquipmentForm extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Equipment entity;
		if (!id.equals("")) {
			EquipmentDAO dao = new EquipmentDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Equipment();
			entity.setAuthor(user);
		}
		setContent(new _POJOObjectWrapper(entity, lang));
		setContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll(), lang));
		setContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll(), lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData, LanguageType lang) {

	}

}
