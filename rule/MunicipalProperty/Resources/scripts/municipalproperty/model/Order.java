package municipalproperty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kz.flabs.dataengine.jpa.SecureAppEntity;

@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.findAll", query = "SELECT m FROM Order AS m ORDER BY m.regDate")
public class Order extends SecureAppEntity {

	@Column(name = "reg_number")
	private String regNumber;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Property property;

}
