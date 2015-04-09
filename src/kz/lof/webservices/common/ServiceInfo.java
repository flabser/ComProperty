/**
 * ServiceInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.common;

public class ServiceInfo  implements java.io.Serializable {
    private kz.lof.webservices.common.InfoEntry[] allInfo;

    public ServiceInfo() {
    }

    public ServiceInfo(
           kz.lof.webservices.common.InfoEntry[] allInfo) {
           this.allInfo = allInfo;
    }


    /**
     * Gets the allInfo value for this ServiceInfo.
     * 
     * @return allInfo
     */
    public kz.lof.webservices.common.InfoEntry[] getAllInfo() {
        return allInfo;
    }


    /**
     * Sets the allInfo value for this ServiceInfo.
     * 
     * @param allInfo
     */
    public void setAllInfo(kz.lof.webservices.common.InfoEntry[] allInfo) {
        this.allInfo = allInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceInfo)) return false;
        ServiceInfo other = (ServiceInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.allInfo==null && other.getAllInfo()==null) || 
             (this.allInfo!=null &&
              java.util.Arrays.equals(this.allInfo, other.getAllInfo())));
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
        if (getAllInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAllInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAllInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://common.webservices.lof.kz", "ServiceInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://common.webservices.lof.kz", "allInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://common.webservices.lof.kz", "InfoEntry"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item"));
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
