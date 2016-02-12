package municipalproperty.dao;

import java.util.UUID;

import kz.lof.dataengine.jpa.DAO;
import kz.nextbase.script._Session;
import municipalproperty.model.Order;

public class OrderDAO extends DAO<Order, UUID> {

	public OrderDAO(_Session session) {
		super(Order.class, session);
	}

}
