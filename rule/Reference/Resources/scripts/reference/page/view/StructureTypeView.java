package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.StructureTypeDAO;


public class StructureTypeView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("add", lang), "", "new_structure_type");
        newDocAction.setURL("Provider?id=structure-type");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

        setContent(actionBar);
        setContent(getViewPage(new StructureTypeDAO(session), formData));
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {

    }
}
