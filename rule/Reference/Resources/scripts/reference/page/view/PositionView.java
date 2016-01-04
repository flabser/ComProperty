package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import reference.dao.PositionDAO;

public class PositionView extends ReferenceView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "position", lang));
		setContent(getViewContent(new PositionDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
