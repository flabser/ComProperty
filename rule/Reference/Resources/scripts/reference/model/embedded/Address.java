package reference.model.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import kz.flabs.util.Util;
import kz.lof.scripting._Session;
import reference.dao.CountryDAO;
import reference.dao.RegionDAO;
import reference.model.Country;
import reference.model.District;
import reference.model.Locality;
import reference.model.Region;
import reference.model.Street;

@Embeddable
public class Address {

	// @ManyToOne
	// @JoinColumn(nullable = false)
	private Country country;

	// @ManyToOne
	// @JoinColumn
	private Region region;

	// @ManyToOne
	// @JoinColumn
	private District district;

	// @ManyToOne
	// @JoinColumn
	private Locality locality;

	// @ManyToOne
	// @JoinColumn
	private Street street;

	@Column(name = "house_number", length = 10)
	private String houseNumber;

	@Column(name = "additional_info")
	private String additionalInfo;

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality l) {
		this.locality = l;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@Override
	public String toString() {
		return Util.toStringGettersVal(this);
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setRegion(String r) {
		region = new Region();

	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public static Address getStub(_Session session) {
		Address addr = new Address();
		CountryDAO cDao = new CountryDAO(session);
		Country country = cDao.findByName("Казахстан");
		addr.setCountry(country);
		RegionDAO rDao = new RegionDAO(session);
		Region region = rDao.findByName("Алматы");
		addr.setRegion(region);
		District district = new District();
		district.setName("");
		addr.setDistrict(district);
		Locality locality = new Locality();
		locality.setName("");
		addr.setLocality(locality);
		Street street = new Street();
		street.setName("");
		addr.setStreet(street);
		addr.setAdditionalInfo("");
		return addr;

	}

}
