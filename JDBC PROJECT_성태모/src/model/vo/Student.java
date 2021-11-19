package model.vo;

public class Student {
	
	private int sequenceNo;				// 시퀀스
	private int studentNO; 				// 학번
	private int studentGrade;			// 학년
	private String studentName;			// 이름
	private String studentSsn;			// 주민번호
	private String studentAddress;		// 주소
	private String studentDepartment;	// 학과
	
	public Student() {}
	
	
	public Student(int studentNO, int studentGrade, String studentName, String studentSsn, String studentAddress,
			String studentDepartment) {
		super();
		this.studentNO = studentNO;
		this.studentGrade = studentGrade;
		this.studentName = studentName;
		this.studentSsn = studentSsn;
		this.studentAddress = studentAddress;
		this.studentDepartment = studentDepartment;
	}


	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public int getStudentNO() {
		return studentNO;
	}

	public void setStudentNO(int studentNO) {
		this.studentNO = studentNO;
	}

	public int getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(int studentGrade) {
		this.studentGrade = studentGrade;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSsn() {
		return studentSsn;
	}

	public void setStudentSsn(String studentSsn) {
		this.studentSsn = studentSsn;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentDepartment() {
		return studentDepartment;
	}

	public void setStudentDepartment(String studentDepartment) {
		this.studentDepartment = studentDepartment;
	}

	@Override
	public String toString() {
		return "Student [sequenceNo=" + sequenceNo + ", studentNO=" + studentNO + ", studentGrade=" + studentGrade
				+ ", studentName=" + studentName + ", studentSsn=" + studentSsn + ", studentAddress=" + studentAddress
				+ ", studentDepartment=" + studentDepartment + "]";
	}

	

}
