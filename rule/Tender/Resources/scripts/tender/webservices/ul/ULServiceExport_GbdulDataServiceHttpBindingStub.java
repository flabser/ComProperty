/**
 * ULServiceExport_GbdulDataServiceHttpBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;

public class ULServiceExport_GbdulDataServiceHttpBindingStub extends org.apache.axis.client.Stub implements GbdulDataService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getJurInfoByBin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurInfoByBinRequest"), GBDULJurInfoByBinRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurInfoResponse"));
        oper.setReturnClass(GBDULJurInfoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "response"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("checkJurNameExists");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULCheckNameExistRequest"), GBDULCheckNameExistRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULCheckNameExistResponse"));
        oper.setReturnClass(GBDULCheckNameExistResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "response"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("checkStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epam.com/kaz-egov/gbdul", "GBDULRequest"), GBDULRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "date"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"), java.util.Date.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "code"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/api/GbdulDataService", "checkStatus_systemFailure"),
                      "tender.webservices.ul.SystemFailureFault",
                      new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", "SystemFailureFault"),
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/api/GbdulDataService", "checkStatus_userFailure"),
                      "tender.webservices.ul.UserFailureFault",
                      new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", "UserFailureFault"),
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("checkAffiliatedPerson");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.epam.com/kaz-egov/gbdul", "GBDULRequest"), tender.webservices.ul.GBDULRequest.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "date"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"), java.util.Date.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "code"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/api/GbdulDataService", "checkAffiliatedPerson_systemFailure"),
                      "tender.webservices.ul.SystemFailureFault",
                      new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", "SystemFailureFault"),
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/api/GbdulDataService", "checkAffiliatedPerson_userFailure"),
                      "tender.webservices.ul.UserFailureFault",
                      new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", "UserFailureFault"),
                      true
                     ));
        _operations[3] = oper;

    }

    public ULServiceExport_GbdulDataServiceHttpBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ULServiceExport_GbdulDataServiceHttpBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ULServiceExport_GbdulDataServiceHttpBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.1");
            Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-egov/gbdul", "GBDULRequest");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", ">SystemInfoType>MessageType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.SystemInfoTypeMessageType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", "SystemFailureFault");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.SystemFailureFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", "SystemInfoType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.SystemInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-egov", "UserFailureFault");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.UserFailureFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULCheckNameExistRequest");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULCheckNameExistRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULCheckNameExistResponse");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULCheckNameExistResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULFizItemListType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULFizItemType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULFizItemType");
            qName2 = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULFizItemType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULFizItemType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULIrrelevantNameListType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULIrrelevantNameType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULIrrelevantNameType");
            qName2 = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULIrrelevantNameType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULIrrelevantNameType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurAddressListType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULJurAddressType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurAddressType");
            qName2 = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurAddressType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULJurAddressType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurInfoByBinRequest");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULJurInfoByBinRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurInfoResponse");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULJurInfoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurInfoType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULJurInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurItemListType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULJurItemType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurItemType");
            qName2 = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurItemType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULJurItemType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULNameType");
            cachedSerQNames.add(qName);
            cls = tender.webservices.ul.GBDULNameType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        Class cls = (Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            Class sf = (Class)
                                 cachedSerFactories.get(i);
                            Class df = (Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public tender.webservices.ul.GBDULJurInfoResponse getJurInfoByBin(tender.webservices.ul.GBDULJurInfoByBinRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/api/GbdulDataService", "getJurInfoByBin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (tender.webservices.ul.GBDULJurInfoResponse) _resp;
            } catch (Exception _exception) {
                return (tender.webservices.ul.GBDULJurInfoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, tender.webservices.ul.GBDULJurInfoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public tender.webservices.ul.GBDULCheckNameExistResponse checkJurNameExists(tender.webservices.ul.GBDULCheckNameExistRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/api/GbdulDataService", "checkJurNameExists"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (tender.webservices.ul.GBDULCheckNameExistResponse) _resp;
            } catch (Exception _exception) {
                return (tender.webservices.ul.GBDULCheckNameExistResponse) org.apache.axis.utils.JavaUtils.convert(_resp, tender.webservices.ul.GBDULCheckNameExistResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void checkStatus(tender.webservices.ul.GBDULRequest request, org.apache.axis.holders.DateHolder date, javax.xml.rpc.holders.StringHolder code) throws java.rmi.RemoteException, tender.webservices.ul.SystemFailureFault, tender.webservices.ul.UserFailureFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/api/GbdulDataService", "checkStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                date.value = (java.util.Date) _output.get(new javax.xml.namespace.QName("", "date"));
            } catch (Exception _exception) {
                date.value = (java.util.Date) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "date")), java.util.Date.class);
            }
            try {
                code.value = (String) _output.get(new javax.xml.namespace.QName("", "code"));
            } catch (Exception _exception) {
                code.value = (String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "code")), String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof tender.webservices.ul.SystemFailureFault) {
              throw (tender.webservices.ul.SystemFailureFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof tender.webservices.ul.UserFailureFault) {
              throw (tender.webservices.ul.UserFailureFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void checkAffiliatedPerson(tender.webservices.ul.GBDULRequest request, org.apache.axis.holders.DateHolder date, javax.xml.rpc.holders.StringHolder code) throws java.rmi.RemoteException, tender.webservices.ul.SystemFailureFault, tender.webservices.ul.UserFailureFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/api/GbdulDataService", "checkAffiliatedPerson"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                date.value = (java.util.Date) _output.get(new javax.xml.namespace.QName("", "date"));
            } catch (Exception _exception) {
                date.value = (java.util.Date) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "date")), java.util.Date.class);
            }
            try {
                code.value = (String) _output.get(new javax.xml.namespace.QName("", "code"));
            } catch (Exception _exception) {
                code.value = (String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "code")), String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof tender.webservices.ul.SystemFailureFault) {
              throw (tender.webservices.ul.SystemFailureFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof tender.webservices.ul.UserFailureFault) {
              throw (tender.webservices.ul.UserFailureFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
