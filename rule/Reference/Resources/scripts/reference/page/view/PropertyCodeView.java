package reference.page.view;

import kz.flabs.localization.LanguageType;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.PropertyCodeDAO;
import reference.model.PropertyCode;

import java.util.UUID;


public class PropertyCodeView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_property_code");
        newDocAction.setURL("Provider?id=propertycode-form");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

        addContent(actionBar);
        addContent(getViewPage(new PropertyCodeDAO(session), formData));
    }

    @Override
    public void doDELETE(_Session session, _WebFormData formData, LanguageType lang) {
        println(formData);

        PropertyCodeDAO dao = new PropertyCodeDAO(session);
        for (String id : formData.getListOfValuesSilently("docid")) {
            PropertyCode m = dao.findById(UUID.fromString(id));
            dao.delete(m);
        }
    }
}
