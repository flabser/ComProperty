package municipalproperty.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.exponentus.common.model.Attachment;
import com.exponentus.dataengine.jpa.SecureAppEntity;
import com.exponentus.scripting._Session;
import com.exponentus.util.Util;

import municipalproperty.dao.ContractDAO;

@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.findAll", query = "SELECT m FROM Order AS m ORDER BY m.regDate")
public class Order extends SecureAppEntity<UUID> {

	public enum OrderStatus {
		INACTIVE, ACTIVE
	}

	@Column(name = "reg_number")
	private String regNumber;

	@Column(name = "applied_reg_date")
	private Date appliedRegDate;

	@ManyToMany
	@JoinTable(name = "property_orders")
	private List<Property> properties;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "order_attachments", joinColumns = { @JoinColumn(name = "parent_id", referencedColumnName = "id") }, inverseJoinColumns = {
	        @JoinColumn(name = "attachment_id", referencedColumnName = "id") })
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

	public Date getAppliedRegDate() {
		return appliedRegDate;
	}

	public void setAppliedRegDate(Date appliedRegDate) {
		this.appliedRegDate = appliedRegDate;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
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
		chunk.append("<regdate>" + Util.convertDateToStringSilently(regDate) + "</regdate>");
		chunk.append("<regnumber>" + regNumber + "</regnumber>");
		chunk.append("<appliedregdate>" + Util.convertDateToStringSilently(appliedRegDate) + "</appliedregdate>");
		chunk.append("<description>" + description + "</description>");
		chunk.append("<orderstatus>" + ses.getAppEnv().vocabulary.getWord(getOrderStatus().name().toLowerCase(), ses.getLang()) + "</orderstatus>");

		if (!getAttachments().isEmpty()) {
			chunk.append("<attachments>" + getAttachments().size() + "</attachments>");
		}
		return chunk.toString();
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<regdate>" + Util.convertDateToStringSilently(regDate) + "</regdate>");
		chunk.append("<regnumber>" + regNumber + "</regnumber>");
		chunk.append("<appliedregdate>" + Util.convertDateToStringSilently(appliedRegDate) + "</appliedregdate>");
		chunk.append("<description>" + description + "</description>");
		chunk.append("<orderstatus>" + getOrderStatus() + "</orderstatus>");

		try {
			String entryAsText = "";
			for (Property property : properties) {
				entryAsText += "<entry id=\"" + property.getId() + "\">" + "<url>" + property.getURL() + "</url>" + "<objectname>"
				        + property.getObjectName() + "</objectname>" + "<balanceholder>" + property.getBalanceHolder().getLocalizedName(ses.getLang())
				        + "</balanceholder>" + "<propertycode>" + property.getPropertyCode().getLocalizedName(ses.getLang()) + "</propertycode>"
				        + "</entry>";
			}
			chunk.append("<properties>" + entryAsText + "</properties>");
		} catch (NullPointerException e) {
			chunk.append("<properties></properties>");
		}

		if (getAttachments() != null && !attachments.isEmpty()) {
			chunk.append("<attachments>");
			for (Attachment att : attachments) {
				String downloadUrl = this.getURL() + "&amp;attachment=" + att.getId() + "&amp;att-name=" + att.getRealFileName();
				chunk.append("<attachment id=\"" + att.getId() + "\">");
				chunk.append("<url>" + downloadUrl + "</url>");
				chunk.append(att.getShortXMLChunk(ses));
				chunk.append("</attachment>");
			}
			chunk.append("</attachments>");
		}

		ContractDAO contractDAO = new ContractDAO(ses);
		if (this.getId() != null) {
			List<Contract> contracts = contractDAO.findAllContractsByOrder(this);
			if (!contracts.isEmpty()) {
				chunk.append("<contracts>");
				for (Contract contract : contracts) {
					chunk.append("<contract>");
					chunk.append("<url>" + contract.getURL() + "</url>");
					chunk.append("<description>" + contract.getDescription() + "</description>");
					chunk.append("</contract>");
				}
				chunk.append("</contracts>");
			}
		}

		return chunk.toString();
	}
}
