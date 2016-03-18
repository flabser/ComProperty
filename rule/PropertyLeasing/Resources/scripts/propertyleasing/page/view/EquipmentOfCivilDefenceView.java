package propertyleasing.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class EquipmentOfCivilDefenceView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		addContent(getSimpleActionBar(session, "equipment-of-civil-defence-form", lang));
		addContent(getViewPage(session, formData, KufType.EQUIPMENT_OF_CIVIL_DEFENCE, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
