package municipalproperty.page.form.transport;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import municipalproperty.dao.VehicleDAO;
import municipalproperty.model.Vehicle;
import municipalproperty.model.constants.KufType;

import java.util.UUID;


public class TramForm extends VehicleAbstractForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        super.doGET(session, formData);
        String id = formData.getValueSilently("docid");
        IUser user = session.getUser();
        Vehicle entity;
        if (!id.isEmpty()) {
            VehicleDAO dao = new VehicleDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = getDefaultEntity(user, KufType.TRAM, session);
        }
        addContent(entity);
        addContent(getActionBar(session, entity));
        startSaveFormTransact(entity);
    }
}
