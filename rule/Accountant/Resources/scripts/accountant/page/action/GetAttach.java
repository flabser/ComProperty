package accountant.page.action;

import java.io.File;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.lof.env.Environment;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;

public class GetAttach extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		User user = session.getUser();
		File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
		try {
			String fileName = formData.getValue("fileid");
			if (fileName.indexOf('/') == -1 && fileName.indexOf('\\') == -1) {
				File xlsFile = new File(userTmpDir + File.separator + formData.getValue("fileid"));
				if (xlsFile.exists()) {
					showFile(xlsFile.getAbsolutePath(), fileName);
				}
			}
		} catch (_Exception e) {
			error(e);
		}
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}
}
