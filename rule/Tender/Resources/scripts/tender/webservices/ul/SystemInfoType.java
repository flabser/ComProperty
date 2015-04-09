/**
 * SystemInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;


import java.util.Calendar;

/**
 * Системная информация в сообщении
 */
public class SystemInfoType  implements java.io.Serializable {
    /* ID сообщения */
    private String messageId = "";

    /* ID цепочки сообщений */
    private String chainId = "";

    /* Дата сообщения */
    private Calendar messageDate = Calendar.getInstance();

    /* Системный тип сообщения */
    private tender.webservices.ul.SystemInfoTypeMessageType messageType = new SystemInfoTypeMessageType("");

    /* Вид сообщения */
    private String type = "";

    /* Код ответа */
    private String response = "";

    /* Доп. информация */
    private String info = "";

    /* Оператор */
    private String operator = "";

    /* Код ЦОН */
    private String conId = "";

    /* ЭЦП */
    private String digiSign = "";

    /* Дополнительная информация для ЭЦП */
    private String digiSignOpt = "";

    public SystemInfoType() {
    }

    public SystemInfoType(
           String messageId,
           String chainId,
           Calendar messageDate,
           tender.webservices.ul.SystemInfoTypeMessageType messageType,
           String type,
           String response,
           String info,
           String operator,
           String conId,
           String digiSign,
           String digiSignOpt) {
           this.messageId = messageId;
           this.chainId = chainId;
           this.messageDate = messageDate;
           this.messageType = messageType;
           this.type = type;
           this.response = response;
           this.info = info;
           this.operator = operator;
           this.conId = conId;
           this.digiSign = digiSign;
           this.digiSignOpt = digiSignOpt;
    }


    /**
     * Gets the messageId value for this SystemInfoType.
     * 
     * @return messageId   * ID сообщения
     */
    public String getMessageId() {
        return messageId;
    }


    /**
     * Sets the messageId value for this SystemInfoType.
     * 
     * @param messageId   * ID сообщения
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }


    /**
     * Gets the chainId value for this SystemInfoType.
     * 
     * @return chainId   * ID цепочки сообщений
     */
    public String getChainId() {
        return chainId;
    }


    /**
     * Sets the chainId value for this SystemInfoType.
     * 
     * @param chainId   * ID цепочки сообщений
     */
    public void setChainId(String chainId) {
        this.chainId = chainId;
    }


    /**
     * Gets the messageDate value for this SystemInfoType.
     * 
     * @return messageDate   * Дата сообщения
     */
    public Calendar getMessageDate() {
        return messageDate;
    }


    /**
     * Sets the messageDate value for this SystemInfoType.
     * 
     * @param messageDate   * Дата сообщения
     */
    public void setMessageDate(Calendar messageDate) {
        this.messageDate = messageDate;
    }


    /**
     * Gets the messageType value for this SystemInfoType.
     * 
     * @return messageType   * Системный тип сообщения
     */
    public tender.webservices.ul.SystemInfoTypeMessageType getMessageType() {
        return messageType;
    }


    /**
     * Sets the messageType value for this SystemInfoType.
     * 
     * @param messageType   * Системный тип сообщения
     */
    public void setMessageType(tender.webservices.ul.SystemInfoTypeMessageType messageType) {
        this.messageType = messageType;
    }


    /**
     * Gets the type value for this SystemInfoType.
     * 
     * @return type   * Вид сообщения
     */
    public String getType() {
        return type;
    }


    /**
     * Sets the type value for this SystemInfoType.
     * 
     * @param type   * Вид сообщения
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Gets the response value for this SystemInfoType.
     * 
     * @return response   * Код ответа
     */
    public String getResponse() {
        return response;
    }


    /**
     * Sets the response value for this SystemInfoType.
     * 
     * @param response   * Код ответа
     */
    public void setResponse(String response) {
        this.response = response;
    }


    /**
     * Gets the info value for this SystemInfoType.
     * 
     * @return info   * Доп. информация
     */
    public String getInfo() {
        return info;
    }


    /**
     * Sets the info value for this SystemInfoType.
     * 
     * @param info   * Доп. информация
     */
    public void setInfo(String info) {
        this.info = info;
    }


