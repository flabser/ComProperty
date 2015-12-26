package reference.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import kz.flabs.dataengine.jpa.DAO;
import kz.flabs.dataengine.jpa.IAppEntity;
import kz.nextbase.script._Session;

public abstract class ReferenceDAO<T extends IAppEntity, K> extends DAO<T, K> {

	public ReferenceDAO(Class<T> entityClass, _Session session) {
		super(entityClass, session);
	}

	public T findByName(String name) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM " + getEntityClass().getName() + " AS m WHERE m.name = :name";
			TypedQuery<T> q = em.createQuery(jpql, getEntityClass());
			q.setParameter("name", name);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

}
