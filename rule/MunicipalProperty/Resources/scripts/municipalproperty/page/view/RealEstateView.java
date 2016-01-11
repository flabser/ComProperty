package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.Arrays;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class RealEstateView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		setContent(getSimpleActionBar(session, "real_estate", lang));
		ArrayList<KufType> params = new ArrayList<KufType>(Arrays.asList(KufType.BUILDINGS, KufType.ROOMS, KufType.STRUCTURES,
		        KufType.RESIDENTIAL_OBJECTS, KufType.LAND, KufType.MONUMENT));
		setContent(getPropertyViewPage(session, formData, params));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
