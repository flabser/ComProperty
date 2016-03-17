package municipalproperty.page.form.intangibleasset;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import municipalproperty.dao.IntangibleAssetDAO;
import municipalproperty.model.IntangibleAsset;
import municipalproperty.model.constants.KufType;

import java.util.UUID;


public class EquityForm extends IntangibleAssetAbstarctForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        super.doGET(session, formData);
        String id = formData.getValueSilently("docid");
        IUser user = session.getUser();
        IntangibleAsset entity;
        if (!id.isEmpty()) {
            IntangibleAssetDAO dao = new IntangibleAssetDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = getDefaultEntity(user, KufType.EQUITY, session);
        }
        addContent(entity);
        addContent(getActionBar(session, entity));
        startSaveFormTransact(entity);
    }
}
