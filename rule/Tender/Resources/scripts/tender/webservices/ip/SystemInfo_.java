/**
 * SystemInfo_.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ip;

public class SystemInfo_  implements java.io.Serializable {
    private java.lang.String chainID;

    private java.lang.String digiSign;

    private java.util.Calendar messageDate;

    private java.lang.String messageID;

    private java.lang.String conId;

    private java.lang.String operatorId;

    private java.lang.String response;

    public SystemInfo_() {
    }

    public SystemInfo_(
           java.lang.String chainID,
           java.lang.String digiSign,
           java.util.Calendar messageDate,
           java.lang.String messageID,
           java.lang.String conId,
           java.lang.String operatorId,
           java.lang.String response) {
           this.chainID = chainID;
           this.digiSign = digiSign;
           this.messageDate = messageDate;
           this.messageID = messageID;
           this.conId = conId;
           this.operatorId = operatorId;
           this.response = response;
    }


    /**
     * Gets the chainID value for this SystemInfo_.
     *
     * @return chainID
     */
    public java.lang.String getChainID() {
        return chainID;
    }


    /**
     * Sets the chainID value for this SystemInfo_.
     *
     * @param chainID
     */
    public void setChainID(java.lang.String chainID) {
        this.chainID = chainID;
    }


    /**
     * Gets the digiSign value for this SystemInfo_.
     *
     * @return digiSign
     */
    public java.lang.String getDigiSign() {
        return digiSign;
    }


    /**
     * Sets the digiSign value for this SystemInfo_.
     *
     * @param digiSign
     */
    public void setDigiSign(java.lang.String digiSign) {
        this.digiSign = digiSign;
    }


    /**
     * Gets the messageDate value for this SystemInfo_.
     *
     * @return messageDate
     */
    public java.util.Calendar getMessageDate() {
        return messageDate;
    }


    /**
     * Sets the messageDate value for this SystemInfo_.
     *
     * @param messageDate
     */
    public void setMessageDate(java.util.Calendar messageDate) {
        this.messageDate = messageDate;
    }


    /**
     * Gets the messageID value for this SystemInfo_.
     *
     * @return messageID
     */
    public java.lang.String getMessageID() {
        return messageID;
    }


    /**
     * Sets the messageID value for this SystemInfo_.
     *
     * @param messageID
     */
    public void setMessageID(java.lang.String messageID) {
        this.messageID = messageID;
    }


    /**
     * Gets the conId value for this SystemInfo_.
     *
     * @return conId
     */
    public java.lang.String getConId() {
        return conId;
    }


    /**
     * Sets the conId value for this SystemInfo_.
     *
     * @param conId
     */
    public void setConId(java.lang.String conId) {
        this.conId = conId;
    }


    /**
     * Gets the operatorId value for this SystemInfo_.
     *
     * @return operatorId
     */
    public java.lang.String getOperatorId() {
        return operatorId;
    }


    /**
     * Sets the operatorId value for this SystemInfo_.
     *
     * @param operatorId
     */
    public void setOperatorId(java.lang.String operatorId) {
        this.operatorId = operatorId;
    }


    /**
     * Gets the response value for this SystemInfo_.
     *
     * @return response
     */
    public java.lang.String getResponse() {
        return response;
    }


    /**
     * Sets the response value for this SystemInfo_.
     *
     * @param response
     */
    public void setResponse(java.lang.String response) {
        this.response = response;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SystemInfo_)) return false;
        SystemInfo_ other = (SystemInfo_) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.chainID==null && other.getChainID()==null) ||
             (this.chainID!=null &&
              this.chainID.equals(other.getChainID()))) &&
            ((this.digiSign==null && other.getDigiSign()==null) ||
             (this.digiSign!=null &&
              this.digiSign.equals(other.getDigiSign()))) &&
            ((this.messageDate==null && other.getMessageDate()==null) ||
             (this.messageDate!=null &&
              this.messageDate.equals(other.getMessageDate()))) &&
            ((this.messageID==null && other.getMessageID()==null) ||
             (this.messageID!=null &&
              this.messageID.equals(other.getMessageID()))) &&
            ((this.conId==null && other.getConId()==null) ||
             (this.conId!=null &&
              this.conId.equals(other.getConId()))) &&
            ((this.operatorId==null && other.getOperatorId()==null) ||
             (this.operatorId!=null &&
              this.operatorId.equals(other.getOperatorId()))) &&
            ((this.response==null && other.getResponse()==null) ||
             (this.response!=null &&
              this.response.equals(other.getResponse())));
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
        if (getChainID() != null) {
            _hashCode += getChainID().hashCode();
        }
        if (getDigiSign() != null) {
            _hashCode += getDigiSign().hashCode();
        }
        if (getMessageDate() != null) {
            _hashCode += getMessageDate().hashCode();
        }
        if (getMessageID() != null) {
            _hashCode += getMessageID().hashCode();
        }
        if (getConId() != null) {
            _hashCode += getConId().hashCode();
        }
        if (getOperatorId() != null) {
            _hashCode += getOperatorId().hashCode();
        }
        if (getResponse() != null) {
            _hashCode += getResponse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SystemInfo_.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://types.service.gbdfl.shep.nit", "SystemInfo_"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chainID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chainID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digiSign");
        elemField.setXmlName(new javax.xml.namespace.QName("", "digiSign"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operatorId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "operatorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("response");
        elemField.setXmlName(new javax.xml.namespace.QName("", "response"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
