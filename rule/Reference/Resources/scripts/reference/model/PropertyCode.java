package reference.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "property_codes")
@NamedQuery(name = "PropertyCode.findAll", query = "SELECT m FROM PropertyCode AS m ORDER BY m.regDate")
public class PropertyCode extends Reference {

}