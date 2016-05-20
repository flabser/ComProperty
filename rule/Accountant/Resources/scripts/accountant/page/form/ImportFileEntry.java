package accountant.page.form;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.exponentus.scripting.POJOObjectAdapter;
import com.exponentus.scripting._Session;

import accountant.page.action.MPXLImporter.ErrorDescription;
import staff.model.Organization;

public class ImportFileEntry extends POJOObjectAdapter<UUID> {

	public final static int JUST_UPLOADED = 1;
	public final static int CHECKED = 2;
	public final static int LOADED = 3;
	public final static int CHECKING_ERROR = 4;
	public final static int LOADING_ERROR = 5;

	private String fileName = "";
	private Organization balanceHolder;
	private List<Long> readers;
	private int status;
	private boolean stopIfWrong;
	private boolean writeOff;
	private boolean isTransfer;
	private String localizedMsg = "";
	private Map<Integer, List<List<ErrorDescription>>> sheetErrs;
	private String orderFileName = "";
	private Organization recipient;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setBalanceHolder(Organization balanceHolder) {
		this.balanceHolder = balanceHolder;
	}

	public void setReaders(List<Long> listr) {
		this.readers = listr;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isStopIfWrong() {
		return stopIfWrong;
	}

	public void setStopIfWrong(boolean stopIfWrong) {
		this.stopIfWrong = stopIfWrong;
	}

	public boolean isWriteOff() {
		return writeOff;
	}

	public void setWriteOff(boolean writeOff) {
		this.writeOff = writeOff;
	}

	public boolean isTransfer() {
		return isTransfer;
	}

	public void setTransfer(boolean isTransfer) {
		this.isTransfer = isTransfer;
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
		return "Provider?id=update-file&amp;fileid=" + fileName;
	}

	public String getOrderFileName() {
		return orderFileName;
	}

	public void setOrderFileName(String orderFileName) {
		this.orderFileName = orderFileName;
	}

	public void setRecipient(Organization recipient) {
		this.recipient = recipient;
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<filename>" + fileName + "</filename>");
		chunk.append("<status>" + status + "</status>");
		if (balanceHolder != null) {
			chunk.append("<balanceholder id=\"" + balanceHolder.getId() + "\">" + balanceHolder.getName() + "</balanceholder>");
		} else {
			chunk.append("<balanceholder id=\"null\"></balanceholder>");
		}
		chunk.append("<stopifwrong>" + stopIfWrong + "</stopifwrong>");
		chunk.append("<writeoff>" + writeOff + "</writeoff>");
		chunk.append("<istransfer>" + isTransfer + "</istransfer>");
		chunk.append("<readers>" + readers + "</readers>");
		chunk.append("<orderfilename>" + orderFileName + "</orderfilename>");
		if (recipient != null) {
			chunk.append("<recipient id=\"" + recipient.getId() + "\">" + recipient.getName() + "</recipient>");
		} else {
			chunk.append("<recipient id=\"null\"></recipient>");
		}
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
