package municipalproperty.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.dataengine.jpa.SecureAppEntity;
import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.scripting._Session;
import com.exponentus.user.SuperUser;

import municipalproperty.model.Order;
import municipalproperty.model.Property;

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

			Predicate condition = c.get("properties").in(property);
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
