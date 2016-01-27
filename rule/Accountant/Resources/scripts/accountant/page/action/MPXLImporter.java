package accountant.page.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import kz.flabs.dataengine.jpa.IAppEntity;
import kz.flabs.util.Util;
import kz.nextbase.script._Session;
import kz.pchelka.server.Server;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Equipment;
import municipalproperty.model.PersonalEstate;
import municipalproperty.model.Property;
import municipalproperty.model.RealEstate;
import municipalproperty.model.constants.KufType;
import municipalproperty.model.constants.StatusType;
import municipalproperty.model.util.PropertyFactory;
import reference.dao.CountryDAO;
import reference.dao.DistrictDAO;
import reference.dao.LocalityDAO;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;
import reference.dao.ReferenceDAO;
import reference.dao.RegionDAO;
import reference.dao.StreetDAO;
import reference.model.Country;
import reference.model.District;
import reference.model.Locality;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import reference.model.Region;
import reference.model.Street;
import staff.dao.EmployeeDAO;
import staff.dao.OrganizationDAO;
import staff.exception.EmployеeException;
import staff.model.Employee;
import staff.model.Organization;

public class MPXLImporter {
	public final static int LOAD = 100;
	public final static int CHECK = 99;
	public final static int FROM_YEAR = 1950;
	public Map<Integer, List<List<ErrorDescription>>> sheetErr = new HashMap<Integer, List<List<ErrorDescription>>>();

	private final static String trueVal = "годен";
	private final static String falseVal = "не годен";

	private int mode;

	public MPXLImporter(int mode) {
		this.mode = mode;
	}

