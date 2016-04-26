package accountant.page.form;

import com.exponentus.env.EnvConst;
import com.exponentus.scripting._POJOListWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.user.IUser;
import com.exponentus.user.SuperUser;
import com.exponentus.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kayra created 30-01-2016
 */

public class UploadUpdatingForm extends _DoPage {

    @Override
    public void doGET(_Session ses, _WebFormData formData) {
        IUser<Long> user = ses.getUser();
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

        if (user.getId() == SuperUser.ID || (user.getRoles() != null && user.getRoles().contains("data_loader"))) {
            _ActionBar actionBar = new _ActionBar(ses);
            actionBar.addAction(new _Action(getLocalizedWord("attach_file", ses.getLang()), "", "attach_file"));
            addContent(actionBar);
        }
    }
}
