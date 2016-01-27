package municipalproperty.page.form;

import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import municipalproperty.dao.ReportTemplateDAO;
import municipalproperty.model.ReportTemplate;

import java.util.UUID;


public class ReportTemplateForm extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        ReportTemplate entity;
        if (!id.isEmpty()) {
            ReportTemplateDAO dao = new ReportTemplateDAO(session);
            entity = dao.findById(UUID.fromString(id));
            setContent(new _POJOObjectWrapper(entity));
            // setContent(new _POJOListWrapper(entity.getPropertyType()));
        } else {
            setBadRequest();
        }
    }

    @Override
    public void doPOST(_Session session, _WebFormData webFormData, String lang) {

    }
}