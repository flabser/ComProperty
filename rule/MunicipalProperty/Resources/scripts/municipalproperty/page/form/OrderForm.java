package municipalproperty.page.form;

import java.util.Date;
import java.util.UUID;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import municipalproperty.dao.OrderDAO;
import municipalproperty.model.Order;
import municipalproperty.model.constants.KufType;

public class OrderForm extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser<Long> user = session.getUser();
		Order entity;
		if (!id.equals("")) {
			OrderDAO dao = new OrderDAO(session);
			entity = dao.findById(UUID.fromString(id));

		} else {
			entity = getDefaultEntity(user, KufType.BUS, session);
		}
		addContent(entity);
		addContent(new _ActionBar(session).addAction(new _Action(_ActionType.CLOSE)));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

	protected Order getDefaultEntity(IUser<Long> user, KufType type, _Session session) {
		Order entity = new Order();
		entity.setAuthor(user);
		entity.setRegDate(new Date());
		return entity;
	}

}
