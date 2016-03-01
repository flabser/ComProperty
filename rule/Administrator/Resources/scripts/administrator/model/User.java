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

}
