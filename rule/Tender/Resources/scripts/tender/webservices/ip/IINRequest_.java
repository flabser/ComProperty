/**
 * IINRequest_.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ip;

public class IINRequest_  implements java.io.Serializable {
    private java.lang.String IIN;

    private SystemInfo_ info;

    public IINRequest_() {
    }

    public IINRequest_(
           java.lang.String IIN,
           SystemInfo_ info) {
           this.IIN = IIN;
           this.info = info;
    }


    /**
     * Gets the IIN value for this IINRequest_.
     *
     * @return IIN
     */
    public java.lang.String getIIN() {
        return IIN;
    }


    /**
     * Sets the IIN value for this IINRequest_.
     *
     * @param IIN
     */
    public void setIIN(java.lang.String IIN) {
        this.IIN = IIN;
    }


    /**
     * Gets the info value for this IINRequest_.
     *
     * @return info
     */
    public SystemInfo_ getInfo() {
        return info;
    }


    /**
     * Sets the info value for this IINRequest_.
     *
     * @param info
     */
    public void setInfo(SystemInfo_ info) {
        this.info = info;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IINRequest_)) return false;
        IINRequest_ other = (IINRequest_) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.IIN==null && other.getIIN()==null) ||
             (this.IIN!=null &&
              this.IIN.equals(other.getIIN()))) &&
            ((this.info==null && other.getInfo()==null) ||
             (this.info!=null &&
              this.info.equals(other.getInfo())));
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
        if (getIIN() != null) {
            _hashCode += getIIN().hashCode();
        }
        if (getInfo() != null) {
            _hashCode += getInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IINRequest_.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "IINRequest_"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("info");
        elemField.setXmlName(new javax.xml.namespace.QName("", "info"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "SystemInfo_"));
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
