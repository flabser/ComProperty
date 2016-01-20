package staff.model;

import kz.flabs.dataengine.DatabaseFactory;
import kz.flabs.dataengine.ISystemDatabase;
import kz.flabs.users.User;
import staff.exception.EmployеeException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


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

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Department department;

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
}
