package reference.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.lof.scripting._Session;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "regions")
@NamedQuery(name = "Region.findAll", query = "SELECT m FROM Region AS m ORDER BY m.regDate")
public class Region extends Reference {

    @JsonIgnore
    @OneToMany(mappedBy = "region")
    private List<District> districts;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Country country;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private RegionType type;

    public RegionType getType() {
        return type;
    }

    public void setType(RegionType type) {
        this.type = type;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append(super.getFullXMLChunk(ses));
        chunk.append("<country>" + country.getId() + "</country>");
        chunk.append("<type>" + type.getId() + "</type>");
        return chunk.toString();
    }

    @Override
    public String getShortXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append("<name>" + getName() + "</name>");
        chunk.append("<country>" + country + "</country>");
        return chunk.toString();
    }
}
