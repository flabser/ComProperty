package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import reference.dao.StructureTypeDAO;

/**
 * Created by Kayra on 03/01/16.
 */

public class StructureTypes extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(new ReferenceViewHelper().getViewContent(new StructureTypeDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}