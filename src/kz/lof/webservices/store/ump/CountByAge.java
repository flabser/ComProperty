/**
 * CountByAge.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class CountByAge  implements java.io.Serializable {
    private int age;

    private int leavedMaleCount;

    private int leavedFemaleCount;

    private int arrivedMaleCount;

    private int arrivedFemaleCount;

    public CountByAge() {
    }

    public CountByAge(
           int age,
           int leavedMaleCount,
           int leavedFemaleCount,
           int arrivedMaleCount,
           int arrivedFemaleCount) {
           this.age = age;
           this.leavedMaleCount = leavedMaleCount;
           this.leavedFemaleCount = leavedFemaleCount;
           this.arrivedMaleCount = arrivedMaleCount;
           this.arrivedFemaleCount = arrivedFemaleCount;
    }


    /**
     * Gets the age value for this CountByAge.
     * 
     * @return age
     */
    public int getAge() {
        return age;
    }


    /**
     * Sets the age value for this CountByAge.
     * 
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }


    /**
     * Gets the leavedMaleCount value for this CountByAge.
     * 
     * @return leavedMaleCount
     */
    public int getLeavedMaleCount() {
        return leavedMaleCount;
    }


    /**
     * Sets the leavedMaleCount value for this CountByAge.
     * 
     * @param leavedMaleCount
     */
    public void setLeavedMaleCount(int leavedMaleCount) {
        this.leavedMaleCount = leavedMaleCount;
    }


    /**
     * Gets the leavedFemaleCount value for this CountByAge.
     * 
     * @return leavedFemaleCount
     */
    public int getLeavedFemaleCount() {
        return leavedFemaleCount;
    }


    /**
     * Sets the leavedFemaleCount value for this CountByAge.
     * 
     * @param leavedFemaleCount
     */
    public void setLeavedFemaleCount(int leavedFemaleCount) {
        this.leavedFemaleCount = leavedFemaleCount;
    }


    /**
     * Gets the arrivedMaleCount value for this CountByAge.
     * 
     * @return arrivedMaleCount
     */
    public int getArrivedMaleCount() {
        return arrivedMaleCount;
    }


    /**
     * Sets the arrivedMaleCount value for this CountByAge.
     * 
     * @param arrivedMaleCount
     */
    public void setArrivedMaleCount(int arrivedMaleCount) {
        this.arrivedMaleCount = arrivedMaleCount;
    }


    /**
     * Gets the arrivedFemaleCount value for this CountByAge.
     * 
     * @return arrivedFemaleCount
     */
    public int getArrivedFemaleCount() {
        return arrivedFemaleCount;
    }


    /**
     * Sets the arrivedFemaleCount value for this CountByAge.
     * 
     * @param arrivedFemaleCount
     */
    public void setArrivedFemaleCount(int arrivedFemaleCount) {
        this.arrivedFemaleCount = arrivedFemaleCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CountByAge)) return false;
        CountByAge other = (CountByAge) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.age == other.getAge() &&
            this.leavedMaleCount == other.getLeavedMaleCount() &&
            this.leavedFemaleCount == other.getLeavedFemaleCount() &&
            this.arrivedMaleCount == other.getArrivedMaleCount() &&
            this.arrivedFemaleCount == other.getArrivedFemaleCount();
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
        _hashCode += getAge();
        _hashCode += getLeavedMaleCount();
        _hashCode += getLeavedFemaleCount();
        _hashCode += getArrivedMaleCount();
        _hashCode += getArrivedFemaleCount();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CountByAge.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountByAge"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("age");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "age"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("leavedMaleCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "leavedMaleCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("leavedFemaleCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "leavedFemaleCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrivedMaleCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "arrivedMaleCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrivedFemaleCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "arrivedFemaleCount"));
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
