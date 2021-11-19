package edu.kh.student.model.vo;

public class Student {

	private int sequenceNo; // 구분 번호 (시퀀스)
	private int studentNo; // 학생 번호
	private int studentGrade; //학년
	private String studentName; //학생 이름
	private String studentSSN; // 주민번호
	private String studentAddress; // 주소
	private String departmentName; // 학과명
	private int professorNo; // 담당교수 번호
	private String pfName; // 교수명  >> TB_PROFESSOR와 조인하여 조회할 컬럼 이름
	
	
	// 기본 생성자
	public Student() {}

	
	// 1. 전체 학생 조회
	public Student(int sequenceNo, int studentNo, int studentGrade, String studentName, String studentSSN,
			String studentAddress, String departmentName, int professorNo) {
		super();
		this.sequenceNo = sequenceNo;
		this.studentNo = studentNo;
		this.studentGrade = studentGrade;
		this.studentName = studentName;
		this.studentSSN = studentSSN;
		this.studentAddress = studentAddress;
		this.departmentName = departmentName;
		this.professorNo = professorNo;
	}

	
	// 2. 학생 등록 추가
	
	public Student(int studentNo, int studentGrade, String studentName, String studentSSN, String studentAddress,
			String departmentName, int professorNo) {
		super();
		this.studentNo = studentNo;
		this.studentGrade = studentGrade;
		this.studentName = studentName;
		this.studentSSN = studentSSN;
		this.studentAddress = studentAddress;
		this.departmentName = departmentName;
		this.professorNo = professorNo;
	}

	
	// 3. 학생 정보 수정(학과명, 교수번호만)
	public Student(int studentNo, String departmentName, int professorNo) {
		super();
		this.studentNo = studentNo;
		this.departmentName = departmentName;
		this.professorNo = professorNo;
	}
	

	
	
	
	// getter/setter
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


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public int getProfessorNo() {
		return professorNo;
	}


	public void setProfessorNo(int professorNo) {
		this.professorNo = professorNo;
	}


	public String getPfName() {
		return pfName;
	}


	public void setPfName(String pfName) {
		this.pfName = pfName;
	}



	@Override
	public String toString() {
		return "Student [sequenceNo=" + sequenceNo + ", studentNo=" + studentNo + ", studentGrade=" + studentGrade
				+ ", studentName=" + studentName + ", studentSSN=" + studentSSN + ", studentAddress=" + studentAddress
				+ ", departmentName=" + departmentName + ", professorNo=" + professorNo + ", pfName=" + pfName + "]";
	}


	
	
	
	
	
	
	
}
