package reference.page.view;

import kz.flabs.localization.LanguageType;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.LocalityDAO;
import reference.model.Locality;

import java.util.UUID;


public class LocalityView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_locality");
        newDocAction.setURL("Provider?id=locality-form");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

        addContent(actionBar);
        addContent(getViewPage(new LocalityDAO(session), formData));
    }

    @Override
    public void doDELETE(_Session session, _WebFormData formData, LanguageType lang) {
        println(formData);

        LocalityDAO dao = new LocalityDAO(session);
        for (String id : formData.getListOfValuesSilently("docid")) {
            Locality m = dao.findById(UUID.fromString(id));
            dao.delete(m);
        }
    }
}
