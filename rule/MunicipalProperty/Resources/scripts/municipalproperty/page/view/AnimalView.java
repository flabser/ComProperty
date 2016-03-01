package municipalproperty.page.view;


import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 05-01-2016
 */

public class AnimalView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		// println(formData);
		addContent(getSimpleActionBar(session, "animal", lang));
		addContent(getViewPage(session, formData, KufType.ANIMALS, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
