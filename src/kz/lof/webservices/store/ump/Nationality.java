/**
 * Nationality.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class Nationality  implements java.io.Serializable {
    private java.lang.String femaleName;

    private int idNat;

    private java.lang.String maleName;

    public Nationality() {
    }

    public Nationality(
           java.lang.String femaleName,
           int idNat,
           java.lang.String maleName) {
           this.femaleName = femaleName;
           this.idNat = idNat;
           this.maleName = maleName;
    }


    /**
     * Gets the femaleName value for this Nationality.
     * 
     * @return femaleName
     */
    public java.lang.String getFemaleName() {
        return femaleName;
    }


    /**
     * Sets the femaleName value for this Nationality.
     * 
     * @param femaleName
     */
    public void setFemaleName(java.lang.String femaleName) {
        this.femaleName = femaleName;
    }


    /**
     * Gets the idNat value for this Nationality.
     * 
     * @return idNat
     */
    public int getIdNat() {
        return idNat;
    }


    /**
     * Sets the idNat value for this Nationality.
     * 
     * @param idNat
     */
    public void setIdNat(int idNat) {
        this.idNat = idNat;
    }


    /**
     * Gets the maleName value for this Nationality.
     * 
     * @return maleName
     */
    public java.lang.String getMaleName() {
        return maleName;
    }


    /**
     * Sets the maleName value for this Nationality.
     * 
     * @param maleName
     */
    public void setMaleName(java.lang.String maleName) {
        this.maleName = maleName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Nationality)) return false;
        Nationality other = (Nationality) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.femaleName==null && other.getFemaleName()==null) || 
             (this.femaleName!=null &&
              this.femaleName.equals(other.getFemaleName()))) &&
            this.idNat == other.getIdNat() &&
            ((this.maleName==null && other.getMaleName()==null) || 
             (this.maleName!=null &&
              this.maleName.equals(other.getMaleName())));
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
        if (getFemaleName() != null) {
            _hashCode += getFemaleName().hashCode();
        }
        _hashCode += getIdNat();
        if (getMaleName() != null) {
            _hashCode += getMaleName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Nationality.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Nationality"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("femaleName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "femaleName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idNat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "idNat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maleName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "maleName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
