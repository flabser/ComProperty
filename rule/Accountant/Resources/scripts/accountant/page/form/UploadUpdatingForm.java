package accountant.page.form;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.util.Util;
import kz.lof.env.EnvConst;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;

/**
 * @author Kayra created 30-01-2016
 */

public class UploadUpdatingForm extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData) {
		String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
		if (fsid.isEmpty()) {
			addContent("formsesid", Util.generateRandomAsText());
		} else {
			addContent("formsesid", fsid);
			List<String> formFiles = null;
			Object obj = ses.getAttribute(fsid);
			if (obj == null) {
				formFiles = new ArrayList<String>();
			} else {
				formFiles = (List<String>) obj;
			}

			List<UploadedFile> filesToPublish = new ArrayList<UploadedFile>();

			for (String fn : formFiles) {
				UploadedFile uf = (UploadedFile) ses.getAttribute(fsid + "_file" + fn);
				if (uf == null) {
					uf = new UploadedFile();
					uf.setName(fn);
					uf.setStatus(UploadedFile.JUST_UPLOADED);
					ses.setAttribute(fsid + "_file" + fn, uf);
				}
				filesToPublish.add(uf);
			}
			addContent(new _POJOListWrapper(filesToPublish, ses));
		}

		if (ses.getUser().getRoles().contains("data_loader")) {
			_ActionBar actionBar = new _ActionBar(ses);
			actionBar.addAction(new _Action(getLocalizedWord("attach_file", ses.getLang()), "", _ActionType.CUSTOM_ACTION));
			addContent(actionBar);
		}
	}
}
