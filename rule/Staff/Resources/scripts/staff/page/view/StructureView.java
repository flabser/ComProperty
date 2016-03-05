package staff.page.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._POJOObjectWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.webserver.servlet.IOutcomeObject;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.lof.scripting.event._DoPage;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class StructureView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		List<IOutcomeObject> content = new ArrayList<IOutcomeObject>();
		OrganizationDAO dao = new OrganizationDAO(session);
		Organization org = dao.findPrimaryOrg();
		if (org != null) {
			content.add(new _POJOObjectWrapper(org, session));
		} else {
			content.add(new _POJOListWrapper(getLocalizedWord("no_primary_org", lang), ""));
		}

		_ActionBar actionBar = new _ActionBar(session);
		_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_organization");
		newDocAction.setURL("Provider?id=organization-form");
		actionBar.addAction(newDocAction);
		actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

		addContent(actionBar);
		addContent(content);
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		println(formData);

		OrganizationDAO dao = new OrganizationDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			Organization m = dao.findById(UUID.fromString(id));
			dao.delete(m);
		}
	}
}
