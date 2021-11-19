package model.vo;

public class Department {
	
	private int departmentNo; // 학과번호
	private String departmentNm; // 학과이름
	private String category; // 학과계열
	
	public Department() {}

	public Department(int departmentNo, String departmentNm, String category) {
		this.departmentNo = departmentNo;
		this.departmentNm = departmentNm;
		this.category = category;
	}

	public int getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(int departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getDepartmentNm() {
		return departmentNm;
	}

	public void setDepartmentNm(String departmentNm) {
		this.departmentNm = departmentNm;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Department [departmentNo=" + departmentNo + ", departmentNm=" + departmentNm + ", category=" + category
				+ "]";
	}
	
}
