/**
 * RegTypeCount.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class RegTypeCount  implements java.io.Serializable {
    private int recCount;

    private kz.lof.webservices.store.ump.RegType regType;

    public RegTypeCount() {
    }

    public RegTypeCount(
           int recCount,
           kz.lof.webservices.store.ump.RegType regType) {
           this.recCount = recCount;
           this.regType = regType;
    }


    /**
     * Gets the recCount value for this RegTypeCount.
     * 
     * @return recCount
     */
    public int getRecCount() {
        return recCount;
    }


    /**
     * Sets the recCount value for this RegTypeCount.
     * 
     * @param recCount
     */
    public void setRecCount(int recCount) {
        this.recCount = recCount;
    }


    /**
     * Gets the regType value for this RegTypeCount.
     * 
     * @return regType
     */
    public kz.lof.webservices.store.ump.RegType getRegType() {
        return regType;
    }


    /**
     * Sets the regType value for this RegTypeCount.
     * 
     * @param regType
     */
    public void setRegType(kz.lof.webservices.store.ump.RegType regType) {
        this.regType = regType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RegTypeCount)) return false;
        RegTypeCount other = (RegTypeCount) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.recCount == other.getRecCount() &&
            ((this.regType==null && other.getRegType()==null) || 
             (this.regType!=null &&
              this.regType.equals(other.getRegType())));
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
        _hashCode += getRecCount();
        if (getRegType() != null) {
            _hashCode += getRegType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RegTypeCount.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "RegTypeCount"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "recCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "regType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "RegType"));
        elemField.setNillable(true);
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
