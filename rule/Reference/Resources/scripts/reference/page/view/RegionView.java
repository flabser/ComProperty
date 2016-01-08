package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.RegionDAO;


public class RegionView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        // println(formData);

        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("add", lang), "", "new_region");
        newDocAction.setURL("Provider?id=region&key=");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

        setContent(actionBar);
        setContent(getViewPage(new RegionDAO(session), formData));
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {

    }
}
