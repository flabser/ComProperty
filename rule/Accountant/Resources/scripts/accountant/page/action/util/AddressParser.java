package accountant.page.action.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.scripting._Session;
import com.exponentus.server.Server;

import reference.dao.CityDistrictDAO;
import reference.dao.StreetDAO;
import reference.model.CityDistrict;
import reference.model.Street;
import reference.model.embedded.Address;

/**
 * Created by Мария on 01.03.2016.
 */
public class AddressParser {

	public void parseAddresses(Address address, _Session session) {

		String line = address.getAdditionalInfo();
		ArrayList<String> consts = new ArrayList<>();
		ArrayList<String> clearElements;
		CityDistrictDAO distrDAO = new CityDistrictDAO(session);
		StreetDAO streetDAO = new StreetDAO(session);
		ArrayList<Unit> parts;

		consts.add("р");
		consts.add("ул");
		consts.add("район");
		consts.add("пр");
		consts.add("а");
		consts.add("д");
		consts.add("кв");
		consts.add("г");
		consts.add("Алматы");
		consts.add("микрорайон");
		consts.add("мкр");
		consts.add("дом");
		consts.add("уг");

		try {

			if (!line.contains("�")) {

				line = line.replaceAll("\"", "");
				line = line.replaceAll(" ", "#").replaceAll("\\.", "#").replaceAll(",", "#");
				String[] elements = line.split("#");
				clearElements = new ArrayList<>();
				parts = new ArrayList<>();
				for (int i = 0; i < elements.length; i++) {
					if (!consts.contains(elements[i]) && !"".equalsIgnoreCase(elements[i])) {
						clearElements.add(elements[i]);
					}
				}

				for (String e : clearElements) {
					parts.add(new Unit(e));
				}

				for (int i = 0; i < parts.size(); i++) {
					checkForDistrict(parts.get(i), distrDAO);
					if (i < parts.size() - 1) {
						checkForStreet(parts.get(i), parts.get(i + 1), streetDAO);
					}
					checkForHouse(parts.get(i));
					checkForFlat(parts.get(i));
				}

				parts = (ArrayList<Unit>) joinStreet(parts);

				for (Unit u : parts) {
					if (u.isDistrict()) {
						address.setCityDistrict(u.cityDistrict);
					}
					if (u.isStreet()) {
						address.setStreet(u.street);
					}
					if (u.isHouse()) {
						address.setHouseNumber(u.house);
					}

					// System.out.println(u);
				}
				// System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isDistrict(String value, CityDistrictDAO dao) {
		if (value.contains("ск")) {
			value = value.substring(0, value.indexOf("ск"));
		}
		ViewPage<CityDistrict> districtViewPage = dao.findAllByKeyword(value, 0, 100);
		return (districtViewPage.getCount() > 0);
	}

	private void checkForHouse(Unit unit) {
		if (unit.name == null) {
			return;
		}
		if (!unit.isDistrict() && !unit.isStreet()) {
			if (unit.name.length() < 6 && StringUtils.containsAny(unit.name, "1234567890")) {
				unit.house = unit.name;
			}
		}
	}

	private void checkForFlat(Unit unit) {
		if (unit.name == null) {
			return;
		}
		if (!unit.isDistrict() && !unit.isStreet() && !unit.isHouse()) {
			if (unit.name.length() < 6 && StringUtils.containsAny(unit.name, "1234567890")) {
				unit.flat = true;
			}
		}
	}

	private void checkForDistrict(Unit unit, CityDistrictDAO dao) {
		String value = "";
		if (unit.name.contains("ск")) {
			value = unit.name.substring(0, unit.name.indexOf("ск"));
			ViewPage<CityDistrict> districtViewPage = dao.findAllByKeyword(value, 0, 100);
			if (districtViewPage.getCount() > 0) {
				unit.cityDistrict = districtViewPage.getResult().get(0);
			}

		}

	}

	private void checkForStreet(Unit unit, Unit nextUnit, StreetDAO dao) {
		if (!unit.isDistrict()) {
			try {
				Street street = dao.findByName(unit.name.toUpperCase());
				if (street != null) {
					unit.street = street;
					street = dao.findByName(unit.name.toUpperCase() + " " + nextUnit.name.toUpperCase());

					if (street != null) {
						unit.full = false;
						// nextUnit.isStreet() = true;
						nextUnit.street = street;
						nextUnit.full = false;
					}

					// unit.street = true;

				} else {
					ViewPage streetViewPage = dao.findAllByKeyword(unit.name.toUpperCase(), 0, 100);
					if (streetViewPage.getCount() > 0 && nextUnit != null) {
						try {
							street = dao.findByName(unit.name.toUpperCase() + " " + nextUnit.name.toUpperCase());
						} catch (DAOException e) {
							Server.logger.errorLogEntry(e);
						}
						if (street != null) {
							// unit.street = true;
							unit.street = street;
							unit.full = false;
							// nextUnit.street = true;
							nextUnit.street = street;
							nextUnit.full = false;
						} else {
							// unit.street = true;
							unit.street = street;
						}
					}
				}
			} catch (DAOException e) {
				Server.logger.errorLogEntry(e);
			}
		}

	}

	private List<Unit> joinStreet(List<Unit> parts) {
		for (int i = 0; i < parts.size(); i++) {
			Unit prevUnit = parts.get(i);
			if (prevUnit.isStreet() && !prevUnit.full) {
				if (i < parts.size() - 1) {
					Unit nextUnit = parts.get(i + 1);
					prevUnit.name = prevUnit.name + " " + nextUnit.name;
					prevUnit.full = true;
					parts.remove(nextUnit);
				}
			}
		}
		return parts;
	}

	class Unit {
		String name;
		CityDistrict cityDistrict;
		Street street;
		String house;
		boolean flat;
		boolean full = true;

		Unit(String name) {
			this.name = name;
		}

		public boolean isDistrict() {
			return this.cityDistrict != null;
		}

		public boolean isStreet() {
			return this.street != null;
		}

		public boolean isHouse() {
			return this.house != null;
		}

		/*
		 * public String toString() { return name + " is district " + district +
		 * "\n" + " is street " + street + "\n" + " is full " + full + "\n" +
		 * " is house " + house + "\n" + " is flat " + flat; }
		 */

		@Override
		public String toString() {
			return name + "\n" + (cityDistrict != null ? " is city district " + cityDistrict + "\n" : "")
			        + (street != null ? " is street " + street + "\n" : "") + (street != null ? " is full " + full + "\n" : "")
			        + (house != null ? " is house " + house + "\n" : "") + (flat ? " is flat " + flat : "");
		}
	}

}
