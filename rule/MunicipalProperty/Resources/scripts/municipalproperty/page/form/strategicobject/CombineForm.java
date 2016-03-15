package municipalproperty.page.form.strategicobject;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import municipalproperty.dao.StrategicObjectDAO;
import municipalproperty.model.StrategicObject;
import municipalproperty.model.constants.KufType;

import java.util.UUID;


public class CombineForm extends StrategicObjectAbstractForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");
        IUser user = session.getUser();
        StrategicObject entity;
        if (!id.isEmpty()) {
            StrategicObjectDAO dao = new StrategicObjectDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = getDefaultEntity(user, KufType.COMBINES, session);
        }
        addContent(entity);
        addContent(getActionBar(session, entity));
        startSaveFormTransact(entity);
    }
}
