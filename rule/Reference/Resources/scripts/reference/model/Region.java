package reference.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "regions")
@NamedQuery(name = "Region.findAll", query = "SELECT m FROM Region AS m ORDER BY m.regDate")
public class Region extends Reference {
	private List<District> districts;
	private Country country;

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	@OneToMany(mappedBy = "region")
	public List<District> getDistricts() {
		return districts;
	}

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
