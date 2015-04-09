/**
 * MigrationLiveData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class MigrationLiveData  implements java.io.Serializable {
    private kz.lof.webservices.store.ump.Address address;

    private kz.lof.webservices.store.ump.CountMigByApartment[] countMigByApartment;

    public MigrationLiveData() {
    }

    public MigrationLiveData(
           kz.lof.webservices.store.ump.Address address,
           kz.lof.webservices.store.ump.CountMigByApartment[] countMigByApartment) {
           this.address = address;
           this.countMigByApartment = countMigByApartment;
    }


    /**
     * Gets the address value for this MigrationLiveData.
     * 
     * @return address
     */
    public kz.lof.webservices.store.ump.Address getAddress() {
        return address;
    }


    /**
     * Sets the address value for this MigrationLiveData.
     * 
     * @param address
     */
    public void setAddress(kz.lof.webservices.store.ump.Address address) {
        this.address = address;
    }


    /**
     * Gets the countMigByApartment value for this MigrationLiveData.
     * 
     * @return countMigByApartment
     */
    public kz.lof.webservices.store.ump.CountMigByApartment[] getCountMigByApartment() {
        return countMigByApartment;
    }


    /**
     * Sets the countMigByApartment value for this MigrationLiveData.
     * 
     * @param countMigByApartment
     */
    public void setCountMigByApartment(kz.lof.webservices.store.ump.CountMigByApartment[] countMigByApartment) {
        this.countMigByApartment = countMigByApartment;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MigrationLiveData)) return false;
        MigrationLiveData other = (MigrationLiveData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.countMigByApartment==null && other.getCountMigByApartment()==null) || 
             (this.countMigByApartment!=null &&
              java.util.Arrays.equals(this.countMigByApartment, other.getCountMigByApartment())));
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
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getCountMigByApartment() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCountMigByApartment());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCountMigByApartment(), i);
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
        new org.apache.axis.description.TypeDesc(MigrationLiveData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationLiveData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countMigByApartment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "countMigByApartment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountMigByApartment"));
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
