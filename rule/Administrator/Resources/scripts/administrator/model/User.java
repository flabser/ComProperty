package administrator.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "_users")
@NamedQuery(name = "User.findAll", query = "SELECT m FROM User AS m ORDER BY m.regDate")
public class User {
	@Id
	@Column(name = "id", nullable = false)
	protected long id;

	@Column(name = "reg_date", nullable = false, updatable = false)
	protected Date regDate;

	@Column(length = 128, unique = true)
	private String userName;

	@Column(length = 64, unique = true)
	private String login;

	@Column(length = 128, unique = true)
	private String pwd;

	private int status;

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
