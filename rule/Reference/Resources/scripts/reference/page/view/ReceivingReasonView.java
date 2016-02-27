package reference.page.view;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.ReceivingReasonDAO;
import reference.model.ReceivingReason;

import java.util.UUID;


public class ReceivingReasonView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_receiving_reason");
        newDocAction.setURL("Provider?id=receivingreason-form");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

        addContent(actionBar);
        addContent(getViewPage(new ReceivingReasonDAO(session), formData));
    }

    @Override
    public void doDELETE(_Session session, _WebFormData formData, LanguageCode lang) {
        println(formData);

        ReceivingReasonDAO dao = new ReceivingReasonDAO(session);
        for (String id : formData.getListOfValuesSilently("docid")) {
            ReceivingReason m = dao.findById(UUID.fromString(id));
            dao.delete(m);
        }
    }
}
