/**
 * FIO_.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ip;

public class FIO_  implements java.io.Serializable {
    private java.lang.String firstName;

    private java.lang.String middleName;

    private java.lang.String surName;

    public FIO_() {
    }

    public FIO_(
           java.lang.String firstName,
           java.lang.String middleName,
           java.lang.String surName) {
           this.firstName = firstName;
           this.middleName = middleName;
           this.surName = surName;
    }


    /**
     * Gets the firstName value for this FIO_.
     *
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this FIO_.
     *
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the middleName value for this FIO_.
     *
     * @return middleName
     */
    public java.lang.String getMiddleName() {
        return middleName;
    }


    /**
     * Sets the middleName value for this FIO_.
     *
     * @param middleName
     */
    public void setMiddleName(java.lang.String middleName) {
        this.middleName = middleName;
    }


    /**
     * Gets the surName value for this FIO_.
     *
     * @return surName
     */
    public java.lang.String getSurName() {
        return surName;
    }


    /**
     * Sets the surName value for this FIO_.
     *
     * @param surName
     */
    public void setSurName(java.lang.String surName) {
        this.surName = surName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FIO_)) return false;
        FIO_ other = (FIO_) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.firstName==null && other.getFirstName()==null) ||
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.middleName==null && other.getMiddleName()==null) ||
             (this.middleName!=null &&
              this.middleName.equals(other.getMiddleName()))) &&
            ((this.surName==null && other.getSurName()==null) ||
             (this.surName!=null &&
              this.surName.equals(other.getSurName())));
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
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getMiddleName() != null) {
            _hashCode += getMiddleName().hashCode();
        }
        if (getSurName() != null) {
            _hashCode += getSurName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FIO_.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "FIO_"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "firstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("middleName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "middleName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "surName"));
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
