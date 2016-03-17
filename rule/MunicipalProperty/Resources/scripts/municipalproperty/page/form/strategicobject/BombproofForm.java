package municipalproperty.page.form.strategicobject;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import municipalproperty.dao.StrategicObjectDAO;
import municipalproperty.model.StrategicObject;
import municipalproperty.model.constants.KufType;

import java.util.UUID;


public class BombproofForm extends StrategicObjectAbstractForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        super.doGET(session, formData);
        String id = formData.getValueSilently("docid");
        IUser user = session.getUser();
        StrategicObject entity;
        if (!id.isEmpty()) {
            StrategicObjectDAO dao = new StrategicObjectDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = getDefaultEntity(user, KufType.BOMBPROOF, session);
        }
        addContent(entity);
        addContent(getActionBar(session, entity));
        startSaveFormTransact(entity);
    }
}
