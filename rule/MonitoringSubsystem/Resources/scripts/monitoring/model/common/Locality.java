package monitoring.model.common;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kz.flabs.dataengine.jpa.AppEntity;
import monitoring.model.constants.LocalityType;
import monitoring.model.embedded.Address;

@Entity
@Table(name = "localities")
@NamedQuery(name = "Locality.findAll", query = "SELECT m FROM Locality AS m ORDER BY m.regDate")
public class Locality extends AppEntity {
	private String name;
	private List<Address> addresses;
	private List<Street> streets;

	@Enumerated(EnumType.STRING)
	@Column(name = "locality_type", nullable = true, length = 16)
	private LocalityType type = LocalityType.UNKNOWN;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false)
	private District district;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "locality")
	public List<Street> getStreets() {
		return streets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "locality")
	public List<Address> getProperties() {
		return addresses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
