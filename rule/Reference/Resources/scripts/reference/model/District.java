package reference.model;

import kz.lof.scripting._Session;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "districts", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "region_id"}))
@NamedQuery(name = "District.findAll", query = "SELECT m FROM District AS m ORDER BY m.regDate")
public class District extends Reference {
    private List<Locality> localities;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Region region;

    @OneToMany(mappedBy = "district")
    public List<Locality> getLocalities() {
        return localities;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append(super.getFullXMLChunk(ses));
        if (region != null && region.getId() != null) {
            chunk.append("<region id=\"" + region.getId() + "\">" + region.getLocalizedName(ses.getLang()) + "</region>");
        }
        return chunk.toString();
    }
}
