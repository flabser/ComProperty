package municipalproperty.scheduled;

import java.util.Date;
import java.util.List;

import com.exponentus.appenv.AppEnv;
import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.env.EnvConst;
import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._Session;
import com.exponentus.scripting.event._DoScheduled;
import com.exponentus.server.Server;
import com.exponentus.user.AnonymousUser;
import com.exponentus.user.IUser;

import administrator.dao.UserDAO;
import municipalproperty.dao.ContractDAO;
import municipalproperty.dao.NotificationDAO;
import municipalproperty.model.Contract;
import municipalproperty.model.Notification;
import municipalproperty.model.constants.NotificationType;

public class ContractExpiryReminder extends _DoScheduled {
	
	@Override
	public void doEvery5Min(AppEnv env, _Session session) {
		// doEveryNight(session);
	}
	
	@Override
	public void doEvery1Hour(AppEnv env, _Session session) {
		
	}
	
	@Override
	public void doEveryNight(AppEnv env, _Session session) {
		Server.logger.infoLogEntry("check the contracts expiration");
		UserDAO uDao = new UserDAO();
		NotificationDAO nDao = null;
		ContractDAO cDao = null;
		try {
			nDao = new NotificationDAO(session);
			cDao = new ContractDAO(session);
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<Contract> list = cDao.findAll();
		Date current = new Date();
		LanguageCode lang = LanguageCode.valueOf(EnvConst.DEFAULT_LANG);
		for (Contract c : list) {
			if (current.after(c.getExpired())) {
				Notification entity = new Notification();
				entity.setSender(new AnonymousUser().getUserName());
				IUser<Long> user = uDao.findById(c.getReaders().iterator().next());
				if (user != null) {
					entity.setRecipient(user.getLogin());
					lang = user.getDefaultLang();
				}
				entity.setBody(getLocalizedWord("contract_has_been_expired", lang));
				entity.setType(NotificationType.CONTRACT_EXPIRED);
				entity.setSendingTime(new Date());
				entity.setRecipient("");
				try {
					nDao.add(entity);
				} catch (SecureException | DAOException e) {
					Server.logger.errorLogEntry(e);
				}
			}
		}
	}
	
}
