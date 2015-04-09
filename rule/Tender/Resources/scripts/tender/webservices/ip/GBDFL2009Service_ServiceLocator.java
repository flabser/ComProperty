/**
 * GBDFL2009Service_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ip;

public class GBDFL2009Service_ServiceLocator extends org.apache.axis.client.Service implements GBDFL2009Service_Service {

    public GBDFL2009Service_ServiceLocator() {
    }


    public GBDFL2009Service_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GBDFL2009Service_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GBDFL2009ServiceBinding
    private java.lang.String GBDFL2009ServiceBinding_address = "http://10.245.12.33/GBDFL.SearchPersonWeb/sca/GBDFL2009Service";

    public java.lang.String getGBDFL2009ServiceBindingAddress() {
        return GBDFL2009ServiceBinding_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GBDFL2009ServiceBindingWSDDServiceName = "GBDFL2009ServiceBinding";

    public java.lang.String getGBDFL2009ServiceBindingWSDDServiceName() {
        return GBDFL2009ServiceBindingWSDDServiceName;
    }

    public void setGBDFL2009ServiceBindingWSDDServiceName(java.lang.String name) {
        GBDFL2009ServiceBindingWSDDServiceName = name;
    }

    public GBDFL2009Service_PortType getGBDFL2009ServiceBinding() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GBDFL2009ServiceBinding_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGBDFL2009ServiceBinding(endpoint);
    }

    public GBDFL2009Service_PortType getGBDFL2009ServiceBinding(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            GBDFL2009ServiceHttpBindingStub _stub = new GBDFL2009ServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getGBDFL2009ServiceBindingWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGBDFL2009ServiceBindingEndpointAddress(java.lang.String address) {
        GBDFL2009ServiceBinding_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (GBDFL2009Service_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                GBDFL2009ServiceHttpBindingStub _stub = new GBDFL2009ServiceHttpBindingStub(new java.net.URL(GBDFL2009ServiceBinding_address), this);
                _stub.setPortName(getGBDFL2009ServiceBindingWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("GBDFL2009ServiceBinding".equals(inputPortName)) {
            return getGBDFL2009ServiceBinding();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

//    http://10.245.12.33/GBDFL.SearchPersonWeb/sca/GBDFL2009Service

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://10.245.12.33/Binding", "GBDFL2009Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://10.245.12.33/Binding", "GBDFL2009ServiceBinding"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

if ("GBDFL2009ServiceBinding".equals(portName)) {
            setGBDFL2009ServiceBindingEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