	public Map<Integer, List<List<ErrorDescription>>> process(Sheet sheet, _Session ses, boolean stopIfWrong, Organization bh) {
		PropertyDAO propertyDao = new PropertyDAO(ses);
		int processed = 0, skipped = 0;
		if (mode == MPXLImporter.LOAD) {
			mode = MPXLImporter.CHECK;
			Map<Integer, List<List<ErrorDescription>>> res = process(sheet, ses, true, bh);
			if (res.size() > 0) {
				Server.logger.errorLogEntry("file " + sheet.getName() + " is incorrect, check it before");
				return null;
			}
			mode = MPXLImporter.LOAD;
		}

		for (int i = 1; i < sheet.getRows(); i++) {
			String kof = sheet.getCell(0, i).getContents().trim();
			String kuf = sheet.getCell(1, i).getContents().trim();
			String invNumber = sheet.getCell(2, i).getContents().trim();
			String name = sheet.getCell(3, i).getContents().trim();
			String propertyCode = sheet.getCell(4, i).getContents().trim();
			Cell acceptanceDateCell = sheet.getCell(5, i);
			String country = sheet.getCell(6, i).getContents().trim();
			String region = sheet.getCell(7, i).getContents().trim();
			String preparedRegion = region.replace("г.а.", "").replace("область", "").trim();
			String district = sheet.getCell(8, i).getContents().trim();
			String preparedDistrict = district.replace("район", "").trim();
			String address = sheet.getCell(9, i).getContents().trim();
			String originalCost = sheet.getCell(10, i).getContents().trim();
			String cumulativeDepreciation = sheet.getCell(11, i).getContents().trim();
			String impairmentLoss = sheet.getCell(12, i).getContents().trim();
			String balanceCost = sheet.getCell(13, i).getContents().trim();
			String revaluationAmount = sheet.getCell(14, i).getContents().trim();
			String residualCost = sheet.getCell(15, i).getContents().trim();
			String receiptBasisinBalance = sheet.getCell(16, i).getContents().trim();
			String model = sheet.getCell(17, i).getContents().trim();
			Cell commissioningYear = sheet.getCell(18, i);
			Cell acquisitionYear = sheet.getCell(19, i);
			String isReadyToOperation = sheet.getCell(20, i).getContents().trim();

			if (mode == MPXLImporter.CHECK) {
				List<List<ErrorDescription>> rowErr = new ArrayList<List<ErrorDescription>>();
				rowErr.add(new CheVal("1, КОФ", kof).isNotEmpty(kof).getErr());
				rowErr.add(new CheVal("2, КУФ", kuf).isNotEmpty(kuf).isKufType(kuf).getErr());
				rowErr.add(new CheVal("3, Инвентарный номер", invNumber).isNotEmpty(invNumber).getErr());
				List<Property> pList = propertyDao.findByInvNum(new CheVal("3, Инвентарный номер", invNumber).getString(invNumber));

				for (Property p : pList) {
					if (p.getBalanceHolder().equals(bh)) {
						rowErr.add(new CheVal("3, Инвентарный номер", invNumber).isNotUniqueMessage().getErr());
						skipped++;
						break;
					}
				}

				rowErr.add(new CheVal("4, Наименование", name).isNotEmpty(name).getErr());
				rowErr.add(new CheVal("5, Код права на имущество", propertyCode).isNotEmpty(propertyCode)
				        .isReferenceValue(new PropertyCodeDAO(ses), propertyCode).getErr());
				rowErr.add(new CheVal("6, Дата принятия на баланс", acceptanceDateCell.getContents()).isDate(acceptanceDateCell).getErr());
				rowErr.add(new CheVal("7, Страна", country).isReferenceValue(new CountryDAO(ses), country).getErr());
				rowErr.add(new CheVal("8, Регион", region).isNotEmpty(preparedRegion).isReferenceValue(new RegionDAO(ses), preparedRegion).getErr());
				rowErr.add(new CheVal("9, Район", district).isNotEmpty(preparedDistrict).isReferenceValue(new DistrictDAO(ses), preparedDistrict)
				        .getErr());
				rowErr.add(new CheVal("10, Адрес", address).isNotEmpty(address).getErr());
				rowErr.add(new CheVal("11, Первоначальная стоимость", originalCost).isNotEmpty(originalCost).isFloatNumber(originalCost).getErr());
				rowErr.add(new CheVal("12, Накопленная амортизация", cumulativeDepreciation).isNotEmpty(cumulativeDepreciation)
				        .isFloatNumber(cumulativeDepreciation).getErr());
				rowErr.add(new CheVal("13, Убыток от обесценения", impairmentLoss).isFloatNumber(impairmentLoss).getErr());
				rowErr.add(new CheVal("14, Балансовая стоимость", balanceCost).isNotEmpty(balanceCost).isFloatNumber(balanceCost).getErr());
				rowErr.add(new CheVal("15, Сумма переоценки", revaluationAmount).isFloatNumber(revaluationAmount).getErr());
				rowErr.add(new CheVal("16, Балансовая стоимость после переоценки", residualCost).isNotEmpty(residualCost).isFloatNumber(residualCost)
				        .getErr());
				rowErr.add(new CheVal("17, Основание поступления на баланс", receiptBasisinBalance).isReferenceValue(new ReceivingReasonDAO(ses),
				        receiptBasisinBalance).getErr());
				rowErr.add(new CheVal("18 Модель", model).isOkAnyway().getErr());
				rowErr.add(new CheVal("19, Год ввода в эксплуатацию", commissioningYear.getContents()).isYear(commissioningYear).getErr());
				rowErr.add(new CheVal("20, Год приобретения ", acquisitionYear.getContents()).isYear(acquisitionYear).getErr());
				rowErr.add(new CheVal("21, Сведения о годности в эксплуатацию", isReadyToOperation).isNotEmpty(isReadyToOperation)
				        .isValueOfList(trueVal, falseVal, isReadyToOperation).getErr());

				rowErr.removeAll(Arrays.asList(null, ""));
				if (!rowErr.isEmpty()) {
					System.out.println("------" + (i + 1) + "---------");
					for (List<ErrorDescription> v : rowErr) {
						for (ErrorDescription vq : v) {
							System.out.println(vq);
						}
					}
					sheetErr.put(i + 1, rowErr);
					if (stopIfWrong) {
						break;
					}
				}

				processed++;
			} else if (mode == MPXLImporter.LOAD) {
				CheVal cv = new CheVal();
				List<Property> p = propertyDao.findByInvNum(cv.getString(invNumber));
				if (p != null) {
					// skipped++;
					// continue;
				}
				Property prop = PropertyFactory.getProperty(kuf);
				prop.setKof(kof);
				prop.setKuf(cv.getKufType(kuf));
				prop.setInvNumber(cv.getString(invNumber));
				prop.setObjectName(cv.getString(name));
				prop.setPropertyCode((PropertyCode) cv.getEntity(new PropertyCodeDAO(ses), propertyCode));
				prop.setAcceptanceDate(cv.getDate(acceptanceDateCell));
				reference.model.embedded.Address addr = new reference.model.embedded.Address();
				addr.setCountry((Country) cv.getEntity(new CountryDAO(ses), country));
				addr.setRegion((Region) cv.getEntity(new RegionDAO(ses), preparedRegion));
				addr.setDistrict((District) cv.getEntity(new DistrictDAO(ses), preparedDistrict));
				addr.setAdditionalInfo(address);
				prop.setOriginalCost(cv.getFloat(originalCost));
				prop.setCumulativeDepreciation(cv.getFloat(cumulativeDepreciation));
				prop.setCumulativeDepreciation(cv.getFloat(impairmentLoss));
				prop.setBalanceCost(cv.getFloat(balanceCost));
				prop.setRevaluationAmount(cv.getFloat(revaluationAmount));
				prop.setResidualCost(cv.getFloat(residualCost));
				prop.setReceivingReason((ReceivingReason) cv.getEntity(new ReceivingReasonDAO(ses), receiptBasisinBalance));
				if (prop instanceof PersonalEstate) {
					((PersonalEstate) prop).setModel(model);
				} else if (prop instanceof Equipment) {
					((Equipment) prop).setModel(model);
				} else if (prop instanceof RealEstate) {
					addr.setLocality((Locality) cv.getEntity(new LocalityDAO(ses), "Алматы"));
					addr.setStreet((Street) cv.getEntity(new StreetDAO(ses), "Unknown"));
					((RealEstate) prop).setAddress(addr);
				}
				prop.setCommissioningYear(cv.getYear(commissioningYear));
				prop.setAcquisitionYear(cv.getYear(acquisitionYear));
				prop.setReadyToUse(cv.getBoolean(trueVal, falseVal, isReadyToOperation));

				OrganizationDAO orgDao = new OrganizationDAO(ses);
				List<Organization> orgList = orgDao.findAll();
				Organization org = orgList.get(1);
				prop.setBalanceHolder(org);
				prop.setStatus(StatusType.ON_BALANCE);
				prop.setAuthor(ses.getUser());
				EmployeeDAO empDao = new EmployeeDAO(ses);
				Employee emp = empDao.findByLogin("cgalina");
				try {
					prop.addReaderEditor(emp.getUser());
				} catch (EmployеeException e) {
					Server.logger.errorLogEntry(e);
				}
				propertyDao.add(prop);
				processed++;
			}
		}
		Server.logger.verboseLogEntry("processed=" + processed + ", skipped=" + skipped);
		return sheetErr;

	}

