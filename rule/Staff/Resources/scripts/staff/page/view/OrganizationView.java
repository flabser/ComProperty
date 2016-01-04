package staff.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import staff.dao.OrganizationDAO;

public class OrganizationView extends StaffView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "organization", lang));
		setContent(getViewContent(new OrganizationDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
