package monitoring.page.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import kz.flabs.dataengine.DatabaseUtil;
import kz.flabs.dataengine.IDBConnectionPool;
import kz.flabs.dataengine.IDatabase;
import kz.flabs.dataengine.h2.Database;
import kz.lof.scripting._Session;

public class ReportUtil {

	public static HashMap<String, String[]> getCategories() {
		HashMap<String, String[]> cat = new HashMap<String, String[]>();
		String[] personalSForm = { "furniture", "animals", "sportsequipment", "others", "shareblocks", "equity" };
		cat.put("personalstateCat", personalSForm);
		cat.put("personalestate_report", personalSForm);

		String[] equipmentForms = { "schoolequipment", "officeequipment", "computerequipment", "medicalequipment",
				"cookequipment", "equipmentofcivildefense" };
		cat.put("equipmentCat", equipmentForms);
		cat.put("equipment_report", equipmentForms);

		String[] realSForm = { "buildings", "rooms", "structures", "residentialobjects", "land", "monument" };
		cat.put("realestateCat", realSForm);
		cat.put("realestate_report", realSForm);

		String[] transportForms = { "automobile", "cargo", "bus", "trolleybus", "tram", "watertransport",
				"hospitaltransport", "specialequipment", "motorcycle" };
		cat.put("transportCat", transportForms);
		cat.put("transport_report", transportForms);

		String[] engInfStructForms = { "billboard", "columns", "electricnetworks", "thermalnetworks", "gas",
				"watersystem", "drain", "road", "parking" };
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
