package monitoring.page.report;

import java.sql.ResultSet;
import java.sql.SQLException;

import kz.flabs.dataengine.DatabaseUtil;
import kz.flabs.dataengine.h2.Database;

public class EquipmentReport extends PropertyReport {

	@Override
	protected EquipmentBean getDocument(ResultSet rs) {
		EquipmentBean object = new EquipmentBean();
		try {
			boolean nextOk = !rs.isAfterLast();
			int docId = rs.getInt(1);
			while (nextOk && rs.getInt(1) == docId) {
				String name = rs.getString("name");
				if (name.equals("invnumber")) {
					object.setInvnumber(rs.getString("value"));
				} else if (name.equals("originalcost")) {
					object.setOriginalcost(ReportUtil.getLongValue(rs, "value"));
				} else if (name.equals("description")) {
					object.setDescription(rs.getString("value"));
				} else if (name.equals("propertycode_name")) {
					object.setPropertycodename(rs.getString("value"));
				} else if (name.equals("objectname")) {
					object.setObjectname(rs.getString("value"));

				}
				// System.out.println(rs.getString("value") + " " + docId);
				nextOk = rs.next();
			}

		} catch (SQLException e) {
			DatabaseUtil.errorPrint(e);
		} catch (Exception e) {
			Database.logger.errorLogEntry(e);
		}
		return object;

	}

	@Override
	protected IPropertyBean getDocument() {
		return new EquipmentBean();
	}

}
