package municipalproperty.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.exponentus.dataengine.jpa.SecureAppEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import staff.model.Organization;

@Entity
@Table(name = "prev_balance_holders")
@NamedQuery(name = "PrevBalanceHolder.findAll", query = "SELECT m FROM PrevBalanceHolder AS m ORDER BY m.regDate")
public class PrevBalanceHolder extends SecureAppEntity<UUID> {

	@JsonIgnore
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Property property;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Organization balanceHolder;

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Organization getBalanceHolder() {
		return balanceHolder;
	}

	public void setBalanceHolder(Organization balanceHolder) {
		this.balanceHolder = balanceHolder;
	}

}