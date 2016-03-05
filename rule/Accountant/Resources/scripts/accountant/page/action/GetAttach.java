package accountant.page.action;

import java.io.File;

import kz.lof.env.Environment;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._Exception;
import kz.lof.scripting.event._DoPage;

public class GetAttach extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {

		String fileName = formData.getValueSilently("fileid");
		if (fileName.isEmpty()) {
			return;
		}

		IUser user = session.getUser();
		File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
		try {
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
}
