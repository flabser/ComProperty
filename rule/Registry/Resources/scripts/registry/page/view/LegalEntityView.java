package registry.page.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._POJOListWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.scripting.event._DoPage;

import reference.dao.OrgCategoryDAO;
import reference.model.OrgCategory;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

/**
 * @author Kayra created 04-01-2016
 */

public class LegalEntityView extends _DoPage {
	
	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		_ActionBar actionBar = new _ActionBar(session);
		_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_organization");
		newDocAction.setURL("Provider?id=organization-form");
		actionBar.addAction(newDocAction);
		actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));
		
		addContent(actionBar);
		OrganizationDAO dao = new OrganizationDAO(session);
		OrgCategoryDAO ocDao = new OrgCategoryDAO(session);
		List<OrgCategory> params = new ArrayList<>();
		try {
			params.add(ocDao.findByName("ТОО"));
			params.add(ocDao.findByName("АО"));
			params.add(ocDao.findByName("Государственное ведомство"));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ViewPage<Organization> vp = dao.findAllByOrgCategory(params, formData.getNumberValueSilently("page", 1),
				session.pageSize);
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
			} catch (SecureException | DAOException e) {
				setError(e);
			}
		}
	}
}
