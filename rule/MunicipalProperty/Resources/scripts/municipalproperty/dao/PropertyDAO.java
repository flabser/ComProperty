package municipalproperty.dao;

import java.util.ArrayList;
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

import com.exponentus.dataengine.RuntimeObjUtil;
import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.dataengine.jpa.SecureAppEntity;
import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.scripting._Session;
import com.exponentus.server.Server;
import com.exponentus.user.SuperUser;

import municipalproperty.dao.filter.PropertyFilter;
import municipalproperty.model.Property;
import municipalproperty.model.constants.PropertyStatusType;
import reference.dao.OrgCategoryDAO;
import reference.model.OrgCategory;
import reference.model.constants.KufType;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class PropertyDAO extends DAO<Property, UUID> {
	
	public PropertyDAO(_Session session) throws DAOException {
		super(Property.class, session);
	}
	
	public ViewPage<Property> findAll(PropertyFilter filter, int pageNum, int pageSize) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Property> cq = cb.createQuery(getEntityClass());
			CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
			Root<Property> c = cq.from(getEntityClass());
			cq.select(c);
			countCq.select(cb.count(c));
			
			Predicate condition = null;
			
			if (filter.getStatus() != PropertyStatusType.UNKNOWN) {
				condition = cb.equal(c.get("propertyStatusType"), filter.getStatus());
			}
			
			if (!filter.getKufTypes().isEmpty()) {
				if (condition == null) {
					condition = c.get("kuf").in(filter.getKufTypes());
				} else {
					condition = cb.and(c.get("kuf").in(filter.getKufTypes()), condition);
				}
			}
			
			if (filter.getPropertyCode() != null) {
				if (condition == null) {
					condition = cb.and(c.get("propertyCode").in(filter.getPropertyCode()));
				} else {
					condition = cb.and(c.get("propertyCode").in(filter.getPropertyCode()), condition);
				}
			}
			
			if (!filter.getBalanceHolders().isEmpty()) {
				if (condition == null) {
					condition = cb.and(c.get("balanceHolder").in(filter.getBalanceHolders()));
				} else {
					condition = cb.and(c.get("balanceHolder").in(filter.getBalanceHolders()), condition);
				}
			}
			
			if (user.getId() != SuperUser.ID && SecureAppEntity.class.isAssignableFrom(getEntityClass())) {
				if (condition == null) {
					condition = cb.and(c.get("readers").in(user.getId()));
				} else {
					condition = cb.and(c.get("readers").in(user.getId()), condition);
				}
			}
			cq.orderBy(cb.asc(c.get("regDate")));
			if (condition != null) {
				cq.where(condition);
				countCq.where(condition);
			}
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
			
			return new ViewPage<>(result, count, maxPage, pageNum);
		} finally {
			em.close();
		}
	}
	
	public ViewPage<Property> findAllByKufAndBalanceHolder(List<KufType> kuf, Organization bh, int pageNum,
			int pageSize) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Property> cq = cb.createQuery(getEntityClass());
			CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
			Root<Property> c = cq.from(getEntityClass());
			cq.select(c);
			countCq.select(cb.count(c));
			Predicate condition = c.get("kuf").in(kuf);
			if (bh != null) {
				condition = cb.and(cb.equal(c.get("balanceHolder"), bh), condition);
			}
			if (user.getId() != SuperUser.ID && SecureAppEntity.class.isAssignableFrom(getEntityClass())) {
				condition = cb.and(c.get("readers").in(user.getId()), condition);
			}
			cq.orderBy(cb.asc(c.get("regDate")));
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
	
	public List<Property> findAllByInvNum(String invNum) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Property> cq = cb.createQuery(Property.class);
			Root<Property> c = cq.from(Property.class);
			cq.select(c);
			Predicate condition = c.get("invNumber").in(invNum);
			cq.where(condition);
			Query query = em.createQuery(cq);
			if (user.getId() != SuperUser.ID) {
				condition = cb.and(c.get("readers").in(user.getId()), condition);
			}
			List<Property> entities = query.getResultList();
			return entities;
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public List<Property> findAllByInvNumAndName(String invNum, String name) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Property> cq = cb.createQuery(Property.class);
			Root<Property> c = cq.from(Property.class);
			cq.select(c);
			Predicate condition = cb.equal(c.get("invNumber"), invNum);
			condition = cb.and(cb.equal(c.get("objectName"), name), condition);
			cq.where(condition);
			Query query = em.createQuery(cq);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	// TODO it need adding dates checking in condition
	public List<Property> findAllForReport(List<KufType> value, UUID balanceHolder, UUID orgCat, Date from, Date to) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Property> cq = cb.createQuery(Property.class);
			Root<Property> c = cq.from(Property.class);
			cq.select(c);
			
			Predicate condition = null;
			
			if (value != null) {
				condition = c.get("kuf").in(value);
			}
			
			if (user.getId() != SuperUser.ID) {
				if (condition != null) {
					condition = cb.and(c.get("readers").in(user.getId()), condition);
				} else {
					condition = c.get("readers").in(user.getId());
				}
			}
			
			if (balanceHolder != null) {
				if (condition != null) {
					condition = cb.and(cb.equal(c.get("balanceHolder").get("id"), balanceHolder), condition);
				} else {
					condition = cb.equal(c.get("balanceHolder").get("id"), balanceHolder);
				}
			} else if (orgCat != null) {
				try {
					OrgCategoryDAO ocDao = new OrgCategoryDAO(getSession());
					OrgCategory orgCatEntity = ocDao.findById(orgCat);
					OrganizationDAO oDao = new OrganizationDAO(getSession());
					List<OrgCategory> cats = new ArrayList<OrgCategory>();
					cats.add(orgCatEntity);
					ViewPage<Organization> orgs = oDao.findAllByOrgCategory(cats, 0, 0);

					if (condition != null) {
						condition = cb.and(c.get("balanceHolder").in(orgs.getResult()), condition);
					} else {
						condition = c.get("balanceHolder").in(orgs);
					}
				} catch (DAOException e) {
					Server.logger.errorLogEntry(e);
				}
			}
			
			cq.where(condition);
			
			Query query = em.createQuery(cq);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
}
