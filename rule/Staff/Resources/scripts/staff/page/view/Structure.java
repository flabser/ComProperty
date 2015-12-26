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
import staff.model.Employer;
import staff.model.Organization;

public class Structure extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		println(formData);
		StructureDAO dao = new StructureDAO(session);
		Organization<_IPOJOObject> org = dao.findPrimaryOrg();
		List<Department> deps = org.getDepartments();
		List<Employer> emps = org.getEmployers();
		List<_IXMLContent> content = new ArrayList<_IXMLContent>();
		content.add(new _POJOObjectWrapper(org));
		content.add(new _POJOListWrapper(emps, emps.size()));
		content.add(new _POJOListWrapper(deps, deps.size()));

		setContent(content);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
