package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.DistrictDAO;
import reference.dao.RegionDAO;
import reference.model.District;

import java.util.UUID;

/**
 * @author Kayra created 03-01-2016
 */

public class DistrictForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        District entity = null;
        if (!id.isEmpty()) {
            DistrictDAO dao = new DistrictDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new District();
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
            DistrictDAO dao = new DistrictDAO(session);
            RegionDAO regionDAO = new RegionDAO(session);
            District entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new District();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new District();
                }
            }

            entity.setName(webFormData.getValueSilently("name"));
            entity.setRegion(regionDAO.findById(UUID.fromString(webFormData.getValue("region"))));

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
