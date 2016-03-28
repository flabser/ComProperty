package municipalproperty.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "contracts")
@NamedQuery(name = "Contract.findAll", query = "SELECT m FROM Contract AS m Order BY m.regDate")
public class Contract extends SecureAppEntity {

	public enum ContractStatus {
		INACTIVE, ACTIVE
	}

	@Column(name = "reg_number")
	private String regNumber;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Order order;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "contract_attachments", joinColumns = { @JoinColumn(name = "parent_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "attachment_id", referencedColumnName = "id") })
	private List<Attachment> attachments = new ArrayList<>();

	private String description = "";

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "order_status")
	private ContractStatus contractStatus = ContractStatus.ACTIVE;

	private Date expired;

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public ContractStatus getOrderStatus() {
		return contractStatus;
	}

	public void setOrderStatus(ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

	@Override
	public String getShortXMLChunk(_Session ses) {
		return getFullXMLChunk(ses);
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<regdate>" + Util.simpleDateTimeFormat.format(regDate) + "</regdate>");
		chunk.append("<regnumber>" + regNumber + "</regnumber>");
		chunk.append("<description>" + description + "</description>");
		chunk.append("<orderstatus>" + ses.getAppEnv().vocabulary.getWord(getOrderStatus().name().toLowerCase(), ses.getLang()) + "</orderstatus>");
		chunk.append("<order docid=\"" + order.getId() + "\">");
		chunk.append("<url>" + order.getURL() + "</url>");
		chunk.append("<ordername>" + order.getDescription() + "</ordername>");
		chunk.append("</order>");

		if (!getAttachments().isEmpty()) {
			chunk.append("<attachments>" + getAttachments().size() + "</attachments>");
		}
		return chunk.toString();
	}
}
