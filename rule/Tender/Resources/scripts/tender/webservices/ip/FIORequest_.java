/**
 * FIORequest_.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ip;

public class FIORequest_  implements java.io.Serializable {
    private FIO_ fio;

    private SystemInfo_ systemInfo;

    public FIORequest_() {
    }

    public FIORequest_(
           FIO_ fio,
           SystemInfo_ systemInfo) {
           this.fio = fio;
           this.systemInfo = systemInfo;
    }


    /**
     * Gets the fio value for this FIORequest_.
     * 
     * @return fio
     */
    public FIO_ getFio() {
        return fio;
    }


    /**
     * Sets the fio value for this FIORequest_.
     * 
     * @param fio
     */
    public void setFio(FIO_ fio) {
        this.fio = fio;
    }


    /**
     * Gets the systemInfo value for this FIORequest_.
     * 
     * @return systemInfo
     */
    public SystemInfo_ getSystemInfo() {
        return systemInfo;
    }


    /**
     * Sets the systemInfo value for this FIORequest_.
     * 
     * @param systemInfo
     */
    public void setSystemInfo(SystemInfo_ systemInfo) {
        this.systemInfo = systemInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FIORequest_)) return false;
        FIORequest_ other = (FIORequest_) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.fio==null && other.getFio()==null) ||
             (this.fio!=null &&
              this.fio.equals(other.getFio()))) &&
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
        if (getFio() != null) {
            _hashCode += getFio().hashCode();
        }
        if (getSystemInfo() != null) {
            _hashCode += getSystemInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FIORequest_.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "FIORequest_"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "FIO_"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "systemInfo"));
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
