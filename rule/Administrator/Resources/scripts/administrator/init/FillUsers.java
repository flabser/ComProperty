package administrator.init;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.dataengine.ISystemDatabase;
import kz.flabs.localization.Vocabulary;
import kz.lof.dataengine.jpa.deploying.InitialDataAdapter;
import kz.lof.env.Environment;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import administrator.dao.UserDAO;
import administrator.model.User;

/**
 * @author Created by Kayra on 03/03/16.
 */

// TODO it is temporary thing
public class FillUsers extends InitialDataAdapter<User, UserDAO> {

	@Override
	public List<User> getData(_Session ses, LanguageCode lang, Vocabulary vocabulary) {
		List<User> entities = new ArrayList<User>();

		ISystemDatabase sysDb = Environment.systemBase;
		List<kz.flabs.users.User> users = sysDb.getAllUsers("", 0, 10000);
		int rCount = users.size();
		System.out.println("System users count = " + rCount);
		for (kz.flabs.users.User oldUser : users) {
			User entity = new User();
			entity.setUserName(oldUser.getUserName());
			entity.setLogin(oldUser.getLogin());
			entity.setPwd(oldUser.getPassword());

			entities.add(entity);
		}

		return entities;
	}

	@Override
	public Class<UserDAO> getDAO() {
		return UserDAO.class;
	}

}
