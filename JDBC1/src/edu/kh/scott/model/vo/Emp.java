package edu.kh.scott.model.vo;

import java.sql.Date;

public class Emp {
	//VO : Value Object 
	//DB와 연결하여 사용하는 경우 조회된 결과의 한 행의 정보를 기옥하는 용도로 사용 
	
	private int empNo;
	private String eName;
	private String job;
	private int mgr;
	private Date hireDate;
	//참조형 date 변수 : java.sql.Date 
	private int sal;
	private int comm;
	private int deptNo;
	
	public Emp() {}

	public Emp(int empNo, String eName, String job, int mgr, Date hireDate, int sal, int comm, int deptNo) {
		super();
		this.empNo = empNo;
		this.eName = eName;
		this.job = job;
		this.mgr = mgr;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
		this.deptNo = deptNo;
	}
	// case 3 사용자 정보 입력 생성자
	public Emp( String eName, String job, int mgr, int sal, int comm, int deptNo) {
		super();
		this.eName = eName;
		this.job = job;
		this.mgr = mgr;
		this.sal = sal;
		this.comm = comm;
		this.deptNo = deptNo;
	}
	
	// case 4 생성자  
	public Emp(int empNo, String job, int sal, int comm) {
		super();
		this.empNo = empNo;
		this.job = job;
		this.sal = sal;
		this.comm = comm;
	}
	
	//case6 생성자 
	public Emp(int empNo, String eName) {
		super();
		this.empNo = empNo;
		this.eName = eName;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}



	public int getEmpNo() {
		return empNo;
	}

	public String geteName() {
		return eName;
	}




	public void seteName(String eName) {
		this.eName = eName;
	}




	public String getJob() {
		return job;
	}




	public void setJob(String job) {
		this.job = job;
	}




	public int getMgr() {
		return mgr;
	}




	public void setMgr(int mgr) {
		this.mgr = mgr;
	}




	public Date getHireDate() {
		return hireDate;
	}




	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}




	public int getSal() {
		return sal;
	}




	public void setSal(int sal) {
		this.sal = sal;
	}




	public int getComm() {
		return comm;
	}




	public void setComm(int comm) {
		this.comm = comm;
	}




	public int getDeptNo() {
		return deptNo;
	}




	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}


	@Override
	public String toString() {
		return "Emp [empNo=" + empNo + ", eName=" + eName + ", job=" + job + ", mgr=" + mgr + ", hireDate=" + hireDate
				+ ", sal=" + sal + ", comm=" + comm + ", dept=" + deptNo + "]";
	}
}

