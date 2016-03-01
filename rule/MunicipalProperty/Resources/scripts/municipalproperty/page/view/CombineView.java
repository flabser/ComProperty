package municipalproperty.page.view;


import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 09-01-2016
 */

public class CombineView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		addContent(getSimpleActionBar(session, "combine-form", lang));
		addContent(getViewPage(session, formData, KufType.COMBINES, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
