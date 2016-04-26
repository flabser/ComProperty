package gisservprovider.page.form;

import com.exponentus.env.Environment;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;

import municipalproperty.page.form.AbstractMunicipalPropertyForm;

public class InfoForm extends AbstractMunicipalPropertyForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		addValue("getbycoord", Environment.getFullHostName() + "/" + session.getAppEnv().appName + "/rest/gis/getbycoord/0");
		addValue("getbystreet", Environment.getFullHostName() + "/" + session.getAppEnv().appName + "/rest/gis/getbystreet/0/0");
		_ActionBar actionBar = new _ActionBar(session);
		actionBar.addAction(new _Action("Close", "Just close the form", _ActionType.CLOSE));
		addContent(actionBar);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
