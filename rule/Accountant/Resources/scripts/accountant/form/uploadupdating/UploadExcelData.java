package accountant.form.uploadupdating;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import kz.flabs.exception.ComplexObjectException;
import kz.flabs.exception.LicenseException;
import kz.flabs.runtimeobj.document.Document;
import kz.flabs.util.Util;
import kz.nextbase.script._Document;
import kz.nextbase.script._Session;
import kz.nextbase.script._ViewEntryCollection;
import kz.nextbase.script.events._FormPostSave;
import kz.nextbase.script.mail._MailAgent;
import kz.nextbase.script.mail._Memo;

public class UploadExcelData extends _FormPostSave {

	@Override
	public void doPostSave(_Session ses, _Document doc) {
		try {
			String fileDir = "tmp" + File.separator + "InventLoad" + File.separator + ses.getCurrentUserID()
					+ File.separator + new Date().getTime();
			doc.getAttachments("rtfcontent", fileDir);

			File dir = new File(fileDir + File.separator + "1");

			FilenameFilter fileNameFilter = new FilenameFilter() {
				@Override
				public boolean accept(File directory, String name) {
					return name.endsWith(".xls");
				}
			};

			int saved_docs_counter = 0;
			StringBuilder defectRows = new StringBuilder();
			StringBuilder savedRows = new StringBuilder();
			String path = new File("").getAbsolutePath() + File.separator + "rule" + File.separator + "Accountant"
					+ File.separator + "Resources" + File.separator + "scripts" + File.separator + "accountant"
					+ File.separator + "resources" + File.separator + "kuf.properties";
			FileInputStream input = new FileInputStream(path);
			Properties kufProp = new Properties();
			kufProp.load(input);

			if (dir != null && dir.isDirectory()) {
				File[] files = dir.listFiles(fileNameFilter);
				for (int ci = 0; ci < files.length; ci++) {
					File xf = files[ci];
					WorkbookSettings ws = new WorkbookSettings();
					ws.setEncoding("Cp1252");
					Workbook workbook = Workbook.getWorkbook(xf, ws);
					Sheet sheet = workbook.getSheet(0);

					new ImportData().importFromExcelSheet(sheet, ses, kufProp);

					int maxCount = 100;
					for (int i = 1; i < sheet.getRows(); i++) {
						String kof = sheet.getCell(0, i).getContents();
						String kuf = sheet.getCell(1, i).getContents();
						Cell cell = sheet.getCell(1, i);
						if (cell.getType() == CellType.EMPTY || cell.getCellFormat() == null || kuf == null) {
							defectRows.append(i).append(",");
							continue;
						}

						try {
							String invnumber = sheet.getCell(2, i).getContents();
							_ViewEntryCollection docs = ses.getCurrentDatabase()
									.getCollectionOfDocuments("invnumber='" + invnumber + "'", false);
							if (docs.getEntries().size() > 0) {
								continue;
							}

							String kufVal = kufProp.getProperty(kuf.trim());
							if (kufVal == null) {
								defectRows.append(i);
								continue;
							}

							Document document = new Document(ses.getCurrentDatabase().getBaseObject(), "[supervisor]");
							_Document _doc = new _Document(document);
							String name = sheet.getCell(3, i).getContents();
							String propertycode_name = sheet.getCell(4, i).getContents();

							Date acceptancedate = null;
							Cell dCell = sheet.getCell(5, i);
							if (dCell.getType() == CellType.DATE) {
								DateCell dateCell = (DateCell) dCell;
								acceptancedate = dateCell.getDate();
							} else {
								String acceptancedateStr = sheet.getCell(5, i).getContents().replace("/", ".")
										.replace("-", ".");
								switch (acceptancedateStr.length()) {
								case 4:
									acceptancedate = new SimpleDateFormat("yyyy").parse(acceptancedateStr);
									break;
								case 8:
									acceptancedate = new SimpleDateFormat("dd.MM.yy").parse(acceptancedateStr);
									break;
								case 10:
									acceptancedate = new SimpleDateFormat("dd.MM.yyyy").parse(acceptancedateStr);
									break;
								}
							}
							String region = ((LabelCell) sheet.getCell(8, i)).getString();
							String address = ((LabelCell) sheet.getCell(9, i)).getString();
							String originalcost = sheet.getCell(10, i).getContents().trim().replace("\u00A0", "");
							String balancecost = sheet.getCell(13, i).getContents().trim().replace("\u00A0", "");

							_doc.setForm(kufVal);
							_doc.setValueString("form", kufVal);
							_doc.setValueString("kof", kof);
							_doc.setValueString("kuf", kuf);
							_doc.setValueString("invnumber", invnumber);
							_doc.setValueString("description", name);
							_doc.setValueString("objectname", name);
							_doc.setValueString("address", region + ", " + address);
							_doc.setValueString("propertycode_name", propertycode_name);

							_doc.setValueDate("acceptancedate", acceptancedate);
							_doc.setValueString("originalcost",
									Float.toString(Util.convertStringToFloat(originalcost)));
							_doc.setValueString("balancecost", Float.toString(Util.convertStringToFloat(balancecost)));
							_doc.addEditor("[operator]");
							_doc.addEditor("[supervisor]");
							_doc.addEditor(ses.getCurrentAppUser().getUserID());
							_doc.setAuthor(ses.getCurrentAppUser().getUserID());

							_doc.setViewText(name);
							_doc.addViewText(originalcost);
							_doc.addViewText(ses.getCurrentAppUser().getFullName());
							_doc.addViewText(kof + "/" + kuf);
							_doc.setViewDate(new Date());

							_doc.save(ses.getCurrentAppUser().getUserID());
							if (i < maxCount) {
								savedRows.append(_doc.getDocID()).append(",");
							} else {
								maxCount += getMaxCount(_doc.getDocID(), 2048);
								insertDocument(ses, "saveddocslist", savedRows, xf.getName());

								savedRows = new StringBuilder();
								savedRows.append(_doc.getDocID()).append(",");
							}
							saved_docs_counter++;
						} catch (Exception e) {
							defectRows.append(i).append(",");
							log("Accountant: doc wasn't saved. Row:" + i + " \n" + e.toString());
						}
					}

					if (savedRows.length() > 0) {
						insertDocument(ses, "saveddocslist", savedRows, xf.getName());
						savedRows.delete(0, savedRows.length());
					}

					StringBuilder msg = new StringBuilder();
					msg.append("На данный момент было загружено: ").append(sheet.getRows() - 1)
							.append(" объектов. Успешно загрузилось ").append(saved_docs_counter)
							.append(". С ошибками загрузилось: ").append(sheet.getRows() - 1 - saved_docs_counter)
							.append(". Номера excel ячеек с ошибками: ").append(defectRows)
							.append(". Необходимо испавить ошибки и загрузить исправленные данные повторно");

					_Document notifDoc = new _Document(new Document(ses.getCurrentDatabase().getBaseObject(),
							ses.getCurrentAppUser().getUserID()));
					notifDoc.setViewText(msg.toString());
					notifDoc.addViewText("uploadobj");

					notifDoc.setValueString("title", "Уведомление  о загрузке объектов");
					notifDoc.setValueDate("sentdate", new Date());
					notifDoc.setValueString("notificationtype", "uploadobj");
					notifDoc.setValueString("balanceholdername", ses.getCurrentAppUser().getFullName());
					// notifDoc.setValueString("description", msg.toString())

					notifDoc.setForm("notification");
					notifDoc.setViewDate(new Date());

					notifDoc.addEditor(ses.getCurrentAppUser().getUserID());
					notifDoc.setAuthor(ses.getCurrentAppUser().getUserID());
					notifDoc.addEditor("[operator]");
					notifDoc.addEditor("[supervisor]");
					notifDoc.save(ses.getCurrentAppUser().getUserID());

					if (defectRows.length() > 0) {
						insertDocument(ses, "defectdocslist", defectRows, xf.getName());
						defectRows.delete(0, defectRows.length());
					}

					_MailAgent ma = ses.getMailAgent();
					try {
						_Memo memo = new _Memo("Информация о загрузке", "", msg.toString(), null, true);
						ArrayList<String> r = new ArrayList<String>();
						r.add(ses.getCurrentAppUser().getEmail());
						ma.sendMail(r, memo);
					} catch (Exception e) {
						error(e);
					}

				}
			}

			log("Accountant: import by user " + ses.getCurrentUserID() + " finished. Total imported docs number = "
					+ saved_docs_counter);
		} catch (Exception e) {
			error(e);
		}

	}

