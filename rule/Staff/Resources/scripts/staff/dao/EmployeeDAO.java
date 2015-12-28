package staff.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import kz.flabs.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import staff.model.Employee;

public class EmployeeDAO extends DAO<Employee, UUID> {

	public EmployeeDAO(_Session session) {
		super(Employee.class, session);
	}

	public Employee findByLogin(String login) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT m FROM Employee AS m WHERE m.login = :login";
			TypedQuery<Employee> q = em.createQuery(jpql, Employee.class);
			q.setParameter("login", login);
			List<Employee> res = q.getResultList();
			return res.get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		} finally {
			em.close();
		}
	}

}
