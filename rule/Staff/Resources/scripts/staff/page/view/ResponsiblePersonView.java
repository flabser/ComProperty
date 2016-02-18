package staff.page.view;

import kz.flabs.localization.LanguageType;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import staff.dao.EmployeeDAO;

/**
 * @author Kayra created 07-01-2016
 */

public class ResponsiblePersonView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		_ActionBar actionBar = new _ActionBar(session);
		_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_employee");
		newDocAction.setURL("Provider?id=employee-form");
		actionBar.addAction(newDocAction);
		actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

		addContent(actionBar);
		addContent(getViewPage(new EmployeeDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}
}
