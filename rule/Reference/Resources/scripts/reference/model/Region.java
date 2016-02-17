package reference.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kz.flabs.localization.LanguageType;
import reference.model.constants.RegionType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "regions")
@NamedQuery(name = "Region.findAll", query = "SELECT m FROM Region AS m ORDER BY m.regDate")
public class Region extends Reference {
	private List<District> districts;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Country country;

	@Enumerated(EnumType.STRING)
	@Column(name = "region_type", nullable = true, length = 32)
	private RegionType type = RegionType.UNKNOWN;

	public RegionType getType() {
		return type;
	}

	public void setType(RegionType type) {
		this.type = type;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "region")
	public List<District> getDistricts() {
		return districts;
	}

	@JsonIgnore
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String getShortXMLChunk(LanguageType lang) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<name>" + getName() + "</name>");
		chunk.append("<country>" + country + "</country>");
		return chunk.toString();

	}
}
