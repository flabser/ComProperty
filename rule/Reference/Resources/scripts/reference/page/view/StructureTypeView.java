package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import reference.dao.StructureTypeDAO;

/**
 * 
 * 
 * @author Kayra created 03-01-2016
 */
public class StructureTypeView extends ReferenceView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "structure_type", lang));
		setContent(getViewContent(new StructureTypeDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
