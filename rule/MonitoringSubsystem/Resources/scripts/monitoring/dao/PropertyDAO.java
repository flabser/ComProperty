package monitoring.dao;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import kz.flabs.dataengine.Const;
import kz.flabs.dataengine.jpa.DAO;
import kz.flabs.dataengine.jpa.SecureAppEntity;
import kz.nextbase.script._Session;
import monitoring.model.Property;
import monitoring.model.constants.KufType;

public class PropertyDAO extends DAO<Property, UUID> {

	public PropertyDAO(_Session session) {
		super(Property.class, session);
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
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			Root<Property> c = cq.from(Property.class);
			CriteriaQuery<Long> select = cq.select(cb.count(c));
			select.where(c.get("kuf").in(EnumSet.of(kuf)));
			if (!user.getUserID().equals(Const.sysUser) && SecureAppEntity.class.isAssignableFrom(getEntityClass())) {
				select.where(c.get("readers").in((long) user.docID));
			}
			return em.createQuery(cq).getSingleResult();
		} finally {
			em.close();
		}
	}

	public List<Property> findAllByKufType(KufType kuf, int firstRec, int pageSize) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Property> cq = cb.createQuery(Property.class);
			Root<Property> c = cq.from(Property.class);
			CriteriaQuery<Property> select = cq.select(c);
			select.where(c.get("kuf").in(EnumSet.of(kuf)));
			if (!user.getUserID().equals(Const.sysUser) && SecureAppEntity.class.isAssignableFrom(getEntityClass())) {
				select.where(c.get("readers").in((long) user.docID));
			}
			TypedQuery<Property> typedQuery = em.createQuery(select);
			typedQuery.setFirstResult(firstRec);
			typedQuery.setMaxResults(pageSize);
			return typedQuery.getResultList();
		} finally {
			em.close();
		}
	}

}
