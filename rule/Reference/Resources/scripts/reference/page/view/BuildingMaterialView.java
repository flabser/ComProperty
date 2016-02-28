package reference.page.view;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.BuildingMaterialDAO;
import reference.model.BuildingMaterial;

import java.util.UUID;


public class BuildingMaterialView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_building_material");
        newDocAction.setURL("Provider?id=buildingmaterial-form");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

        addContent(actionBar);
        addContent(getViewPage(new BuildingMaterialDAO(session), formData));
    }

    @Override
    public void doDELETE(_Session session, _WebFormData formData, LanguageCode lang) {
        println(formData);

        BuildingMaterialDAO dao = new BuildingMaterialDAO(session);
        for (String id : formData.getListOfValuesSilently("docid")) {
            BuildingMaterial m = dao.findById(UUID.fromString(id));
            dao.delete(m);
        }
    }
}
