/**
 * CountMigByNat.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class CountMigByNat  implements java.io.Serializable {
    private kz.lof.webservices.store.ump.Nationality nat;

    private int pribylCount;

    private int ubylCount;

    public CountMigByNat() {
    }

    public CountMigByNat(
           kz.lof.webservices.store.ump.Nationality nat,
           int pribylCount,
           int ubylCount) {
           this.nat = nat;
           this.pribylCount = pribylCount;
           this.ubylCount = ubylCount;
    }


    /**
     * Gets the nat value for this CountMigByNat.
     * 
     * @return nat
     */
    public kz.lof.webservices.store.ump.Nationality getNat() {
        return nat;
    }


    /**
     * Sets the nat value for this CountMigByNat.
     * 
     * @param nat
     */
    public void setNat(kz.lof.webservices.store.ump.Nationality nat) {
        this.nat = nat;
    }


    /**
     * Gets the pribylCount value for this CountMigByNat.
     * 
     * @return pribylCount
     */
    public int getPribylCount() {
        return pribylCount;
    }


    /**
     * Sets the pribylCount value for this CountMigByNat.
     * 
     * @param pribylCount
     */
    public void setPribylCount(int pribylCount) {
        this.pribylCount = pribylCount;
    }


    /**
     * Gets the ubylCount value for this CountMigByNat.
     * 
     * @return ubylCount
     */
    public int getUbylCount() {
        return ubylCount;
    }


    /**
     * Sets the ubylCount value for this CountMigByNat.
     * 
     * @param ubylCount
     */
    public void setUbylCount(int ubylCount) {
        this.ubylCount = ubylCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CountMigByNat)) return false;
        CountMigByNat other = (CountMigByNat) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nat==null && other.getNat()==null) || 
             (this.nat!=null &&
              this.nat.equals(other.getNat()))) &&
            this.pribylCount == other.getPribylCount() &&
            this.ubylCount == other.getUbylCount();
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
        if (getNat() != null) {
            _hashCode += getNat().hashCode();
        }
        _hashCode += getPribylCount();
        _hashCode += getUbylCount();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CountMigByNat.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountMigByNat"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "nat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Nationality"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pribylCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "pribylCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ubylCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "ubylCount"));
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
