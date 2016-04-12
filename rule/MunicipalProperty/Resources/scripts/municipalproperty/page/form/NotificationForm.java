package municipalproperty.page.form;

import kz.flabs.util.Util;
import kz.lof.exception.SecureException;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import municipalproperty.dao.NotificationDAO;
import municipalproperty.model.Notification;
import java.util.UUID;


public class NotificationForm extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {

        IUser<Long> user = session.getUser();
        Notification entity;
        String id = formData.getValueSilently("docid");
            NotificationDAO dao = new NotificationDAO(session);
            entity = dao.findById(UUID.fromString(id));
            addValue("formsesid", Util.generateRandomAsText());


        addContent(entity);
        _ActionBar actionBar = new _ActionBar(session);
        actionBar.addAction(new _Action(getLocalizedWord("close", session.getLang()), "", _ActionType.CLOSE));
        addContent(actionBar);
        startSaveFormTransact(entity);
    }


    @Override
    public void doDELETE(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");

        if (id.isEmpty()) {
            return;
        }

        NotificationDAO dao = new NotificationDAO(session);
        Notification entity = dao.findById(id);

        try {
            dao.update(entity);
        } catch (SecureException e) {
            setError(e);
        }
    }
}
