package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import reference.dao.ReceivingReasonDAO;
import reference.model.ReceivingReason;

import java.util.UUID;


public class ReceivingReasonForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        ReceivingReason entity = null;
        if (!id.isEmpty()) {
            ReceivingReasonDAO dao = new ReceivingReasonDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new ReceivingReason();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity));
    }

    @Override
    public void doPOST(_Session session, _WebFormData webFormData, String lang) {

    }
}
