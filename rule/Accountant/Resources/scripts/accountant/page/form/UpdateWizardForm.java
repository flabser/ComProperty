package accountant.page.form;

import java.util.ArrayList;
import java.util.List;

import com.exponentus.dataengine.jpa.IAppFile;
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

public class UpdateWizardForm extends _DoPage {

	// хранить состояние пока шаг не 0 если есть состояние,
	// что бы можно было ходить вперед-назад без потери состояния

	@Override
	public void doGET(_Session ses, _WebFormData formData) {
		IUser<Long> user = ses.getUser();
		OrganizationDAO oDao = new OrganizationDAO(ses);
		String step = formData.getValueSilently("step", "0");
		String fsId = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
		String fileAttr = getSesAttrName();

		ImportFileEntry uf = (ImportFileEntry) ses.getAttribute(fileAttr);
		if (uf == null) {
			uf = new ImportFileEntry();
			ses.setAttribute(fileAttr, uf);
			step = "0";
		} else if (uf != null && step.equals("0")) {
			uf = new ImportFileEntry();
			if (!fsId.isEmpty()) {
				ses.removeAttribute(fsId);
			}
			ses.setAttribute(fileAttr, uf);
		}

		if (fsId.isEmpty()) {
			fsId = Util.generateRandomAsText();
		}
		String uploadType = formData.getValueSilently("uploadtype");
		if (uploadType.equals("upload") || uploadType.equals("writeoff") || uploadType.equals("transfer")) {
			uf.setLoadType(uploadType);
		} else {
			uf.setLoadType("");
		}
		uf.setStep(step);

		if (step.equals("1")) {
			String file = getFileNameByType(ses, fsId, "upfile", uf);
			if (!file.isEmpty() && uf.getStatus() == ImportFileEntry.INIT) {
				uf.setStatus(ImportFileEntry.JUST_UPLOADED);
				uf.setFileName(file);
			}
		} else if (step.equals("2")) {
			String file = getFileNameByType(ses, fsId, "upfile", uf);
			if (!file.isEmpty() && uf.getStatus() == ImportFileEntry.INIT) {
				uf.setStatus(ImportFileEntry.JUST_UPLOADED);
				uf.setFileName(file);
			}
		} else if (step.equals("3")) {
			if (uf.getLoadType().equals("upload")) {
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
			} else if (uf.getLoadType().equals("writeoff")) {

			} else if (uf.getLoadType().equals("transfer")) {
				String bh = formData.getValueSilently("recipient");
				if (!bh.isEmpty()) {
					uf.setRecipient(oDao.findById(bh));
				}
				uf.setOrderFileName(getFileNameByType(ses, fsId, "uporder", uf));
			}
		} else if (step.equals("4")) {

		}

		addValue("formsesid", fsId);
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

	public static String getFileNameByType(_Session ses, String fsid, String type, ImportFileEntry uf) {
		_FormAttachments formFiles = ses.getFormAttachments(fsid);

		for (IAppFile fn : formFiles.getFiles()) {
			if (fn.getFieldName().equalsIgnoreCase(type)) {
				// fake sign result
				// if has sign > set sign valid
				if (!fn.getSign().isEmpty()) {
					uf.setUploadFileSignStatus("valid");
				}
				return fn.getRealFileName();
			}
		}
		return "";
	}
}
