package monitoring.model.common;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

@Entity
@Table(name = "countries")
@Converter(name = "uuidConverter", converterClass = UUIDConverter.class)
// @NamedQuery(name = "Country.findAll", query = "SELECT m FROM Country AS m ")
public class Country {
	@Id

	@Convert("uuidConverter")
	@Column(name = "uuid")
	protected UUID uuid;

	@Column(name = "reg_date", nullable = false, updatable = false)
	protected Date regDate;

	private String name;
	private List<Region> regions;

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "country") public
	 * List<Region> getRegions() { return regions; }
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
