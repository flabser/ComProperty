package accountant.page.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import accountant.page.action.MPXLImporter.ErrorDescription;

public class Outcome {
	public int processed;
	public int skipped;
	public Map<Integer, List<List<ErrorDescription>>> sheetErr = new HashMap<Integer, List<List<ErrorDescription>>>();

}
