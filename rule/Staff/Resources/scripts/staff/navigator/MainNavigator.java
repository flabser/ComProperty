package staff.navigator;

import java.util.ArrayList;
import java.util.List;

import kz.nextbase.script._IXMLContent;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;
import staff.dao.RoleDAO;
import staff.model.Role;

/**
 * @author Kayra created 21-12-2015
 */

public class MainNavigator extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		List<_IXMLContent> list = new ArrayList<_IXMLContent>();

		_Outline common_outline = new _Outline(getLocalizedWord("common_staff_data", lang), "common");
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("structure", lang), "structure_view"));
		_OutlineEntry employeeEntry = new _OutlineEntry(getLocalizedWord("employees", lang), "employee_view");
		RoleDAO dao = new RoleDAO(session);
		for (Role role : dao.findAll()) {
			employeeEntry.addEntry(new _OutlineEntry(role.getName(), getLocalizedWord("assigned", lang) + ": " + role.getName(), role.getName(),
			        "Provider?id=role_view&docid=" + role.getId()));
		}
		common_outline.addEntry(employeeEntry);
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("organizations", lang), "organization_view"));
		_OutlineEntry rolesEntry = new _OutlineEntry(getLocalizedWord("roles", lang), "role_view");
		common_outline.addEntry(rolesEntry);
		_Outline specific_outline = new _Outline(getLocalizedWord("specific_staff_data", lang), "specific");
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("contractors", lang), "contractor_view"));
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("individuals", lang), "individual_view"));
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("legal_entities", lang), "legal_entity_view"));
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("responsible_persons", lang), "responsible_person_view"));

		list.add(common_outline);
		list.add(specific_outline);

		setContent(list);

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
