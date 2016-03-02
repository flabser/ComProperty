package reference.page.view;

import java.util.UUID;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.RegionDAO;
import reference.model.Region;

public class RegionView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		_ActionBar actionBar = new _ActionBar(session);
		_Action newDocAction = new _Action(getLocalizedWord("new_", session.getLang()), "", "new_region");
		newDocAction.setURL("Provider?id=region-form");
		actionBar.addAction(newDocAction);
		actionBar.addAction(new _Action(getLocalizedWord("del_document", session.getLang()), "", _ActionType.DELETE_DOCUMENT));

		addContent(actionBar);
		addContent(getViewPage(new RegionDAO(session), formData));
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		println(formData);

		RegionDAO dao = new RegionDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			Region m = dao.findById(UUID.fromString(id));
			dao.delete(m);
		}
	}
}
