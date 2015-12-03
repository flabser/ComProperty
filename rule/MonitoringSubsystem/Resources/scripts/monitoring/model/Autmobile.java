package monitoring.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import kz.flabs.dataengine.jpa.AppEntity;

@Entity
@Table(name = "automobiles")
public class Autmobile extends AppEntity {
	private String bodynumber;
	private String category;
	private String color;
	private String fueltype;
	private String grnz;
	private int maxLoad;
	private String enginenumber;

}
