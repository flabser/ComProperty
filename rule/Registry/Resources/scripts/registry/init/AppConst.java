package registry.init;

public class AppConst {
	public static String MODULE_VERSION = "1.0";
	public static String NAME = "Registry";
	public static String NAME_ENG = "Registry";
	public static String NAME_RUS = "Реестродержатели";
	public static String NAME_KAZ = "Тіркеушілер";
	public static String DEFAULT_PAGE = "organization-view";
	public static String FT_INDEX_SCOPE = "[{\"tableName\":\"orgs\",\"fieldNames\":"
	        + "[\"name\"],\"daoImpl\":\"staff.dao.OrganizationDAO\"},{\"tableName\":\"employees\",\"fieldNames\":"
	        + "[\"name\"],\"daoImpl\":\"staff.dao.EmployeeDAO\"}]";
}
