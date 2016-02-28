package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class TransportView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.AUTOMOBILE);
		params.add(KufType.CARGO);
		params.add(KufType.DEJ_TRANSPORT);
		params.add(KufType.OFFICIAL_TRANSPORT);
		params.add(KufType.HOSPITAL_TRANSPORT);
		addContent(getViewPage(session, formData, params, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {

	}

}
