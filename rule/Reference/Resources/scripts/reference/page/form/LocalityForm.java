package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.DistrictDAO;
import reference.dao.LocalityDAO;
import reference.model.Locality;
import reference.model.constants.LocalityType;

import java.util.UUID;

/**
 * @author Kayra created 03-01-2016
 */

public class LocalityForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Locality entity = null;
        if (!id.isEmpty()) {
            LocalityDAO dao = new LocalityDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Locality();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity));
    }

    @Override
    public void doPOST(_Session session, _WebFormData webFormData, String lang) {
        println(webFormData);
        try {
            boolean v = validate(webFormData);
            if (v == false) {
                setBadRequest();
                return;
            }

            boolean isNew = false;
            String id = webFormData.getValueSilently("docid");
            LocalityDAO dao = new LocalityDAO(session);
            DistrictDAO districtDAO = new DistrictDAO(session);
            Locality entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Locality();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new Locality();
                }
            }

            entity.setName(webFormData.getValueSilently("name"));
            entity.setType(LocalityType.valueOf(webFormData.getValueSilently("type", "UNKNOWN")));
            entity.setDistrict(districtDAO.findById(UUID.fromString(webFormData.getValue("district"))));

            if (isNew) {
                dao.add(entity);
            } else {
                dao.update(entity);
            }

            _URL returnURL = session.getURLOfLastPage();
            localizedMsgBox(getLocalizedWord("document_was_saved_succesfully", lang));
            setRedirectURL(returnURL);
        } catch (_Exception e) {
            log(e);
        }
    }
}
