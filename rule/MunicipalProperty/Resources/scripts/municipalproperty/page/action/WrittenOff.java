package municipalproperty.page.action;

import java.util.UUID;

import kz.nextbase.script._Exception;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Property;
import municipalproperty.model.constants.StatusType;

public class WrittenOff extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData, String lang) throws _Exception {
		String id = webFormData.getValue("docid");
		PropertyDAO dao = new PropertyDAO(ses);
		Property entity = dao.findById(UUID.fromString(id));
		if (entity != null) {
			entity.setStatus(StatusType.WRITTENOFF);
			dao.update(entity);
		} else {
			setBadRequest();
		}
	}
}
