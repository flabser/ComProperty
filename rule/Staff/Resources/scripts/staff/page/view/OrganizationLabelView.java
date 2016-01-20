package staff.page.view;

import java.util.List;
import java.util.UUID;

import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.OrganizationLabelDAO;
import staff.model.Organization;
import staff.model.OrganizationLabel;

/**
 * @author Kayra created 08-01-2016
 */

public class OrganizationLabelView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		OrganizationLabelDAO dao = new OrganizationLabelDAO(session);
		String id = formData.getValueSilently("docid");
		if (!id.isEmpty()) {
			OrganizationLabel role = dao.findById(UUID.fromString(id));
			List<Organization> emps = role.getLabels();
			setContent(new _POJOListWrapper(emps));
		} else {
			setContent(getSimpleActionBar(session, "organization-label", lang));
			setContent(getViewPage(dao, formData));
		}
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
