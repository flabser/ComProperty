package municipalproperty.dao;

import java.util.UUID;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.scripting._Session;
import municipalproperty.model.Notification;

public class NotificationDAO extends DAO<Notification, UUID> {

	public NotificationDAO(_Session session) {
		super(Notification.class, session);
	}

}
