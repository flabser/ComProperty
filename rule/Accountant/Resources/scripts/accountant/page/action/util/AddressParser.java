package accountant.page.action.util;

import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._Session;
import org.apache.commons.lang3.StringUtils;
import reference.dao.DistrictDAO;
import reference.dao.StreetDAO;
import reference.model.District;
import reference.model.Street;
import reference.model.embedded.Address;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Мария on 01.03.2016.
 */
public class AddressParser {

    public void parseAddresses(Address address, _Session session) {

        String line = address.getAdditionalInfo();
        ArrayList<String> consts = new ArrayList<>();
        ArrayList<String> clearElements;
        DistrictDAO distrDAO = new DistrictDAO(session);
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
                        address.setDistrict(u.district);
                    }
                    if (u.isStreet()) {
                        address.setStreet(u.street);
                    }
                    if (u.isHouse()) {
                        address.setHouseNumber(u.house);
                    }

                    //System.out.println(u);
                }
                //System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isDistrict(String value, DistrictDAO dao) {
        if (value.contains("ск")) {
            value = value.substring(0, value.indexOf("ск"));
        }
        ViewPage<District> districtViewPage = dao.findAllByKeyword(value, 0, 100);
        return (districtViewPage.getCount() > 0);
    }

    private void checkForHouse(Unit unit) {
        if (unit.name == null) return;
        if (!unit.isDistrict() && !unit.isStreet()) {
            if (unit.name.length() < 6 && StringUtils.containsAny(unit.name, "1234567890")) {
                unit.house = unit.name;
            }
        }
    }

    private void checkForFlat(Unit unit) {
        if (unit.name == null) return;
        if (!unit.isDistrict() && !unit.isStreet() && !unit.isHouse()) {
            if (unit.name.length() < 6 && StringUtils.containsAny(unit.name, "1234567890")) {
                unit.flat = true;
            }
        }
    }

    private void checkForDistrict(Unit unit, DistrictDAO dao) {
        String value = "";
        if (unit.name.contains("ск")) {
            value = unit.name.substring(0, unit.name.indexOf("ск"));
            ViewPage<District> districtViewPage = dao.findAllByKeyword(value, 0, 100);
            if (districtViewPage.getCount() > 0) {
                unit.district = districtViewPage.getResult().get(0);
            }

        }

    }

    private void checkForStreet(Unit unit, Unit nextUnit, StreetDAO dao) {
        if (!unit.isDistrict()) {
            Street street = dao.findByName(unit.name.toUpperCase());
            if (street != null) {
                street = dao.findByName(unit.name.toUpperCase() + " " + nextUnit.name.toUpperCase());

                if (street != null) {
                    unit.full = false;
                    //nextUnit.isStreet() = true;
                    nextUnit.street = street;
                    nextUnit.full = false;
                }

                //unit.street = true;
                unit.street = street;
            } else {
                ViewPage streetViewPage = dao.findAllByKeyword(unit.name.toUpperCase(), 0, 100);
                if (streetViewPage.getCount() > 0 && nextUnit != null) {
                    street = dao.findByName(unit.name.toUpperCase() + " " + nextUnit.name.toUpperCase());
                    if (street != null) {
                        //unit.street = true;
                        unit.street = street;
                        unit.full = false;
                        //nextUnit.street = true;
                        nextUnit.street = street;
                        nextUnit.full = false;
                    } else {
                        //unit.street = true;
                        unit.street = street;
                    }
                }
            }
        }
    }

    private boolean isStreet(String firstValue, String secondValue, StreetDAO dao) {
        Street street = dao.findByName(firstValue.toUpperCase());
        if (street == null) {
            street = dao.findByName(secondValue.toUpperCase());
            if (street == null) {
                street = dao.findByName(firstValue.toUpperCase() + " " + secondValue.toUpperCase());
                if (street == null) {
                    ViewPage<Street> streetViewPage = dao.findAllByKeyword(firstValue + " " + secondValue, 0, 100);
                    if (streetViewPage.getCount() == 0) {
                        streetViewPage = dao.findAllByKeyword(firstValue, 0, 100);
                        return (streetViewPage.getCount() > 0);
                    }

                }
            }
        }
        return street == null;
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
        District district;
        Street street;
        String house;
        boolean flat;
        boolean full = true;

        Unit(String name) {
            this.name = name;
        }



        public boolean isDistrict() {
            return this.district != null;
        }

        public boolean isStreet() {
            return this.street != null;
        }

        public boolean isHouse() {
            return this.house != null;
        }

     /*   public String toString() {
            return name + " is district " + district + "\n" +
                    " is street " + street + "\n" +
                    " is full "  + full + "\n" +
                    " is house " + house + "\n" +
                    " is flat " + flat;
        }*/


        public String toString() {
            return name + "\n" +
                    (district != null ? " is district " + district + "\n" : "") +
                    (street != null ? " is street " + street + "\n" : "") +
                    (street != null ? " is full " + full + "\n" : "") +
                    (house != null ? " is house " + house + "\n" : "") +
                    (flat ? " is flat " + flat : "");
        }
    }

}
