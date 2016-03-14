package reference.page.view;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import reference.dao.RegionTypeDAO;
import reference.model.RegionType;

import java.util.UUID;


public class RegionTypeView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("new_", session.getLang()), "", "new_region_type");
        newDocAction.setURL("Provider?id=regiontype-form");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", session.getLang()), "", _ActionType.DELETE_DOCUMENT));

        addContent(actionBar);
        addContent(getViewPage(new RegionTypeDAO(session), formData));
    }

    @Override
    public void doDELETE(_Session session, _WebFormData formData) {
        println(formData);

        RegionTypeDAO dao = new RegionTypeDAO(session);
        for (String id : formData.getListOfValuesSilently("docid")) {
            RegionType m = dao.findById(UUID.fromString(id));
            dao.delete(m);
        }
    }
}
