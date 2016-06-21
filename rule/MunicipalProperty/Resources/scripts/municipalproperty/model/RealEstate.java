package municipalproperty.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.exponentus.scripting._Session;

import reference.model.embedded.Address;

@Entity
@Table(name = "real_estates")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "RealEstate.findAll", query = "SELECT m FROM RealEstate AS m ORDER BY m.regDate")
public class RealEstate extends Property {
	@Embedded
	private Address address = new Address();

	@Column(name = "count_floors")
	private int countFloors;

	private String material;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getCountFloors() {
		return countFloors;
	}

	public void setCountFloors(int countFloors) {
		this.countFloors = countFloors;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<countfloors>" + countFloors + "</countfloors>");
		chunk.append("<material>" + material + "</material>");

		chunk.append("<address>");
		chunk.append("<regiontype id=\"" + address.getRegion().getType().getId() + "\">"
		        + address.getRegion().getType().getLocalizedName(ses.getLang()) + "</regiontype>");
		chunk.append("<region id=\"" + address.getRegion().getId() + "\">" + address.getRegion().getLocalizedName(ses.getLang()) + "</region>");

		if (address.getDistrict().getId() != null) {
			chunk.append("<district id=\"" + address.getDistrict().getId() + "\">" + address.getDistrict().getLocalizedName(ses.getLang())
			        + "</district>");
		}

		chunk.append("<localitytype id=\"" + address.getLocality().getType().getId() + "\">"
		        + address.getLocality().getType().getLocalizedName(ses.getLang()) + "</localitytype>");
		chunk.append(
		        "<locality id=\"" + address.getLocality().getId() + "\">" + address.getLocality().getLocalizedName(ses.getLang()) + "</locality>");

		if (address.getStreet().getId() != null) {
			chunk.append("<street id=\"" + address.getStreet().getId() + "\">" + address.getStreet().getLocalizedName(ses.getLang()) + "</street>");
		}
		chunk.append("<housenumber>" + address.getHouseNumber() + "</housenumber>");
		chunk.append("<additionalinfo>" + address.getAdditionalInfo() + "</additionalinfo>");
		chunk.append("<coordinates>" + address.getCoordinates() + "</coordinates>");
		chunk.append("</address>");

		return super.getFullXMLChunk(ses) + chunk;
	}
}
