package municipalproperty.model;

import kz.lof.scripting._Session;
import reference.model.embedded.Address;

import javax.persistence.*;


@Entity
@Table(name = "real_estates")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "RealEstate.findAll", query = "SELECT m FROM RealEstate AS m ORDER BY m.regDate")
public class RealEstate extends Property {
    @Embedded
    private Address address;

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
        if (address.getRegion().getType() != null) {
            chunk.append("<regiontype id=\"" + address.getRegion().getType().getId() + "\">" + address.getRegion().getType().getLocalizedName(ses.getLang()) + "</regiontype>");
        }
        if (address.getRegion().getId() != null) {
            chunk.append("<region id=\"" + address.getRegion().getId() + "\">" + address.getRegion().getLocalizedName(ses.getLang()) + "</region>");
        }
        if (address.getDistrict().getId() != null) {
            chunk.append("<district id=\"" + address.getDistrict().getId() + "\">" + address.getDistrict().getLocalizedName(ses.getLang()) + "</district>");
        }
        if (address.getLocality().getType() != null) {
            chunk.append("<localitytype id=\"" + address.getLocality().getType().getId() + "\">" + address.getLocality().getType().getLocalizedName(ses.getLang()) + "</localitytype>");
        }
        if (address.getLocality().getId() != null) {
            chunk.append("<locality id=\"" + address.getLocality().getId() + "\">" + address.getLocality().getLocalizedName(ses.getLang()) + "</locality>");
        }
        if (address.getStreet().getId() != null) {
            chunk.append("<street id=\"" + address.getStreet().getId() + "\">" + address.getStreet().getLocalizedName(ses.getLang()) + "</street>");
        }
        /*chunk.append("<regiontype>" + address.getRegion().getType() + "</regiontype>");
        chunk.append("<region>" + address.getRegion().getName() + "</region>");
        chunk.append("<regionid>" + address.getRegion().getId() + "</regionid>");
        chunk.append("<district>" + address.getDistrict().getName() + "</district>");
        chunk.append("<districtid>" + address.getDistrict().getId() + "</districtid>");
        chunk.append("<localitytype>" + address.getLocality().getType() + "</localitytype>");
        chunk.append("<locality>" + address.getLocality().getName() + "</locality>");
        chunk.append("<localityid>" + address.getLocality().getId() + "</localityid>");
        chunk.append("<street>" + address.getStreet().getName() + "</street>");
        chunk.append("<streetid>" + address.getStreet().getId() + "</streetid>");*/
        chunk.append("<housenumber>" + address.getHouseNumber() + "</housenumber>");
        chunk.append("<additionalinfo>" + address.getAdditionalInfo() + "</additionalinfo>");
        chunk.append("</address>");

        return super.getFullXMLChunk(ses) + chunk;
    }
}
