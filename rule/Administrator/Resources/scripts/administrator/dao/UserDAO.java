package administrator.dao;

import javax.persistence.EntityManagerFactory;

import kz.flabs.dataengine.IDatabase;
import kz.flabs.users.User;
import kz.lof.env.Environment;
import kz.lof.scripting._Session;

public class UserDAO {
	public User user;
	private EntityManagerFactory emf;
	protected _Session ses;

	public UserDAO() {
		IDatabase db = Environment.dataBase;
		emf = db.getEntityManagerFactory();
	}
}
