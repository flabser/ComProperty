package staff.dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import staff.model.Organization;

public class StructureDAO extends DAO<Organization, UUID> {

	public StructureDAO(_Session session) {
		super(Organization.class, session);
	}

	public Organization findPrimaryOrg() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM Organization AS m WHERE m.isPrimary = true";
			TypedQuery<Organization> q = em.createQuery(jpql, Organization.class);
			return q.getResultList().get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		} finally {
			em.close();
		}
	}

}
