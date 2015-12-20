package monitoring.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import kz.flabs.users.User;
import kz.nextbase.script._Session;

public class CountryDAO<T> {
	private Class<T> entityClass;
	private EntityManagerFactory emf;

	protected User user;

	public CountryDAO(_Session session) {
		this.entityClass = entityClass;
		emf = session.getCurrentDatabase().getEntityManagerFactory();
		user = session.getUser();
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public T add(T entity) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				// entity.setAuthor((long) user.docID);
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
}
