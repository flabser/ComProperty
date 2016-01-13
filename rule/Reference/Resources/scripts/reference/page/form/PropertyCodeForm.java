package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import reference.dao.PropertyCodeDAO;
import reference.model.PropertyCode;

import java.util.UUID;


public class PropertyCodeForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        PropertyCode entity = null;
        if (!id.isEmpty()) {
            PropertyCodeDAO dao = new PropertyCodeDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new PropertyCode();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity));
    }

    @Override
    public void doPOST(_Session session, _WebFormData webFormData, String lang) {

    }
}
