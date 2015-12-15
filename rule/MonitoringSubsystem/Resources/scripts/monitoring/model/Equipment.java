package monitoring.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import monitoring.model.constants.PropertyType;

@Entity
@Table(name = "equipments")
@NamedQuery(name = "Equipment.findAll", query = "SELECT m FROM Equipment AS m ORDER BY m.regDate")
public class Equipment extends Property implements IProperty {

	@Override
	public PropertyType getType() {
		return PropertyType.EQUIPMENT;
	}

}
