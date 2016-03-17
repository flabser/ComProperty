package municipalproperty.page.form.realestate;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import municipalproperty.model.RealEstate;
import municipalproperty.model.constants.KufType;


public class BuildingForm extends RealEstateAbstractForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        super.doGET(session, formData);
        String id = formData.getValueSilently("docid");
        IUser<Long> user = session.getUser();
        RealEstate entity;
        if (!id.isEmpty()) {
            entity = getEntity(id, session);
        } else {
            entity = getDefaultEntity(user, KufType.BUILDINGS, session);
        }
        addContent(entity);
        addContent(getActionBar(session, entity));
        startSaveFormTransact(entity);
    }
}
