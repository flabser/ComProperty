package staff.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import staff.model.Employer;

public class EmployerDAO extends DAO<Employer, UUID> {

	public EmployerDAO(_Session session) {
		super(Employer.class, session);
	}

	public Employer findByLogin(String login) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM Employer AS m WHERE m.login = :login";
			TypedQuery<Employer> q = em.createQuery(jpql, Employer.class);
			q.setParameter("login", login);
			List<Employer> res = q.getResultList();
			return res.get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		} finally {
			em.close();
		}
	}

}
