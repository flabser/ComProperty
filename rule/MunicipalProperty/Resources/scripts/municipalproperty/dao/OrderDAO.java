package municipalproperty.dao;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.dataengine.jpa.SecureAppEntity;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._Session;
import kz.lof.user.SuperUser;
import municipalproperty.model.Order;
import municipalproperty.model.Property;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;


public class OrderDAO extends DAO<Order, UUID> {

    public OrderDAO(_Session session) {
        super(Order.class, session);
    }

    public ViewPage<Order> findAllByProperty(Property property) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        try {
            CriteriaQuery<Order> cq = cb.createQuery(Order.class);
            CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
            Root<Order> c = cq.from(Order.class);
            cq.select(c);
            countCq.select(cb.count(c));

            Predicate condition = c.get("property").in(property);
            if (user.getId() != SuperUser.ID && SecureAppEntity.class.isAssignableFrom(getEntityClass())) {
                if (condition == null) {
                    condition = cb.and(c.get("readers").in(user.getId()));
                } else {
                    condition = cb.and(c.get("readers").in(user.getId()), condition);
                }
            }
            cq.orderBy(cb.asc(c.get("regDate")));
            cq.where(condition);
            countCq.where(condition);
            TypedQuery<Order> typedQuery = em.createQuery(cq);
            Query query = em.createQuery(countCq);
            long count = (long) query.getSingleResult();

            List<Order> result = typedQuery.getResultList();

            return new ViewPage<>(result, count, 0, 0);
        } finally {
            em.close();
        }
    }
}
