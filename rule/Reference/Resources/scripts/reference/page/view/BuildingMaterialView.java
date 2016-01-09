package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.BuildingMaterialDAO;

/**
 * @author Kayra created 03-01-2016
 */

public class BuildingMaterialView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        // println(formData);
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("add", lang), "", "new_building_material");
        newDocAction.setURL("Provider?id=building-material");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

        setContent(actionBar);
        setContent(getViewPage(new BuildingMaterialDAO(session), formData));
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {

    }
}
