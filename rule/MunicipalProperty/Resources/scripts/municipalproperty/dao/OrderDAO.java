package municipalproperty.dao;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.scripting._Session;
import municipalproperty.model.Order;

import java.util.UUID;


public class OrderDAO extends DAO<Order, UUID> {

    public OrderDAO(_Session session) {
        super(Order.class, session);
    }
}
