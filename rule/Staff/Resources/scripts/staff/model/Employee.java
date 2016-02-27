package staff.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kz.flabs.dataengine.DatabaseFactory;
import kz.flabs.dataengine.ISystemDatabase;
import kz.flabs.users.User;
import kz.flabs.util.Util;
import kz.lof.dataengine.system.IEmployee;
import kz.lof.scripting._Session;
import reference.model.Position;
import staff.exception.EmployeeException;

@Entity
@Table(name = "employees")
@NamedQuery(name = "Employee.findAll", query = "SELECT m FROM Employee AS m ORDER BY m.regDate")
public class Employee extends Staff implements IEmployee {
	@Column(nullable = false, length = 32)
	private String login;

	@Column(name = "birth_date")
	private String birthDate;

	private String iin;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Organization organization;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Department department;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Position position;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employee_role")
	private List<Role> roles;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws EmployeeException {
		ISystemDatabase sysDb = DatabaseFactory.getSysDatabase();
		User user = sysDb.getUser(login);
		if (user != null) {
			this.login = user.getUserID();
		} else {
			throw new EmployeeException("Login " + login + " is not been associated with some user. Register a User before");
		}

	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getIin() {
		return iin;
	}

	public void setIin(String iin) {
		this.iin = iin;
	}

	public User getUser() throws EmployeeException {
		ISystemDatabase sysDb = DatabaseFactory.getSysDatabase();
		User user = sysDb.getUser(login);
		if (user != null) {
			return user;
		} else {
			throw new EmployeeException("User who associated with the Employer has not been found");
		}

	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role r) {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		roles.add(r);
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		if (regDate != null) {
			chunk.append("<regdate>" + Util.simpleDateTimeFormat.format(regDate) + "</regdate>");
		}
		chunk.append("<name>" + getName() + "</name>");
		chunk.append("<email>" + login + "</email>");
		chunk.append("<login>" + login + "</login>");
		chunk.append("<birthdate>" + getName() + "</birthdate>");
		if (department != null) {
			chunk.append("<department>" + department + "</department>");
		} else {
			chunk.append("<department></department>");
		}
		chunk.append("<iin>" + iin + "</iin>");
		chunk.append("<organization>" + organization + "</organization>");
		chunk.append("<position>" + position + "</position>");
		chunk.append("<roles>" + roles + "</roles>");
		return chunk.toString();
	}
}
