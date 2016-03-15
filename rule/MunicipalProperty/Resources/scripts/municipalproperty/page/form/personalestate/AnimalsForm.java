package municipalproperty.page.form.personalestate;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import municipalproperty.dao.PersonalEstateDAO;
import municipalproperty.model.PersonalEstate;
import municipalproperty.model.constants.KufType;

import java.util.UUID;


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
        addContent(getActionBar(session, entity));
        startSaveFormTransact(entity);
    }
}
