package reference.dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import kz.lof.scripting._Session;
import municipalproperty.model.constants.KufType;
import reference.model.Kuf;

public class KufDAO extends ReferenceDAO<Kuf, UUID> {

	public KufDAO(_Session session) {
		super(Kuf.class, session);
	}

	public Kuf findByKufType(KufType name) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM Kuf AS m WHERE m.name = :name";
			TypedQuery<Kuf> q = em.createQuery(jpql, getEntityClass());
			q.setParameter("name", name);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
}
