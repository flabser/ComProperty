package gisservprovider.page.form;

import com.exponentus.env.Environment;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;

import kz.flabs.util.Util;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import municipalproperty.page.form.AbstractMunicipalPropertyForm;

public class InfoForm extends AbstractMunicipalPropertyForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		addValue("hostname", Environment.hostName);
		addValue("port", Environment.httpPort);
		addValue("tmpdir", Environment.tmpDir);
		addValue("orgname", Environment.orgName);
		addValue("database", Environment.adminApplication.getDataBase().getInfo());
		addValue("devmode", Environment.isDevMode());
		addValue("officeframe", Environment.getOfficeFrameDir());
		addValue("kernel", Environment.getKernelDir());
		addValue("starttime", Util.convertDataTimeToString(Environment.startTime));
		addValue("devmode", Environment.isDevMode());
		_ActionBar actionBar = new _ActionBar(session);
		actionBar.addAction(new _Action("Close", "Just close the form", _ActionType.CLOSE));
		addContent(actionBar);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
