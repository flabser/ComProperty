package monitoring.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import kz.flabs.dataengine.jpa.AppEntity;

@Entity
@Table(name = "properties")
public class Property extends AppEntity {
	private String address;
	private long balanceCost;
	private String description;
	private String form;
	private String invnumber;
	private String objectname;
	private long originalcost;
	private String propertycode_name;

}
