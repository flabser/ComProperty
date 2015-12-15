package monitoring.model.common;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kz.flabs.dataengine.jpa.AppEntity;

@Entity
@Table(name = "regions")
@NamedQuery(name = "Region.findAll", query = "SELECT m FROM Country AS m ORDER BY m.regDate")
public class Region extends AppEntity {
	private String name;
	private List<District> districts;
	private Country country;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	public List<District> getDistricts() {
		return districts;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
