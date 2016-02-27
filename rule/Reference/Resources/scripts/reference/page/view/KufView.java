package reference.page.view;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.KufDAO;
import reference.model.Kuf;

public class KufView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		_ActionBar actionBar = new _ActionBar(session);
		_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_kuf");
		newDocAction.setURL("Provider?id=kuf-form");
		actionBar.addAction(newDocAction);
		actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

		addContent(actionBar);
		addContent(getViewPage(new KufDAO(session), formData));
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData, LanguageType lang) {
		// println(formData);

		KufDAO dao = new KufDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			Kuf m = dao.findById(UUID.fromString(id));
			dao.delete(m);
		}
	}
}
