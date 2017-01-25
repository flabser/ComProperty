package registry.page.view;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._Session;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.scripting.event._DoPage;

import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class OrganizationView extends _DoPage {

	@Override
	public void doGET(_Session session, WebFormData formData) {
		try {
			LanguageCode lang = session.getLang();
			_ActionBar actionBar = new _ActionBar(session);
			_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_organization");
			newDocAction.setURL("Provider?id=organization-form");
			actionBar.addAction(newDocAction);
			actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

			addContent(actionBar);
			addContent(getViewPage(new OrganizationDAO(session), formData));
		} catch (DAOException e) {
			logError(e);
			setBadRequest();
		}
	}

	@Override
	public void doDELETE(_Session session, WebFormData formData) {
		println(formData);
		try {
			OrganizationDAO dao = new OrganizationDAO(session);
			for (String id : formData.getListOfValuesSilently("docid")) {
				Organization m = dao.findById(UUID.fromString(id));
				try {
					dao.delete(m);
				} catch (SecureException | DAOException e) {
					setError(e);
				}
			}
		} catch (DAOException e) {
			logError(e);
			setBadRequest();
		}
	}
}
