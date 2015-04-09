/**
 * Education.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class Education  implements java.io.Serializable {
    private int id;

    private java.lang.String nameEdu;

    private java.lang.String nameSpec;

    public Education() {
    }

    public Education(
           int id,
           java.lang.String nameEdu,
           java.lang.String nameSpec) {
           this.id = id;
           this.nameEdu = nameEdu;
           this.nameSpec = nameSpec;
    }


    /**
     * Gets the id value for this Education.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this Education.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the nameEdu value for this Education.
     * 
     * @return nameEdu
     */
    public java.lang.String getNameEdu() {
        return nameEdu;
    }


    /**
     * Sets the nameEdu value for this Education.
     * 
     * @param nameEdu
     */
    public void setNameEdu(java.lang.String nameEdu) {
        this.nameEdu = nameEdu;
    }


    /**
     * Gets the nameSpec value for this Education.
     * 
     * @return nameSpec
     */
    public java.lang.String getNameSpec() {
        return nameSpec;
    }


    /**
     * Sets the nameSpec value for this Education.
     * 
     * @param nameSpec
     */
    public void setNameSpec(java.lang.String nameSpec) {
        this.nameSpec = nameSpec;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Education)) return false;
        Education other = (Education) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.nameEdu==null && other.getNameEdu()==null) || 
             (this.nameEdu!=null &&
              this.nameEdu.equals(other.getNameEdu()))) &&
            ((this.nameSpec==null && other.getNameSpec()==null) || 
             (this.nameSpec!=null &&
              this.nameSpec.equals(other.getNameSpec())));
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
        _hashCode += getId();
        if (getNameEdu() != null) {
            _hashCode += getNameEdu().hashCode();
        }
        if (getNameSpec() != null) {
            _hashCode += getNameSpec().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Education.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Education"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameEdu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "nameEdu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameSpec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "nameSpec"));
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
