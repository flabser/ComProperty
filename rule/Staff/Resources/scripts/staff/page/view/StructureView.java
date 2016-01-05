package staff.page.view;

import java.util.ArrayList;
import java.util.List;

import kz.nextbase.script._IPOJOObject;
import kz.nextbase.script._IXMLContent;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.StructureDAO;
import staff.model.Organization;

public class StructureView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		List<_IXMLContent> content = new ArrayList<_IXMLContent>();
		StructureDAO dao = new StructureDAO(session);
		Organization<_IPOJOObject> org = dao.findPrimaryOrg();
		if (org != null) {
			content.add(new _POJOObjectWrapper(org));
		} else {
			content.add(new _POJOListWrapper(getLocalizedWord("no_primary_org", lang)));
		}
		setContent(getSimpleActionBar(session, "organization", lang));
		setContent(content);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
