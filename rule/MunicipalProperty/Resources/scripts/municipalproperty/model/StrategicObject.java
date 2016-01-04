package municipalproperty.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "strategic_objects")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "StrategicObject.findAll", query = "SELECT m FROM StrategicObject AS m ORDER BY m.regDate")
public class StrategicObject extends Property {

}
