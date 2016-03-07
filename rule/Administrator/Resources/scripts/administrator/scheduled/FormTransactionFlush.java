package administrator.scheduled;

import kz.lof.scripting._Session;
import kz.lof.scripting.event._DoScheduledTask;

public class FormTransactionFlush extends _DoScheduledTask {

	@Override
	public void doEvery5Min(_Session session) {
		System.out.println("test 5");
	}

	@Override
	public void doEvery1Hour(_Session session) {

	}

	@Override
	public void doEveryDay(_Session session) {

	}

}
