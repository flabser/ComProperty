package municipalproperty.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import kz.lof.dataengine.jpa.SecureAppEntity;
import municipalproperty.model.constants.NotificationType;

@Entity
@Table(name = "notifications")
@NamedQuery(name = "Notification.findAll", query = "SELECT m FROM Notification AS m Order BY m.regDate")
public class Notification extends SecureAppEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false, length = 32)
	private NotificationType type;

	private String sender;

	private String body;

	private String recipient;

	@Column(name = "sending_time")
	private Date sendingTime;

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Date getSendingTime() {
		return sendingTime;
	}

	public void setSendingTime(Date sendingTime) {
		this.sendingTime = sendingTime;
	}

}
