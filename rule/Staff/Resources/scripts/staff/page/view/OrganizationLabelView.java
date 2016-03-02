package staff.page.view;

import java.util.List;
import java.util.UUID;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import staff.dao.OrganizationLabelDAO;
import staff.model.Organization;
import staff.model.OrganizationLabel;

/**
 * @author Kayra created 08-01-2016
 */

public class OrganizationLabelView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		OrganizationLabelDAO dao = new OrganizationLabelDAO(session);
		String id = formData.getValueSilently("docid");
		if (!id.isEmpty()) {
			OrganizationLabel role = dao.findById(UUID.fromString(id));
			List<Organization> emps = role.getLabels();
			addContent(new _POJOListWrapper(emps, session));
		} else {
			_ActionBar actionBar = new _ActionBar(session);
			_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_organization_label");
			newDocAction.setURL("Provider?id=organization-label-form");
			actionBar.addAction(newDocAction);
			actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

			addContent(actionBar);
			addContent(getViewPage(dao, formData));
		}
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		println(formData);

		OrganizationLabelDAO dao = new OrganizationLabelDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			OrganizationLabel m = dao.findById(UUID.fromString(id));
			dao.delete(m);
		}
	}
}
