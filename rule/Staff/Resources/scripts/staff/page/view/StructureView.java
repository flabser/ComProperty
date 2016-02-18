package staff.page.view;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.localization.LanguageType;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._POJOObjectWrapper;
import kz.lof.webserver.servlet.IOutcomeObject;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class StructureView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		List<IOutcomeObject> content = new ArrayList<IOutcomeObject>();
		OrganizationDAO dao = new OrganizationDAO(session);
		Organization org = dao.findPrimaryOrg();
		if (org != null) {
			content.add(new _POJOObjectWrapper(org, lang));
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
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}
}
