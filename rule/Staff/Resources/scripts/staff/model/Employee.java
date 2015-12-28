package staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kz.flabs.dataengine.DatabaseFactory;
import kz.flabs.dataengine.ISystemDatabase;
import kz.flabs.users.User;
import kz.nextbase.script._URL;
import staff.exception.EmployеeException;

@Entity
@Table(name = "employees")
@NamedQuery(name = "Employee.findAll", query = "SELECT m FROM Employee AS m ORDER BY m.regDate")
public class Employee extends Staff {
	@Column(nullable = false, length = 32)
	private String login;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Organization organization;

	private Department department;

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Organization getOrganization() {
		return organization;
	}

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	public Department getDepartment() {
		return department;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws EmployеeException {
		ISystemDatabase sysDb = DatabaseFactory.getSysDatabase();
		User user = sysDb.getUser(login);
		if (user != null) {
			this.login = user.getUserID();
		} else {
			throw new EmployеeException("Login " + login + " is not been associated with some user. Register a User before");
		}

	}

	public User getUser() throws EmployеeException {
		ISystemDatabase sysDb = DatabaseFactory.getSysDatabase();
		User user = sysDb.getUser(login);
		if (user != null) {
			return user;
		} else {
			throw new EmployеeException("User who associated with the Employer has not been found");
		}

	}

	@Override
	public _URL getURL() {
		return new _URL("Provider?id=employee_form&amp;docid=" + getId());
	}

}
