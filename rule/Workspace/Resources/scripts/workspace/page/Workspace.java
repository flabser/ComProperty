package workspace.page;

import java.util.List;

import kz.lof.administrator.dao.LanguageDAO;
import kz.lof.administrator.model.Application;
import kz.lof.administrator.model.User;
import kz.lof.env.Environment;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.AnonymousUser;
import kz.nextbase.script._AppEntourage;
import kz.nextbase.script._Exception;

public class Workspace extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) throws _Exception {
		_AppEntourage ent = session.getAppEntourage();
		addValue("serverversion", ent.getServerVersion());
		addValue("build", ent.getBuildTime());
		addValue("org", Environment.orgName);
		addValue("appname", ent.getAppName());
		if (!session.getUser().getUserID().equalsIgnoreCase(AnonymousUser.USER_NAME)) {
			User user = (User) session.getUser();
			List<Application> aa = user.getAllowedApps();
			addContent(new _POJOListWrapper(aa, session));
		}
		addContent(new _POJOListWrapper(new LanguageDAO(session).findAll(), session));

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) throws _Exception {

	}
}
