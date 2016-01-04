package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import reference.dao.PropertyCodeDAO;

public class PropertyCodeView extends ReferenceView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "property_code", lang));
		setContent(getViewContent(new PropertyCodeDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
