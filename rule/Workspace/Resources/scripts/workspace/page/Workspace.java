package workspace.page;

import kz.flabs.localization.LanguageType;
import kz.lof.env.Environment;
import kz.lof.scripting._Session;
import kz.nextbase.script._AppEntourage;
import kz.nextbase.script._Exception;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;

public class Workspace extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) throws _Exception {
		_AppEntourage ent = session.getAppEntourage();
		publishElement("serverversion", ent.getServerVersion());
		publishElement("build", ent.getBuildTime());
		publishElement("org", ent.getGeneralName());
		publishElement("img", ent.getLogoImg());
		publishElement("appname", ent.getAppName());
		publishElement("availableapps", ent.getAvailableApps());
		addContent("availablelangs", Environment.langs);

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) throws _Exception {

	}
}
