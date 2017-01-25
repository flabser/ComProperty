package registry.page.view;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.exception.SecureException;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._POJOListWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.scripting.event._DoPage;

import staff.dao.OrganizationDAO;
import staff.model.Organization;

/**
 * @author Kayra created 04-01-2016
 */

public class ContractorView extends _DoPage {

	@Override
	public void doGET(_Session session, WebFormData formData) {
		try {
			_ActionBar actionBar = new _ActionBar(session);
			_Action newDocAction = new _Action(getLocalizedWord("new_", session.getLang()), "", "new_organization");
			newDocAction.setURL("Provider?id=organization-form");
			actionBar.addAction(newDocAction);
			actionBar.addAction(
					new _Action(getLocalizedWord("del_document", session.getLang()), "", _ActionType.DELETE_DOCUMENT));

			addContent(actionBar);

			OrganizationDAO dao = new OrganizationDAO(session);
			ViewPage<Organization> vp = dao.findAllByLabel("balance_holder", formData.getNumberValueSilently("page", 1),
					session.pageSize);
			addContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), session));
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
				
				dao.delete(m);
				
			}
		} catch (DAOException | SecureException e) {
			logError(e);
			setBadRequest();
			
		}
	}
}
