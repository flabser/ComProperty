package accountant.page.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Session;
import kz.nextbase.script._Tag;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script._XMLDocument;
import kz.nextbase.script.events._DoPage;
import kz.pchelka.env.Environment;

import org.apache.commons.io.FilenameUtils;

import accountant.page.action.MPXLImporter.ErrorDescription;

public class ImportFile extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {
		String fileDir = Environment.tmpDir;
		try {
			String fn = formData.getValue("file");
			String ext = FilenameUtils.getExtension(fn);
			if (ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx")) {
				_Tag rootTag = new _Tag("result");
				String result_message = "";
				File xlsFile = new File(fn);

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

				rootTag.setAttr("status", result_message.isEmpty() ? "success" : result_message);
				_XMLDocument xml = new _XMLDocument(rootTag);
				setContent(xml);
			}
		} catch (_Exception | BiffException | IOException e) {
			error(e);
		}

	}
}
