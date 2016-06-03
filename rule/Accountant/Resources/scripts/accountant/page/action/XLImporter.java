package accountant.page.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.exponentus.common.model.Attachment;
import com.exponentus.dataengine.jpa.IAppEntity;
import com.exponentus.exception.SecureException;
import com.exponentus.scripting._Session;
import com.exponentus.server.Server;
import com.exponentus.user.IUser;
import com.exponentus.util.Util;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import municipalproperty.dao.OrderDAO;
import municipalproperty.dao.PrevBalanceHolderDAO;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Equipment;
import municipalproperty.model.Order;
import municipalproperty.model.Order.OrderStatus;
import municipalproperty.model.PersonalEstate;
import municipalproperty.model.PrevBalanceHolder;
import municipalproperty.model.Property;
import municipalproperty.model.RealEstate;
import municipalproperty.model.constants.PropertyStatusType;
import municipalproperty.model.util.PropertyFactory;
import reference.dao.CityDistrictDAO;
import reference.dao.CountryDAO;
import reference.dao.LocalityDAO;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;
import reference.dao.ReferenceDAO;
import reference.dao.RegionDAO;
import reference.dao.StreetDAO;
import reference.model.CityDistrict;
import reference.model.Country;
import reference.model.Locality;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import reference.model.Region;
import reference.model.Street;
import reference.model.constants.KufType;
import staff.dao.EmployeeDAO;
import staff.model.Employee;
import staff.model.Organization;

public class XLImporter {
	public final static int PROCESS = 100;
	public final static int CHECK = 99;
	public final static int FROM_YEAR = 1930;
	public Map<Integer, List<List<ErrorDescription>>> sheetErr = new HashMap<Integer, List<List<ErrorDescription>>>();

	private final static String defaultCity = "Алматы";
	private final static String undefinedStreet = "unknown";
	private final static String trueVal = "годен";
	private final static String falseVal = "не годен";

	private int mode;
	private _Session ses;
	private PropertyDAO propertyDao;
	private PrevBalanceHolderDAO pbhDao;
	private OrderDAO orderDao;
	private EmployeeDAO empDao;
	private Order order;
	private List<Property> propList = new ArrayList<Property>();

	public XLImporter(int mode) {
		this.mode = mode;
	}

