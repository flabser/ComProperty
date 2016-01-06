package municipalproperty.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class ResidentialObjectView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "residential_object", lang));
		setContent(getPropertyViewPage(session, formData, KufType.RESIDENTIAL_OBJECTS));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
