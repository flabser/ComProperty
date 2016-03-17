package municipalproperty.page.action;

import java.util.UUID;


import kz.lof.scripting._Session;
import kz.nextbase.script._Exception;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Property;

public class WrittenOff extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData) throws _Exception {
		String id = webFormData.getValue("docid");
		PropertyDAO dao = new PropertyDAO(session);
		Property entity = dao.findById(UUID.fromString(id));
		if (entity != null) {
			// entity.setStatus(PropertyStatusType.WRITTENOFF);
			dao.update(entity);
		} else {
			setBadRequest();
		}
	}
}
