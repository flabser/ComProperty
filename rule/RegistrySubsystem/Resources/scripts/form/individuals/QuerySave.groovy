package form.individuals

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

		doc.setForm("individuals");
		doc.addStringField("orgfullname", webFormData.getValueSilently("orgfullname"))
		doc.addStringField("orgfullnamekaz", webFormData.getValueSilently("orgfullnamekaz"))
		doc.addStringField("fullactualaddress", webFormData.getValueSilently("fullactualaddress"))
		doc.addStringField("phone", webFormData.getValueSilently("phone"))
		doc.addStringField("fax", webFormData.getValueSilently("fax"))
		doc.addStringField("email", webFormData.getValueSilently("email"))
		doc.addStringField("fio", webFormData.getValueSilently("fio"))
		doc.addStringField("iin", webFormData.getValueSilently("iin"))
		doc.addStringField("oked", webFormData.getValueSilently("oked"))
		doc.addStringField("typeactivity", webFormData.getValueSilently("typeactivity"))
		//doc.addNumberField("oked", webFormData.getNumberValueSilently("oked", 0))
		doc.addStringField("orgregdate", webFormData.getValueSilently("orgregdate"))
		doc.addStringField("orgregnumber", webFormData.getValueSilently("orgregnumber"))
		doc.addStringField("regdateorder", webFormData.getValueSilently("regdateorder"))
		doc.addStringField("numberorder", webFormData.getValueSilently("numberorder"))
		//doc.addNumberField("typeactivity", webFormData.getNumberValueSilently("typeactivity", 0))
		doc.addStringField("privilege",webFormData.getValueSilently("privilege"))
        doc.addStringField("registration",webFormData.getValueSilently("registration"))
		doc.addStringField("author", webFormData.getValue("author"))
		
		doc.addFile("rtfcontent", webFormData)

		def returnURL = session.getURLOfLastPage()
        Date tDate = new Date()
		localizedMsgBox(getLocalizedWord("Документ сохранен",lang))
		if(doc.isNewDoc){
			returnURL.changeParameter("page", "0");
		}

        doc.setViewText(doc.getValueString('fio'))
        doc.addViewText(doc.getValueString('fullactualaddress'))
        doc.addViewText(doc.getValueString('iin'))
        doc.addViewText(doc.getValueString('orgfullname'))
        doc.addViewText(doc.getValueString('oked'))
/*		if (doc.getValueString("typeactivity").isNumber()) {
			doc.addViewText(session.db.getGlossaryCustomFieldValueByDOCID(doc.getValueString("typeactivity").toInteger(), "koef").replace(",", "."))
		} else {
			doc.addViewText(doc.getValueString("typeactivity"));
		}*/
        doc.addViewText(doc.getValueString("privilege"))
		doc.addViewText(doc.getValueString("orgfullnamekaz"))
		//doc.setViewNumber(doc.getValueNumber("vnnumber"))
		//doc.setViewDate(doc.getValueDate("acceptancedate"))
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		/*if (webFormData.getValueSilently("invnumber") == ""){
			localizedMsgBox("Поле \"Инвентарный номер\" не заполнено.")
			return false
		}*/
		return true
	}
}
