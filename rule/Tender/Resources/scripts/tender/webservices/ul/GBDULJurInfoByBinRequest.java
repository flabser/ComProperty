/**
 * GBDULJurInfoByBinRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;


/**
 * Запрос в ГБД для получения информации о ЮЛ по БИН
 */
public class GBDULJurInfoByBinRequest  implements java.io.Serializable {
    /* БИН */
    private String BIN;

    /* РНН */
    private String RNN;

    /* Системная информация */
    private tender.webservices.ul.SystemInfoType systemInfo;

    public GBDULJurInfoByBinRequest() {
    }

    public GBDULJurInfoByBinRequest(
           String BIN,
           String RNN,
           tender.webservices.ul.SystemInfoType systemInfo) {
           this.BIN = BIN;
           this.RNN = RNN;
           this.systemInfo = systemInfo;
    }


    /**
     * Gets the BIN value for this GBDULJurInfoByBinRequest.
     *
     * @return BIN   * БИН
     */
    public String getBIN() {
        return BIN;
    }


    /**
     * Sets the BIN value for this GBDULJurInfoByBinRequest.
     *
     * @param BIN   * БИН
     */
    public void setBIN(String BIN) {
        this.BIN = BIN;
    }


    /**
     * Gets the RNN value for this GBDULJurInfoByBinRequest.
     *
     * @return RNN   * РНН
     */
    public String getRNN() {
        return RNN;
    }


    /**
     * Sets the RNN value for this GBDULJurInfoByBinRequest.
     *
     * @param RNN   * РНН
     */
    public void setRNN(String RNN) {
        this.RNN = RNN;
    }


    /**
     * Gets the systemInfo value for this GBDULJurInfoByBinRequest.
     *
     * @return systemInfo   * Системная информация
     */
    public tender.webservices.ul.SystemInfoType getSystemInfo() {
        return systemInfo;
    }


    /**
     * Sets the systemInfo value for this GBDULJurInfoByBinRequest.
     *
     * @param systemInfo   * Системная информация
     */
    public void setSystemInfo(tender.webservices.ul.SystemInfoType systemInfo) {
        this.systemInfo = systemInfo;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GBDULJurInfoByBinRequest)) return false;
        GBDULJurInfoByBinRequest other = (GBDULJurInfoByBinRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.BIN==null && other.getBIN()==null) ||
             (this.BIN!=null &&
              this.BIN.equals(other.getBIN()))) &&
            ((this.RNN==null && other.getRNN()==null) ||
             (this.RNN!=null &&
              this.RNN.equals(other.getRNN()))) &&
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
        if (getBIN() != null) {
            _hashCode += getBIN().hashCode();
        }
        if (getRNN() != null) {
            _hashCode += getRNN().hashCode();
        }
        if (getSystemInfo() != null) {
            _hashCode += getSystemInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GBDULJurInfoByBinRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurInfoByBinRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "BIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RNN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "RNN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
