package tender.webservices.ul;


import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

public class TestClass {

    public void testMethod() throws ServiceException, RemoteException {
        ULServiceExport_GbdulDataServiceHttpServiceLocator l = new ULServiceExport_GbdulDataServiceHttpServiceLocator();
        GbdulDataService service = l.getULServiceExport_GbdulDataServiceHttpPort();
        GBDULJurInfoResponse response = service.getJurInfoByBin(new GBDULJurInfoByBinRequest("801110401904", "510510286582", new SystemInfoType()));
        System.out.println();
    }

}
