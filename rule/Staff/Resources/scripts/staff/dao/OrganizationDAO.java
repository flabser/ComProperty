package staff.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import staff.model.Organization;

public class OrganizationDAO extends DAO<Organization, UUID> {

	public OrganizationDAO(_Session session) {
		super(Organization.class, session);
	}

	public List<Organization> findAllByKeyword(String keyword, int firstRec, int pageSize) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM Organization AS m WHERE m.name LIKE :keyword";
			TypedQuery<Organization> q = em.createQuery(jpql, Organization.class);
			q.setFirstResult(firstRec);
			q.setMaxResults(pageSize);
			q.setParameter("keyword", "%" + keyword + "%");
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public Long getCountByKeyword(String keyword) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT count(m) FROM Organization AS m WHERE m.name LIKE :keyword";
			Query q = em.createQuery(jpql);
			q.setParameter("keyword", "%" + keyword + "%");
			return (Long) q.getSingleResult();
		} finally {
			em.close();
		}
	}

}
