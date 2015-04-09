/**
 * HumansSearchServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.clients.ump;

import kz.pchelka.env.Environment;

import org.apache.axis.client.Stub;

public class HumansSearchServiceServiceLocator extends org.apache.axis.client.Service implements kz.lof.webservices.clients.ump.HumansSearchServiceService {

    public HumansSearchServiceServiceLocator() {
    }


    public HumansSearchServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public HumansSearchServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HumansSearchService
    private java.lang.String HumansSearchService_address = "http://" + Environment.getExtHost("ump") + "/WS/services/HumansSearchService";

    public java.lang.String getHumansSearchServiceAddress() {
        return HumansSearchService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HumansSearchServiceWSDDServiceName = "HumansSearchService";

    public java.lang.String getHumansSearchServiceWSDDServiceName() {
        return HumansSearchServiceWSDDServiceName;
    }

    public void setHumansSearchServiceWSDDServiceName(java.lang.String name) {
        HumansSearchServiceWSDDServiceName = name;
    }

    public kz.lof.webservices.clients.ump.HumansSearchService getHumansSearchService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HumansSearchService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        kz.lof.webservices.clients.ump.HumansSearchService hSSServiceService = getHumansSearchService(endpoint);
        ((Stub)hSSServiceService).setUsername("temp_user");
        ((Stub)hSSServiceService).setPassword("temp_password");
        return hSSServiceService;
    }

    public kz.lof.webservices.clients.ump.HumansSearchService getHumansSearchService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            kz.lof.webservices.clients.ump.HumansSearchServiceSoapBindingStub _stub = new kz.lof.webservices.clients.ump.HumansSearchServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getHumansSearchServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHumansSearchServiceEndpointAddress(java.lang.String address) {
        HumansSearchService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (kz.lof.webservices.clients.ump.HumansSearchService.class.isAssignableFrom(serviceEndpointInterface)) {
                kz.lof.webservices.clients.ump.HumansSearchServiceSoapBindingStub _stub = new kz.lof.webservices.clients.ump.HumansSearchServiceSoapBindingStub(new java.net.URL(HumansSearchService_address), this);
                _stub.setPortName(getHumansSearchServiceWSDDServiceName());
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
        if ("HumansSearchService".equals(inputPortName)) {
            return getHumansSearchService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "HumansSearchServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "HumansSearchService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HumansSearchService".equals(portName)) {
            setHumansSearchServiceEndpointAddress(address);
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
