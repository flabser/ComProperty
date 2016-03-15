package reference.model;

import kz.lof.scripting._Session;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "streets")
@NamedQuery(name = "Street.findAll", query = "SELECT m FROM Street AS m ORDER BY m.regDate")
public class Street extends Reference {
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Locality locality;

    @Column(name = "street_id")
    private int streetId;

    public int getStreetId() {
        return streetId;
    }

    public void setStreetId(int streetId) {
        this.streetId = streetId;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality city) {
        this.locality = city;
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append(super.getFullXMLChunk(ses));
        if (locality != null && locality.getId() != null) {
            chunk.append("<locality id=\"" + locality.getId() + "\">" + locality.getLocalizedName(ses.getLang()) + "</locality>");
        }
        return chunk.toString();
    }
}
