package municipalproperty.page.form;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import municipalproperty.dao.OrderDAO;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Order;
import municipalproperty.model.Property;

import java.util.Date;
import java.util.UUID;


public class OrderForm extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");
        IUser<Long> user = session.getUser();
        Order entity;
        if (!id.isEmpty()) {
            OrderDAO dao = new OrderDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = getDefaultEntity(user);
            String propertyId = formData.getValueSilently("propertyid");
            PropertyDAO propertyDAO = new PropertyDAO(session);
            Property property = propertyDAO.findById(propertyId);
            entity.setProperty(property);
        }
        addContent(entity);
        addContent(new _EnumWrapper<>(Order.OrderStatus.class.getEnumConstants()));
        _ActionBar actionBar = new _ActionBar(session);
        actionBar.addAction(new _Action(getLocalizedWord("save_close", session.getLang()), "", _ActionType.SAVE_AND_CLOSE));
        actionBar.addAction(new _Action(getLocalizedWord("close", session.getLang()), "", _ActionType.CLOSE));
        addContent(actionBar);
    }

    protected Order getDefaultEntity(IUser<Long> user) {
        Order entity = new Order();
        entity.setAuthor(user);
        entity.setRegDate(new Date());
        return entity;
    }
}
