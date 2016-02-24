package accountant.page.action;

import java.io.File;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.lof.env.EnvConst;
import kz.lof.env.Environment;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;

public class DeleteAttach extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		//
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {
		try {
			String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
			if (!fsid.isEmpty()) {
				String fn = formData.getValueSilently("fileid");
				session.removeAttribute(fsid + "_file" + fn);
				User user = session.getUser();
				File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());

				if (fn.indexOf('/') == -1 && fn.indexOf('\\') == -1) {
					File xlsFile = new File(userTmpDir + File.separator + fn);
					if (xlsFile.exists()) {
						xlsFile.delete();
					}
				}
			}
		} catch (Exception e) {
			setBadRequest();
			error(e);
		}
	}
}
