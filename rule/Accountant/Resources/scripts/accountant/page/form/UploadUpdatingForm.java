package accountant.page.form;

import java.util.ArrayList;
import java.util.List;

import com.exponentus.common.model.Attachment;
import com.exponentus.env.EnvConst;
import com.exponentus.scripting._FormAttachments;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.user.IUser;
import com.exponentus.user.SuperUser;
import com.exponentus.util.Util;

import staff.dao.EmployeeDAO;
import staff.dao.OrganizationDAO;
import staff.model.Employee;

/**
 * @author Kayra created 30-01-2016
 */

public class UploadUpdatingForm extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData) {
		IUser<Long> user = ses.getUser();
		OrganizationDAO oDao = new OrganizationDAO(ses);
		String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
		if (fsid.isEmpty()) {
			addValue("formsesid", Util.generateRandomAsText());
		} else {
			addValue("formsesid", fsid);

			_FormAttachments formFiles = ses.getAttachments(fsid);

			String fileName = "", orderFileName = "";
			for (Attachment fn : formFiles.getFiles()) {
				if (fn.getFieldName().equalsIgnoreCase("upfile")) {
					fileName = fn.getRealFileName();
				} else if (fn.getFieldName().equalsIgnoreCase("uporder")) {
					orderFileName = fn.getRealFileName();
				}
			}
			String fileAttr = getSesAttrName(fsid, fileName);
			ImportFileEntry uf = (ImportFileEntry) ses.getAttribute(fileAttr);
			if (uf == null) {
				uf = new ImportFileEntry();
				uf.setStatus(ImportFileEntry.JUST_UPLOADED);
				uf.setFileName(fileName);
				ses.setAttribute(fileAttr, uf);
			}
			uf.setOrderFileName(orderFileName);

			String bh = formData.getValueSilently("balanceholder");
			if (!bh.isEmpty()) {
				uf.setBalanceHolder(oDao.findById(bh));
			}

			String recipient = formData.getValueSilently("recipient");
			if (!recipient.isEmpty()) {
				uf.setRecipient(oDao.findById(recipient));
			}

			uf.setStopIfWrong(formData.getBoolSilently("stopifwrong"));
			uf.setWriteOff(formData.getBoolSilently("writeoff"));
			uf.setTransfer(formData.getBoolSilently("istransfer"));

			String[] readers = formData.getListOfStringValues("readers", null);
			List<Long> readersList = new ArrayList<Long>();
			EmployeeDAO eDao = new EmployeeDAO(ses);
			if (readers[0] != null) {
				for (String r : readers) {
					Employee entity = eDao.findById(r);
					readersList.add(entity.getUser().getId());
				}
				uf.setReaders(readersList);
			}
			addContent(uf);
		}

		if (user.getId() == SuperUser.ID || (user.getRoles() != null && user.getRoles().contains("data_loader"))) {
			_ActionBar actionBar = new _ActionBar(ses);
			actionBar.addAction(new _Action(getLocalizedWord("attach_file", ses.getLang()), "", "attach_file"));
			addContent(actionBar);
		}
	}

	public static String getSesAttrName(String fsid, String fileName) {
		String key = fsid + "_file_" + fileName;
		return key;
	}
}
