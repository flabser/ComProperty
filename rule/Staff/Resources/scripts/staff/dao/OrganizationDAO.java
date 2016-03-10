package staff.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import kz.flabs.runtimeobj.RuntimeObjUtil;
import kz.lof.dataengine.jpa.DAO;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._Session;
import staff.model.Organization;
import staff.model.OrganizationLabel;

public class OrganizationDAO extends DAO<Organization, UUID> {

	public OrganizationDAO(_Session session) {
		super(Organization.class, session);
	}

	public Organization findPrimaryOrg() {
		try {
			ViewPage<Organization> result = findAllByLabel("primary", 1, 1);
			if (result.getCount() > 0) {
				return result.getResult().get(0);
			}
			return null;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public ViewPage<Organization> findAllByLabel(String labelName, int pageNum, int pageSize) {
		List<OrganizationLabel> val = new ArrayList<OrganizationLabel>();
		OrganizationLabelDAO olDAO = new OrganizationLabelDAO(ses);
		OrganizationLabel l = olDAO.findByName(labelName);
		val.add(l);
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Organization> cq = cb.createQuery(getEntityClass());
			CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
			Root<Organization> c = cq.from(getEntityClass());
			cq.select(c);
			countCq.select(cb.count(c));
			Predicate condition = c.get("labels").in(val);
			cq.where(condition);
			countCq.where(condition);
			TypedQuery<Organization> typedQuery = em.createQuery(cq);
			Query query = em.createQuery(countCq);
			long count = (long) query.getSingleResult();
			int maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
			if (pageNum == 0) {
				pageNum = maxPage;
			}
			int firstRec = RuntimeObjUtil.calcStartEntry(pageNum, pageSize);
			typedQuery.setFirstResult(firstRec);
			typedQuery.setMaxResults(pageSize);
			List<Organization> result = typedQuery.getResultList();
			return new ViewPage<Organization>(result, count, maxPage, pageNum);
		} finally {
			em.close();
		}
	}

	public ViewPage<Organization> findAllByKeyword(String keyword, int pageNum, int pageSize) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Organization> cq = cb.createQuery(getEntityClass());
			CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
			Root<Organization> c = cq.from(getEntityClass());
			cq.select(c);
			countCq.select(cb.count(c));
			if (!keyword.isEmpty()) {
				Predicate condition = cb.like(cb.lower(c.<String> get("name")), "%" + keyword.toLowerCase() + "%");
				cq.where(condition);
				countCq.where(condition);
			}
			TypedQuery<Organization> typedQuery = em.createQuery(cq);
			Query query = em.createQuery(countCq);
			long count = (long) query.getSingleResult();
			int maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
			if (pageNum == 0) {
				pageNum = maxPage;
			}
			int firstRec = RuntimeObjUtil.calcStartEntry(pageNum, pageSize);
			typedQuery.setFirstResult(firstRec);
			typedQuery.setMaxResults(pageSize);
			List<Organization> result = typedQuery.getResultList();
			return new ViewPage<Organization>(result, count, maxPage, pageNum);
		} finally {
			em.close();
		}
	}

}
