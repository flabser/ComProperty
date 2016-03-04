package administrator.services;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.dataengine.ISystemDatabase;
import kz.lof.env.Environment;
import kz.lof.user.IUser;
import administrator.dao.UserDAO;
import administrator.model.User;

public class UserServices {

	@Deprecated
	public void importFromH2() {
		importFromH2(true);
	}

	@SuppressWarnings("deprecation")
	public void importFromH2(boolean showConsoleOutput) {
		List<User> entities = new ArrayList<User>();

		ISystemDatabase sysDb = Environment.systemBase;
		List<kz.flabs.users.User> users = sysDb.getAllUsers("", 0, 10000);
		int rCount = users.size();
		if (showConsoleOutput) {
			System.out.println("System users count = " + rCount);
		}
		for (kz.flabs.users.User oldUser : users) {
			User entity = new User();
			entity.setUserName(oldUser.getUserName());
			entity.setLogin(oldUser.getLogin());
			entity.setPwd(oldUser.getPassword());
			entity.setPwdHash(oldUser.getPasswordHash());

			entities.add(entity);
		}

		UserDAO uDao = new UserDAO();
		for (User user : entities) {
			uDao.add(user);
		}

	}

	public IUser getUser(String login) {
		UserDAO uDao = new UserDAO();
		IUser user = uDao.findByLogin(login);
		return user;

	}
}
