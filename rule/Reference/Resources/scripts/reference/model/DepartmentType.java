package reference.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "department_types")
@NamedQuery(name = "DepartmentType.findAll", query = "SELECT m FROM DepartmentType AS m ORDER BY m.regDate")
public class DepartmentType extends Reference {

}
