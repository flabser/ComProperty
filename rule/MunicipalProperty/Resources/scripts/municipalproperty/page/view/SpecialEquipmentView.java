package municipalproperty.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 08-01-2016
 */

public class SpecialEquipmentView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		setContent(getSimpleActionBar(session, "special_equipment", lang));
		setContent(getPropertyViewPage(session, formData, KufType.SPECIAL_EQUIPMENT));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
