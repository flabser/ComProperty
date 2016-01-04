package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import reference.dao.StreetDAO;

public class StreetView extends ReferenceView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "street", lang));
		setContent(getViewContent(new StreetDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
