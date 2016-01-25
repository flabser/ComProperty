package accountant.page.action;

import accountant.page.action.MPXLImporter.ErrorDescription;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import kz.flabs.users.User;
import kz.nextbase.script._Session;
import kz.nextbase.script._Tag;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script._XMLDocument;
import kz.nextbase.script.events._DoPage;
import kz.pchelka.env.Environment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class ImportFileChecker extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        User user = session.getUser();
        File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
        try {
            File xlsFile = new File(userTmpDir + File.separator + formData.getEncodedValueSilently("fileid"));

            _Tag rootTag = new _Tag("result");

            MPXLImporter id = new MPXLImporter(MPXLImporter.CHECK);

            Workbook workbook = Workbook.getWorkbook(xlsFile);
            Sheet sheet = workbook.getSheet(0);
            Map<Integer, List<List<ErrorDescription>>> sheetErrs = id.process(sheet, ses, false);
            for (Entry<Integer, List<List<ErrorDescription>>> row : sheetErrs.entrySet()) {
                _Tag entry = rootTag.addTag("entry");
                entry.setAttr("row", row.getKey());
                for (List<ErrorDescription> colList : row.getValue()) {
                    for (ErrorDescription val : colList) {
                        entry.addTag("column", val.toString());
                    }
                }
            }

            _XMLDocument xml = new _XMLDocument(rootTag);
            setContent(xml);
        } catch (BiffException | IOException e) {
            setBadRequest();
            error(e);
        }
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {

    }
}
