package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import reference.dao.StructureTypeDAO;

/**
 * 
 * 
 * @author Kayra created 03-01-2016
 */
public class StructureTypeView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "structure_type", lang));
		setContent(getViewPage(new StructureTypeDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