	public Outcome process(Sheet sheet, _Session ses, boolean stopIfWrong, Organization bh, String[] readers, String uploadtype, String addFilePath) {
		int processed = 0, skipped = 0;

		/*
		 * long start = System.currentTimeMillis(); ForkJoinPool forkJoinPool =
		 * new ForkJoinPool(); ImportStream is = new ImportStream(sheet, 1,
		 * sheet.getRows(), ses, stopIfWrong, bh, readers, mode); sheetErr =
		 * (Map<Integer, List<List<ErrorDescription>>>) forkJoinPool.invoke(is);
		 * long stop = System.currentTimeMillis(); long diff = stop - start;
		 * System.out.println("New method: " + diff); return sheetErr;
		 */
		// return (Map<Integer, List<List<ErrorDescription>>>)
		// forkJoinPool.invoke(is);
		Outcome result = new Outcome();
		// long start = System.currentTimeMillis();
		this.ses = ses;
		propertyDao = new PropertyDAO(ses);
		pbhDao = new PrevBalanceHolderDAO(ses);
		empDao = new EmployeeDAO(ses);
		processed = 0;
		skipped = 0;

		if (mode == XLImporter.PROCESS) {
			if (uploadtype.equals("upload")) {
				mode = XLImporter.CHECK;

				result = process(sheet, ses, true, bh, readers, uploadtype, addFilePath);
				if (result.sheetErr.size() > 0) {
					Server.logger.errorLogEntry("file " + sheet.getName() + " is incorrect, check it before");
					return null;
				}
				mode = XLImporter.PROCESS;
			} else if (uploadtype.equals("transfer")) {
				orderDao = new OrderDAO(ses);
				order = composeNewOrder(addFilePath, "... о передаче имущества " + bh.getName());

			}
		}

		for (int i = 1; i < sheet.getRows(); i++) {
			XLRow row = new XLRow();
			row.kof = sheet.getCell(0, i).getContents().trim();

			if ("".equalsIgnoreCase(row.kof)) {
				if (checkForEmptyRow(sheet.getRow(i))) {
					continue;
				}
			}
			row.kuf = sheet.getCell(1, i).getContents().trim();
			row.invNumber = sheet.getCell(2, i).getContents().trim();
			row.name = sheet.getCell(3, i).getContents().trim();
			row.propertyCode = normalizeString(sheet.getCell(4, i).getContents());
			row.acceptanceDateCell = sheet.getCell(5, i);
			String country = normalizeString(sheet.getCell(6, i).getContents());
			if (country.equalsIgnoreCase("казахстан")) {
				country = "Kazakhstan";
			}
			row.country = country;
			String region = normalizeString(sheet.getCell(7, i).getContents());
			row.preparedRegion = normalizeString(region.replace("г.а.", "").replace("область", ""));
			String district = normalizeString(sheet.getCell(8, i).getContents());
			row.preparedDistrict = district.replaceAll("(район)|(р-н)", "").trim();
			row.address = sheet.getCell(9, i).getContents().trim();
			row.originalCostCell = sheet.getCell(10, i);
			row.cumulativeDepreciationCell = sheet.getCell(11, i);
			row.impairmentLossCell = sheet.getCell(12, i);
			row.balanceCostCell = sheet.getCell(13, i);
			row.revaluationAmountCell = sheet.getCell(14, i);
			row.residualCostCell = sheet.getCell(15, i);
			row.receiptBasisinBalance = normalizeString(sheet.getCell(16, i).getContents());
			row.model = sheet.getCell(17, i).getContents().trim();
			row.commissioningYear = sheet.getCell(18, i);
			row.acquisitionYear = sheet.getCell(19, i);
			row.isReadyToOperation = sheet.getCell(20, i).getContents().trim();

			if (mode == XLImporter.CHECK) {
				List<List<ErrorDescription>> rowErr = null;
				if (uploadtype.equals("upload")) {
					rowErr = preLoad(row, region, district);
				} else {
					rowErr = preProcess(row, bh);
				}

				rowErr.removeAll(Arrays.asList(null, ""));
				if (!rowErr.isEmpty()) {
					System.out.println("------" + (i + 1) + "---------");
					for (List<ErrorDescription> v : rowErr) {
						for (ErrorDescription vq : v) {
							System.out.println(vq);
						}
					}
					result.sheetErr.put(i + 1, rowErr);
					if (stopIfWrong) {
						break;
					}
				}
				processed++;
			} else if (mode == XLImporter.PROCESS) {
				if (uploadtype.equals("upload")) {
					if (load(row, bh, readers)) {
						processed++;
					}
				} else if (uploadtype.equals("writeoff")) {
					if (writeOff(row)) {
						processed++;
					}
				} else if (uploadtype.equals("transfer")) {
					if (transfer(row, bh, addFilePath, readers)) {
						processed++;
					}
				}
			}
		}
		// long stop = System.currentTimeMillis();
		// long diff = stop - start;
		// System.out.println("Old method: " + diff);
		if (uploadtype.equals("transfer") && mode == XLImporter.PROCESS) {
			order.setProperties(propList);
			Set<Long> allReaders = new HashSet<Long>();
			for (Property prop : propList) {
				allReaders.addAll(prop.getReaders());
			}
			order.setReaders(allReaders);
			try {
				orderDao.update(order);
			} catch (SecureException e) {
				Server.logger.errorLogEntry(e);
			}

		}

		Server.logger.debugLogEntry("processed=" + processed + ", skipped=" + skipped);
		result.processed = processed;
		result.skipped = skipped;
		return result;
	}

