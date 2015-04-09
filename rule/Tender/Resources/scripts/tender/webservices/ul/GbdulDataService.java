/**
 * GbdulDataService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;

public interface GbdulDataService extends java.rmi.Remote {
    public GBDULJurInfoResponse getJurInfoByBin(GBDULJurInfoByBinRequest request) throws java.rmi.RemoteException;
    public GBDULCheckNameExistResponse checkJurNameExists(GBDULCheckNameExistRequest request) throws java.rmi.RemoteException;
    public void checkStatus(GBDULRequest request, org.apache.axis.holders.DateHolder date, javax.xml.rpc.holders.StringHolder code) throws java.rmi.RemoteException;
    public void checkAffiliatedPerson(GBDULRequest request, org.apache.axis.holders.DateHolder date, javax.xml.rpc.holders.StringHolder code) throws java.rmi.RemoteException;
}
