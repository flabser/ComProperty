package accountant.page.action;

import java.io.File;

import kz.lof.env.EnvConst;
import kz.lof.env.Environment;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script.events._DoPage;

public class DeleteAttach extends _DoPage {

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {

		String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
		String fn = formData.getValueSilently("fileid");
		if (fsid.isEmpty() || fn.isEmpty()) {
			return;
		}

		try {
			session.removeAttribute(fsid + "_file" + fn);
			session.removeAttribute(fsid);
			IUser user = session.getUser();
			File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());

			if (fn.indexOf('/') == -1 && fn.indexOf('\\') == -1) {
				File xlsFile = new File(userTmpDir + File.separator + fn);
				if (xlsFile.exists()) {
					xlsFile.delete();
				}
			}
		} catch (Exception e) {
			setBadRequest();
			error(e);
		}
	}
}
