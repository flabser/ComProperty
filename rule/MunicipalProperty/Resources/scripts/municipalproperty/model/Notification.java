package municipalproperty.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import kz.flabs.util.Util;
import kz.lof.dataengine.jpa.SecureAppEntity;
import kz.lof.scripting._Session;
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

	@Override
	public String getShortXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<type>" + ses.getAppEnv().vocabulary.getWord(type.name().toLowerCase(), ses.getLang()) + "</type>");
		chunk.append("<sender>" + sender + "</sender>");
		chunk.append("<sendingtime>" + Util.convertDateToStringSilently(sendingTime) + "</sendingtime>");
		chunk.append("<body>" + body + "</body>");
		chunk.append("<recipient>" + recipient + "</recipient>");
		return chunk.toString();
	}
}
