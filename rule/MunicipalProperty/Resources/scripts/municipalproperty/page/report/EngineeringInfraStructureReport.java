package municipalproperty.page.report;

import java.sql.ResultSet;
import java.sql.SQLException;

import kz.flabs.dataengine.DatabaseUtil;
import kz.flabs.dataengine.h2.Database;

public class EngineeringInfraStructureReport extends PropertyReport {

	@Override
	protected EngineeringInfraStructureBean getDocument(ResultSet rs) {
		EngineeringInfraStructureBean object = new EngineeringInfraStructureBean();
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
					object.setAssignment(rs.getString("value"));
				} else if (name.equals("propertycode_name")) {
					object.setPropertycodename(rs.getString("value"));
				} else if (name.equals("material")) {
					object.setMaterial(ReportUtil.getIntValue(rs, "valueasnumber"));
				} else if (name.equals("landtotalarea")) {
					object.setTotalarea(rs.getString("value"));
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
		return new EngineeringInfraStructureBean();
	}

}
