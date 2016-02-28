package accountant.page.action;

import kz.flabs.localization.LanguageCode;
import kz.flabs.users.User;
import kz.lof.env.Environment;
import kz.lof.scripting._Session;
import kz.nextbase.script._Exception;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.events._DoPage;

import java.io.File;


public class GetAttach extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {

        String fileName = formData.getValueSilently("fileid");
        if (fileName.isEmpty()) {
            return;
        }

        User user = session.getUser();
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
