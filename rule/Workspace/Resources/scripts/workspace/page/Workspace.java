package workspace.page;

import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script._AppEntourage;
import kz.nextbase.script._Exception;
import kz.nextbase.script.events._DoPage;
import administrator.dao.LanguageDAO;

public class Workspace extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) throws _Exception {
		_AppEntourage ent = session.getAppEntourage();
		publishElement("serverversion", ent.getServerVersion());
		publishElement("build", ent.getBuildTime());
		publishElement("org", ent.getGeneralName());
		publishElement("img", ent.getLogoImg());
		publishElement("appname", ent.getAppName());
		publishElement("availableapps", ent.getAvailableApps());
		addContent(new _POJOListWrapper(new LanguageDAO(session).findAll(), session));

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) throws _Exception {

	}
}
