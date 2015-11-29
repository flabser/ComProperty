package monitoring.page.report;

public class ReportRowEntity {
	private Integer countNum;
	private Integer primaryCostNum;
	private Integer depreciationNum;
	private Integer bookvalueNum;
	private Integer reassessmentCostNum;

	private String category;
	private String subCategory;

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
			return Integer.toString(countNum);
		} else {
			return count;
		}
	}

	public String getPrimaryCost() {
		if (primaryCost == null) {
			return Integer.toString(primaryCostNum);
		} else {
			return primaryCost;
		}
	}

	public void setPrimaryCost(String primaryCost) {
		this.primaryCost = primaryCost;
	}

	public String getDepreciation() {
		if (depreciation == null) {
			return Integer.toString(depreciationNum);
		} else {
			return depreciation;
		}
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}

	public String getBookvalue() {
		if (bookvalue == null) {
			return Integer.toString(bookvalueNum);
		} else {
			return bookvalue;
		}
	}

	public void setBookvalue(String bookvalue) {
		this.bookvalue = bookvalue;
	}

	public String getReassessmentCost() {
		if (reassessmentCost == null) {
			return Integer.toString(reassessmentCostNum);
		} else {
			return reassessmentCost;
		}
	}

	public void setReassessmentCost(String reassessmentCost) {
		this.reassessmentCost = reassessmentCost;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public Integer getPrimaryCostNum() {
		return primaryCostNum;
	}

	public void setPrimaryCostNum(Integer primaryCostNum) {
		this.primaryCostNum = primaryCostNum;
	}

	public Integer getDepreciationNum() {
		return depreciationNum;
	}

	public void setDepreciationNum(Integer depreciationNum) {
		this.depreciationNum = depreciationNum;
	}

	public Integer getBookvalueNum() {
		return bookvalueNum;
	}

	public void setBookvalueNum(Integer bookvalueNum) {
		this.bookvalueNum = bookvalueNum;
	}

	public Integer getReassessmentCostNum() {
		return reassessmentCostNum;
	}

	public void setReassessmentCostNum(Integer reassessmentCostNum) {
		this.reassessmentCostNum = reassessmentCostNum;
	}

}
