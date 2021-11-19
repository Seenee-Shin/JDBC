package student.model.vo;

public class Student {
	/*
	 * SEQUENCE_NO NUMBER PRIMARY KEY,
	 * STUDENT_NO NUMBER NOT NULL,
	 * STUDENT_GRADE VARCHAR2(10) NOT NULL,
	 * STUDENT_NAME VARCHAR2(40) NOT NULL,
	 * STUDENT_SSN VARCHAR2(14) NOT NULL,
	 * STUDENT_ADDRESS VARCHAR2(200) NOT NULL,
	 * DEPARTMENT_NAME VARCHAR2(100) NOT NULL,
	 */
	private int sequenceNo;
	private int studentNo;
	private int studentGrade;
	private String studentNm;
	private String studentSSN;
	private String studentAddress;
	private String departmentNm;
	
	public Student() {
	}
	
	
	public Student(int studentNo, int studentGrade, String studentNm, String studentSSN, String studentAddress,
			String departmentNm) {
		super();
		this.studentNo = studentNo;
		this.studentGrade = studentGrade;
		this.studentNm = studentNm;
		this.studentSSN = studentSSN;
		this.studentAddress = studentAddress;
		this.departmentNm = departmentNm;
	}


	public Student(int sequenceNo, int studentNo, int studentGrade, String studentNm, String studentSSN,
			String studentAddress, String departmentNm) {
		super();
		this.sequenceNo = sequenceNo;
		this.studentNo = studentNo;
		this.studentGrade = studentGrade;
		this.studentNm = studentNm;
		this.studentSSN = studentSSN;
		this.studentAddress = studentAddress;
		this.departmentNm = departmentNm;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public int getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(int studentGrade) {
		this.studentGrade = studentGrade;
	}

	public String getStudentNm() {
		return studentNm;
	}

	public void setStudentNm(String studentNm) {
		this.studentNm = studentNm;
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

	public String getDepartmentNm() {
		return departmentNm;
	}

	public void setDepartmentNm(String departmentNm) {
		this.departmentNm = departmentNm;
	}

	@Override
	public String toString() {
		return "Student [sequenceNo=" + sequenceNo + ", studentNo=" + studentNo + ", studentGrade=" + studentGrade
				+ ", studentNm=" + studentNm + ", studentSSN=" + studentSSN + ", studentAddress=" + studentAddress
				+ ", departmentNm=" + departmentNm + "]";
	}
	
}
