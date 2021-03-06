package municipalproperty.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.exponentus.scripting._Session;

@Entity
@Table(name = "equipments")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "Equipment.findAll", query = "SELECT m FROM Equipment AS m ORDER BY m.regDate")
public class Equipment extends Property {

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
