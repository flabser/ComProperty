package administrator.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import kz.lof.user.IUser;

@Entity
@Table(name = "_users")
@NamedQuery(name = "User.findAll", query = "SELECT m FROM User AS m ORDER BY m.regDate")
public class User implements IUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	protected long id;

	@Column(name = "reg_date", nullable = false, updatable = false)
	protected Date regDate;

	@Transient
	private String userName;

	@Column(length = 64, unique = false)
	private String login;

	@Column(length = 64)
	private String email = "";

	private String pwd;

	private String pwdHash;

	private int status;

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Transient
	private boolean isAuthorized;

	@PrePersist
	private void prePersist() {
		regDate = new Date();
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String getPwdHash() {
		return pwdHash;
	}

	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public boolean isAuthorized() {
		return isAuthorized;
	}

	@Override
	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	@Override
	public String getUserID() {
		return login;
	}

}
