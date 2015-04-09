/**
 * GBDULCheckNameExistRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;


/**
 * Запрос в ГБД для проверки существования наименования ЮЛ
 */
public class GBDULCheckNameExistRequest  implements java.io.Serializable {
    /* Полное наименование */
    private String fullName;

    /* Сокращенное наименование */
    private String shortName;

    /* Язак, на котором указано наименование */
    private String language;

    /* Системная информация */
    private tender.webservices.ul.SystemInfoType systemInfo;

    public GBDULCheckNameExistRequest() {
    }

    public GBDULCheckNameExistRequest(
           String fullName,
           String shortName,
           String language,
           tender.webservices.ul.SystemInfoType systemInfo) {
           this.fullName = fullName;
           this.shortName = shortName;
           this.language = language;
           this.systemInfo = systemInfo;
    }


    /**
     * Gets the fullName value for this GBDULCheckNameExistRequest.
     *
     * @return fullName   * Полное наименование
     */
    public String getFullName() {
        return fullName;
    }


    /**
     * Sets the fullName value for this GBDULCheckNameExistRequest.
     *
     * @param fullName   * Полное наименование
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    /**
     * Gets the shortName value for this GBDULCheckNameExistRequest.
     *
     * @return shortName   * Сокращенное наименование
     */
    public String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this GBDULCheckNameExistRequest.
     *
     * @param shortName   * Сокращенное наименование
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the language value for this GBDULCheckNameExistRequest.
     *
     * @return language   * Язак, на котором указано наименование
     */
    public String getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this GBDULCheckNameExistRequest.
     *
     * @param language   * Язак, на котором указано наименование
     */
    public void setLanguage(String language) {
        this.language = language;
    }


    /**
     * Gets the systemInfo value for this GBDULCheckNameExistRequest.
     *
     * @return systemInfo   * Системная информация
     */
    public tender.webservices.ul.SystemInfoType getSystemInfo() {
        return systemInfo;
    }


    /**
     * Sets the systemInfo value for this GBDULCheckNameExistRequest.
     *
     * @param systemInfo   * Системная информация
     */
    public void setSystemInfo(tender.webservices.ul.SystemInfoType systemInfo) {
        this.systemInfo = systemInfo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GBDULCheckNameExistRequest)) return false;
        GBDULCheckNameExistRequest other = (GBDULCheckNameExistRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.fullName==null && other.getFullName()==null) ||
             (this.fullName!=null &&
              this.fullName.equals(other.getFullName()))) &&
            ((this.shortName==null && other.getShortName()==null) ||
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.language==null && other.getLanguage()==null) ||
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.systemInfo==null && other.getSystemInfo()==null) ||
             (this.systemInfo!=null &&
              this.systemInfo.equals(other.getSystemInfo())));
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
        if (getFullName() != null) {
            _hashCode += getFullName().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getSystemInfo() != null) {
            _hashCode += getSystemInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GBDULCheckNameExistRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULCheckNameExistRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "FullName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "SystemInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", "SystemInfoType"));
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
