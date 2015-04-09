package accountant.form.uploadupdating

import java.text.SimpleDateFormat
import java.util.Map
import kz.nextbase.script.*
import kz.nextbase.script.events.*
import kz.nextbase.script._Helper
import kz.flabs.servlets.sitefiles.UploadedFile

class QuerySave extends _FormQuerySave {

	@Override
	public void doQuerySave(_Session session, _Document doc, _WebFormData webFormData, String lang) {
		doc.setForm("update")
		doc.addStringField("author", webFormData.getValue("author"))
		doc.addStringField("savedate", webFormData.getValue("savedate"))
		doc.addFile("rtfcontent", webFormData)

		//def returnURL = session.getURLOfLastPage()
		localizedMsgBox(getLocalizedWord("Документ сохранен",lang))
		//returnURL.changeParameter("page", "0");

		doc.setViewText(webFormData.getValue("author") +" : " +  webFormData.getValue("savedate"))
		//setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		return true
	}
}
