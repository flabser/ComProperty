package municipalproperty.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;
import com.exponentus.user.SuperUser;

import municipalproperty.model.Contract;
import municipalproperty.model.Order;

public class ContractDAO extends DAO<Contract, UUID> {
	
	public ContractDAO(_Session session) throws DAOException {
		super(Contract.class, session);
	}
	
	public List<Contract> findAllContractsByOrder(Order order) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		try {
			CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
			Root<Contract> c = cq.from(Contract.class);
			cq.select(c);
			Predicate condition = c.get("order").in(order);
			if (user.getId() != SuperUser.ID) {
				condition = cb.and(c.get("readers").in(user.getId()), condition);
			}
			cq.where(condition);
			Query query = em.createQuery(cq);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
	
}
