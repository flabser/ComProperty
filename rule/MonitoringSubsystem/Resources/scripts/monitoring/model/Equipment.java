package monitoring.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "equipments")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "Equipment.findAll", query = "SELECT m FROM Equipment AS m ORDER BY m.regDate")
public class Equipment extends Property {

}