	class CheVal {
		private List<ErrorDescription> errMsg = new ArrayList<ErrorDescription>();;
		String info;
		String sourceValue;

		CheVal(String column, String sv) {
			info = column;
			sourceValue = sv;
		}

		public CheVal isNotUniqueMessage() {
			errMsg.add(new ErrorDescription(info, sourceValue, "value is not unique"));
			return this;
		}

		public CheVal() {

		}

		CheVal isOkAnyway() {
			return this;
		}

		CheVal isNotEmpty(String v) {
			String value = getString(v);
			if (value == null || value.equals("")) {
				errMsg.add(new ErrorDescription(info, sourceValue, "value is null"));
			}
			return this;
		}

		String getString(String value) {
			return value.trim();
		}

		CheVal isYear(Cell value) {
			if (getYear(value) == null) {
				errMsg.add(new ErrorDescription(info, sourceValue, "value is larger than " + Calendar.getInstance().get(Calendar.YEAR)
				        + " or less than " + MPXLImporter.FROM_YEAR + ")"));
			}
			return this;
		}

		Integer getYear(Cell cell) {
			if (cell.getType() == CellType.DATE) {
				DateCell dateCell = (DateCell) cell;
				Date dateVal = dateCell.getDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateVal);
				return cal.get(Calendar.YEAR);
			} else {
				String value = cell.getContents().trim();
				int v = Util.convertStringToInt(value);
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);
				if (!value.equals("") && v == 0) {
					Date dv = getDate(cell);
					if (dv != null) {
						Calendar cal = Calendar.getInstance();
						cal.setTime(dv);
						return cal.get(Calendar.YEAR);
					} else {
						return null;
					}
				} else {
					if (v > currentYear || v < MPXLImporter.FROM_YEAR) {
						return null;
					} else {
						return v;
					}
				}
			}

		}

		CheVal isKufType(String value) {
			if (getKufType(value) == KufType.UNKNOWN) {
				errMsg.add(new ErrorDescription(info, sourceValue, "Kuf value is not correct "));
			}
			return this;
		}

		KufType getKufType(String value) {
			int v = Util.convertStringToInt(value);
			return KufType.getType(v);
		}

		CheVal isReferenceValue(ReferenceDAO<? extends IAppEntity, UUID> dao, String value) {
			IAppEntity entity = getEntity(dao, value);
			if (entity == null) {
				errMsg.add(new ErrorDescription(info, sourceValue, "is not reference value "));
			}
			return this;
		}

		IAppEntity getEntity(ReferenceDAO<? extends IAppEntity, UUID> dao, String value) {
			if (value != null && !value.equals("")) {
				IAppEntity entity = dao.findByName(value);
				if (entity != null) {
					return entity;
				}
			}
			return null;
		}

		CheVal isValueOfList(String trueVal, String falseVal, String value) {
			if (getBoolean(trueVal, falseVal, value) == null) {
				errMsg.add(new ErrorDescription(info, sourceValue, "value is incorrect, it should be match to: \"" + trueVal + "\" or \"" + falseVal
				        + "\""));
			}
			return this;
		}

		Boolean getBoolean(String trueVal, String falseVal, String value) {
			String nv = value.trim();
			if (trueVal.trim().equalsIgnoreCase(nv)) {
				return true;
			} else if (falseVal.equalsIgnoreCase(nv)) {
				return false;
			} else {
				return null;
			}
		}

		CheVal isIntNumber(String value) {
			if (getInt(value) == null) {
				errMsg.add(new ErrorDescription(info, sourceValue, "value of the cell is not allowed to convert to int number "));
			}

			return this;

		}

		Integer getInt(String value) {
			value = value.replace("\u00A0", "");
			int intVal = 0;
			if (value.equals("0") || value.equals("0,0") || value.equals("0,00")) {

			} else {
				intVal = Util.convertStringToInt(value);
				if (intVal == 0) {
					return null;
				}
			}
			return intVal;
		}

		CheVal isFloatNumber(String value) {
			if (getFloat(value) == null) {
				errMsg.add(new ErrorDescription(info, sourceValue, "value of the cell is not allowed to convert to float number"));
			}
			return this;
		}

		Float getFloat(String value) {
			value = value.replace("\u00A0", "");
			float floatVal = 0;
			if (value.equals("0") || value.equals("0,0") || value.equals("0,00")) {

			} else {
				floatVal = Util.convertStringToFloat(value);
				if (floatVal == 0) {
					return null;
				}
			}
			return floatVal;
		}

		CheVal isDate(Cell dCell) {
			if (getDate(dCell) == null) {
				errMsg.add(new ErrorDescription(info, sourceValue, "value of the cell is not allowed to convert to data"));
			}
			return this;
		}

		Date getDate(Cell dCell) {
			Date dateVal = null;
			if (dCell.getType() == CellType.DATE) {
				DateCell dateCell = (DateCell) dCell;
				dateVal = dateCell.getDate();
			} else {
				try {
					String acceptancedateStr = dCell.getContents().trim().replace("/", ".").replace("-", ".");
					switch (acceptancedateStr.length()) {
					case 4:
						dateVal = new SimpleDateFormat("yyyy").parse(acceptancedateStr);
						break;
					case 8:
						dateVal = new SimpleDateFormat("dd.MM.yy").parse(acceptancedateStr);
						break;
					case 10:

						dateVal = new SimpleDateFormat("dd.MM.yyyy").parse(acceptancedateStr);
					}
				} catch (ParseException e) {

				}
			}
			return dateVal;
		}

		public List<ErrorDescription> getErr() {
			errMsg.removeAll(Arrays.asList(null, ""));
			if (errMsg.size() > 0) {
				return errMsg;
			} else {
				return null;
			}
		}
	}

	public class ErrorDescription {
		String cellInfo;
		String cellValue;
		String errorMsg;

		public ErrorDescription(String cellInfo, String cellValue, String errorMsg) {
			this.cellInfo = cellInfo;
			this.cellValue = cellValue;
			this.errorMsg = errorMsg;
		}

		@Override
		public String toString() {
			return "column=" + cellInfo + ", value=" + cellValue + ", error=" + errorMsg;
		}

	}

}
