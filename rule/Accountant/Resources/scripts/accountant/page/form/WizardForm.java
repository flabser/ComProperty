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
 * @author Kayra created 24-05-2016
 */

public class WizardForm extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData) {
		IUser<Long> user = ses.getUser();
		OrganizationDAO oDao = new OrganizationDAO(ses);
		String step = formData.getValueSilently("step", "0");
		String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
		String fileAttr = getSesAttrName();
		ImportFileEntry uf = (ImportFileEntry) ses.getAttribute(fileAttr);
		if (uf == null) {
			uf = new ImportFileEntry();
			ses.setAttribute(fileAttr, uf);
			step = "0";
		}

		uf.setStep(step);
		if (step.equals("0")) {
			fsid = Util.generateRandomAsText();
			uf.setStatus(ImportFileEntry.INIT);
		} else if (step.equals("1")) {
			String uo = formData.getValueSilently("uploadtype");
			uf.setLoadType(uo);
			uf.setStatus(ImportFileEntry.JUST_UPLOADED);
			uf.setFileName(getFileNameByType(ses, fsid, "upfile"));
		} else if (step.equals("2-upload")) {
			String bh = formData.getValueSilently("balanceholder");
			if (!bh.isEmpty()) {
				uf.setBalanceHolder(oDao.findById(bh));
			}
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
		} else if (step.equals("2-writeoff")) {

		} else if (step.equals("2-transfer")) {
			String bh = formData.getValueSilently("balanceholder");
			if (!bh.isEmpty()) {
				uf.setBalanceHolder(oDao.findById(bh));
			}
			uf.setFileName(getFileNameByType(ses, fsid, "uporder"));
		} else if (step.equals("3")) {
			uf.setStatus(ImportFileEntry.INIT);
		}

		addValue("formsesid", fsid);
		addContent(uf);

		if (user.getId() == SuperUser.ID || (user.getRoles() != null && user.getRoles().contains("data_loader"))) {
			_ActionBar actionBar = new _ActionBar(ses);
			actionBar.addAction(new _Action(getLocalizedWord("attach_file", ses.getLang()), "", "attach_file"));
			addContent(actionBar);
		}
	}

	public static String getSesAttrName() {
		return "wizard_form";
	}

	private String getFileNameByType(_Session ses, String fsid, String type) {
		_FormAttachments formFiles = ses.getAttachments(fsid);

		for (Attachment fn : formFiles.getFiles()) {
			if (fn.getFieldName().equalsIgnoreCase(type)) {
				return fn.getRealFileName();
			}
		}
		return "";
	}
}
