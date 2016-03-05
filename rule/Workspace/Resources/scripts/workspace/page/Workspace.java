package workspace.page;

import kz.lof.env.Environment;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.AnonymousUser;
import kz.nextbase.script._AppEntourage;
import kz.nextbase.script._Exception;
import kz.lof.scripting.event._DoPage;
import administrator.dao.ApplicationDAO;
import administrator.dao.LanguageDAO;

public class Workspace extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) throws _Exception {
		_AppEntourage ent = session.getAppEntourage();
		publishElement("serverversion", ent.getServerVersion());
		publishElement("build", ent.getBuildTime());
		publishElement("org", Environment.orgName);
		publishElement("appname", ent.getAppName());
		if (!session.getUser().getUserID().equalsIgnoreCase(AnonymousUser.USER_NAME)) {
			addContent(new _POJOListWrapper(new ApplicationDAO(session).findAll(), session));
		}
		addContent(new _POJOListWrapper(new LanguageDAO(session).findAll(), session));

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) throws _Exception {

	}
}
