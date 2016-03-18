package propertyleasing.page.view;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 05-01-2016
 */

public class AnimalsView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		// println(formData);
		addContent(getSimpleActionBar(session, "animals-form", session.getLang()));
		addContent(getViewPage(session, formData, KufType.ANIMALS, session.getLang()));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
