package municipalproperty.page.form.enginfrastructure;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import municipalproperty.dao.EngInfrastructureDAO;
import municipalproperty.model.EngineeringInfrastructure;
import municipalproperty.model.constants.KufType;

import java.util.UUID;


public class BillboardForm extends EngInfrastructureAbstractForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");
        IUser user = session.getUser();
        EngineeringInfrastructure entity;
        if (!id.isEmpty()) {
            EngInfrastructureDAO dao = new EngInfrastructureDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = getDefaultEntity(user, KufType.BILLBOARD, session);
        }
        addContent(entity);
        addContent(getActionBar(session, entity));
        startSaveFormTransact(entity);
    }
}
