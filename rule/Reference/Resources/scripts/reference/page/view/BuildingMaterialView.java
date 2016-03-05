package reference.page.view;


import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.lof.scripting.event._DoPage;
import reference.dao.BuildingMaterialDAO;
import reference.model.BuildingMaterial;

import java.util.UUID;


public class BuildingMaterialView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("new_", session.getLang()), "", "new_building_material");
        newDocAction.setURL("Provider?id=buildingmaterial-form");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", session.getLang()), "", _ActionType.DELETE_DOCUMENT));

        addContent(actionBar);
        addContent(getViewPage(new BuildingMaterialDAO(session), formData));
    }

    @Override
    public void doDELETE(_Session session, _WebFormData formData) {
        println(formData);

        BuildingMaterialDAO dao = new BuildingMaterialDAO(session);
        for (String id : formData.getListOfValuesSilently("docid")) {
            BuildingMaterial m = dao.findById(UUID.fromString(id));
            dao.delete(m);
        }
    }
}
