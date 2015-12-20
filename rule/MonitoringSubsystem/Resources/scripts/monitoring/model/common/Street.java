package monitoring.model.common;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import kz.flabs.dataengine.jpa.AppEntity;

@Entity
@Table(name = "streets")
@NamedQuery(name = "Street.findAll", query = "SELECT m FROM Street AS m ORDER BY m.regDate")
public class Street extends AppEntity {
	private String name;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "locality_id", referencedColumnName = "id", nullable = false)
	private Locality locality;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality city) {
		this.locality = city;
	}

}
