package monitoring.handler.sync_ord

import java.util.Formatter.DateTime

import kz.flabs.runtimeobj.View
import kz.flabs.runtimeobj.document.Document
import kz.flabs.util.Util
import kz.lof.database.Database
import kz.nextbase.script._Document
import kz.nextbase.script._Helper
import kz.nextbase.script._Session
import kz.nextbase.script.events._DoScheduledHandler
import kz.pchelka.env.Environment
import kz.pchelka.env.ExternalHost
import lotus.domino.*

class Trigger extends _DoScheduledHandler {

	@Override
	public int doHandler(_Session session) {
		try {
			ExternalHost host = Environment.getExternalHost("ord");
			def com_db = session.getCurrentDatabase()
			def ses = NotesFactory.createSession(host.host, host.user, host.pwd);
			ses.setConvertMIME(false);
			Database ord_db = ses.getDatabase(host.server, host.db);
			View all_ord_view = ord_db.getView("AllDocumentsINORD");
			Document ord = all_ord_view.firstDocument;
			while (ord != null) {
				String form = ord.getItemValueString("form");
				if ("INORD".equalsIgnoreCase(form)) {
					String unid = ord.getUniversalID();
					def docs = com_db.getDocsCollection("unid = '" + unid + "'", 0, 0);
					if (docs.getCount() == 0) {
						def new_ord = new _Document(com_db);
						new_ord.setValueString("regnumber", ord.getItemValueString("vn"));
						String content = ord.getItemValueString("briefcontent");
						if (!content) {
							content = "";
						}
						String format_regdate = "";
						Item dvn = ord.getFirstItem("dvn");
						if (dvn) {
							DateTime dt = dvn.getDateTimeValue();
							Date regdate = dt.toJavaDate();
							format_regdate = _Helper.getDateAsString(regdate);
							new_ord.setValueString("regdate", format_regdate);
						}
						new_ord.setValueString("content", content);
						new_ord.setForm("order");
						new_ord.setValueString("propertycode", "0");
						new_ord.setValueString("unid", unid);
						new_ord.setValueNumber("organization", 0);
						new_ord.setViewText(new_ord.getValueString('regnumber') + ' от  ' + format_regdate + ' ' + new_ord.getValueString('content'))
						new_ord.addViewText(new_ord.getValueString('regnumber'))
						new_ord.addViewText(content)
						new_ord.addViewText(format_regdate)
						new_ord.addViewText("")
						new_ord.addViewText("")
						new_ord.addViewText("")
						new_ord.addViewText("")


						RichTextItem rtitem = (RichTextItem) ord.getFirstItem("RTFContent");
						Vector<EmbeddedObject> objects = rtitem.getEmbeddedObjects();
						for (EmbeddedObject att : objects) {
							InputStream is = att.getInputStream();
							String fileName = att.getName();
							String formSesID = Util.generateRandomAsText("0qwertyuiopasdfghjklzxcvbnm123456789");
							File tmpFolder = new File(Environment.tmpDir + File.separator + formSesID + File.separator + "rtfcontent" + File.separator);
							tmpFolder.mkdirs();
							File file = new File(tmpFolder.getAbsolutePath() + File.separator + fileName);
							OutputStream os = new FileOutputStream(file);
							int read = 0;
							byte[] bytes = new byte[1048576];
							while ((read = is.read(bytes)) != -1) {
								os.write(bytes, 0, read);
							}
							os.flush();
							os.close();
							is.close();
							new_ord.addAttachment("rtfcontent", file)
						}
						new_ord.save("[supervisor]");
					}
				}
				Document tmpord = ord;
				ord = all_ord_view.getNextDocument(tmpord);
				tmpord.recycle();
			}
			ses.recycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0
	}
}