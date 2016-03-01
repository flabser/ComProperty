package municipalproperty.page.form.strategicobject;

import java.util.UUID;


import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.StrategicObjectDAO;
import municipalproperty.model.StrategicObject;
import municipalproperty.model.constants.KufType;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;
import reference.dao.TagDAO;

public class AirportForm extends StrategicObjectAbstractForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		StrategicObject entity;
		if (!id.isEmpty()) {
			StrategicObjectDAO dao = new StrategicObjectDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = getDefaultEntity(user, KufType.AIRPORT, session);
		}
		addContent(entity);
		addContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll(), session));
		addContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll(), session));
		addContent(new _POJOListWrapper(new TagDAO(session).findAll(), session));
		addContent(getActionBar(session, lang, entity));
		startSaveFormTransact(entity);
	}
}
