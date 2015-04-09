package workspace.page.institution
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class DoScript extends _DoScript {
	
	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		//println(formData)
		def page = 1;
        def keyword = "";
		if (formData.containsField("page") && formData.getValue("page")){
			page = Integer.parseInt(formData.getValue("page"))
		}
        def formula = "(form='kgu' or form='kgp' or form='gkkp' or form='ao' or form='too' or form='subsidiaries') and registration='1'";
        keyword = formData.getEncodedValueSilently("keyword");

        if (formData.containsField("keyword") && keyword != ""){
            formula = "$formula and viewtext ~* '$keyword'";
            //page = 1;
        }

		def db = session.getCurrentDatabase()
		def filters = []
		def sorting = []
		//def col = db.getCollectionOfGlossaries(formula, page, 20)
        def col = db.getCollectionOfDocuments(formula, page, false)
       // println col.count;
       // println(formula)
        setContent(col)
	}
}