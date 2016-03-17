package municipalproperty.page.form.equipment;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import municipalproperty.dao.EquipmentDAO;
import municipalproperty.model.Equipment;
import municipalproperty.model.constants.KufType;

import java.util.UUID;


public class SchoolEquipmentForm extends EquipmentAbstractForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        super.doGET(session, formData);
        String id = formData.getValueSilently("docid");
        IUser user = session.getUser();
        Equipment entity;
        if (!id.isEmpty()) {
            EquipmentDAO dao = new EquipmentDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = getDefaultEntity(user, KufType.SCHOOL_EQUIPMENT, session);
        }
        addContent(entity);
        addContent(getActionBar(session, entity));
        startSaveFormTransact(entity);
    }
}
