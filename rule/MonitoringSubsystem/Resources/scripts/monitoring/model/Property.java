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
	private String invNumber;
	private String objectName;
	private long originalCost;
	private String propertycode_name;
	private String assignment;
	private int yearRelease;

}