    /**
     * Gets the operator value for this SystemInfoType.
     * 
     * @return operator   * Оператор
     */
    public String getOperator() {
        return operator;
    }


    /**
     * Sets the operator value for this SystemInfoType.
     * 
     * @param operator   * Оператор
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }


    /**
     * Gets the conId value for this SystemInfoType.
     * 
     * @return conId   * Код ЦОН
     */
    public String getConId() {
        return conId;
    }


    /**
     * Sets the conId value for this SystemInfoType.
     * 
     * @param conId   * Код ЦОН
     */
    public void setConId(String conId) {
        this.conId = conId;
    }


    /**
     * Gets the digiSign value for this SystemInfoType.
     * 
     * @return digiSign   * ЭЦП
     */
    public String getDigiSign() {
        return digiSign;
    }


    /**
     * Sets the digiSign value for this SystemInfoType.
     * 
     * @param digiSign   * ЭЦП
     */
    public void setDigiSign(String digiSign) {
        this.digiSign = digiSign;
    }


    /**
     * Gets the digiSignOpt value for this SystemInfoType.
     * 
     * @return digiSignOpt   * Дополнительная информация для ЭЦП
     */
    public String getDigiSignOpt() {
        return digiSignOpt;
    }


    /**
     * Sets the digiSignOpt value for this SystemInfoType.
     * 
     * @param digiSignOpt   * Дополнительная информация для ЭЦП
     */
    public void setDigiSignOpt(String digiSignOpt) {
        this.digiSignOpt = digiSignOpt;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SystemInfoType)) return false;
        SystemInfoType other = (SystemInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageId==null && other.getMessageId()==null) || 
             (this.messageId!=null &&
              this.messageId.equals(other.getMessageId()))) &&
            ((this.chainId==null && other.getChainId()==null) || 
             (this.chainId!=null &&
              this.chainId.equals(other.getChainId()))) &&
            ((this.messageDate==null && other.getMessageDate()==null) || 
             (this.messageDate!=null &&
              this.messageDate.equals(other.getMessageDate()))) &&
            ((this.messageType==null && other.getMessageType()==null) || 
             (this.messageType!=null &&
              this.messageType.equals(other.getMessageType()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.response==null && other.getResponse()==null) || 
             (this.response!=null &&
              this.response.equals(other.getResponse()))) &&
            ((this.info==null && other.getInfo()==null) || 
             (this.info!=null &&
              this.info.equals(other.getInfo()))) &&
            ((this.operator==null && other.getOperator()==null) || 
             (this.operator!=null &&
              this.operator.equals(other.getOperator()))) &&
            ((this.conId==null && other.getConId()==null) || 
             (this.conId!=null &&
              this.conId.equals(other.getConId()))) &&
            ((this.digiSign==null && other.getDigiSign()==null) || 
             (this.digiSign!=null &&
              this.digiSign.equals(other.getDigiSign()))) &&
            ((this.digiSignOpt==null && other.getDigiSignOpt()==null) || 
             (this.digiSignOpt!=null &&
              this.digiSignOpt.equals(other.getDigiSignOpt())));
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
        if (getMessageId() != null) {
            _hashCode += getMessageId().hashCode();
        }
        if (getChainId() != null) {
            _hashCode += getChainId().hashCode();
        }
        if (getMessageDate() != null) {
            _hashCode += getMessageDate().hashCode();
        }
        if (getMessageType() != null) {
            _hashCode += getMessageType().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getResponse() != null) {
            _hashCode += getResponse().hashCode();
        }
        if (getInfo() != null) {
            _hashCode += getInfo().hashCode();
        }
        if (getOperator() != null) {
            _hashCode += getOperator().hashCode();
        }
        if (getConId() != null) {
            _hashCode += getConId().hashCode();
        }
        if (getDigiSign() != null) {
            _hashCode += getDigiSign().hashCode();
        }
        if (getDigiSignOpt() != null) {
            _hashCode += getDigiSignOpt().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SystemInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", "SystemInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chainId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ChainId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", ">SystemInfoType>MessageType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("response");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Response"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("info");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Info"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Operator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ConId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digiSign");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DigiSign"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digiSignOpt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DigiSignOpt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
