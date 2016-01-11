package staff.navigator;

import java.util.ArrayList;
import java.util.List;

import kz.nextbase.script._IXMLContent;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;
import staff.dao.OrganizationLabelDAO;
import staff.dao.RoleDAO;
import staff.model.OrganizationLabel;
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
		for (Role role : new RoleDAO(session).findAll()) {
			employeeEntry.addEntry(new _OutlineEntry(getLocalizedWord(role.getName(), lang), getLocalizedWord("assigned", lang) + " : "
			        + getLocalizedWord(role.getName(), lang), role.getName(), "Provider?id=role_view&docid=" + role.getId()));
		}
		common_outline.addEntry(employeeEntry);

		_OutlineEntry orgEntry = new _OutlineEntry(getLocalizedWord("organizations", lang), "organization_view");
		for (OrganizationLabel label : new OrganizationLabelDAO(session).findAll()) {
			orgEntry.addEntry(new _OutlineEntry(getLocalizedWord(label.getName(), lang), getLocalizedWord("labeled", lang) + " : "
			        + getLocalizedWord(label.getName(), lang), label.getName(), "Provider?id=organization_label_view&docid=" + label.getId()));
		}
		common_outline.addEntry(orgEntry);
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("roles", lang), "role_view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("organization_labels", lang), "organization_label_view"));
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
