package accountant.page.action;

import accountant.page.action.MPXLImporter.ErrorDescription;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import kz.flabs.users.User;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import kz.pchelka.env.Environment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class LoadFileData extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {

    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {
        User user = session.getUser();
        File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
        try {
            String fileName = formData.getEncodedValueSilently("fileid");
            File xlsFile = new File(userTmpDir + File.separator + fileName);
            MPXLImporter id = new MPXLImporter(MPXLImporter.LOAD);

            Workbook workbook = Workbook.getWorkbook(xlsFile);
            Sheet sheet = workbook.getSheet(0);
            Map<Integer, List<List<ErrorDescription>>> sheetErrs = id.process(sheet, ses, true);
            if (sheetErrs == null) {
                setBadRequest();
            }

        } catch (BiffException | IOException e) {
            error(e);
            setBadRequest();
        }
    }
}
