package staff.init;

public class AppConst {
	public static String DEFAULT_URL = "p?id=organization-view";
	public static String FT_INDEX_SCOPE = "[{\"tableName\":\"orgs\",\"fieldNames\":"
	        + "[\"name\",\"localized_name\"],\"daoImpl\":\"staff.dao.OrganizationDAO\"},{\"tableName\":\"employees\",\"fieldNames\":"
	        + "[\"name\"],\"daoImpl\":\"staff.dao.EmployeeDAO\"}]";
}
