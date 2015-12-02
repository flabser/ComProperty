package monitoring.page.report;

public class ReportRowEntity {
	private long countNum;
	private long primaryCostNum;
	private long depreciationNum;
	private long bookvalueNum;
	private long reassessmentCostNum;

	private String category = "";
	private String subCategory = "";

	private String count;
	private String primaryCost;
	private String depreciation;
	private String bookvalue;
	private String reassessmentCost;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public void setCount(String c) {
		this.count = c;
	}

	public String getCount() {
		if (count == null) {
			return Long.toString(countNum);
		} else {
			return count;
		}
	}

	public String getPrimaryCost() {
		if (primaryCost == null) {
			return Long.toString(primaryCostNum);
		} else {
			return primaryCost;
		}
	}

	public void setPrimaryCost(String primaryCost) {
		this.primaryCost = primaryCost;
	}

	public String getDepreciation() {
		if (depreciation == null) {
			return Long.toString(depreciationNum);
		} else {
			return depreciation;
		}
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}

	public String getBookvalue() {
		if (bookvalue == null) {
			return Long.toString(bookvalueNum);
		} else {
			return bookvalue;
		}
	}

	public void setBookvalue(String bookvalue) {
		this.bookvalue = bookvalue;
	}

	public String getReassessmentCost() {
		if (reassessmentCost == null) {
			return Long.toString(reassessmentCostNum);
		} else {
			return reassessmentCost;
		}
	}

	public void setReassessmentCost(String reassessmentCost) {
		this.reassessmentCost = reassessmentCost;
	}

	public Long getCountNum() {
		return countNum;
	}

	public void setCountNum(Long countNum) {
		this.countNum = countNum;
	}

	public Long getPrimaryCostNum() {
		return primaryCostNum;
	}

	public void setPrimaryCostNum(Long primaryCostNum) {
		this.primaryCostNum = primaryCostNum;
	}

	public Long getDepreciationNum() {
		return depreciationNum;
	}

	public void setDepreciationNum(Long depreciationNum) {
		this.depreciationNum = depreciationNum;
	}

	public Long getBookvalueNum() {
		return bookvalueNum;
	}

	public void setBookvalueNum(Long bookvalueNum) {
		this.bookvalueNum = bookvalueNum;
	}

	public Long getReassessmentCostNum() {
		return reassessmentCostNum;
	}

	public void setReassessmentCostNum(Integer reassessmentCostNum) {
		this.reassessmentCostNum = reassessmentCostNum;
	}

}
