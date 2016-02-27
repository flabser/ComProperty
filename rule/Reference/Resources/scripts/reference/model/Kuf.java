package reference.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import municipalproperty.model.constants.KufType;

@Entity
@Table(name = "kufs")
@NamedQuery(name = "Kuf.findAll", query = "SELECT m FROM Kuf AS m ORDER BY m.regDate")
public class Kuf extends Reference {

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 32, unique = true)
	private KufType code = KufType.UNKNOWN;

	public KufType getKuf() {
		return code;
	}

	public void setKuf(KufType kuf) {
		this.code = kuf;
	}

}
