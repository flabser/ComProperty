package municipalproperty.page.form.personalestate;

import java.util.UUID;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import municipalproperty.dao.PersonalEstateDAO;
import municipalproperty.model.PersonalEstate;
import municipalproperty.model.constants.KufType;

public class FurnitureForm extends PersonalEstateAbstractForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		super.doGET(session, formData);
		String id = formData.getValueSilently("docid");
		IUser<Long> user = session.getUser();
		PersonalEstate entity;
		if (!id.isEmpty()) {
			PersonalEstateDAO dao = new PersonalEstateDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = getDefaultEntity(user, KufType.FURNITURE, session);
		}
		addContent(entity);
		addContent(getActionBar(session, entity));
		startSaveFormTransact(entity);
	}
}
