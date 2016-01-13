package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import reference.dao.StreetDAO;
import reference.model.Street;

import java.util.UUID;


public class StreetForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Street entity = null;
        if (!id.isEmpty()) {
            StreetDAO dao = new StreetDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Street();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity));
    }

    @Override
    public void doPOST(_Session session, _WebFormData webFormData, String lang) {

    }
}
