package monitoring.handler.test_new_handler

import java.util.Map

import kz.flabs.exception.DocumentAccessException;
import kz.flabs.exception.QueryException;
import kz.flabs.exception.RuleException;
import kz.flabs.parser.QueryFormulaParserException;
import kz.nextbase.script._Session;
import kz.nextbase.script.events._DoHandler;
import kz.nextbase.script.events._DoScheduledHandler


class Trigger extends _DoScheduledHandler {
	
	@Override
	public int doHandler(_Session session) {
		
		return 0;
	}
}
