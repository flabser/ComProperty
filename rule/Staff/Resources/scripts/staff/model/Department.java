package staff.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import staff.model.constants.DepartmentType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "departments")
@NamedQuery(name = "Department.findAll", query = "SELECT m FROM Department AS m ORDER BY m.regDate")
public class Department extends Staff {
	private DepartmentType type;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Organization organization;

	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@JsonIgnore
	public List<Employee> getEmployees() {
		return employees;
	}

	public DepartmentType getType() {
		return type;
	}

	public void setType(DepartmentType type) {
		this.type = type;
	}

}