	private List<List<ErrorDescription>> preLoad(XLRow row, String region, String district) {
		List<List<ErrorDescription>> rowErr = new ArrayList<List<ErrorDescription>>();
		rowErr.add(new CheVal("1, КОФ", row.kof).isNotEmpty(row.kof).getErr());
		rowErr.add(new CheVal("2, КУФ", row.kuf).isNotEmpty(row.kuf).isKufType(row.kuf).getErr());
		rowErr.add(new CheVal("3, Инвентарный номер", row.invNumber).isNotEmpty(row.invNumber).getErr());
		List<Property> pList = propertyDao.findAllByInvNum(new CheVal("3, Инвентарный номер", row.invNumber).getString(row.invNumber));

		for (Property p : pList) {
			if (p.getObjectName().equalsIgnoreCase(row.name)) {
				rowErr.add(new CheVal("3, Инвентарный номер, наименование", row.invNumber + "," + row.name).isNotUniqueMessage().getErr());
				break;
			}

		}

		rowErr.add(new CheVal("4, Наименование", row.name).isNotEmpty(row.name).getErr());
		rowErr.add(new CheVal("5, Код права на имущество", row.propertyCode).isNotEmpty(row.propertyCode)
		        .isReferenceValue(new PropertyCodeDAO(ses), row.propertyCode).getErr());
		rowErr.add(new CheVal("6, Дата принятия на баланс", row.acceptanceDateCell.getContents()).isDate(row.acceptanceDateCell).getErr());
		rowErr.add(new CheVal("7, Страна", row.country).isReferenceValue(new CountryDAO(ses), row.country).getErr());
		rowErr.add(new CheVal("8, Регион", region).isNotEmpty(row.preparedRegion).isReferenceValue(new RegionDAO(ses), row.preparedRegion).getErr());
		rowErr.add(new CheVal("9, Район", district).isNotEmpty(row.preparedDistrict).isReferenceValue(new CityDistrictDAO(ses), row.preparedDistrict)
		        .getErr());
		rowErr.add(new CheVal("10, Адрес", row.address).isNotEmpty(row.address).getErr());
		rowErr.add(new CheVal("11, Первоначальная стоимость", row.originalCostCell.getContents()).isFloatNumber(row.originalCostCell).getErr());
		rowErr.add(new CheVal("12, Накопленная амортизация", row.cumulativeDepreciationCell.getContents())
		        .isFloatNumber(row.cumulativeDepreciationCell).getErr());
		rowErr.add(new CheVal("13, Убыток от обесценения", row.impairmentLossCell.getContents()).isFloatNumber(row.impairmentLossCell).getErr());
		rowErr.add(new CheVal("14, Балансовая стоимость", row.balanceCostCell.getContents()).isFloatNumber(row.balanceCostCell).getErr());
		rowErr.add(new CheVal("15, Сумма переоценки", row.revaluationAmountCell.getContents()).isFloatNumber(row.revaluationAmountCell).getErr());
		rowErr.add(new CheVal("16, Балансовая стоимость после переоценки", row.residualCostCell.getContents()).isFloatNumber(row.residualCostCell)
		        .getErr());
		rowErr.add(new CheVal("17, Основание поступления на баланс", row.receiptBasisinBalance)
		        .isReferenceValue(new ReceivingReasonDAO(ses), row.receiptBasisinBalance).getErr());
		rowErr.add(new CheVal("18 Модель", row.model).isOkAnyway().getErr());
		rowErr.add(new CheVal("19, Год ввода в эксплуатацию", row.commissioningYear.getContents()).isYear(row.commissioningYear).getErr());
		rowErr.add(new CheVal("20, Год приобретения ", row.acquisitionYear.getContents()).isYear(row.acquisitionYear).getErr());
		rowErr.add(new CheVal("21, Сведения о годности в эксплуатацию", row.isReadyToOperation).isNotEmpty(row.isReadyToOperation)
		        .isValueOfList(trueVal, falseVal, row.isReadyToOperation).getErr());

		return rowErr;

	}

