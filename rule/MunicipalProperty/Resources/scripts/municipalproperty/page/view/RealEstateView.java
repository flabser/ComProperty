package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.Arrays;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class RealEstateView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		ArrayList<KufType> params = new ArrayList<KufType>(Arrays.asList(KufType.BUILDINGS, KufType.ROOMS, KufType.STRUCTURES,
		        KufType.RESIDENTIAL_OBJECTS, KufType.LAND, KufType.MONUMENT));
		addContent(getViewPage(session, formData, params, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {

	}

}
