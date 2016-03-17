package municipalproperty.page.form.enginfrastructure;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._EnumWrapper;
import municipalproperty.dao.EngInfrastructureDAO;
import municipalproperty.model.EngineeringInfrastructure;
import municipalproperty.model.constants.KufType;
import municipalproperty.model.constants.PropertyStatusType;

import java.util.UUID;


public class ColumnForm extends EngInfrastructureAbstractForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        super.doGET(session, formData);
        String id = formData.getValueSilently("docid");
        IUser user = session.getUser();
        EngineeringInfrastructure entity;
        if (!id.isEmpty()) {
            EngInfrastructureDAO dao = new EngInfrastructureDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = getDefaultEntity(user, KufType.COLUMNS, session);
        }
        addContent(entity);
        addContent(new _EnumWrapper<>(PropertyStatusType.class.getEnumConstants()));
        addContent(getActionBar(session, entity));
        startSaveFormTransact(entity);
    }
}
