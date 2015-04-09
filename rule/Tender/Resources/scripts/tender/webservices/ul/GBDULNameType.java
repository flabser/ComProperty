/**
 * GBDULNameType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;


/**
 * Наименование ЮЛ
 */
public class GBDULNameType  implements java.io.Serializable {
    /* Наименование на русском языке */
    private String nameRu;

    /* Наименование на гос. языке */
    private String nameKz;

    /* Наименование на англ. языке */
    private String nameEn;

    public GBDULNameType() {
    }

    public GBDULNameType(
           String nameRu,
           String nameKz,
           String nameEn) {
           this.nameRu = nameRu;
           this.nameKz = nameKz;
           this.nameEn = nameEn;
    }


    /**
     * Gets the nameRu value for this GBDULNameType.
     *
     * @return nameRu   * Наименование на русском языке
     */
    public String getNameRu() {
        return nameRu;
    }


    /**
     * Sets the nameRu value for this GBDULNameType.
     *
     * @param nameRu   * Наименование на русском языке
     */
    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }


    /**
     * Gets the nameKz value for this GBDULNameType.
     *
     * @return nameKz   * Наименование на гос. языке
     */
    public String getNameKz() {
        return nameKz;
    }


    /**
     * Sets the nameKz value for this GBDULNameType.
     *
     * @param nameKz   * Наименование на гос. языке
     */
    public void setNameKz(String nameKz) {
        this.nameKz = nameKz;
    }


    /**
     * Gets the nameEn value for this GBDULNameType.
     *
     * @return nameEn   * Наименование на англ. языке
     */
    public String getNameEn() {
        return nameEn;
    }


    /**
     * Sets the nameEn value for this GBDULNameType.
     *
     * @param nameEn   * Наименование на англ. языке
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GBDULNameType)) return false;
        GBDULNameType other = (GBDULNameType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.nameRu==null && other.getNameRu()==null) ||
             (this.nameRu!=null &&
              this.nameRu.equals(other.getNameRu()))) &&
            ((this.nameKz==null && other.getNameKz()==null) ||
             (this.nameKz!=null &&
              this.nameKz.equals(other.getNameKz()))) &&
            ((this.nameEn==null && other.getNameEn()==null) ||
             (this.nameEn!=null &&
              this.nameEn.equals(other.getNameEn())));
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
        if (getNameRu() != null) {
            _hashCode += getNameRu().hashCode();
        }
        if (getNameKz() != null) {
            _hashCode += getNameKz().hashCode();
        }
        if (getNameEn() != null) {
            _hashCode += getNameEn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GBDULNameType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULNameType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameRu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "NameRu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameKz");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "NameKz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameEn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "NameEn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
