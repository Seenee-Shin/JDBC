package edu.kh.jdbc.project.professor.model.vo;

public class Professor {
	
	private String professorCode;
	private String facultyCode;
	private String deptOffice;
	private String pOffice;
	private String pName;
	private String pPhone;
	
	public Professor() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Professor(String pName, String pOffice ,String pPhone) {
		super();
		this.pName = pName;
		this.pOffice = pOffice;
		this.pPhone = pPhone;
	}



	public Professor(String deptOffice) {
		super();
		this.deptOffice = deptOffice;
	}


	public String getDeptOffice() {
		return deptOffice;
	}

	public void setDeptOffice(String deptOffice) {
		this.deptOffice = deptOffice;
	}

	public String getpOffice() {
		return pOffice;
	}

	public void setpOffice(String pOffice) {
		this.pOffice = pOffice;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}


	public String getpPhone() {
		return pPhone;
	}

	public void setpPhone(String pPhone) {
		this.pPhone = pPhone;
	}

	public String getProfessorCode() {
		return professorCode;
	}

	public void setProfessorCode(String professorCode) {
		this.professorCode = professorCode;
	}

	public String getFacultyCode() {
		return facultyCode;
	}

	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
	}

	@Override
	public String toString() {
		return "Professor [professorCode=" + professorCode + ", facultyCode=" + facultyCode + ", deptOffice="
				+ deptOffice + ", pOffice=" + pOffice + ", pName=" + pName + ", pPhone=" + pPhone + "]";
	}
	

}
