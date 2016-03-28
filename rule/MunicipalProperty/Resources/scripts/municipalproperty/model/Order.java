package municipalproperty.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kz.flabs.util.Util;
import kz.lof.common.model.Attachment;
import kz.lof.dataengine.jpa.SecureAppEntity;
import kz.lof.scripting._Session;

@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.findAll", query = "SELECT m FROM Order AS m ORDER BY m.regDate")
public class Order extends SecureAppEntity {

	public enum OrderStatus {
		INACTIVE, ACTIVE
	}

	@Column(name = "reg_number")
	private String regNumber;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Property property;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "order_attachments", joinColumns = { @JoinColumn(name = "parent_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "attachment_id", referencedColumnName = "id") })
	private List<Attachment> attachments = new ArrayList<>();

	private String description = "";

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "order_status")
	private OrderStatus orderStatus = OrderStatus.ACTIVE;

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String getShortXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<regdate>" + Util.simpleDateTimeFormat.format(regDate) + "</regdate>");
		chunk.append("<regnumber>" + regNumber + "</regnumber>");
		chunk.append("<description>" + description + "</description>");
		chunk.append("<orderstatus>" + ses.getAppEnv().vocabulary.getWord(getOrderStatus().name().toLowerCase(), ses.getLang()) + "</orderstatus>");
		chunk.append("<property docid=\"" + property.getId() + "\">");
		chunk.append("<url>" + property.getURL() + "</url>");
		chunk.append("<objectname>" + property.getObjectName() + "</objectname>");
		chunk.append("<balanceholder>" + property.getBalanceHolder().getLocalizedName(ses.getLang()) + "</balanceholder>");
		chunk.append("<propertycode>" + property.getPropertyCode().getLocalizedName(ses.getLang()) + "</propertycode>");
		chunk.append("</property>");

		if (!getAttachments().isEmpty()) {
			chunk.append("<attachments>" + getAttachments().size() + "</attachments>");
		}
		return chunk.toString();
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<regdate>" + Util.simpleDateTimeFormat.format(regDate) + "</regdate>");
		chunk.append("<regnumber>" + regNumber + "</regnumber>");
		chunk.append("<description>" + description + "</description>");
		chunk.append("<orderstatus>" + getOrderStatus() + "</orderstatus>");
		chunk.append("<property docid=\"" + property.getId() + "\">");
		chunk.append("<url>" + property.getURL() + "</url>");
		chunk.append("<objectname>" + property.getObjectName() + "</objectname>");
		chunk.append("<balanceholder>" + property.getBalanceHolder().getLocalizedName(ses.getLang()) + "</balanceholder>");
		chunk.append("<propertycode>" + property.getPropertyCode().getLocalizedName(ses.getLang()) + "</propertycode>");
		chunk.append("</property>");

		if (!getAttachments().isEmpty()) {
			chunk.append("<attachments>" + getAttachments().stream().map(it -> it.getShortXMLChunk(ses)).collect(Collectors.joining(""))
			        + "</attachments>");
		}
		return chunk.toString();
	}
}
