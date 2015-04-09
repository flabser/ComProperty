/**
 * FullResponse_.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ip;

public class FullResponse_  implements java.io.Serializable {
    private Address_[] addressIrrelevantList;

    private Address_ birthAddress;

    private CommonInfo_ commonInfo;

    private Address_ currentAddress;

    private FIO_ currentFIO;

    private Document_[] documentIrrelevantList;

    private Document_[] documentList;

    private SystemInfo_ info;

    public FullResponse_() {
    }

    public FullResponse_(
           Address_[] addressIrrelevantList,
           Address_ birthAddress,
           CommonInfo_ commonInfo,
           Address_ currentAddress,
           FIO_ currentFIO,
           Document_[] documentIrrelevantList,
           Document_[] documentList,
           SystemInfo_ info) {
           this.addressIrrelevantList = addressIrrelevantList;
           this.birthAddress = birthAddress;
           this.commonInfo = commonInfo;
           this.currentAddress = currentAddress;
           this.currentFIO = currentFIO;
           this.documentIrrelevantList = documentIrrelevantList;
           this.documentList = documentList;
           this.info = info;
    }


    /**
     * Gets the addressIrrelevantList value for this FullResponse_.
     * 
     * @return addressIrrelevantList
     */
    public Address_[] getAddressIrrelevantList() {
        return addressIrrelevantList;
    }


    /**
     * Sets the addressIrrelevantList value for this FullResponse_.
     * 
     * @param addressIrrelevantList
     */
    public void setAddressIrrelevantList(Address_[] addressIrrelevantList) {
        this.addressIrrelevantList = addressIrrelevantList;
    }


    /**
     * Gets the birthAddress value for this FullResponse_.
     * 
     * @return birthAddress
     */
    public Address_ getBirthAddress() {
        return birthAddress;
    }


    /**
     * Sets the birthAddress value for this FullResponse_.
     * 
     * @param birthAddress
     */
    public void setBirthAddress(Address_ birthAddress) {
        this.birthAddress = birthAddress;
    }


    /**
     * Gets the commonInfo value for this FullResponse_.
     * 
     * @return commonInfo
     */
    public CommonInfo_ getCommonInfo() {
        return commonInfo;
    }


    /**
     * Sets the commonInfo value for this FullResponse_.
     * 
     * @param commonInfo
     */
    public void setCommonInfo(CommonInfo_ commonInfo) {
        this.commonInfo = commonInfo;
    }


    /**
     * Gets the currentAddress value for this FullResponse_.
     * 
     * @return currentAddress
     */
    public Address_ getCurrentAddress() {
        return currentAddress;
    }


    /**
     * Sets the currentAddress value for this FullResponse_.
     * 
     * @param currentAddress
     */
    public void setCurrentAddress(Address_ currentAddress) {
        this.currentAddress = currentAddress;
    }


    /**
     * Gets the currentFIO value for this FullResponse_.
     * 
     * @return currentFIO
     */
    public FIO_ getCurrentFIO() {
        return currentFIO;
    }


    /**
     * Sets the currentFIO value for this FullResponse_.
     * 
     * @param currentFIO
     */
    public void setCurrentFIO(FIO_ currentFIO) {
        this.currentFIO = currentFIO;
    }


    /**
     * Gets the documentIrrelevantList value for this FullResponse_.
     * 
     * @return documentIrrelevantList
     */
    public Document_[] getDocumentIrrelevantList() {
        return documentIrrelevantList;
    }


    /**
     * Sets the documentIrrelevantList value for this FullResponse_.
     * 
     * @param documentIrrelevantList
     */
    public void setDocumentIrrelevantList(Document_[] documentIrrelevantList) {
        this.documentIrrelevantList = documentIrrelevantList;
    }


    /**
     * Gets the documentList value for this FullResponse_.
     * 
     * @return documentList
     */
    public Document_[] getDocumentList() {
        return documentList;
    }


    /**
     * Sets the documentList value for this FullResponse_.
     * 
     * @param documentList
     */
    public void setDocumentList(Document_[] documentList) {
        this.documentList = documentList;
    }


    /**
     * Gets the info value for this FullResponse_.
     * 
     * @return info
     */
    public SystemInfo_ getInfo() {
        return info;
    }


    /**
     * Sets the info value for this FullResponse_.
     * 
     * @param info
     */
    public void setInfo(SystemInfo_ info) {
        this.info = info;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FullResponse_)) return false;
        FullResponse_ other = (FullResponse_) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.addressIrrelevantList==null && other.getAddressIrrelevantList()==null) ||
             (this.addressIrrelevantList!=null &&
              java.util.Arrays.equals(this.addressIrrelevantList, other.getAddressIrrelevantList()))) &&
            ((this.birthAddress==null && other.getBirthAddress()==null) ||
             (this.birthAddress!=null &&
              this.birthAddress.equals(other.getBirthAddress()))) &&
            ((this.commonInfo==null && other.getCommonInfo()==null) ||
             (this.commonInfo!=null &&
              this.commonInfo.equals(other.getCommonInfo()))) &&
            ((this.currentAddress==null && other.getCurrentAddress()==null) ||
             (this.currentAddress!=null &&
              this.currentAddress.equals(other.getCurrentAddress()))) &&
            ((this.currentFIO==null && other.getCurrentFIO()==null) ||
             (this.currentFIO!=null &&
              this.currentFIO.equals(other.getCurrentFIO()))) &&
            ((this.documentIrrelevantList==null && other.getDocumentIrrelevantList()==null) ||
             (this.documentIrrelevantList!=null &&
              java.util.Arrays.equals(this.documentIrrelevantList, other.getDocumentIrrelevantList()))) &&
            ((this.documentList==null && other.getDocumentList()==null) ||
             (this.documentList!=null &&
              java.util.Arrays.equals(this.documentList, other.getDocumentList()))) &&
            ((this.info==null && other.getInfo()==null) ||
             (this.info!=null &&
              this.info.equals(other.getInfo())));
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
        if (getAddressIrrelevantList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddressIrrelevantList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddressIrrelevantList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBirthAddress() != null) {
            _hashCode += getBirthAddress().hashCode();
        }
        if (getCommonInfo() != null) {
            _hashCode += getCommonInfo().hashCode();
        }
        if (getCurrentAddress() != null) {
            _hashCode += getCurrentAddress().hashCode();
        }
        if (getCurrentFIO() != null) {
            _hashCode += getCurrentFIO().hashCode();
        }
        if (getDocumentIrrelevantList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentIrrelevantList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentIrrelevantList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocumentList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getInfo() != null) {
            _hashCode += getInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FullResponse_.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "FullResponse_"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressIrrelevantList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressIrrelevantList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "Address_"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "Address_"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "Address_"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commonInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "commonInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "CommonInfo_"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currentAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "Address_"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentFIO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currentFIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "FIO_"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentIrrelevantList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentIrrelevantList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "Document_"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "Document_"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "Document_"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "Document_"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("info");
        elemField.setXmlName(new javax.xml.namespace.QName("", "info"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "SystemInfo_"));
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
