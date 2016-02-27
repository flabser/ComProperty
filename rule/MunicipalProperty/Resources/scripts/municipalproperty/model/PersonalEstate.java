package municipalproperty.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import kz.lof.scripting._Session;

@Entity
@Table(name = "personal_estates")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "PersonalEstate.findAll", query = "SELECT m FROM PersonalEstate AS m ORDER BY m.regDate")
public class PersonalEstate extends Property {

	private String model;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		return super.getFullXMLChunk(ses) + "<model>" + model + "</model>";
	}
}
