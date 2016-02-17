package reference.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "org_categories")
@NamedQuery(name = "OrgCategory.findAll", query = "SELECT m FROM OrgCategory AS m ORDER BY m.regDate")
public class OrgCategory extends Reference {

}
