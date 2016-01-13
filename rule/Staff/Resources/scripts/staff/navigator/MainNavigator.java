package staff.navigator;

import kz.nextbase.script.*;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;
import staff.dao.OrganizationLabelDAO;
import staff.dao.RoleDAO;
import staff.model.OrganizationLabel;
import staff.model.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kayra created 21-12-2015
 */

public class MainNavigator extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        List<_IXMLContent> list = new ArrayList<_IXMLContent>();

        _Tag currentTag = new _Tag("current");
        currentTag.setAttr("id", formData.getValueSilently("id"));
        currentTag.setAttr("entryid", formData.getValueSilently("entryid"));

        _Outline common_outline = new _Outline(getLocalizedWord("common_staff_data", lang), "common");
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("structure", lang), "structure"));
        _OutlineEntry employeeEntry = new _OutlineEntry(getLocalizedWord("employees", lang), "employees");
        for (Role role : new RoleDAO(session).findAll()) {
            employeeEntry.addEntry(new _OutlineEntry(getLocalizedWord(role.getName(), lang), getLocalizedWord("assigned", lang) + " : "
                    + getLocalizedWord(role.getName(), lang), role.getName(), "Provider?id=roles&docid=" + role.getId()));
        }
        common_outline.addEntry(employeeEntry);

        _OutlineEntry orgEntry = new _OutlineEntry(getLocalizedWord("organizations", lang), "organizations");
        for (OrganizationLabel label : new OrganizationLabelDAO(session).findAll()) {
            orgEntry.addEntry(new _OutlineEntry(getLocalizedWord(label.getName(), lang), getLocalizedWord("labeled", lang) + " : "
                    + getLocalizedWord(label.getName(), lang), label.getName(), "Provider?id=organization-labels&docid=" + label.getId()));
        }
        common_outline.addEntry(orgEntry);
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("roles", lang), "roles"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("organization_labels", lang), "organization-labels"));
        _Outline specific_outline = new _Outline(getLocalizedWord("specific_staff_data", lang), "specific");
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("contractors", lang), "contractors"));
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("individuals", lang), "individuals"));
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("legal_entities", lang), "legal-entities"));
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("responsible_persons", lang), "responsible-persons"));

        list.add(common_outline);
        list.add(specific_outline);

        setContent(new _XMLDocument(currentTag));
        setContent(list);
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {

    }
}