package registry.page.view;

import java.util.UUID;

import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.exception.SecureException;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

/**
 * @author Kayra created 04-01-2016
 */

public class ContractorView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		_ActionBar actionBar = new _ActionBar(session);
		_Action newDocAction = new _Action(getLocalizedWord("new_", session.getLang()), "", "new_organization");
		newDocAction.setURL("Provider?id=organization-form");
		actionBar.addAction(newDocAction);
		actionBar.addAction(new _Action(getLocalizedWord("del_document", session.getLang()), "", _ActionType.DELETE_DOCUMENT));

		addContent(actionBar);

		OrganizationDAO dao = new OrganizationDAO(session);
		ViewPage<Organization> vp = dao.findAllByLabel("balance_holder", formData.getNumberValueSilently("page", 1), session.pageSize);
		addContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), session));
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		println(formData);

		OrganizationDAO dao = new OrganizationDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			Organization m = dao.findById(UUID.fromString(id));
			try {
				dao.delete(m);
			} catch (SecureException e) {
				setError(e);
			}
		}
	}
}
