package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.localization.LanguageType;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 09-01-2016
 */

public class SpecialConstructionView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.BOMBPROOF);
		params.add(KufType.FACTORY);
		params.add(KufType.COMBINES);
		params.add(KufType.AIRPORT);
		params.add(KufType.TRANSITIONS);
		addContent(getViewPage(session, formData, params, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}

}
