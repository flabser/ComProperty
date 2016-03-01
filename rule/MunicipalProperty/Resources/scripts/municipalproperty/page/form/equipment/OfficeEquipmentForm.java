package municipalproperty.page.form.equipment;

import java.util.UUID;


import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.EquipmentDAO;
import municipalproperty.model.Equipment;
import municipalproperty.model.constants.KufType;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;
import reference.dao.TagDAO;

public class OfficeEquipmentForm extends EquipmentAbstractForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Equipment entity;
		if (!id.isEmpty()) {
			EquipmentDAO dao = new EquipmentDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = getDefaultEntity(user, KufType.OFFICE_EQUIPMENT, session);
		}
		addContent(entity);
		addContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll(), session));
		addContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll(), session));
		addContent(new _POJOListWrapper(new TagDAO(session).findAll(), session));
		addContent(getActionBar(session, lang, entity));
		startSaveFormTransact(entity);
	}
}
