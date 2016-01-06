package municipalproperty.page.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import kz.flabs.dataengine.DatabaseUtil;
import kz.flabs.dataengine.IDBConnectionPool;
import kz.flabs.dataengine.IDatabase;
import kz.flabs.dataengine.h2.Database;
import kz.nextbase.script._Session;
import municipalproperty.model.constants.KufType;

public class ReportUtil {

	public static HashMap<String, List<KufType>> getCat() {
		HashMap<String, List<KufType>> cat = new HashMap<String, List<KufType>>();
		ArrayList<KufType> peList = new ArrayList<KufType>(Arrays.asList(KufType.FURNITURE, KufType.ANIMALS, KufType.SPORT_EQUIPMENT, KufType.OTHERS,
		        KufType.SHARE_BLOCK, KufType.EQUITY));
		cat.put("personalstateCat", peList);
		cat.put("personalestate_report", peList);

		ArrayList<KufType> eList = new ArrayList<KufType>(Arrays.asList(KufType.SCHOOL_EQUIPMENT, KufType.OFFICE_EQUIPMENT,
		        KufType.COMPUTER_EQUIPMENT, KufType.MEDICAL_EQUIPMENT, KufType.COOK_EQUIPMENT, KufType.EQUIPMENT_OF_CIVIL_DEFENSE));
		cat.put("equipmentCat", eList);
		cat.put("equipment_report", eList);

		ArrayList<KufType> reList = new ArrayList<KufType>(Arrays.asList(KufType.BUILDINGS, KufType.ROOMS, KufType.STRUCTURES,
		        KufType.RESIDENTIAL_OBJECTS, KufType.LAND, KufType.MONUMENT));
		cat.put("realestateCat", reList);
		cat.put("realestate_report", reList);

		ArrayList<KufType> tList = new ArrayList<KufType>(Arrays.asList(KufType.CARGO, KufType.BUS, KufType.TROLLEYBUS, KufType.TRAM,
		        KufType.WATER_TRANSPORT, KufType.HOSPITAL_TRANSPORT, KufType.SPECIAL_EQUIPMENT));
		cat.put("transportCat", tList);
		cat.put("transport_report", tList);

		ArrayList<KufType> eiList = new ArrayList<KufType>(Arrays.asList(KufType.BILLBOARD, KufType.COLUMNS, KufType.ELECTRIC_NETWORKS,
		        KufType.THERMAL_NETWORKS, KufType.GAS, KufType.WATER_SYSTEM, KufType.DRAIN, KufType.ROAD, KufType.PARKING));
		cat.put("specialconstructionsCat", eiList);
		cat.put("engineeringInfrastructure_report", eiList);

		ArrayList<KufType> soList = new ArrayList<KufType>(Arrays.asList(KufType.BOMBPROOF, KufType.FACTORY, KufType.COMBINES, KufType.AIRPORT,
		        KufType.LAND, KufType.TRANSITIONS));
		cat.put("strategicobjectsCat", soList);
		cat.put("strategicobjects_report", soList);

		ArrayList<KufType> esList = new ArrayList<KufType>(Arrays.asList(KufType.SHARE_BLOCK, KufType.EQUITY));
		cat.put("engineeringInfrastructureCat", esList);

		return cat;
	}

	@Deprecated
	public static HashMap<String, String[]> getCategories() {
		HashMap<String, String[]> cat = new HashMap<String, String[]>();
		String[] personalSForm = { "furniture", "animals", "sportsequipment", "others", "shareblocks", "equity" };
		cat.put("personalstateCat", personalSForm);
		cat.put("personalestate_report", personalSForm);

		String[] equipmentForms = { "schoolequipment", "officeequipment", "computerequipment", "medicalequipment", "cookequipment",
		        "equipmentofcivildefense" };
		cat.put("equipmentCat", equipmentForms);
		cat.put("equipment_report", equipmentForms);

		String[] realSForm = { "buildings", "rooms", "structures", "residentialobjects", "land", "monument" };
		cat.put("realestateCat", realSForm);
		cat.put("realestate_report", realSForm);

		String[] transportForms = { "automobile", "cargo", "bus", "trolleybus", "tram", "watertransport", "hospitaltransport", "specialequipment",
		        "motorcycle" };
		cat.put("transportCat", transportForms);
		cat.put("transport_report", transportForms);

		String[] engInfStructForms = { "billboard", "columns", "electricnetworks", "thermalnetworks", "gas", "watersystem", "drain", "road",
		        "parking" };
		cat.put("specialconstructionsCat", engInfStructForms);
		cat.put("engineeringInfrastructure_report", engInfStructForms);

		String[] stratObjForms = { "bombproof", "factory", "combines", "airport", "land", "transitions" };
		cat.put("strategicobjectsCat", stratObjForms);
		cat.put("strategicobjects_report", stratObjForms);

		String[] engStrucForms = { "shareblocks", "equity" };
		cat.put("engineeringInfrastructureCat", engStrucForms);

		return cat;
	}

	public static String getOrgName(_Session ses, int code) {
		IDatabase db = ses.getCurrentDatabase().getBaseObject();

		IDBConnectionPool dbPool = db.getConnectionPool();
		Connection conn = dbPool.getConnection();
		try {

			conn.setAutoCommit(false);
			Statement s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			String sql = "select m.viewtext from maindocs as m where ( m.form='kgp' or m.form='kgu' or m.form='ao' "
			        + "or m.form='too' or m.form='subsidiaries' ) and m.docid=" + code;
			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				return rs.getString(1);
			}

			rs.close();
			s.close();
			conn.commit();

		} catch (SQLException e) {
			DatabaseUtil.errorPrint(db.getDbID(), e);
		} catch (Exception e) {
			Database.logger.errorLogEntry(e);
		} finally {
			dbPool.returnConnection(conn);
		}
		return "";

	}

	public static int getIntValue(ResultSet rs, String filedName) {
		try {
			return Integer.parseInt(rs.getString(filedName));
		} catch (NumberFormatException e1) {
			return 0;
		} catch (SQLException e) {
			return 0;
		}
	}

	public static long getLongValue(ResultSet rs, String filedName) {
		try {
			return Long.parseLong(rs.getString(filedName));
		} catch (NumberFormatException e1) {
			return 0;
		} catch (SQLException e) {
			return 0;
		}
	}

}
