/**
 * CountByReason.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class CountByReason  implements java.io.Serializable {
    private int countGetIn;

    private int countGetOut;

    private kz.lof.webservices.store.ump.VisitReason visitReason;

    public CountByReason() {
    }

    public CountByReason(
           int countGetIn,
           int countGetOut,
           kz.lof.webservices.store.ump.VisitReason visitReason) {
           this.countGetIn = countGetIn;
           this.countGetOut = countGetOut;
           this.visitReason = visitReason;
    }


    /**
     * Gets the countGetIn value for this CountByReason.
     * 
     * @return countGetIn
     */
    public int getCountGetIn() {
        return countGetIn;
    }


    /**
     * Sets the countGetIn value for this CountByReason.
     * 
     * @param countGetIn
     */
    public void setCountGetIn(int countGetIn) {
        this.countGetIn = countGetIn;
    }


    /**
     * Gets the countGetOut value for this CountByReason.
     * 
     * @return countGetOut
     */
    public int getCountGetOut() {
        return countGetOut;
    }


    /**
     * Sets the countGetOut value for this CountByReason.
     * 
     * @param countGetOut
     */
    public void setCountGetOut(int countGetOut) {
        this.countGetOut = countGetOut;
    }


    /**
     * Gets the visitReason value for this CountByReason.
     * 
     * @return visitReason
     */
    public kz.lof.webservices.store.ump.VisitReason getVisitReason() {
        return visitReason;
    }


    /**
     * Sets the visitReason value for this CountByReason.
     * 
     * @param visitReason
     */
    public void setVisitReason(kz.lof.webservices.store.ump.VisitReason visitReason) {
        this.visitReason = visitReason;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CountByReason)) return false;
        CountByReason other = (CountByReason) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.countGetIn == other.getCountGetIn() &&
            this.countGetOut == other.getCountGetOut() &&
            ((this.visitReason==null && other.getVisitReason()==null) || 
             (this.visitReason!=null &&
              this.visitReason.equals(other.getVisitReason())));
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
        _hashCode += getCountGetIn();
        _hashCode += getCountGetOut();
        if (getVisitReason() != null) {
            _hashCode += getVisitReason().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CountByReason.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountByReason"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countGetIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "countGetIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countGetOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "countGetOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visitReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "visitReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "VisitReason"));
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
