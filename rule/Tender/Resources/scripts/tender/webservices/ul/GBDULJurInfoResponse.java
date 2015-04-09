/**
 * GBDULJurInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;


/**
 * Ответ от ГБД с информацией о ЮЛ
 */
public class GBDULJurInfoResponse  implements java.io.Serializable {
    /* Информация о ЮЛ */
    private tender.webservices.ul.GBDULJurInfoType jurInfo;

    /* Системная информация */
    private tender.webservices.ul.SystemInfoType systemInfo;

    public GBDULJurInfoResponse() {
    }

    public GBDULJurInfoResponse(
           tender.webservices.ul.GBDULJurInfoType jurInfo,
           tender.webservices.ul.SystemInfoType systemInfo) {
           this.jurInfo = jurInfo;
           this.systemInfo = systemInfo;
    }


    /**
     * Gets the jurInfo value for this GBDULJurInfoResponse.
     * 
     * @return jurInfo   * Информация о ЮЛ
     */
    public tender.webservices.ul.GBDULJurInfoType getJurInfo() {
        return jurInfo;
    }


    /**
     * Sets the jurInfo value for this GBDULJurInfoResponse.
     * 
     * @param jurInfo   * Информация о ЮЛ
     */
    public void setJurInfo(tender.webservices.ul.GBDULJurInfoType jurInfo) {
        this.jurInfo = jurInfo;
    }


    /**
     * Gets the systemInfo value for this GBDULJurInfoResponse.
     * 
     * @return systemInfo   * Системная информация
     */
    public tender.webservices.ul.SystemInfoType getSystemInfo() {
        return systemInfo;
    }


    /**
     * Sets the systemInfo value for this GBDULJurInfoResponse.
     * 
     * @param systemInfo   * Системная информация
     */
    public void setSystemInfo(tender.webservices.ul.SystemInfoType systemInfo) {
        this.systemInfo = systemInfo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GBDULJurInfoResponse)) return false;
        GBDULJurInfoResponse other = (GBDULJurInfoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.jurInfo==null && other.getJurInfo()==null) ||
             (this.jurInfo!=null &&
              this.jurInfo.equals(other.getJurInfo()))) &&
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
        if (getJurInfo() != null) {
            _hashCode += getJurInfo().hashCode();
        }
        if (getSystemInfo() != null) {
            _hashCode += getSystemInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GBDULJurInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jurInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "JurInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "SystemInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", "SystemInfoType"));
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
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
