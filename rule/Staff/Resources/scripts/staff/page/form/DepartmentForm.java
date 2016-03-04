package staff.page.form;

import java.util.Date;
import java.util.UUID;

import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import staff.dao.DepartmentDAO;
import staff.model.Department;
import staff.model.constants.DepartmentType;
import administrator.dao.LanguageDAO;

/**
 * @author Kayra created 07-01-2016
 */

public class DepartmentForm extends StaffForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser user = session.getUser();
		Department entity;
		if (!id.isEmpty()) {
			DepartmentDAO dao = new DepartmentDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Department();
			entity.setRegDate(new Date());
			entity.setAuthor(user);
			entity.setName("");
		}
		addContent(entity);
		addContent(new _EnumWrapper<>(DepartmentType.class.getEnumConstants()));
		addContent(new _POJOListWrapper(new LanguageDAO(session).findAll(), session));
		addContent(getSimpleActionBar(session, session.getLang()));
		startSaveFormTransact(entity);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {
		try {
			_Validation ve = validate(formData, session.getLang());
			if (ve.hasError()) {
				setBadRequest();
				setValidation(ve);
				return;
			}

			boolean isNew = false;
			String id = formData.getValueSilently("docid");
			DepartmentDAO dao = new DepartmentDAO(session);
			Department entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new Department();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setType(DepartmentType.valueOf(formData.getValueSilently("type", "UNKNOWN")));

			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}

			finishSaveFormTransact(entity);
		} catch (_Exception e) {
			error(e);
		}
	}
}
