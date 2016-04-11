package municipalproperty.scheduled;

import java.util.Date;
import java.util.List;

import kz.lof.administrator.dao.UserDAO;
import kz.lof.administrator.model.User;
import kz.lof.exception.SecureException;
import kz.lof.scripting._Session;
import kz.lof.scripting.event._DoScheduledTask;
import kz.lof.server.Server;
import kz.lof.user.AnonymousUser;
import municipalproperty.dao.ContractDAO;
import municipalproperty.dao.NotificationDAO;
import municipalproperty.model.Contract;
import municipalproperty.model.Notification;

public class ContractExpiryReminder extends _DoScheduledTask {

	@Override
	public void doEvery5Min(_Session session) {
		Server.logger.infoLogEntry("check the contracts expiration");
		UserDAO uDao = new UserDAO();
		NotificationDAO nDao = new NotificationDAO(session);
		ContractDAO cDao = new ContractDAO(session);
		List<Contract> list = cDao.findAll();
		Date current = new Date();

		for (Contract c : list) {
			if (current.after(c.getExpired())) {
				Notification entity = new Notification();
				entity.setSender(new AnonymousUser().getUserName());
				User user = uDao.findById(c.getReaders().iterator().next());
				entity.setRecipient(user.getLogin());
				entity.setBody(getLocalizedWord("contract_has_been_expired", user.getDefaultLang()));
				try {
					nDao.add(entity);
				} catch (SecureException e) {
					Server.logger.errorLogEntry(e);
				}
			}
		}
	}

	@Override
	public void doEvery1Hour(_Session session) {

	}

	@Override
	public void doEveryNight(_Session session) {
		UserDAO uDao = new UserDAO();
		NotificationDAO nDao = new NotificationDAO(session);
		ContractDAO cDao = new ContractDAO(session);
		List<Contract> list = cDao.findAll();
		Date current = new Date();

		for (Contract c : list) {
			if (current.after(c.getExpired())) {
				Notification entity = new Notification();
				entity.setSender(new AnonymousUser().getUserName());
				User user = uDao.findById(c.getReaders().iterator().next());
				entity.setRecipient(user.getLogin());
				entity.setBody(getLocalizedWord("contract_has_been_expired", user.getDefaultLang()));
				try {
					nDao.add(entity);
				} catch (SecureException e) {
					Server.logger.errorLogEntry(e);
				}
			}
		}

	}

}
