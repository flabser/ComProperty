package municipalproperty.dao;

import kz.flabs.dataengine.Const;
import kz.flabs.runtimeobj.RuntimeObjUtil;
import kz.lof.dataengine.jpa.DAO;
import kz.lof.dataengine.jpa.SecureAppEntity;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._Session;
import kz.lof.user.SuperUser;
import municipalproperty.dao.filter.PropertyFilter;
import municipalproperty.model.Property;
import municipalproperty.model.constants.KufType;
import staff.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class PropertyDAO extends DAO<Property, UUID> {

    public PropertyDAO(_Session session) {
        super(Property.class, session);
    }

    public ViewPage<Property> findAll(PropertyFilter filter, int pageNum, int pageSize) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        try {
            CriteriaQuery<Property> cq = cb.createQuery(entityClass);
            CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
            Root<Property> c = cq.from(entityClass);
            cq.select(c);
            countCq.select(cb.count(c));

            Predicate condition = null;
            if (!filter.getKufTypes().isEmpty()) {
                condition = c.get("kuf").in(filter.getKufTypes());
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

    public ViewPage<Property> findAllByKufAndBalanceHolder(List<KufType> kuf, Organization bh, int pageNum, int pageSize) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        try {
            CriteriaQuery<Property> cq = cb.createQuery(entityClass);
            CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
            Root<Property> c = cq.from(entityClass);
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

    public List<Property> findByInvNumAndName(String invNum, String name) {
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
    public List<Property> find(List<KufType> value, UUID balanceHolder, Date from, Date to) {
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

            if (!user.getUserID().equals(Const.sysUser)) {
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
            }

            cq.where(condition);

            Query query = em.createQuery(cq);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
