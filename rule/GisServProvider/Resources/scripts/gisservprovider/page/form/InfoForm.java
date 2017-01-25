package gisservprovider.page.form;

import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._Session;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;

import municipalproperty.page.form.AbstractMunicipalPropertyForm;

public class InfoForm extends AbstractMunicipalPropertyForm {
	
	@Override
	public void doGET(_Session session, WebFormData formData) {
		addValue("getbycoord", "/" + getCurrentAppEnv().appName + "/rest/gis/getbycoord/0");
		addValue("getbystreet", "/" + getCurrentAppEnv().appName + "/rest/gis/getbystreet/0/0");
		_ActionBar actionBar = new _ActionBar(session, getCurrentAppEnv());
		actionBar.addAction(new _Action("Close", "just close the form", _ActionType.CLOSE));
		addContent(actionBar);
	}
}
