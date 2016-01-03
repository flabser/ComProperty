package staff.page.view;

import java.util.ArrayList;
import java.util.List;

import kz.nextbase.script._IPOJOObject;
import kz.nextbase.script._IXMLContent;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.StructureDAO;
import staff.model.Department;
import staff.model.Employee;
import staff.model.Organization;

public class Structure extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		println(formData);
		List<_IXMLContent> content = new ArrayList<_IXMLContent>();
		StructureDAO dao = new StructureDAO(session);
		Organization<_IPOJOObject> org = dao.findPrimaryOrg();
		if (org != null) {
			List<Department> deps = org.getDepartments();
			List<Employee> emps = org.getEmployers();
			content.add(new _POJOObjectWrapper(org));
			content.add(new _POJOListWrapper(emps));
			content.add(new _POJOListWrapper(deps));
		} else {
			content.add(new _POJOListWrapper(getLocalizedWord("Primary organization has not been found", lang)));
		}
		setContent(content);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
