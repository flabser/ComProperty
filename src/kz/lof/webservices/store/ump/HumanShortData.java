/**
 * HumanShortData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class HumanShortData  implements java.io.Serializable {
    private kz.lof.webservices.store.ump.Document[] idDocument;

    private long id;

    private java.lang.String firstName;

    private java.lang.String lastName;

    private java.lang.String middleName;

    private java.util.Calendar birthDate;

    private int gender;

    private int count_nac;

    private java.lang.String iin;

    private kz.lof.webservices.store.ump.Nationality nationality;

    private kz.lof.webservices.store.ump.Country citizenship;

    private kz.lof.webservices.store.ump.Apartment apartment;

    private kz.lof.webservices.store.ump.ApartmentType type;

    private kz.lof.webservices.store.ump.Address address;

    private boolean isCitizen;

    public HumanShortData() {
    }

    public HumanShortData(
           kz.lof.webservices.store.ump.Document[] idDocument,
           long id,
           java.lang.String firstName,
           java.lang.String lastName,
           java.lang.String middleName,
           java.util.Calendar birthDate,
           int gender,
           int count_nac,
           java.lang.String iin,
           kz.lof.webservices.store.ump.Nationality nationality,
           kz.lof.webservices.store.ump.Country citizenship,
           kz.lof.webservices.store.ump.Apartment apartment,
           kz.lof.webservices.store.ump.ApartmentType type,
           kz.lof.webservices.store.ump.Address address,
           boolean isCitizen) {
           this.idDocument = idDocument;
           this.id = id;
           this.firstName = firstName;
           this.lastName = lastName;
           this.middleName = middleName;
           this.birthDate = birthDate;
           this.gender = gender;
           this.count_nac = count_nac;
           this.iin = iin;
           this.nationality = nationality;
           this.citizenship = citizenship;
           this.apartment = apartment;
           this.type = type;
           this.address = address;
           this.isCitizen = isCitizen;
    }


    /**
     * Gets the idDocument value for this HumanShortData.
     * 
     * @return idDocument
     */
    public kz.lof.webservices.store.ump.Document[] getIdDocument() {
        return idDocument;
    }


    /**
     * Sets the idDocument value for this HumanShortData.
     * 
     * @param idDocument
     */
    public void setIdDocument(kz.lof.webservices.store.ump.Document[] idDocument) {
        this.idDocument = idDocument;
    }


    /**
     * Gets the id value for this HumanShortData.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this HumanShortData.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the firstName value for this HumanShortData.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this HumanShortData.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the lastName value for this HumanShortData.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this HumanShortData.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the middleName value for this HumanShortData.
     * 
     * @return middleName
     */
    public java.lang.String getMiddleName() {
        return middleName;
    }


    /**
     * Sets the middleName value for this HumanShortData.
     * 
     * @param middleName
     */
    public void setMiddleName(java.lang.String middleName) {
        this.middleName = middleName;
    }


    /**
     * Gets the birthDate value for this HumanShortData.
     * 
     * @return birthDate
     */
    public java.util.Calendar getBirthDate() {
        return birthDate;
    }


    /**
     * Sets the birthDate value for this HumanShortData.
     * 
     * @param birthDate
     */
    public void setBirthDate(java.util.Calendar birthDate) {
        this.birthDate = birthDate;
    }


    /**
     * Gets the gender value for this HumanShortData.
     * 
     * @return gender
     */
    public int getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this HumanShortData.
     * 
     * @param gender
     */
    public void setGender(int gender) {
        this.gender = gender;
    }


    /**
     * Gets the count_nac value for this HumanShortData.
     * 
     * @return count_nac
     */
    public int getCount_nac() {
        return count_nac;
    }


    /**
     * Sets the count_nac value for this HumanShortData.
     * 
     * @param count_nac
     */
    public void setCount_nac(int count_nac) {
        this.count_nac = count_nac;
    }


    /**
     * Gets the iin value for this HumanShortData.
     * 
     * @return iin
     */
    public java.lang.String getIin() {
        return iin;
    }


    /**
     * Sets the iin value for this HumanShortData.
     * 
     * @param iin
     */
    public void setIin(java.lang.String iin) {
        this.iin = iin;
    }


    /**
     * Gets the nationality value for this HumanShortData.
     * 
     * @return nationality
     */
    public kz.lof.webservices.store.ump.Nationality getNationality() {
        return nationality;
    }


    /**
     * Sets the nationality value for this HumanShortData.
     * 
     * @param nationality
     */
    public void setNationality(kz.lof.webservices.store.ump.Nationality nationality) {
        this.nationality = nationality;
    }


    /**
     * Gets the citizenship value for this HumanShortData.
     * 
     * @return citizenship
     */
    public kz.lof.webservices.store.ump.Country getCitizenship() {
        return citizenship;
    }


    /**
     * Sets the citizenship value for this HumanShortData.
     * 
     * @param citizenship
     */
    public void setCitizenship(kz.lof.webservices.store.ump.Country citizenship) {
        this.citizenship = citizenship;
    }


    /**
     * Gets the apartment value for this HumanShortData.
     * 
     * @return apartment
     */
    public kz.lof.webservices.store.ump.Apartment getApartment() {
        return apartment;
    }


    /**
     * Sets the apartment value for this HumanShortData.
     * 
     * @param apartment
     */
    public void setApartment(kz.lof.webservices.store.ump.Apartment apartment) {
        this.apartment = apartment;
    }


    /**
     * Gets the type value for this HumanShortData.
     * 
     * @return type
     */
    public kz.lof.webservices.store.ump.ApartmentType getType() {
        return type;
    }


    /**
     * Sets the type value for this HumanShortData.
     * 
     * @param type
     */
    public void setType(kz.lof.webservices.store.ump.ApartmentType type) {
        this.type = type;
    }


    /**
     * Gets the address value for this HumanShortData.
     * 
     * @return address
     */
    public kz.lof.webservices.store.ump.Address getAddress() {
        return address;
    }


    /**
     * Sets the address value for this HumanShortData.
     * 
     * @param address
     */
    public void setAddress(kz.lof.webservices.store.ump.Address address) {
        this.address = address;
    }


    /**
     * Gets the isCitizen value for this HumanShortData.
     * 
     * @return isCitizen
     */
    public boolean isIsCitizen() {
        return isCitizen;
    }


    /**
     * Sets the isCitizen value for this HumanShortData.
     * 
     * @param isCitizen
     */
    public void setIsCitizen(boolean isCitizen) {
        this.isCitizen = isCitizen;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HumanShortData)) return false;
        HumanShortData other = (HumanShortData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idDocument==null && other.getIdDocument()==null) || 
             (this.idDocument!=null &&
              java.util.Arrays.equals(this.idDocument, other.getIdDocument()))) &&
            this.id == other.getId() &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.middleName==null && other.getMiddleName()==null) || 
             (this.middleName!=null &&
              this.middleName.equals(other.getMiddleName()))) &&
            ((this.birthDate==null && other.getBirthDate()==null) || 
             (this.birthDate!=null &&
              this.birthDate.equals(other.getBirthDate()))) &&
            this.gender == other.getGender() &&
            this.count_nac == other.getCount_nac() &&
            ((this.iin==null && other.getIin()==null) || 
             (this.iin!=null &&
              this.iin.equals(other.getIin()))) &&
            ((this.nationality==null && other.getNationality()==null) || 
             (this.nationality!=null &&
              this.nationality.equals(other.getNationality()))) &&
            ((this.citizenship==null && other.getCitizenship()==null) || 
             (this.citizenship!=null &&
              this.citizenship.equals(other.getCitizenship()))) &&
            ((this.apartment==null && other.getApartment()==null) || 
             (this.apartment!=null &&
              this.apartment.equals(other.getApartment()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            this.isCitizen == other.isIsCitizen();
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
        if (getIdDocument() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdDocument());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdDocument(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getId()).hashCode();
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getMiddleName() != null) {
            _hashCode += getMiddleName().hashCode();
        }
        if (getBirthDate() != null) {
            _hashCode += getBirthDate().hashCode();
        }
        _hashCode += getGender();
        _hashCode += getCount_nac();
        if (getIin() != null) {
            _hashCode += getIin().hashCode();
        }
        if (getNationality() != null) {
            _hashCode += getNationality().hashCode();
        }
        if (getCitizenship() != null) {
            _hashCode += getCitizenship().hashCode();
        }
        if (getApartment() != null) {
            _hashCode += getApartment().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        _hashCode += (isIsCitizen() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HumanShortData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumanShortData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idDocument");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "idDocument"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Document"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "firstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "lastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("middleName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "middleName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "birthDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("count_nac");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "count_nac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "iin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nationality");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "nationality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Nationality"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("citizenship");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "citizenship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Country"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apartment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "apartment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Apartment"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "ApartmentType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isCitizen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "isCitizen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
