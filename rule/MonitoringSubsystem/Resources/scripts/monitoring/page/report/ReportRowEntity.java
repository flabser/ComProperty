package monitoring.page.report;

public class ReportRowEntity {
	private String category;
	private String subCategory;
	private int count;
	private int primaryCost;
	private int depreciation;
	private int bookvalue;
	private int reassessmentCost;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrimaryCost() {
		return primaryCost;
	}

	public void setPrimaryCost(int primaryCost) {
		this.primaryCost = primaryCost;
	}

	public int getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(int depreciation) {
		this.depreciation = depreciation;
	}

	public int getBookvalue() {
		return bookvalue;
	}

	public void setBookvalue(int bookvalue) {
		this.bookvalue = bookvalue;
	}

	public int getReassessmentCost() {
		return reassessmentCost;
	}

	public void setReassessmentCost(int reassessmentCost) {
		this.reassessmentCost = reassessmentCost;
	}

}
