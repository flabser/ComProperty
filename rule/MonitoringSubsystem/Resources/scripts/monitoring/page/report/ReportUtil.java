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
import kz.nextbase.script._Session;

public class ReportUtil {

	public static HashMap<String, String[]> getCategories() {
		HashMap<String, String[]> cat = new HashMap<String, String[]>();
		cat.put("personalstateCat",
				new String[] { "furniture", "animals", "sportsequipment", "others", "shareblocks", "equity" });
		cat.put("equipmentCat", new String[] { "schoolequipment", "officeequipment", "computerequipment",
				"medicalequipment", "cookequipment", "equipmentofcivildefense" });
		cat.put("realestateCat",
				new String[] { "buildings", "rooms", "structures", "residentialobjects", "land", "monument" });
		cat.put("transportCat", new String[] { "automobile", "cargo", "bus", "trolleybus", "tram", "watertransport",
				"hospitaltransport", "specialequipment", "motorcycle" });
		cat.put("strategicobjectsCat", new String[] { "billboard", "columns", "electricnetworks", "thermalnetworks",
				"gas", "watersystem", "drain", "road", "parking" });
		cat.put("specialconstructionsCat",
				new String[] { "bombproof", "factory", "combines", "airport", "land", "transitions" });

		cat.put("engineeringInfrastructureCat", new String[] { "shareblocks", "equity" });
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
