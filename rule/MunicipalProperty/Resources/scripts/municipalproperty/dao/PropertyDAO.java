package municipalproperty.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import kz.flabs.dataengine.Const;
import kz.flabs.runtimeobj.RuntimeObjUtil;
import kz.lof.dataengine.jpa.DAO;
import kz.lof.dataengine.jpa.SecureAppEntity;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._Session;
import municipalproperty.model.Property;
import municipalproperty.model.constants.KufType;

public class PropertyDAO extends DAO<Property, UUID> {

	public PropertyDAO(_Session session) {
		super(Property.class, session);
	}

	public ViewPage<Property> findAllBy(String fieldName, List value, int pageNum, int pageSize) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Property> cq = cb.createQuery(getEntityClass());
			CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
			Root<Property> c = cq.from(entityClass);
			cq.select(c);
			countCq.select(cb.count(c));
			Predicate condition = c.get(fieldName).in(value);
			if (!user.getUserID().equals(Const.sysUser) && SecureAppEntity.class.isAssignableFrom(getEntityClass())) {
				condition = cb.and(c.get("readers").in((long) user.docID), condition);
			}
			cq.where(condition);
			countCq.where(condition);
			TypedQuery<Property> typedQuery = em.createQuery(cq);
			Query query = em.createQuery(countCq);
			long count = (long) query.getSingleResult();
			int maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
			if (pageNum == 0) {
				pageNum = maxPage;
			}
			int firstRec = RuntimeObjUtil.calcStartEntry(pageNum, pageSize);
			typedQuery.setFirstResult(firstRec);
			typedQuery.setMaxResults(pageSize);
			List<Property> result = typedQuery.getResultList();

			ViewPage<Property> r = new ViewPage<Property>(result, count, maxPage, pageNum);
			return r;
		} finally {
			em.close();
		}
	}

	public List<Property> findByInvNum(String invNum) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Property> cq = cb.createQuery(Property.class);
			Root<Property> c = cq.from(Property.class);
			cq.select(c);
			Predicate condition = c.get("invNumber").in(invNum);
			cq.where(condition);
			Query query = em.createQuery(cq);
			if (!user.getUserID().equals(Const.sysUser)) {
				condition = cb.and(c.get("readers").in((long) user.docID), condition);
			}
			List<Property> entities = query.getResultList();
			return entities;
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}

	}

	// TODO it need adding dates checking in condition
	public List<Property> find(List<KufType> value, UUID balanceHolder, Date from, Date to) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Property> cq = cb.createQuery(Property.class);
			Root<Property> c = cq.from(Property.class);
			cq.select(c);

			Predicate condition = c.get("kuf").in(value);
			if (!user.getUserID().equals(Const.sysUser)) {
				condition = cb.and(c.get("readers").in((long) user.docID), condition);
			}

			if (balanceHolder != null) {
				condition = cb.and(cb.equal(c.get("balanceHolder"), balanceHolder), condition);
			}

			cq.where(condition);
			Query query = em.createQuery(cq);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

}
