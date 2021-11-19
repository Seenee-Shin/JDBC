package edu.kh.jdbc.project.student.model.vo;

public class Student {
	private int studentNo;
	private String grade;
	private String studentName;
	private String studentSSN;
	private String studentAddress;
	private String sDeptName;
	
	
	public Student() {
		// TODO Auto-generated constructor stub
	}


	public Student(int studentNo, String grade, String studentName, String studentSSN, String studentAddress,
			String sDeptName) {
		super();
		this.studentNo = studentNo;
		this.grade = grade;
		this.studentName = studentName;
		this.studentSSN = studentSSN;
		this.studentAddress = studentAddress;
		this.sDeptName = sDeptName;
	}


	public Student(String grade, String studentName, String studentSSN, String studentAddress, String sDeptName) {
		super();
		this.grade = grade;
		this.studentName = studentName;
		this.studentSSN = studentSSN;
		this.studentAddress = studentAddress;
		this.sDeptName = sDeptName;
	}


	public int getStudentNo() {
		return studentNo;
	}


	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getStudentSSN() {
		return studentSSN;
	}


	public void setStudentSSN(String studentSSN) {
		this.studentSSN = studentSSN;
	}


	public String getStudentAddress() {
		return studentAddress;
	}


	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}


	public String getsDeptName() {
		return sDeptName;
	}


	public void setsDeptName(String sDeptName) {
		this.sDeptName = sDeptName;
	}


	@Override
	public String toString() {
		return "Student [studentNo=" + studentNo + ", grade=" + grade + ", studentName=" + studentName + ", studentSSN="
				+ studentSSN + ", studentAddress=" + studentAddress + ", sDeptName=" + sDeptName + "]";
	}
	 

}