	private static void insertDocument(_Session ses, String form, StringBuilder value, String fileName)
			throws LicenseException, ComplexObjectException {
		value.deleteCharAt(value.length() - 1);

		_Document doc = new _Document(
				new Document(ses.getCurrentDatabase().getBaseObject(), ses.getCurrentAppUser().getUserID()));
		doc.setForm(form);
		doc.setViewText(value.toString());
		doc.setViewDate(new Date());
		doc.addEditor(ses.getCurrentAppUser().getUserID());
		doc.setAuthor(ses.getCurrentAppUser().getUserID());
		doc.addEditor("[operator]");
		doc.addEditor("[supervisor]");
		doc.setValueString("filename", fileName);
		doc.save(ses.getCurrentAppUser().getUserID());
	}

	private static int getMaxCount(int docid, int fieldLength) {
		int initialDocId = docid;
		int docIdLen = String.valueOf(docid).length();
		int count = 0;

		int nCount;
		int fullLength = 0;

		while ((nCount = (int) Math.pow(10, docIdLen) - initialDocId)
				* (docIdLen + /** colon symbol */
				1) + fullLength < fieldLength) {
			fullLength += nCount * (docIdLen + 1);
			count += nCount;
			initialDocId = (int) Math.pow(10, docIdLen);
			docIdLen++;
		}

		return count + (fieldLength - fullLength) / (docIdLen + 1);
	}

}
