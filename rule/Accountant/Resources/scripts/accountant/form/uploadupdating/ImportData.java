package accountant.form.uploadupdating;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.Sheet;
import kz.flabs.util.Util;
import kz.nextbase.script._Session;
import kz.pchelka.server.Server;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.PersonalEstate;
import municipalproperty.model.Property;
import municipalproperty.model.constants.KufType;
import municipalproperty.model.util.PropertyFactory;
import reference.dao.CountryDAO;
import reference.dao.DistrictDAO;
import reference.dao.PropertyCodeDAO;
import reference.dao.RegionDAO;
import staff.dao.EmployeeDAO;
import staff.dao.OrganizationDAO;
import staff.model.Employee;
import staff.model.Organization;
import accountant.page.action.MPXLImporter;
import accountant.page.action.MPXLImporter.ErrorDescription;

public class ImportData {
	StringBuilder defectRows = new StringBuilder();
	StringBuilder savedRows = new StringBuilder();

	public boolean importFromExcelSheet(Sheet sheet, _Session ses, Properties kufProp) {
		MPXLImporter fc = new MPXLImporter(MPXLImporter.LOAD);
		Map<Integer, List<List<ErrorDescription>>> checkResult = fc.process(sheet, ses, true);
		if (checkResult.size() == 0) {
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
					String country = ((LabelCell) sheet.getCell(6, i)).getString();
					String region = ((LabelCell) sheet.getCell(7, i)).getString();
					String district = ((LabelCell) sheet.getCell(8, i)).getString();
					String address = ((LabelCell) sheet.getCell(9, i)).getString();
					String originalcost = sheet.getCell(10, i).getContents().trim().replace("\u00A0", "");
					String cumulativeDepreciation = sheet.getCell(11, i).getContents().trim().replace("\u00A0", "");
					String impairmentLoss = sheet.getCell(12, i).getContents().trim();
					String balancecost = sheet.getCell(13, i).getContents().trim().replace("\u00A0", "");
					String revaluationAmount = sheet.getCell(14, i).getContents().trim().replace("\u00A0", "");
					String residualCost = sheet.getCell(15, i).getContents().trim().replace("\u00A0", "");
					String receiptBasisinBalance = sheet.getCell(13, i).getContents().trim().replace("\u00A0", "");
					String model = sheet.getCell(17, i).getContents().trim();
					String commissioningYear = sheet.getCell(18, i).getContents().trim();
					String acquisitionYear = sheet.getCell(19, i).getContents().trim();
					String isReadyToOperation = sheet.getCell(20, i).getContents().trim();

					Property prop = PropertyFactory.getProperty(kuf);
					prop.setKof(kof);
					prop.setKuf(KufType.getType(Util.convertStringToInt(kuf)));
					prop.setInvNumber(invnumber);
					prop.setObjectName(name);
					prop.setDescription(name);
					prop.setPropertyCode(new PropertyCodeDAO(ses).findByName(propertycode_name));
					prop.setAcceptanceDate(acceptancedate);
					reference.model.embedded.Address addr = new reference.model.embedded.Address();
					addr.setCountry(new CountryDAO(ses).findByName(country));
					addr.setRegion(new RegionDAO(ses).findByName(region));
					addr.setDistrict(new DistrictDAO(ses).findByName(district));
					addr.setAdditionalInfo(address);
					prop.setOriginalCost(Util.convertStringToFloat(originalcost));
					prop.setCumulativeDepreciation(Util.convertStringToFloat(cumulativeDepreciation));
					prop.setBalanceCost(Util.convertStringToFloat(balancecost));
					OrganizationDAO orgDao = new OrganizationDAO(ses);
					List<Organization> orgList = orgDao.findAll();
					Organization org = orgList.get(1);
					prop.setBalanceHolder(org);
					prop.setAuthor(ses.getUser());
					EmployeeDAO empDao = new EmployeeDAO(ses);
					Employee emp = empDao.findByLogin("cgalina");
					prop.addReaderEditor(emp.getUser());

					if (prop instanceof PersonalEstate) {
						PersonalEstate pe = (PersonalEstate) prop;
						pe.setReadyToUse(true);
					}
					dao.add(prop);
					return true;
				} catch (Exception e) {
					Server.logger.errorLogEntry(e);
				}
			}

		}
		return false;
	}
}
