package municipalproperty.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.lof.scripting._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import municipalproperty.dao.ReportTemplateDAO;
import municipalproperty.model.ReportTemplate;

public class ReportTemplateForm extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		String id = formData.getValueSilently("docid");
		ReportTemplate entity;
		if (!id.equals("")) {
			ReportTemplateDAO dao = new ReportTemplateDAO(session);
			entity = dao.findById(UUID.fromString(id));
			setContent(new _POJOObjectWrapper(entity, lang));
			// setContent(new _POJOListWrapper(entity.getPropertyType()));
		} else {
			setBadRequest();
		}
	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData, LanguageType lang) {
		println(webFormData);
	}

}
