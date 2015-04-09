/**
 * GBDULJurAddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;


/**
 * Юридический адрес
 */
public class GBDULJurAddressType  implements java.io.Serializable {
    /* Код адресного регистра (Резерв для интеграции с
     * 						ГБД АР) */
    private String KAR;

    /* Страна */
    private String country;

    /* Регион */
    private String region;

    /* Область */
    private String district;

    /* Город */
    private String city;

    /* Улица */
    private String street;

    /* Здание */
    private String house;

    /* Корпус */
    private String building;

    /* Квартира */
    private String apartment;

    public GBDULJurAddressType() {
    }

    public GBDULJurAddressType(
           String KAR,
           String country,
           String region,
           String district,
           String city,
           String street,
           String house,
           String building,
           String apartment) {
           this.KAR = KAR;
           this.country = country;
           this.region = region;
           this.district = district;
           this.city = city;
           this.street = street;
           this.house = house;
           this.building = building;
           this.apartment = apartment;
    }


    /**
     * Gets the KAR value for this GBDULJurAddressType.
     *
     * @return KAR   * Код адресного регистра (Резерв для интеграции с
     * 						ГБД АР)
     */
    public String getKAR() {
        return KAR;
    }


    /**
     * Sets the KAR value for this GBDULJurAddressType.
     *
     * @param KAR   * Код адресного регистра (Резерв для интеграции с
     * 						ГБД АР)
     */
    public void setKAR(String KAR) {
        this.KAR = KAR;
    }


    /**
     * Gets the country value for this GBDULJurAddressType.
     *
     * @return country   * Страна
     */
    public String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this GBDULJurAddressType.
     *
     * @param country   * Страна
     */
    public void setCountry(String country) {
        this.country = country;
    }


    /**
     * Gets the region value for this GBDULJurAddressType.
     *
     * @return region   * Регион
     */
    public String getRegion() {
        return region;
    }


    /**
     * Sets the region value for this GBDULJurAddressType.
     *
     * @param region   * Регион
     */
    public void setRegion(String region) {
        this.region = region;
    }


    /**
     * Gets the district value for this GBDULJurAddressType.
     *
     * @return district   * Область
     */
    public String getDistrict() {
        return district;
    }


    /**
     * Sets the district value for this GBDULJurAddressType.
     *
     * @param district   * Область
     */
    public void setDistrict(String district) {
        this.district = district;
    }


    /**
     * Gets the city value for this GBDULJurAddressType.
     *
     * @return city   * Город
     */
    public String getCity() {
        return city;
    }


    /**
     * Sets the city value for this GBDULJurAddressType.
     *
     * @param city   * Город
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Gets the street value for this GBDULJurAddressType.
     *
     * @return street   * Улица
     */
    public String getStreet() {
        return street;
    }


    /**
     * Sets the street value for this GBDULJurAddressType.
     *
     * @param street   * Улица
     */
    public void setStreet(String street) {
        this.street = street;
    }


    /**
     * Gets the house value for this GBDULJurAddressType.
     *
     * @return house   * Здание
     */
    public String getHouse() {
        return house;
    }


    /**
     * Sets the house value for this GBDULJurAddressType.
     *
     * @param house   * Здание
     */
    public void setHouse(String house) {
        this.house = house;
    }


    /**
     * Gets the building value for this GBDULJurAddressType.
     *
     * @return building   * Корпус
     */
    public String getBuilding() {
        return building;
    }


    /**
     * Sets the building value for this GBDULJurAddressType.
     *
     * @param building   * Корпус
     */
    public void setBuilding(String building) {
        this.building = building;
    }


    /**
     * Gets the apartment value for this GBDULJurAddressType.
     *
     * @return apartment   * Квартира
     */
    public String getApartment() {
        return apartment;
    }


    /**
     * Sets the apartment value for this GBDULJurAddressType.
     *
     * @param apartment   * Квартира
     */
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GBDULJurAddressType)) return false;
        GBDULJurAddressType other = (GBDULJurAddressType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.KAR==null && other.getKAR()==null) ||
             (this.KAR!=null &&
              this.KAR.equals(other.getKAR()))) &&
            ((this.country==null && other.getCountry()==null) ||
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.region==null && other.getRegion()==null) ||
             (this.region!=null &&
              this.region.equals(other.getRegion()))) &&
            ((this.district==null && other.getDistrict()==null) ||
             (this.district!=null &&
              this.district.equals(other.getDistrict()))) &&
            ((this.city==null && other.getCity()==null) ||
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.street==null && other.getStreet()==null) ||
             (this.street!=null &&
              this.street.equals(other.getStreet()))) &&
            ((this.house==null && other.getHouse()==null) ||
             (this.house!=null &&
              this.house.equals(other.getHouse()))) &&
            ((this.building==null && other.getBuilding()==null) ||
             (this.building!=null &&
              this.building.equals(other.getBuilding()))) &&
            ((this.apartment==null && other.getApartment()==null) ||
             (this.apartment!=null &&
              this.apartment.equals(other.getApartment())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getKAR() != null) {
            _hashCode += getKAR().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getRegion() != null) {
            _hashCode += getRegion().hashCode();
        }
        if (getDistrict() != null) {
            _hashCode += getDistrict().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getStreet() != null) {
            _hashCode += getStreet().hashCode();
        }
        if (getHouse() != null) {
            _hashCode += getHouse().hashCode();
        }
        if (getBuilding() != null) {
            _hashCode += getBuilding().hashCode();
        }
        if (getApartment() != null) {
            _hashCode += getApartment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GBDULJurAddressType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurAddressType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KAR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "KAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("region");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Region"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("district");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "District"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "City"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("street");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Street"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("house");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "House"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("building");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Building"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apartment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Apartment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
