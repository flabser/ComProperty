package accountant.page.action;

import accountant.page.action.MPXLImporter.ErrorDescription;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import kz.flabs.exception.DocumentException;
import kz.nextbase.script.*;
import kz.nextbase.script.events._DoPage;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class ImportFileChecker extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String fileDir = "tmp" + File.separator + "InventLoad" + File.separator + session.getCurrentUserID() + File.separator + new Date().getTime();
        try {
            ArrayList<String> ids = new ArrayList<String>();
            ids.add(formData.getValue("fileid"));
            _Document doc;

            doc = new _Document(session.getCurrentDatabase());

            doc.getAttachments("rtfcontent", fileDir, ids);

            File dir = new File(fileDir + File.separator + "1");
            String result_message = "";
            FilenameFilter fileNameFilter = new FilenameFilter() {
                @Override
                public boolean accept(File directory, String name) {
                    if (name.endsWith(".xls") || name.endsWith(".xlsx")) {
                        return true;
                    }
                    log("Accountant: import failed, file extension have to be .xls");
                    // result_message = "file_extension_error";
                    return false;
                }
            };

            _Tag rootTag = new _Tag("result");

            if (dir != null && dir.isDirectory()) {
                File[] files = dir.listFiles(fileNameFilter);

                MPXLImporter id = new MPXLImporter(MPXLImporter.CHECK);

                for (File xlsFile : files) {
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

                }
            }
            rootTag.setAttr("status", result_message.isEmpty() ? "success" : result_message);
            _XMLDocument xml = new _XMLDocument(rootTag);
            setContent(xml);
        } catch (_Exception | BiffException | IOException | DocumentException e) {
            error(e);
        }
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {

    }
}
