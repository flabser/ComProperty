package reference.page.view;


import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import reference.dao.TagDAO;
import reference.model.Tag;

import java.util.UUID;

/**
 * @author Kayra created 28-01-2016
 */

public class TagView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        _ActionBar actionBar = new _ActionBar(session);
        _Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_tag");
        newDocAction.setURL("Provider?id=tag-form");
        actionBar.addAction(newDocAction);
        actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

        addContent(actionBar);
        addContent(getViewPage(new TagDAO(session), formData));
    }

    @Override
    public void doDELETE(_Session session, _WebFormData formData) {
        println(formData);

        TagDAO dao = new TagDAO(session);
        for (String id : formData.getListOfValuesSilently("docid")) {
            Tag m = dao.findById(UUID.fromString(id));
            dao.delete(m);
        }
    }
}
