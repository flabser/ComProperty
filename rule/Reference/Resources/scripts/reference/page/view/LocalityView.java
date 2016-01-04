package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import reference.dao.LocalityDAO;

public class LocalityView extends ReferenceView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "locality", lang));
		setContent(getViewContent(new LocalityDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
