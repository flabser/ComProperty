package monitoring.handler.recalc_end_contract_date

import kz.nextbase.script._Session
import kz.nextbase.script.events._DoScheduledHandler
import org.joda.time.Days
import org.joda.time.LocalDate

import java.text.SimpleDateFormat

class Trigger extends _DoScheduledHandler {

    @Override
    public int doHandler(_Session session) {
        try {
            def db = session.getCurrentDatabase()
            def coll = db.getCollectionOfDocuments("form='contract'", true).getEntries()
            def doc = null;
            Date end_rent = null;
            Date now_date = new Date();
            coll.each {
                doc = it.getDocument();
                if (doc) {
                    end_rent = new SimpleDateFormat("dd.MM.yyyy").parse(doc.getValueString("endcontractdate"));
                    println(end_rent)
                    if (end_rent) {
                        doc.setValueNumber("dbd", Days.daysBetween(new LocalDate(now_date), new LocalDate(end_rent)).getDays());
                        doc.save("[supervisor]");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0
    }
}