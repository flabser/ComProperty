package municipalproperty.dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import municipalproperty.model.Property;

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

}
