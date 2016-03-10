package municipalproperty.page.form.personalestate;

import java.util.UUID;

import kz.lof.user.IUser;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.PersonalEstateDAO;
import municipalproperty.model.PersonalEstate;
import municipalproperty.model.constants.KufType;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;
import reference.dao.TagDAO;

public class AnimalsForm extends PersonalEstateAbstractForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser user = session.getUser();
		PersonalEstate entity;
		if (!id.isEmpty()) {
			PersonalEstateDAO dao = new PersonalEstateDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = getDefaultEntity(user, KufType.ANIMALS, session);
		}
		addContent(entity);
		addContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll(), session));
		addContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll(), session));
		addContent(new _POJOListWrapper(new TagDAO(session).findAll(), session));
		addContent(getActionBar(session, entity));
		startSaveFormTransact(entity);
	}
}
