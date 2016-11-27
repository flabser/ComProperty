package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;

import municipalproperty.model.Notification;

public class NotificationDAO extends DAO<Notification, UUID> {
	
	public NotificationDAO(_Session session) throws DAOException {
		super(Notification.class, session);
	}
	
}
