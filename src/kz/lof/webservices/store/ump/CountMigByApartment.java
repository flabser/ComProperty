/**
 * CountMigByApartment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class CountMigByApartment  implements java.io.Serializable {
    private kz.lof.webservices.store.ump.ApartmentType apartmentType;

    private int livePeopleCount;

    public CountMigByApartment() {
    }

    public CountMigByApartment(
           kz.lof.webservices.store.ump.ApartmentType apartmentType,
           int livePeopleCount) {
           this.apartmentType = apartmentType;
           this.livePeopleCount = livePeopleCount;
    }


    /**
     * Gets the apartmentType value for this CountMigByApartment.
     * 
     * @return apartmentType
     */
    public kz.lof.webservices.store.ump.ApartmentType getApartmentType() {
        return apartmentType;
    }


    /**
     * Sets the apartmentType value for this CountMigByApartment.
     * 
     * @param apartmentType
     */
    public void setApartmentType(kz.lof.webservices.store.ump.ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }


    /**
     * Gets the livePeopleCount value for this CountMigByApartment.
     * 
     * @return livePeopleCount
     */
    public int getLivePeopleCount() {
        return livePeopleCount;
    }


    /**
     * Sets the livePeopleCount value for this CountMigByApartment.
     * 
     * @param livePeopleCount
     */
    public void setLivePeopleCount(int livePeopleCount) {
        this.livePeopleCount = livePeopleCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CountMigByApartment)) return false;
        CountMigByApartment other = (CountMigByApartment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.apartmentType==null && other.getApartmentType()==null) || 
             (this.apartmentType!=null &&
              this.apartmentType.equals(other.getApartmentType()))) &&
            this.livePeopleCount == other.getLivePeopleCount();
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
        if (getApartmentType() != null) {
            _hashCode += getApartmentType().hashCode();
        }
        _hashCode += getLivePeopleCount();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CountMigByApartment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountMigByApartment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apartmentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "apartmentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "ApartmentType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("livePeopleCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "livePeopleCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
