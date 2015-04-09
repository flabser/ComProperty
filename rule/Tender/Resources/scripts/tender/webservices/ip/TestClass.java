package tender.webservices.ip;


import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.Calendar;

public class TestClass {

    public void testResponse() throws RemoteException, ServiceException {
        GBDFL2009Service_ServiceLocator l = new GBDFL2009Service_ServiceLocator();
        GBDFL2009Service_PortType service = l.getGBDFL2009ServiceBinding();
        FullResponse_ fullResponse_ = service.getPersonByIIN(new IINRequest_("120127300015", new SystemInfo_("1", "1", Calendar.getInstance(), "1", "1", "1", "")));
        System.out.println();
    }
}
