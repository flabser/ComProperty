package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 05-01-2016
 */

public class EquipmentView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "equipment", lang));
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.SCHOOL_EQUIPMENT);
		params.add(KufType.COMPUTER_EQUIPMENT);
		params.add(KufType.COOK_EQUIPMENT);
		params.add(KufType.EQUIPMENT_OF_CIVIL_DEFENSE);
		params.add(KufType.OTHERS_EQUIPMENTS);
		setContent(getPropertyViewContent(session, formData, params));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
