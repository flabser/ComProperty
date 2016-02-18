package municipalproperty.page.view;

import kz.flabs.localization.LanguageType;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class EquipmentOfCivilDefenceView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		addContent(getSimpleActionBar(session, "equipment_of_civildefence", lang));
		addContent(getPropertyViewPage(session, formData, KufType.EQUIPMENT_OF_CIVIL_DEFENSE, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}

}
