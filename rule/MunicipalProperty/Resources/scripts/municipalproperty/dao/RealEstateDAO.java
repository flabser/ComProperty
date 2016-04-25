package municipalproperty.dao;

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

import com.exponentus.dataengine.RuntimeObjUtil;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.dataengine.jpa.SecureAppEntity;
import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.scripting._Session;
import com.exponentus.user.SuperUser;

import municipalproperty.model.RealEstate;

public class RealEstateDAO extends DAO<RealEstate, UUID> {

	public RealEstateDAO(_Session session) {
		super(RealEstate.class, session);
	}

	public ViewPage<RealEstate> findAllInconsistAddr(int pageNum, int pageSize) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<RealEstate> cq = cb.createQuery(RealEstate.class);
			CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
			Root<RealEstate> c = cq.from(RealEstate.class);
			cq.select(c);
			countCq.select(cb.count(c));

			// Predicate condition =
			// cb.notEqual(c.get("address").get("additionalInfo"), "");
			// condition = cb.and(cb.equal(c.get("address").get("houseNumber"),
			// ""), condition);

			cq.orderBy(cb.asc(c.get("regDate")));
			// cq.where(condition);
			// countCq.where(condition);
			TypedQuery<RealEstate> typedQuery = em.createQuery(cq);
			Query query = em.createQuery(countCq);
			long count = (long) query.getSingleResult();
			int maxPage = 1;
			if (pageNum != 0 || pageSize != 0) {
				maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
				if (pageNum == 0) {
					pageNum = maxPage;
				}
				int firstRec = RuntimeObjUtil.calcStartEntry(pageNum, pageSize);
				typedQuery.setFirstResult(firstRec);
				typedQuery.setMaxResults(pageSize);
			}
			List<RealEstate> result = typedQuery.getResultList();

			return new ViewPage<RealEstate>(result, count, maxPage, pageNum);
		} finally {
			em.close();
		}

	}

	public ViewPage<RealEstate> findByCoord(String coord, int pageNum, int pageSize) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<RealEstate> cq = cb.createQuery(RealEstate.class);
			CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
			Root<RealEstate> c = cq.from(RealEstate.class);
			cq.select(c);
			countCq.select(cb.count(c));
			Predicate condition = null;
			if (!coord.equalsIgnoreCase("0")) {
				condition = cb.notEqual(c.get("address").get("coordinates"), coord);
			}
			if (user.getId() != SuperUser.ID && SecureAppEntity.class.isAssignableFrom(getEntityClass())) {
				condition = cb.and(c.get("readers").in(user.getId()), condition);
			}
			if (condition != null) {
				cq.where(condition);
				countCq.where(condition);
			}
			cq.orderBy(cb.asc(c.get("regDate")));
			TypedQuery<RealEstate> typedQuery = em.createQuery(cq);
			Query query = em.createQuery(countCq);
			long count = (long) query.getSingleResult();
			int maxPage = 1;
			if (pageNum != 0 || pageSize != 0) {
				maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
				if (pageNum == 0) {
					pageNum = maxPage;
				}
				int firstRec = RuntimeObjUtil.calcStartEntry(pageNum, pageSize);
				typedQuery.setFirstResult(firstRec);
				typedQuery.setMaxResults(pageSize);
			}

			List<RealEstate> result = typedQuery.getResultList();
			return new ViewPage<RealEstate>(result, count, maxPage, pageNum);
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}

	}

	public ViewPage<RealEstate> findByStreetAndHome(String streetId, String buildingNum, int pageNum, int pageSize) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<RealEstate> cq = cb.createQuery(RealEstate.class);
			CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
			Root<RealEstate> c = cq.from(RealEstate.class);
			cq.select(c);
			countCq.select(cb.count(c));
			Predicate condition = null;

			try {
				int id = Integer.parseInt(streetId);
				condition = cb.equal(c.get("address").get("street").get("streetId"), id);
			} catch (NumberFormatException e) {
				condition = cb.equal(cb.lower(c.get("address").get("street").get("name")), streetId.toLowerCase());
			}

			condition = cb.and(cb.equal(c.get("address").get("houseNumber"), buildingNum), condition);

			if (user.getId() != SuperUser.ID && SecureAppEntity.class.isAssignableFrom(getEntityClass())) {
				condition = cb.and(c.get("readers").in(user.getId()), condition);
			}

			cq.where(condition);
			countCq.where(condition);

			cq.orderBy(cb.asc(c.get("regDate")));
			TypedQuery<RealEstate> typedQuery = em.createQuery(cq);
			Query query = em.createQuery(countCq);
			long count = (long) query.getSingleResult();
			int maxPage = 1;
			if (pageNum != 0 || pageSize != 0) {
				maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
				if (pageNum == 0) {
					pageNum = maxPage;
				}
				int firstRec = RuntimeObjUtil.calcStartEntry(pageNum, pageSize);
				typedQuery.setFirstResult(firstRec);
				typedQuery.setMaxResults(pageSize);
			}

			List<RealEstate> result = typedQuery.getResultList();
			return new ViewPage<RealEstate>(result, count, maxPage, pageNum);
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
}
