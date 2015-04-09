/**
 * GBDULJurItemType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;


/**
 * Информация о ЮЛ
 */
public class GBDULJurItemType  implements java.io.Serializable {
    /* Страна инкорпорации */
    private String incorporationCountry;

    /* Регистрационный номер в стране инкорпорации */
    private String incorporationCountryRegNumber;

    /* Налоговый номер в стране инкорпорации */
    private String incorporationCountryNN;

    /* Дата (пере)регистрации */
    private java.util.Date regDate;

    /* Полное наименование */
    private tender.webservices.ul.GBDULNameType fullName;

    public GBDULJurItemType() {
    }

    public GBDULJurItemType(
           String incorporationCountry,
           String incorporationCountryRegNumber,
           String incorporationCountryNN,
           java.util.Date regDate,
           tender.webservices.ul.GBDULNameType fullName) {
           this.incorporationCountry = incorporationCountry;
           this.incorporationCountryRegNumber = incorporationCountryRegNumber;
           this.incorporationCountryNN = incorporationCountryNN;
           this.regDate = regDate;
           this.fullName = fullName;
    }


    /**
     * Gets the incorporationCountry value for this GBDULJurItemType.
     *
     * @return incorporationCountry   * Страна инкорпорации
     */
    public String getIncorporationCountry() {
        return incorporationCountry;
    }


    /**
     * Sets the incorporationCountry value for this GBDULJurItemType.
     *
     * @param incorporationCountry   * Страна инкорпорации
     */
    public void setIncorporationCountry(String incorporationCountry) {
        this.incorporationCountry = incorporationCountry;
    }


    /**
     * Gets the incorporationCountryRegNumber value for this GBDULJurItemType.
     *
     * @return incorporationCountryRegNumber   * Регистрационный номер в стране инкорпорации
     */
    public String getIncorporationCountryRegNumber() {
        return incorporationCountryRegNumber;
    }


    /**
     * Sets the incorporationCountryRegNumber value for this GBDULJurItemType.
     *
     * @param incorporationCountryRegNumber   * Регистрационный номер в стране инкорпорации
     */
    public void setIncorporationCountryRegNumber(String incorporationCountryRegNumber) {
        this.incorporationCountryRegNumber = incorporationCountryRegNumber;
    }


    /**
     * Gets the incorporationCountryNN value for this GBDULJurItemType.
     *
     * @return incorporationCountryNN   * Налоговый номер в стране инкорпорации
     */
    public String getIncorporationCountryNN() {
        return incorporationCountryNN;
    }


    /**
     * Sets the incorporationCountryNN value for this GBDULJurItemType.
     *
     * @param incorporationCountryNN   * Налоговый номер в стране инкорпорации
     */
    public void setIncorporationCountryNN(String incorporationCountryNN) {
        this.incorporationCountryNN = incorporationCountryNN;
    }


    /**
     * Gets the regDate value for this GBDULJurItemType.
     *
     * @return regDate   * Дата (пере)регистрации
     */
    public java.util.Date getRegDate() {
        return regDate;
    }


    /**
     * Sets the regDate value for this GBDULJurItemType.
     *
     * @param regDate   * Дата (пере)регистрации
     */
    public void setRegDate(java.util.Date regDate) {
        this.regDate = regDate;
    }


    /**
     * Gets the fullName value for this GBDULJurItemType.
     *
     * @return fullName   * Полное наименование
     */
    public tender.webservices.ul.GBDULNameType getFullName() {
        return fullName;
    }


    /**
     * Sets the fullName value for this GBDULJurItemType.
     *
     * @param fullName   * Полное наименование
     */
    public void setFullName(tender.webservices.ul.GBDULNameType fullName) {
        this.fullName = fullName;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GBDULJurItemType)) return false;
        GBDULJurItemType other = (GBDULJurItemType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.incorporationCountry==null && other.getIncorporationCountry()==null) ||
             (this.incorporationCountry!=null &&
              this.incorporationCountry.equals(other.getIncorporationCountry()))) &&
            ((this.incorporationCountryRegNumber==null && other.getIncorporationCountryRegNumber()==null) ||
             (this.incorporationCountryRegNumber!=null &&
              this.incorporationCountryRegNumber.equals(other.getIncorporationCountryRegNumber()))) &&
            ((this.incorporationCountryNN==null && other.getIncorporationCountryNN()==null) ||
             (this.incorporationCountryNN!=null &&
              this.incorporationCountryNN.equals(other.getIncorporationCountryNN()))) &&
            ((this.regDate==null && other.getRegDate()==null) ||
             (this.regDate!=null &&
              this.regDate.equals(other.getRegDate()))) &&
            ((this.fullName==null && other.getFullName()==null) ||
             (this.fullName!=null &&
              this.fullName.equals(other.getFullName())));
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
        if (getIncorporationCountry() != null) {
            _hashCode += getIncorporationCountry().hashCode();
        }
        if (getIncorporationCountryRegNumber() != null) {
            _hashCode += getIncorporationCountryRegNumber().hashCode();
        }
        if (getIncorporationCountryNN() != null) {
            _hashCode += getIncorporationCountryNN().hashCode();
        }
        if (getRegDate() != null) {
            _hashCode += getRegDate().hashCode();
        }
        if (getFullName() != null) {
            _hashCode += getFullName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GBDULJurItemType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurItemType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incorporationCountry");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "IncorporationCountry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incorporationCountryRegNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "IncorporationCountryRegNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incorporationCountryNN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "IncorporationCountryNN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "RegDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "FullName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULNameType"));
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
