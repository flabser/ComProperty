package monitoring.dao;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import kz.flabs.dataengine.jpa.DAO;
import kz.flabs.dataengine.jpa.IAppEntity;
import kz.nextbase.script._Session;
import monitoring.model.Property;
import monitoring.model.constants.KufType;

public class PropertyDAO<T1 extends IAppEntity, K1> extends DAO<Property, UUID> {

	public PropertyDAO(_Session session) {
		super(Property.class, session);
	}

	public T1 findById(Class<T1> entityClass, K1 id) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM " + entityClass.getName() + " AS m WHERE m.id = :id";
			TypedQuery<T1> q = em.createQuery(jpql, entityClass);
			q.setParameter("id", id);
			return q.getSingleResult();
		} finally {
			em.close();
		}
	}

	public List<T1> findAllByIds(Class<T1> entityClass, List<K1> ids) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM " + entityClass.getName() + " AS m WHERE m.id IN :ids";
			TypedQuery<T1> q = em.createQuery(jpql, entityClass);
			q.setParameter("ids", ids);
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public List<T1> findAll(Class<T1> entityClass) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<T1> q = em.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass);
			q.setFirstResult(0);
			q.setMaxResults(10);
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public Property findByInvNum(String invNum) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM Property AS m WHERE m.invNumber = :invNum";
			TypedQuery<Property> q = em.createQuery(jpql, Property.class);
			q.setParameter("invNum", invNum);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	public Long getCountByKufType(KufType kuf) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT count(m) FROM Property AS m WHERE m.kuf IN :kuf";
			Query q = em.createQuery(jpql);
			q.setParameter("kuf", EnumSet.of(kuf));
			return (Long) q.getSingleResult();
		} finally {
			em.close();
		}
	}

	public List<Property> findAllByKufType(KufType kuf, int firstRec, int pageSize) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM  Property AS m WHERE m.kuf IN :kuf";
			TypedQuery<Property> q = em.createQuery(jpql, Property.class);
			q.setFirstResult(firstRec);
			q.setMaxResults(pageSize);
			q.setParameter("kuf", EnumSet.of(kuf));
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public List<T1> findAllByKufType(Class<T1> entityClass, KufType kuf) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM " + entityClass.getName() + " AS m WHERE m.kuf IN :kuf";
			TypedQuery<T1> q = em.createQuery(jpql, entityClass);
			q.setParameter("kuf", EnumSet.of(kuf));
			return q.getResultList();
		} finally {
			em.close();
		}
	}

}
