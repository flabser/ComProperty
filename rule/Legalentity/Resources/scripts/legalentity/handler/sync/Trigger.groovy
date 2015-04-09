package legalentity.handler.sync

import kz.flabs.runtimeobj.document.glossary.Glossary
import kz.flabs.users.User
import kz.lof.webservices.clients.ump.HumansSearchServiceProxy
import kz.lof.webservices.store.ump.Street
import kz.nextbase.script._Database
import kz.nextbase.script._Glossary
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScheduledHandler

class Trigger extends _DoScheduledHandler {


    /*@Override
    void doHandler(_Session session, _WebFormData formData) {
        def db = (_Database) session.getCurrentDatabase();
        HumansSearchServiceProxy hSSProxy = new HumansSearchServiceProxy(new User("temp_user"));
        def street_glos;
        Street[] streets = hSSProxy.getAllStreets("");
        int gloss_count = db.getGlossaryDocsCount("form = 'street'");
        int max_page = countMaxPage(gloss_count, 100);
        def gloss_part;

        for (int i = 1; i <= max_page; i++) {
            gloss_part = db.getGlossaryDocs("form = 'street'", i);
            gloss_part.each { gloss_street ->
                def serv_street = streets.find { s -> s.id == gloss_street.getValueInt("id") }
                if (serv_street) {
                    gloss_street.setValueString("name", serv_street.name)
                    gloss_street.setViewText(serv_street.name)
                    gloss_street.addViewText(serv_street.name)
                    gloss_street.setValueNumber("id", serv_street.id)
                    gloss_street.setValueString("is_actual", "true")
                    gloss_street.save("[supervisor]");
                    streets = streets - serv_street;
                } else {
                    gloss_street.setValueString("is_actual", "false")
                    gloss_street.save("[supervisor]");
                }
            }
        }

        streets.each {
            street_glos = new _Glossary(db)
            street_glos.setForm("street");
            street_glos.setValueString("form", "street");
            street_glos.setValueString("name", it.name)
            street_glos.setViewText(it.name)
            street_glos.addViewText(it.name)
            street_glos.setValueNumber("id", it.id)
            street_glos.setValueString("is_actual", "true")
            street_glos.save("[supervisor]");
        }
    }*/

   /* @Override
    public int doHandler(_Session session, String lang) {
        def db = (_Database) session.getCurrentDatabase();
        HumansSearchServiceProxy hSSProxy = new HumansSearchServiceProxy(new User("temp_user"));
        def street_glos;
        Street[] streets = hSSProxy.getAllStreets("");
        int gloss_count = db.getGlossaryDocsCount("form = 'street'");
        int max_page = countMaxPage(gloss_count, 100);
        def gloss_part;

        for (int i = 1; i <= max_page; i++) {
            gloss_part = db.getGlossaryDocs("form = 'street'", i);
            gloss_part.each { gloss_street ->
                def serv_street = streets.find { s -> s.id == gloss_street.getValueInt("id") }
                if (serv_street) {
                    gloss_street.setValueString("name", serv_street.name)
                    gloss_street.setViewText(serv_street.name)
                    gloss_street.addViewText(serv_street.name)
                    gloss_street.setValueNumber("id", serv_street.id)
                    gloss_street.setValueString("is_actual", "true")
                    gloss_street.save("[supervisor]");
                    streets = streets - serv_street;
                } else {
                    gloss_street.setValueString("is_actual", "false")
                    gloss_street.save("[supervisor]");
                }
            }
        }

        streets.each {
            street_glos = new _Glossary(db)
            street_glos.setForm("street");
            street_glos.setValueString("form", "street");
            street_glos.setValueString("name", it.name)
            street_glos.setViewText(it.name)
            street_glos.addViewText(it.name)
            street_glos.setValueNumber("id", it.id)
            street_glos.setValueString("is_actual", "true")
            street_glos.save("[supervisor]");
        }
        return 0;
    }*/

    public static int countMaxPage(int colCount, int pageSize) {
        float mp = (float) colCount / (float) pageSize;
        float d = Math.round(mp);

        int maxPage = (int) d;
        if (mp > d) {
            maxPage++;
        }
        if (maxPage < 1) maxPage = 1;
        return maxPage;
    }

    public static int calcStartEntry(int pageNum, int pageSize) {
        int pageNumMinusOne = pageNum;
        pageNumMinusOne--;
        return pageNumMinusOne * pageSize;
    }

	@Override
	public int doHandler(_Session session) {
        def db = (_Database) session.getCurrentDatabase();
        HumansSearchServiceProxy hSSProxy = new HumansSearchServiceProxy(new User("temp_user"));
        def street_glos;
        Street[] streets = hSSProxy.getAllStreets("");
        int gloss_count = db.getGlossaryDocsCount("form = 'street'");
        int max_page = countMaxPage(gloss_count, 100);
        def gloss_part;

        for (int i = 1; i <= max_page; i++) {
            gloss_part = db.getGlossaryDocs("form = 'street'", i);
            gloss_part.each { gloss_street ->
                def serv_street = streets.find { s -> s.id == gloss_street.getValueInt("id") }
                if (serv_street) {
                    gloss_street.setValueString("name", serv_street.name)
                    gloss_street.setViewText(serv_street.name)
                    gloss_street.addViewText(serv_street.name)
                    gloss_street.setValueNumber("id", serv_street.id)
                    gloss_street.setValueString("is_actual", "true")
                    gloss_street.save("[supervisor]");
                    streets = streets - serv_street;
                } else {
                    gloss_street.setValueString("is_actual", "false")
                    gloss_street.save("[supervisor]");
                }
            }
        }

        streets.each {
            Glossary base_glos = new Glossary(db.baseObject);
            street_glos = new _Glossary(base_glos, session)
            street_glos.setForm("street");
            street_glos.setValueString("form", "street");
            street_glos.setValueString("name", it.name)
            street_glos.setViewText(it.name)
            street_glos.addViewText(it.name)
            street_glos.setValueNumber("id", it.id)
            street_glos.setValueString("is_actual", "true")
            street_glos.save("[supervisor]");
        }
	}

}
