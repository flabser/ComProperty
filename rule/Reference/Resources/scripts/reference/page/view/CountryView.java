package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import reference.dao.CountryDAO;

public class CountryView extends ReferenceView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "country", lang));
		setContent(getViewContent(new CountryDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
