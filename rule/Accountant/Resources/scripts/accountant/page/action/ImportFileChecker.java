package accountant.page.action;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.flabs.util.Util;
import kz.lof.env.EnvConst;
import kz.lof.env.Environment;
import kz.lof.scripting._Session;
import kz.nextbase.script._Tag;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;

import org.apache.commons.io.FilenameUtils;

import staff.dao.OrganizationDAO;
import staff.model.Organization;
import accountant.page.action.MPXLImporter.ErrorDescription;
import accountant.page.form.UploadedFile;

public class ImportFileChecker extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		println(formData);
		try {
			String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
			if (!fsid.isEmpty()) {
				String fn = formData.getValueSilently("fileid");
				UploadedFile uf = (UploadedFile) session.getAttribute(fsid + "_file" + fn);
				if (uf == null) {
					uf = new UploadedFile();
					uf.setName(fn);
					uf.setStatus(UploadedFile.JUST_UPLOADED);
					session.setAttribute(fsid + "_file" + fn, uf);
				}

				User user = session.getUser();
				File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());

				_Tag rootTag = new _Tag("result");
				String excelFile = userTmpDir + File.separator + fn;
				String ext = FilenameUtils.getExtension(excelFile);
				if (ext.equalsIgnoreCase("xls")) {
					File xlsFile = new File(excelFile);

					MPXLImporter id = new MPXLImporter(MPXLImporter.CHECK);
					Workbook workbook = null;
					try {
						workbook = Workbook.getWorkbook(xlsFile);
					} catch (BiffException e) {
						uf.setStatus(UploadedFile.CHECKING_ERROR);
						uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
						return;
					}
					Sheet sheet = workbook.getSheet(0);

					OrganizationDAO oDao = new OrganizationDAO(session);
					List<Organization> oList = oDao.findAll();
					Organization org = (Organization) Util.getRndListElement(oList);
					String[] readers = formData.getListOfValuesSilently("reader");

					Map<Integer, List<List<ErrorDescription>>> sheetErrs = id.process(sheet, session, false, org, readers);

					if (sheetErrs.size() > 0) {
						uf.setStatus(UploadedFile.CHECKING_ERROR);
						uf.setLocalizedMsg(getLocalizedWord("file_data_is_incorrect", lang));
						uf.setSheetErrs(sheetErrs);
					} else {
						uf.setStatus(UploadedFile.CHECKED);
					}

					for (Entry<Integer, List<List<ErrorDescription>>> row : sheetErrs.entrySet()) {
						_Tag entry = rootTag.addTag("entry");
						entry.setAttr("row", row.getKey());
						for (List<ErrorDescription> colList : row.getValue()) {
							for (ErrorDescription val : colList) {
								entry.addTag("column", val.toString());
							}
						}
					}
				} else {
					uf.setStatus(UploadedFile.CHECKING_ERROR);
					uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
					_Tag entry = rootTag.addTag("entry");
					entry.addTag("column", getLocalizedWord("incorrect_xls_file", lang));
					return;
				}
				// addContent(rootTag);

			}
		} catch (Exception e) {
			setBadRequest();
			error(e);
		}
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}
}
