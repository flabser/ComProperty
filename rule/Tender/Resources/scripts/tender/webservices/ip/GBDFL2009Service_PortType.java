/**
 * GBDFL2009Service_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ip;

public interface GBDFL2009Service_PortType extends java.rmi.Remote {
    public FullResponse_ getPersonByDocument(DocumentRequest_ request) throws java.rmi.RemoteException;
    public FullResponse_[] getPersonByFIO(FIORequest_ request) throws java.rmi.RemoteException;
    public FullResponse_ getPersonByIIN(IINRequest_ request) throws java.rmi.RemoteException;
}
