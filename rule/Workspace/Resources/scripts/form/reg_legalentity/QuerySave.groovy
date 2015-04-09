package form.reg_legalentity

import java.text.SimpleDateFormat
import java.util.Map
import kz.nextbase.script.*
import kz.nextbase.script.events.*
import kz.nextbase.script._Helper
import kz.flabs.servlets.sitefiles.UploadedFile

class QuerySave extends _FormQuerySave {

	@Override
	public void doQuerySave(_Session session, _Document doc, _WebFormData webFormData, String lang) {

		println(webFormData)
		
		boolean v = validate(webFormData)
		if(v == false){
			stopSave()
			return
		}

		doc.setForm("legalentity");
		doc.addStringField("orgfullname", webFormData.getValueSilently("orgfullname"))
		doc.addStringField("orgfullnamekaz", webFormData.getValueSilently("orgfullnamekaz"))
		doc.addStringField("fulllegaladdress", webFormData.getValueSilently("fulllegaladdress"))
		doc.addStringField("fullactualaddress", webFormData.getValueSilently("fullactualaddress"))
		doc.addStringField("phone", webFormData.getValueSilently("phone"))
		doc.addStringField("fax", webFormData.getValueSilently("fax"))
		doc.addStringField("email", webFormData.getValueSilently("email"))
		doc.addStringField("bin", webFormData.getValueSilently("bin"))
		doc.addStringField("iik", webFormData.getValueSilently("iik"))
		doc.addStringField("rnn", webFormData.getValueSilently("rnn"))
		doc.addStringField("bik", webFormData.getValueSilently("bik"))
		doc.addStringField("executive", webFormData.getValueSilently("executive"))
		doc.addStringField("rukfullname", webFormData.getValueSilently("rukfullname"))
		doc.addStringField("login", webFormData.getValueSilently("login"))
		doc.addStringField("password", webFormData.getValueSilently("password"))
		doc.addStringField("bankdetails", webFormData.getValueSilently("bankdetails"))
		doc.addFile("rtfcontent", webFormData)

		//def returnURL = session.getURLOfLastPage()
        Date tDate = new Date()
		localizedMsgBox(getLocalizedWord("Документ сохранен",lang))
		//returnURL.changeParameter("page", "0");

        doc.setViewText(doc.getValueString('orgfullname'))
        doc.addViewText(doc.getValueString('fulllegaladdress'))
        doc.addViewText(doc.getValueString('bin'))
        doc.addViewText(doc.getValueString("orgfullnamekaz"))
		//setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		/*if (webFormData.getValueSilently("invnumber") == ""){
			localizedMsgBox("Поле \"Инвентарный номер\" не заполнено.")
			return false
		}*/
		return true
	}
}
