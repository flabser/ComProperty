/**
 * HumansSearchResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class HumansSearchResult  implements java.io.Serializable {
    private int bigAge;

    private int pribyl;

    private kz.lof.webservices.store.ump.HumanShortData[] shortData;

    private int smallAge;

    private int totalFound;

    private int ubyl;

    public HumansSearchResult() {
    }

    public HumansSearchResult(
           int bigAge,
           int pribyl,
           kz.lof.webservices.store.ump.HumanShortData[] shortData,
           int smallAge,
           int totalFound,
           int ubyl) {
           this.bigAge = bigAge;
           this.pribyl = pribyl;
           this.shortData = shortData;
           this.smallAge = smallAge;
           this.totalFound = totalFound;
           this.ubyl = ubyl;
    }


    /**
     * Gets the bigAge value for this HumansSearchResult.
     * 
     * @return bigAge
     */
    public int getBigAge() {
        return bigAge;
    }


    /**
     * Sets the bigAge value for this HumansSearchResult.
     * 
     * @param bigAge
     */
    public void setBigAge(int bigAge) {
        this.bigAge = bigAge;
    }


    /**
     * Gets the pribyl value for this HumansSearchResult.
     * 
     * @return pribyl
     */
    public int getPribyl() {
        return pribyl;
    }


    /**
     * Sets the pribyl value for this HumansSearchResult.
     * 
     * @param pribyl
     */
    public void setPribyl(int pribyl) {
        this.pribyl = pribyl;
    }


    /**
     * Gets the shortData value for this HumansSearchResult.
     * 
     * @return shortData
     */
    public kz.lof.webservices.store.ump.HumanShortData[] getShortData() {
        return shortData;
    }


    /**
     * Sets the shortData value for this HumansSearchResult.
     * 
     * @param shortData
     */
    public void setShortData(kz.lof.webservices.store.ump.HumanShortData[] shortData) {
        this.shortData = shortData;
    }


    /**
     * Gets the smallAge value for this HumansSearchResult.
     * 
     * @return smallAge
     */
    public int getSmallAge() {
        return smallAge;
    }


    /**
     * Sets the smallAge value for this HumansSearchResult.
     * 
     * @param smallAge
     */
    public void setSmallAge(int smallAge) {
        this.smallAge = smallAge;
    }


    /**
     * Gets the totalFound value for this HumansSearchResult.
     * 
     * @return totalFound
     */
    public int getTotalFound() {
        return totalFound;
    }


    /**
     * Sets the totalFound value for this HumansSearchResult.
     * 
     * @param totalFound
     */
    public void setTotalFound(int totalFound) {
        this.totalFound = totalFound;
    }


    /**
     * Gets the ubyl value for this HumansSearchResult.
     * 
     * @return ubyl
     */
    public int getUbyl() {
        return ubyl;
    }


    /**
     * Sets the ubyl value for this HumansSearchResult.
     * 
     * @param ubyl
     */
    public void setUbyl(int ubyl) {
        this.ubyl = ubyl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HumansSearchResult)) return false;
        HumansSearchResult other = (HumansSearchResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.bigAge == other.getBigAge() &&
            this.pribyl == other.getPribyl() &&
            ((this.shortData==null && other.getShortData()==null) || 
             (this.shortData!=null &&
              java.util.Arrays.equals(this.shortData, other.getShortData()))) &&
            this.smallAge == other.getSmallAge() &&
            this.totalFound == other.getTotalFound() &&
            this.ubyl == other.getUbyl();
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
        _hashCode += getBigAge();
        _hashCode += getPribyl();
        if (getShortData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getShortData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getShortData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getSmallAge();
        _hashCode += getTotalFound();
        _hashCode += getUbyl();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HumansSearchResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumansSearchResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bigAge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "bigAge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pribyl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "pribyl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "shortData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumanShortData"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smallAge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "smallAge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalFound");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "totalFound"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ubyl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "ubyl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
