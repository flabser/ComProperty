package municipalproperty.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "engineering_infrastructure")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "EngineeringInfrastructure.findAll", query = "SELECT m FROM EngineeringInfrastructure AS m ORDER BY m.regDate")
public class EngineeringInfrastructure extends Property {

}
