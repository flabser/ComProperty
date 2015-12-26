package monitoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "personal_estates")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "PersonalEstate.findAll", query = "SELECT m FROM PersonalEstate AS m ORDER BY m.regDate")
public class PersonalEstate extends Property {

	@Column(name = "ready_to_use")
	private boolean readyToUse;

	public boolean isReadyToUse() {
		return readyToUse;
	}

	public void setReadyToUse(boolean readyToUse) {
		this.readyToUse = readyToUse;
	}

}
