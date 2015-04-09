/**
 * ULServiceExport_GbdulDataServiceHttpServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;

public class ULServiceExport_GbdulDataServiceHttpServiceLocator extends org.apache.axis.client.Service implements ULServiceExport_GbdulDataServiceHttpService {

    public ULServiceExport_GbdulDataServiceHttpServiceLocator() {
    }


    public ULServiceExport_GbdulDataServiceHttpServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ULServiceExport_GbdulDataServiceHttpServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ULServiceExport_GbdulDataServiceHttpPort
    private String ULServiceExport_GbdulDataServiceHttpPort_address = "http://10.245.12.33/SHEP_GBDUL_Service/services/GbdulDataServiceHttpPort";

    public String getULServiceExport_GbdulDataServiceHttpPortAddress() {
        return ULServiceExport_GbdulDataServiceHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String ULServiceExport_GbdulDataServiceHttpPortWSDDServiceName = "UL.Service.Export_GbdulDataServiceHttpPort";

    public String getULServiceExport_GbdulDataServiceHttpPortWSDDServiceName() {
        return ULServiceExport_GbdulDataServiceHttpPortWSDDServiceName;
    }

    public void setULServiceExport_GbdulDataServiceHttpPortWSDDServiceName(String name) {
        ULServiceExport_GbdulDataServiceHttpPortWSDDServiceName = name;
    }

    public GbdulDataService getULServiceExport_GbdulDataServiceHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ULServiceExport_GbdulDataServiceHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getULServiceExport_GbdulDataServiceHttpPort(endpoint);
    }

    public GbdulDataService getULServiceExport_GbdulDataServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ULServiceExport_GbdulDataServiceHttpBindingStub _stub = new ULServiceExport_GbdulDataServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getULServiceExport_GbdulDataServiceHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setULServiceExport_GbdulDataServiceHttpPortEndpointAddress(String address) {
        ULServiceExport_GbdulDataServiceHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (GbdulDataService.class.isAssignableFrom(serviceEndpointInterface)) {
                ULServiceExport_GbdulDataServiceHttpBindingStub _stub = new ULServiceExport_GbdulDataServiceHttpBindingStub(new java.net.URL(ULServiceExport_GbdulDataServiceHttpPort_address), this);
                _stub.setPortName(getULServiceExport_GbdulDataServiceHttpPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
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
        String inputPortName = portName.getLocalPart();
        if ("UL.Service.Export_GbdulDataServiceHttpPort".equals(inputPortName)) {
            return getULServiceExport_GbdulDataServiceHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/api/GbdulDataService/Binding", "UL.Service.Export_GbdulDataServiceHttpService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/api/GbdulDataService/Binding", "UL.Service.Export_GbdulDataServiceHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("ULServiceExport_GbdulDataServiceHttpPort".equals(portName)) {
            setULServiceExport_GbdulDataServiceHttpPortEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
