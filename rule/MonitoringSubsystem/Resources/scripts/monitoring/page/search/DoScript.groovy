package monitoring.page.search
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class DoScript extends _DoScript {


    public void doProcess(_Session session, _WebFormData formData, String lang) {

        def page = formData.getNumberValueSilently("page", 1);

        def db = session.getCurrentDatabase()
        def condition = "form in ('furniture', 'schoolequipment', 'buildings', 'automobile', 'cargo', 'dejtransport', 'officialtransport', 'hospitaltransport', 'bus', 'trolleybus', 'tram', 'watertransport', 'taxi', 'specialequipment', 'motorcycle', 'objectreservedfund', \n" +
                "  'bombproof', 'factory', 'combines', 'airport', 'transitions', 'billboard', 'columns', 'electricnetworks', 'thermalnetworks', 'gas', 'watersystem', 'drain', 'road', 'parking', 'order', 'contract')  ";
        String[] cond = ["","", "","","","", condition]
        def col = db.search(formData.getValue("keyword"), page as int, cond)
        //def col = db.search(formData.getValue("keyword"), page as int)
        setContent(col)
    }
}