package accountant.page.form;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import kz.lof.scripting.POJOObjectAdapter;
import kz.lof.scripting._Session;
import accountant.page.action.MPXLImporter.ErrorDescription;

public class UploadedFile extends POJOObjectAdapter {
	public final static int JUST_UPLOADED = 1;
	public final static int CHECKED = 2;
	public final static int LOADED = 3;
	public final static int CHECKING_ERROR = 4;
	public final static int LOADING_ERROR = 5;

	private String name;
	private String balanceHolderName = "";
	private UUID balanceHolderId;
	private List<String[]> readers;
	private int status;
	private String localizedMsg = "";
	private Map<Integer, List<List<ErrorDescription>>> sheetErrs;

	public void setName(String name) {
		this.name = name;
	}

	public void setBalanceHolderName(String balanceHolderName) {
		this.balanceHolderName = balanceHolderName;
	}

	public void setBalanceHolderId(UUID balanceHolderId) {
		this.balanceHolderId = balanceHolderId;
	}

	public void setReaders(List<String[]> readers) {
		this.readers = readers;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setLocalizedMsg(String localizedErrorMsg) {
		this.localizedMsg = localizedErrorMsg;
	}

	public void setSheetErrs(Map<Integer, List<List<ErrorDescription>>> sheetErrs) {
		this.sheetErrs = sheetErrs;
	}

	public Map<Integer, List<List<ErrorDescription>>> geSheetErrs() {
		return sheetErrs;
	}

	@Override
	public String getURL() {
		return "Provider?id=get-attach&amp;fileid=" + name;
	}

	@Override
	public String getShortXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<name>" + name + "</name>");
		chunk.append("<status>" + status + "</status>");
		chunk.append("<balanceholdername>" + balanceHolderName + "</balanceholdername>");
		chunk.append("<balanceholderid>" + balanceHolderId + "</balanceholderid>");
		chunk.append("<readers>" + readers + "</readers>");
		/* chunk.append("<errormsg>" + localizedMsg + "</errormsg>"); */
		chunk.append("<msg>" + localizedMsg + "</msg>");
		chunk.append("<sheeterrs>");
		if (sheetErrs != null) {
			for (Entry<Integer, List<List<ErrorDescription>>> row : sheetErrs.entrySet()) {
				chunk.append("<row number=\"" + row.getKey() + "\">");
				for (List<ErrorDescription> colList : row.getValue()) {
					for (ErrorDescription val : colList) {
						chunk.append("<column>" + val.toString() + "</column>");
					}
				}
				chunk.append("</row>");
			}
		}
		chunk.append("</sheeterrs>");
		return chunk.toString();
	}

}