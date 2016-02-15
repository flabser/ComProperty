package municipalproperty.page.form.realestate;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.dao.RealEstateDAO;
import municipalproperty.model.RealEstate;
import municipalproperty.model.constants.KufType;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;

import java.util.UUID;


public class BuildingForm extends RealEstateForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        RealEstate entity;
        if (!id.isEmpty()) {
            RealEstateDAO dao = new RealEstateDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = getDefaultEntity(user, KufType.BUILDINGS);
        }
        setContent(new _POJOObjectWrapper(entity, lang));
        setContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll(), lang));
        setContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll(), lang));
        setContent(getActionBar(session, lang, entity));
        startSaveFormTransact(entity);
    }
}
