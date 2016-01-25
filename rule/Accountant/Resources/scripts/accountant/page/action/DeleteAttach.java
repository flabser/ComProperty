package accountant.page.action;

import kz.flabs.users.User;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import kz.pchelka.env.Environment;

import java.io.File;


public class DeleteAttach extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        //
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {
        User user = session.getUser();
        File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
        try {
            String fileName = formData.getValue("fileid");
            if (fileName.indexOf('/') == -1 && fileName.indexOf('\\') == -1) {
                File xlsFile = new File(userTmpDir + File.separator + formData.getValue("fileid"));
                if (xlsFile.exists()) {
                    xlsFile.delete();
                }
            }
        } catch (_Exception e) {
            error(e);
        }
    }
}
