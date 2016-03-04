package administrator.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import kz.flabs.dataengine.IDatabase;
import kz.lof.env.Environment;
import kz.lof.scripting._Session;
import kz.lof.user.IUser;

import org.eclipse.persistence.exceptions.DatabaseException;

import administrator.model.User;

public class UserDAO {
	public User user;
	private EntityManagerFactory emf;
	protected _Session ses;

	public UserDAO() {
		IDatabase db = Environment.dataBase;
		emf = db.getEntityManagerFactory();
	}

	public List<User> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<User> q = em.createNamedQuery("User.findAll", User.class);
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public User add(User entity) throws DatabaseException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				em.persist(entity);
				t.commit();
				return entity;
			} finally {
				if (t.isActive()) {
					t.rollback();
				}
			}
		} finally {
			em.close();

		}

	}

	public User update(User entity) {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				em.merge(entity);
				t.commit();
				return entity;
			} finally {
				if (t.isActive()) {
					t.rollback();
				}
			}
		} finally {
			em.close();
		}
	}

	public IUser findByLogin(String login) {
		EntityManager em = emf.createEntityManager();
		try {
			String jpql = "SELECT m FROM User AS m WHERE m.login = :login";
			TypedQuery<User> q = em.createQuery(jpql, User.class);
			q.setParameter("login", login);
			List<User> res = q.getResultList();
			return res.get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		} finally {
			em.close();
		}

	}
}
