package accountant.page.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import reference.dao.CountryDAO;
import reference.dao.DistrictDAO;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReferenceDAO;
import reference.dao.RegionDAO;

public class FileChecker {

	public Map<Integer, List<List<String>>> check(Sheet sheet, _Session ses, boolean stopIfWrong) {
		Map<Integer, List<List<String>>> sheetErr = new HashMap<Integer, List<List<String>>>();
		for (int i = 1; i < sheet.getRows(); i++) {
			String kof = sheet.getCell(0, i).getContents().trim();
			String kuf = sheet.getCell(1, i).getContents().trim();
			String invNumber = sheet.getCell(2, i).getContents().trim();
			String name = sheet.getCell(3, i).getContents().trim();
			String propertyCode = sheet.getCell(4, i).getContents().trim();
			Cell acceptanceDateCell = sheet.getCell(5, i);
			String country = sheet.getCell(6, i).getContents().trim();
			String region = sheet.getCell(7, i).getContents().trim();
			String district = sheet.getCell(8, i).getContents().trim();
			String address = sheet.getCell(9, i).getContents().trim();
			String originalCost = sheet.getCell(10, i).getContents().trim();
			String cumulativeDepreciation = sheet.getCell(11, i).getContents().trim();
			String impairmentLoss = sheet.getCell(12, i).getContents().trim();
			String balanceCost = sheet.getCell(13, i).getContents().trim();
			String revaluationAmount = sheet.getCell(14, i).getContents().trim();
			String residualCost = sheet.getCell(15, i).getContents().trim();
			String receiptBasisinBalance = sheet.getCell(16, i).getContents().trim();
			String model = sheet.getCell(17, i).getContents().trim();
			String commissioningYear = sheet.getCell(18, i).getContents().trim();
			String acquisitionYear = sheet.getCell(19, i).getContents().trim();
			String isReadyToOperation = sheet.getCell(20, i).getContents().trim();

			List<List<String>> rowErr = new ArrayList<List<String>>();
			rowErr.add(new CheVal("1, КОФ, " + kof).isNotEmpty(kof).getErrMsg());
			rowErr.add(new CheVal("2, КУФ, " + kuf).isNotEmpty(kuf).getErrMsg());
			rowErr.add(new CheVal("3, Инвентарный номер, " + invNumber).isNotEmpty(invNumber).getErrMsg());
			rowErr.add(new CheVal("4, Наименование, " + name).isNotEmpty(name).getErrMsg());
			rowErr.add(new CheVal("5, Код права на имущество, " + propertyCode).isNotEmpty(propertyCode)
					.isReferenceValue(new PropertyCodeDAO(ses), propertyCode).getErrMsg());
			rowErr.add(new CheVal("6, Дата принятия на баланс, " + acceptanceDateCell.getContents())
					.isDate(acceptanceDateCell).getErrMsg());
			rowErr.add(new CheVal("7, Страна, " + country).isReferenceValue(new CountryDAO(ses), country).getErrMsg());
			String preparedRegion = region.replace("г.а.", "").replace("область", "").trim();
			rowErr.add(new CheVal("8, Регион, " + region).isNotEmpty(preparedRegion)
					.isReferenceValue(new RegionDAO(ses), preparedRegion).getErrMsg());
			String preparedDistrict = district.replace("район", "").trim();
			rowErr.add(new CheVal("9, Район, " + district).isNotEmpty(preparedDistrict)
					.isReferenceValue(new DistrictDAO(ses), preparedDistrict).getErrMsg());
			rowErr.add(new CheVal("10, Адрес, " + address).isNotEmpty(address).getErrMsg());
			rowErr.add(new CheVal("11, Первоначальная стоимость, " + originalCost).isNotEmpty(originalCost)
					.isFloatNumber(originalCost).getErrMsg());
			rowErr.add(new CheVal("12, Накопленная амортизация, " + cumulativeDepreciation)
					.isNotEmpty(cumulativeDepreciation).isFloatNumber(cumulativeDepreciation).getErrMsg());
			rowErr.add(new CheVal("13").isOkAnyway().getErrMsg());
			rowErr.add(new CheVal("14, Балансовая стоимость, " + balanceCost).isNotEmpty(balanceCost)
					.isFloatNumber(balanceCost).getErrMsg());
			rowErr.add(new CheVal("15").isOkAnyway().getErrMsg());
			rowErr.add(new CheVal("16, Балансовая стоимость после переоценки, " + residualCost).isNotEmpty(residualCost)
					.isFloatNumber(residualCost).getErrMsg());
			rowErr.add(new CheVal("17, Основание поступления на баланс, " + receiptBasisinBalance)
					.isNotEmpty(receiptBasisinBalance).getErrMsg());
			rowErr.add(new CheVal("18").isOkAnyway().getErrMsg());
			rowErr.add(new CheVal("19, Год ввода в эксплуатацию, " + commissioningYear).isNotEmpty(commissioningYear)
					.getErrMsg());
			rowErr.add(new CheVal("20, Год приобретения, " + acquisitionYear).isNotEmpty(acquisitionYear).getErrMsg());
			rowErr.add(new CheVal("21, Сведения о годности в эксплуатацию, " + isReadyToOperation)
					.isNotEmpty(isReadyToOperation).getErrMsg());

			rowErr.removeAll(Arrays.asList(null, ""));
			System.out.println("------" + (i + 1) + "---------");
			for (List<String> v : rowErr) {
				// System.out.println("------" + (i + 1) + "---------");
				for (String vq : v) {
					System.out.println(vq);
				}

			}

			sheetErr.put(i + 1, rowErr);
			if (rowErr.size() > 0 && stopIfWrong) {
				break;
			}

		}
		return sheetErr;

	}

	class CheVal {
		private List<String> errMsg = new ArrayList<String>();;
		String info;

		CheVal(String column) {
			info = column;
		}

		CheVal isOkAnyway() {
			return this;
		}

		CheVal isNotEmpty(String value) {
			if (value == null || value.equals("")) {
				errMsg.add("value is null (" + info + ")");
			}
			return this;
		}

		CheVal isReferenceValue(ReferenceDAO<? extends IAppEntity, UUID> dao, String value) {
			if (value != null && !value.equals("")) {
				IAppEntity entity = dao.findByName(value);
				if (entity == null) {
					errMsg.add("is not reference value (" + info + ")");
				}

			}
			return this;
		}

		CheVal isFloatNumber(String value) {
			if (!value.equals("0") && Util.convertStringToFloat(value.replace("\u00A0", "")) == 0) {
				errMsg.add("value of the cell is not allowed to convert to float number (" + info + ")");
			}
			return this;

		}

		CheVal isDate(Cell dCell) {
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
			if (dateVal == null) {
				errMsg.add("value of the cell is not allowed to convert to data (" + info + ")");

			}
			return this;
		}

		public List<String> getErrMsg() {
			errMsg.removeAll(Arrays.asList(null, ""));
			if (errMsg.size() > 0) {
				return errMsg;
			} else {
				return null;
			}
		}
	}

}
