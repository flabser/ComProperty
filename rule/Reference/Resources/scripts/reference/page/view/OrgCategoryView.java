package reference.page.view;

import java.util.UUID;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.lof.scripting.event._DoPage;
import reference.dao.OrgCategoryDAO;
import reference.model.OrgCategory;

public class OrgCategoryView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		_ActionBar actionBar = new _ActionBar(session);
		_Action newDocAction = new _Action(getLocalizedWord("new_", session.getLang()), "", "new_org_category");
		newDocAction.setURL("Provider?id=orgcategory-form");
		actionBar.addAction(newDocAction);
		actionBar.addAction(new _Action(getLocalizedWord("del_document", session.getLang()), "", _ActionType.DELETE_DOCUMENT));

		addContent(actionBar);
		addContent(getViewPage(new OrgCategoryDAO(session), formData));
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		println(formData);

		OrgCategoryDAO dao = new OrgCategoryDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			OrgCategory m = dao.findById(UUID.fromString(id));
			dao.delete(m);
		}
	}
}
