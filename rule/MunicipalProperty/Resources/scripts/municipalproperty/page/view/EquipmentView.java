package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kayra created 05-01-2016
 */

public class EquipmentView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        List<KufType> params = new ArrayList<KufType>();
        params.add(KufType.SCHOOL_EQUIPMENT);
        params.add(KufType.OFFICE_EQUIPMENT);
        params.add(KufType.COMPUTER_EQUIPMENT);
        params.add(KufType.MEDICAL_EQUIPMENT);
        params.add(KufType.COOK_EQUIPMENT);
        params.add(KufType.EQUIPMENT_OF_CIVIL_DEFENCE);
        params.add(KufType.OTHERS_EQUIPMENT);
        addContent(getViewPage(session, formData, params, lang));
        addContent(getSimpleActionBar(session, "equipment-form", lang));
    }
}
