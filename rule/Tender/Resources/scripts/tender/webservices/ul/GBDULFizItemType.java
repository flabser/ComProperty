/**
 * GBDULFizItemType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;


/**
 * Информация о ФЛ
 */
public class GBDULFizItemType  implements java.io.Serializable {
    /* Страна постоянного места жительства */
    private String country;

    /* ИИН */
    private String IIN;

    /* Налоговый номер в стране инкорпорации */
    private String RNN;

    /* ФИО */
    private String fullName;

    public GBDULFizItemType() {
    }

    public GBDULFizItemType(
           String country,
           String IIN,
           String RNN,
           String fullName) {
           this.country = country;
           this.IIN = IIN;
           this.RNN = RNN;
           this.fullName = fullName;
    }


    /**
     * Gets the country value for this GBDULFizItemType.
     *
     * @return country   * Страна постоянного места жительства
     */
    public String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this GBDULFizItemType.
     *
     * @param country   * Страна постоянного места жительства
     */
    public void setCountry(String country) {
        this.country = country;
    }


    /**
     * Gets the IIN value for this GBDULFizItemType.
     *
     * @return IIN   * ИИН
     */
    public String getIIN() {
        return IIN;
    }


    /**
     * Sets the IIN value for this GBDULFizItemType.
     *
     * @param IIN   * ИИН
     */
    public void setIIN(String IIN) {
        this.IIN = IIN;
    }


    /**
     * Gets the RNN value for this GBDULFizItemType.
     *
     * @return RNN   * Налоговый номер в стране инкорпорации
     */
    public String getRNN() {
        return RNN;
    }


    /**
     * Sets the RNN value for this GBDULFizItemType.
     *
     * @param RNN   * Налоговый номер в стране инкорпорации
     */
    public void setRNN(String RNN) {
        this.RNN = RNN;
    }


    /**
     * Gets the fullName value for this GBDULFizItemType.
     *
     * @return fullName   * ФИО
     */
    public String getFullName() {
        return fullName;
    }


    /**
     * Sets the fullName value for this GBDULFizItemType.
     *
     * @param fullName   * ФИО
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GBDULFizItemType)) return false;
        GBDULFizItemType other = (GBDULFizItemType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.country==null && other.getCountry()==null) ||
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.IIN==null && other.getIIN()==null) ||
             (this.IIN!=null &&
              this.IIN.equals(other.getIIN()))) &&
            ((this.RNN==null && other.getRNN()==null) ||
             (this.RNN!=null &&
              this.RNN.equals(other.getRNN()))) &&
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
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getIIN() != null) {
            _hashCode += getIIN().hashCode();
        }
        if (getRNN() != null) {
            _hashCode += getRNN().hashCode();
        }
        if (getFullName() != null) {
            _hashCode += getFullName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GBDULFizItemType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULFizItemType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "IIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RNN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "RNN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "FullName"));
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
