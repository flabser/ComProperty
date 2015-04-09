package monitoring.page

import java.util.Map;
import kz.nextbase.script.*;
import kz.nextbase.script.events._DoScript

class DoScript extends _DoScript {
	
	

	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		println(lang)
			def tag = new _Tag("root","")
			def resp = new _Tag("resp","resp val")
			tag.addTag(resp)
			def xml = new _XMLDocument(tag)
			setContent(xml);
		
	}

	

}
