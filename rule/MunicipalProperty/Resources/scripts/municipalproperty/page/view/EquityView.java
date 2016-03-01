package municipalproperty.page.view;


import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class EquityView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		addContent(getSimpleActionBar(session, "equity-form", lang));
		addContent(getViewPage(session, formData, KufType.EQUITY, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
