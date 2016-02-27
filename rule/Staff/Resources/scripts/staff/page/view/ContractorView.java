package staff.page.view;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

import java.util.UUID;

/**
 * @author Kayra created 04-01-2016
 */

public class ContractorView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_organization");
        newDocAction.setURL("Provider?id=organization-form");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

        addContent(actionBar);
        addContent(getViewPage(new OrganizationDAO(session), formData));
    }

    @Override
    public void doDELETE(_Session session, _WebFormData formData, LanguageCode lang) {
        println(formData);

        OrganizationDAO dao = new OrganizationDAO(session);
        for (String id : formData.getListOfValuesSilently("docid")) {
            Organization m = dao.findById(UUID.fromString(id));
            dao.delete(m);
        }
    }
}
