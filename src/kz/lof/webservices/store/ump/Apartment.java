/**
 * Apartment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class Apartment  implements java.io.Serializable {
    private int id;

    private int room;

    private java.lang.String flat;

    private java.lang.String phone;

    private kz.lof.webservices.store.ump.ApartmentType apartmentType;

    public Apartment() {
    }

    public Apartment(
           int id,
           int room,
           java.lang.String flat,
           java.lang.String phone,
           kz.lof.webservices.store.ump.ApartmentType apartmentType) {
           this.id = id;
           this.room = room;
           this.flat = flat;
           this.phone = phone;
           this.apartmentType = apartmentType;
    }


    /**
     * Gets the id value for this Apartment.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this Apartment.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the room value for this Apartment.
     * 
     * @return room
     */
    public int getRoom() {
        return room;
    }


    /**
     * Sets the room value for this Apartment.
     * 
     * @param room
     */
    public void setRoom(int room) {
        this.room = room;
    }


    /**
     * Gets the flat value for this Apartment.
     * 
     * @return flat
     */
    public java.lang.String getFlat() {
        return flat;
    }


    /**
     * Sets the flat value for this Apartment.
     * 
     * @param flat
     */
    public void setFlat(java.lang.String flat) {
        this.flat = flat;
    }


    /**
     * Gets the phone value for this Apartment.
     * 
     * @return phone
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this Apartment.
     * 
     * @param phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the apartmentType value for this Apartment.
     * 
     * @return apartmentType
     */
    public kz.lof.webservices.store.ump.ApartmentType getApartmentType() {
        return apartmentType;
    }


    /**
     * Sets the apartmentType value for this Apartment.
     * 
     * @param apartmentType
     */
    public void setApartmentType(kz.lof.webservices.store.ump.ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Apartment)) return false;
        Apartment other = (Apartment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            this.room == other.getRoom() &&
            ((this.flat==null && other.getFlat()==null) || 
             (this.flat!=null &&
              this.flat.equals(other.getFlat()))) &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            ((this.apartmentType==null && other.getApartmentType()==null) || 
             (this.apartmentType!=null &&
              this.apartmentType.equals(other.getApartmentType())));
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
        _hashCode += getRoom();
        if (getFlat() != null) {
            _hashCode += getFlat().hashCode();
        }
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        if (getApartmentType() != null) {
            _hashCode += getApartmentType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Apartment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Apartment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("room");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "room"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "flat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apartmentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "apartmentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "ApartmentType"));
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
