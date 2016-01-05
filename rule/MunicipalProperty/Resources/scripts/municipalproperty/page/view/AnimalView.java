package municipalproperty.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 05-01-2016
 */

public class AnimalView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "animal", lang));
		setContent(getPropertyViewContent(session, formData, KufType.ANIMALS));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
