package municipalproperty.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class OtherEquipmentView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		setContent(getSimpleActionBar(session, "other_equipment", lang));
		setContent(getPropertyViewPage(session, formData, KufType.OTHERS_EQUIPMENT));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