	private boolean load(XLRow row, Organization bh, String[] readers) {
		CheVal cv = new CheVal();
		List<Property> pList = propertyDao.findAllByInvNum(cv.getString(row.invNumber));
		for (Property p : pList) {
			if (p.getBalanceHolder().equals(bh)) {
				return false;
			}
		}

		Property prop = PropertyFactory.getPropertyInstance(row.kuf);
		prop.setKof(row.kof);
		prop.setKuf(cv.getKufType(row.kuf));
		prop.setInvNumber(cv.getString(row.invNumber));
		prop.setObjectName(cv.getString(row.name));
		prop.setPropertyCode((PropertyCode) cv.getEntity(new PropertyCodeDAO(ses), row.propertyCode));
		prop.setAcceptanceDate(cv.getDate(row.acceptanceDateCell));
		reference.model.embedded.Address addr = new reference.model.embedded.Address();
		addr.setCountry((Country) cv.getEntity(new CountryDAO(ses), row.country));
		addr.setRegion((Region) cv.getEntity(new RegionDAO(ses), row.preparedRegion));
		addr.setCityDistrict((CityDistrict) cv.getEntity(new CityDistrictDAO(ses), row.preparedDistrict));
		addr.setAdditionalInfo(row.address);
		accountant.page.action.util.AddressParser parser = new accountant.page.action.util.AddressParser();
		parser.parseAddresses(addr, ses);
		prop.setPropertyStatusType(PropertyStatusType.ON_BALANCE);
		prop.setOriginalCost(cv.getFloat(row.originalCostCell));
		prop.setCumulativeDepreciation(cv.getFloat(row.cumulativeDepreciationCell));
		prop.setImpairmentLoss(cv.getFloat(row.impairmentLossCell));
		prop.setBalanceCost(cv.getFloat(row.balanceCostCell));
		prop.setRevaluationAmount(cv.getFloat(row.revaluationAmountCell));
		prop.setResidualCost(cv.getFloat(row.residualCostCell));
		prop.setReceivingReason((ReceivingReason) cv.getEntity(new ReceivingReasonDAO(ses), row.receiptBasisinBalance));

		if (prop instanceof PersonalEstate) {
			((PersonalEstate) prop).setModel(row.model);
		} else if (prop instanceof Equipment) {
			((Equipment) prop).setModel(row.model);
		} else if (prop instanceof RealEstate) {
			addr.setLocality((Locality) cv.getEntity(new LocalityDAO(ses), defaultCity));
			if (addr.getStreet() == null) {
				addr.setStreet((Street) cv.getEntity(new StreetDAO(ses), undefinedStreet));
				addr.setCoordinates("");
			}
			((RealEstate) prop).setAddress(addr);
		}
		prop.setCommissioningYear(cv.getYear(row.commissioningYear));
		prop.setAcquisitionYear(cv.getYear(row.acquisitionYear));
		prop.setReadyToUse(cv.getBoolean(trueVal, falseVal, row.isReadyToOperation));
		prop.setAuthor(ses.getUser());
		for (String uuid : readers) {
			Employee emp = empDao.findById(UUID.fromString(uuid));
			prop.addReaderEditor(emp.getUser());
		}

		prop.setBalanceHolder(bh);

		try {
			propertyDao.add(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

	private List<List<ErrorDescription>> preProcess(XLRow row, Organization bh) {
		List<List<ErrorDescription>> rowErr = new ArrayList<List<ErrorDescription>>();
		rowErr.add(new CheVal("3, Инвентарный номер", row.invNumber).isNotEmpty(row.invNumber).getErr());
		List<Property> pList = propertyDao.findAllByInvNum(new CheVal("3, Инвентарный номер", row.invNumber).getString(row.invNumber));

		if (pList.size() > 1) {
			rowErr.add(new CheVal("3, Инвентарный номер, наименование", row.invNumber + "," + row.name).isNotUniqueMessage().getErr());
		}
		return rowErr;

	}

	private boolean writeOff(XLRow row) {
		CheVal cv = new CheVal();
		List<Property> pList = propertyDao.findAllByInvNum(cv.getString(row.invNumber));
		Property prop = null;
		if (pList.size() > 1) {
			return false;
		} else {
			prop = pList.get(0);
			prop.setPropertyStatusType(PropertyStatusType.WRITTENOFF);
			try {
				propertyDao.update(prop);
				return true;
			} catch (Exception e) {
				Server.logger.errorLogEntry(e);
			}
		}
		return false;
	}

	private boolean transfer(XLRow row, Organization bh, String addFilePath, String[] readers) {

		CheVal cv = new CheVal();
		List<Property> pList = propertyDao.findAllByInvNum(cv.getString(row.invNumber));
		Property prop = null;
		if (pList.size() > 1) {
			return false;
		} else {
			prop = pList.get(0);
		}

		if (prop != null) {
			List<PrevBalanceHolder> pbhl = new ArrayList<PrevBalanceHolder>();
			PrevBalanceHolder pbh = new PrevBalanceHolder();
			pbh.setBalanceHolder(prop.getBalanceHolder());
			pbh.setProperty(prop);
			pbh.setReaders(prop.getReaders());
			pbh.setEditors(prop.getEditors());

			try {
				pbhDao.add(pbh);
				pbhl.add(pbhDao.findById(pbh.getId()));
				prop.setPrevBalanceHolders(pbhl);
				prop.setBalanceHolder(bh);
				prop.resetReaderEditor();
				for (String uuid : readers) {
					Employee emp = empDao.findById(UUID.fromString(uuid));
					prop.addReaderEditor(emp.getUser());
				}
			} catch (SecureException e) {
				Server.logger.errorLogEntry(e);
			}

			try {
				propertyDao.update(prop);
				propList.add(prop);
				return true;
			} catch (Exception e) {
				Server.logger.errorLogEntry(e);
			}

		}
		return false;
	}

	private Order composeNewOrder(String fn, String descr) {
		Order entity = new Order();
		IUser<Long> user = ses.getUser();
		entity.setDescription(descr);
		entity.setRegNumber("#");
		entity.setAppliedRegDate(new Date());
		entity.setOrderStatus(OrderStatus.ACTIVE);

		File file = new File(fn);
		InputStream is;
		try {
			is = new FileInputStream(file);
			Attachment att = new Attachment();
			att.setRealFileName(file.getName());
			att.setFile(IOUtils.toByteArray(is));
			entity.getAttachments().add(att);
		} catch (IOException e) {
			Server.logger.errorLogEntry(e);
		}

		entity.addReaderEditor(user);
		try {
			entity = orderDao.add(entity);
		} catch (SecureException e) {
			e.printStackTrace();
		}
		return entity;

	}

	private static boolean checkForEmptyRow(Cell[] cells) {
		String cellContent = "";
		boolean empty = true;
		for (int i = 0; i < cells.length; i++) {
			cellContent = cells[i].getContents().trim();
			if (!"".equalsIgnoreCase(cellContent)) {
				empty = false;
				return empty;
			}
		}
		return empty;
	}

	private String normalizeString(String cap) {
		cap = cap.trim().toLowerCase();
		cap = StringUtils.capitalize(cap);
		return cap;
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
			errMsg.add(new ErrorDescription(info, sourceValue, "значение не уникально"));
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
				errMsg.add(new ErrorDescription(info, sourceValue, "значение является null"));
			}
			return this;
		}

		String getString(String value) {
			return value.trim();
		}

		CheVal isYear(Cell value) {
			if (getYear(value) == null) {
				errMsg.add(new ErrorDescription(info, sourceValue,
				        "значение больше чем: " + Calendar.getInstance().get(Calendar.YEAR) + " or less than " + XLImporter.FROM_YEAR + ")"));
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
					if (v > currentYear || v < XLImporter.FROM_YEAR) {
						return null;
					} else {
						return v;
					}
				}
			}

		}

		CheVal isKufType(String value) {
			if (getKufType(value) == KufType.UNKNOWN) {
				errMsg.add(new ErrorDescription(info, sourceValue, "значение КУФ не корректно "));
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
				errMsg.add(new ErrorDescription(info, sourceValue, "значение не соответствует значению из справочника "));
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
				errMsg.add(new ErrorDescription(info, sourceValue,
				        "значение не корректно, оно должно быть равно: \"" + trueVal + "\" или \"" + falseVal + "\""));
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
				errMsg.add(new ErrorDescription(info, sourceValue, "значение не возможно преобразовать в число "));
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

		CheVal isFloatNumber(Cell fCell) {
			if (fCell.getType() == CellType.NUMBER || fCell.getType() == CellType.NUMBER_FORMULA || fCell.getType() == CellType.EMPTY
			        || NumberUtils.isDigits(fCell.getContents().trim().replaceAll("[.|,|\\s|\\uFFFD]", ""))) {
				return this;
			}
			errMsg.add(new ErrorDescription(info, sourceValue, "значение не возможно преобразовать в число (float)"));
			return this;
		}

		CheVal isFloatNumber(String value) {
			if (getFloat(value) == null) {
				errMsg.add(new ErrorDescription(info, sourceValue, "значение не возможно преобразовать в число (float)"));
			}
			return this;
		}

		Float getFloat(Cell fCell) {
			String cellValue = fCell.getContents().trim().replaceAll("[\\s|\\uFFFD]", "");
			if (cellValue.contains(".")) {
				cellValue = cellValue.replace(",", "");
			} else {
				cellValue = cellValue.replace(",", ".");
			}
			if (fCell.getType() == CellType.NUMBER || fCell.getType() == CellType.NUMBER_FORMULA) {
				return (float) ((NumberCell) fCell).getValue();
			}
			if (fCell.getType() == CellType.EMPTY) {
				return Float.valueOf(0);
			} else {
				return NumberUtils.toFloat(cellValue, 0);
			}
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
				errMsg.add(new ErrorDescription(info, sourceValue, "значение не возможно преобразовать в дату"));
			}
			return this;
		}

		Date getDate(Cell dCell) {
			Date dateVal = null;
			if (dCell.getType() == CellType.DATE) {
				DateCell dateCell = (DateCell) dCell;
				dateVal = dateCell.getDate();
			} else {
				String val = dCell.getContents();
				try {

					dateVal = DateUtils.parseDate(val, "yyyy", "dd.MM.yy", "dd.MM.yyyy", "dd.MM.yy hh:mm:ss", "dd.MM.yyyy hh:mm:ss", "yyyy.MM.dd",
					        "yyyy.MM.dd hh:mm:ss");
					/*
					 * String acceptancedateStr =
					 * dCell.getContents().trim().replace("/", ".").replace("-",
					 * "."); switch (acceptancedateStr.length()) { case 4:
					 * dateVal = new
					 * SimpleDateFormat("yyyy").parse(acceptancedateStr); break;
					 * case 8: dateVal = new
					 * SimpleDateFormat("dd.MM.yy").parse(acceptancedateStr);
					 * break; case 10:
					 * 
					 * dateVal = new
					 * SimpleDateFormat("dd.MM.yyyy").parse(acceptancedateStr);
					 */
				} catch (ParseException e) {
					errMsg.add(new ErrorDescription(info, val, "значение не возможно преобразовать в дату"));
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
