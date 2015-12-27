package accountant.form.uploadupdating;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.Sheet;
import kz.flabs.util.Util;
import kz.nextbase.script._Session;
import kz.nextbase.script.struct._Employer;
import kz.pchelka.server.Server;
import monitoring.dao.PropertyDAO;
import monitoring.model.PersonalEstate;
import monitoring.model.Property;
import monitoring.model.constants.KufType;
import monitoring.model.util.PropertyFactory;

public class ImportData {
	StringBuilder defectRows = new StringBuilder();
	StringBuilder savedRows = new StringBuilder();

	public void importFromExcelSheet(Sheet sheet, _Session ses, Properties kufProp) {
		for (int i = 1; i < sheet.getRows(); i++) {
			String kof = sheet.getCell(0, i).getContents();
			String kuf = sheet.getCell(1, i).getContents();
			Cell cell = sheet.getCell(1, i);
			if (cell.getType() == CellType.EMPTY || cell.getCellFormat() == null || kuf == null) {
				defectRows.append(i).append(",");
				continue;
			}

			try {
				PropertyDAO dao = new PropertyDAO(ses);
				String invnumber = sheet.getCell(2, i).getContents();
				Property p = dao.findByInvNum(invnumber);
				if (p != null) {
					continue;
				}

				String kufVal = kufProp.getProperty(kuf.trim());
				if (kufVal == null) {
					defectRows.append(i);
					continue;
				}

				String name = sheet.getCell(3, i).getContents();
				String propertycode_name = sheet.getCell(4, i).getContents();
				Date acceptancedate = null;
				Cell dCell = sheet.getCell(5, i);
				if (dCell.getType() == CellType.DATE) {
					DateCell dateCell = (DateCell) dCell;
					acceptancedate = dateCell.getDate();
				} else {
					String acceptancedateStr = sheet.getCell(5, i).getContents().replace("/", ".").replace("-", ".");
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

				Property prop = PropertyFactory.getProperty(kuf);
				prop.setKof(kof);
				prop.setKuf(KufType.getType(Util.convertStringToInt(kuf)));
				prop.setInvNumber(invnumber);
				prop.setObjectName(name);
				prop.setDescription(name);
				prop.setPropertyCode(propertycode_name);
				prop.setAcceptanceDate(acceptancedate);
				reference.model.embedded.Address addr = new reference.model.embedded.Address();
				addr.setRegion(region);
				addr.setAdditionalInfo(address);
				prop.setOriginalCost(Util.convertStringToFloat(originalcost));
				prop.setBalanceCost(Util.convertStringToFloat(balancecost));
				prop.setAuthor(ses.getUser());
				_Employer emp = ses.getStructure().getEmployer("cgalina");
				prop.addEditor(emp.employer.getUser());

				if (prop instanceof PersonalEstate) {
					PersonalEstate pe = (PersonalEstate) prop;
					pe.setReadyToUse(true);
				}
				dao.add(prop);

			} catch (Exception e) {
				Server.logger.errorLogEntry(e);
			}
		}

	}
}
