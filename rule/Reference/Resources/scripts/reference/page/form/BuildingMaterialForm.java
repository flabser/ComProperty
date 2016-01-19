package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.BuildingMaterialDAO;
import reference.model.BuildingMaterial;

import java.util.UUID;


public class BuildingMaterialForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        BuildingMaterial entity = null;
        if (!id.isEmpty()) {
            BuildingMaterialDAO dao = new BuildingMaterialDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new BuildingMaterial();
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
            BuildingMaterialDAO dao = new BuildingMaterialDAO(session);
            BuildingMaterial entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new BuildingMaterial();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new BuildingMaterial();
                }
            }

            entity.setName(webFormData.getValueSilently("name"));

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
