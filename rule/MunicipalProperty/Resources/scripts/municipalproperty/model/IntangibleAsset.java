package municipalproperty.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "intangible_asset")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "IntangibleAsset.findAll", query = "SELECT m FROM IntangibleAsset AS m ORDER BY m.regDate")
public class IntangibleAsset extends Property {

}
