/**
 * GBDULRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;


/**
 * Сведения для поиска субъекта
 */
public class GBDULRequest  implements java.io.Serializable {
    /* БИН */
    private String BIN;

    /* РНН */
    private String RNN;

    public GBDULRequest() {
    }

    public GBDULRequest(
           String BIN,
           String RNN) {
           this.BIN = BIN;
           this.RNN = RNN;
    }


    /**
     * Gets the BIN value for this GBDULRequest.
     *
     * @return BIN   * БИН
     */
    public String getBIN() {
        return BIN;
    }


    /**
     * Sets the BIN value for this GBDULRequest.
     *
     * @param BIN   * БИН
     */
    public void setBIN(String BIN) {
        this.BIN = BIN;
    }


    /**
     * Gets the RNN value for this GBDULRequest.
     *
     * @return RNN   * РНН
     */
    public String getRNN() {
        return RNN;
    }


    /**
     * Sets the RNN value for this GBDULRequest.
     *
     * @param RNN   * РНН
     */
    public void setRNN(String RNN) {
        this.RNN = RNN;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GBDULRequest)) return false;
        GBDULRequest other = (GBDULRequest) obj;
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
              this.RNN.equals(other.getRNN())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GBDULRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-egov/gbdul", "GBDULRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-egov/gbdul", "BIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RNN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-egov/gbdul", "RNN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
