package reference.page.view;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.DistrictDAO;
import reference.model.District;

import java.util.UUID;


public class DistrictView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_district");
        newDocAction.setURL("Provider?id=district-form");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

        addContent(actionBar);
        addContent(getViewPage(new DistrictDAO(session), formData));
    }

    @Override
    public void doDELETE(_Session session, _WebFormData formData, LanguageCode lang) {
        println(formData);

        DistrictDAO dao = new DistrictDAO(session);
        for (String id : formData.getListOfValuesSilently("docid")) {
            District m = dao.findById(UUID.fromString(id));
            dao.delete(m);
        }
    }
}
