package municipalproperty.page.view;


import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 19-02-2016
 */

public class DejTransportView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		addContent(getSimpleActionBar(session, "dej-transport-form", lang));
		addContent(getViewPage(session, formData, KufType.DEJ_TRANSPORT, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
