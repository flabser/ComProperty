/**
 * SystemInfoTypeMessageType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;

public class SystemInfoTypeMessageType implements java.io.Serializable {
    private String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SystemInfoTypeMessageType(String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final String _request = "request";
    public static final String _response = "response";
    public static final String _notification = "notification";
    public static final String _error = "error";
    public static final String _other = "other";
    public static final SystemInfoTypeMessageType request = new SystemInfoTypeMessageType(_request);
    public static final SystemInfoTypeMessageType response = new SystemInfoTypeMessageType(_response);
    public static final SystemInfoTypeMessageType notification = new SystemInfoTypeMessageType(_notification);
    public static final SystemInfoTypeMessageType error = new SystemInfoTypeMessageType(_error);
    public static final SystemInfoTypeMessageType other = new SystemInfoTypeMessageType(_other);
    public String getValue() { return _value_;}
    public static SystemInfoTypeMessageType fromValue(String value)
          throws IllegalArgumentException {
        SystemInfoTypeMessageType enumeration = (SystemInfoTypeMessageType)
            _table_.get(value);
        if (enumeration==null) throw new IllegalArgumentException();
        return enumeration;
    }
    public static SystemInfoTypeMessageType fromString(String value)
          throws IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public String toString() { return _value_;}
    public Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SystemInfoTypeMessageType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", ">SystemInfoType>MessageType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
