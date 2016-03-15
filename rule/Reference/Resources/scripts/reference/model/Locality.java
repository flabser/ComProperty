package reference.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.lof.scripting._Session;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "localities")
@NamedQuery(name = "Locality.findAll", query = "SELECT m FROM Locality AS m ORDER BY m.regDate")
public class Locality extends Reference {

    @JsonIgnore
    @OneToMany(mappedBy = "locality")
    private List<Street> streets;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private LocalityType type;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private District district;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Region region;

    public LocalityType getType() {
        return type;
    }

    public void setType(LocalityType type) {
        this.type = type;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append(super.getFullXMLChunk(ses));
        if (type != null && type.getId() != null) {
            chunk.append("<localitytype id=\"" + type.getId() + "\">" + type.getLocalizedName(ses.getLang()) + "</localitytype>");
        }
        if (region != null && region.getId() != null) {
            chunk.append("<region id=\"" + region.getId() + "\">" + region.getLocalizedName(ses.getLang()) + "</region>");
        }
        if (district != null && district.getId() != null) {
            chunk.append("<district id=\"" + district.getId() + "\">" + district.getLocalizedName(ses.getLang()) + "</district>");
        }
        return chunk.toString();
    }
}
