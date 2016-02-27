package reference.dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import kz.lof.scripting._Session;
import reference.model.Country;
import reference.model.constants.CountryCode;

public class CountryDAO extends ReferenceDAO<Country, UUID> {

	public CountryDAO(_Session session) {
		super(Country.class, session);
	}

	public Country findByCode(CountryCode code) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM Country AS m WHERE m.code = :code";
			TypedQuery<Country> q = em.createQuery(jpql, getEntityClass());
			q.setParameter("code", code);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
}
