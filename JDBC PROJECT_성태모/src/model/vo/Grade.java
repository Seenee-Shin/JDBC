package model.vo;

public class Grade {
	private int studentNo; // 학번
	private String studentNm; // 이름
	private double point1; // 1학기
	private double point2; // 2학기
	private double avg; // 평균 평점
	private String rate; // 평점 등급
	
	public Grade() {}

	public Grade(int studentNo, String studentNm, double point1, double point2, double avg, String rate) {
		super();
		this.studentNo = studentNo;
		this.studentNm = studentNm;
		this.point1 = point1;
		this.point2 = point2;
		this.avg = avg;
		this.rate = rate;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentNm() {
		return studentNm;
	}

	public void setStudentNm(String studentNm) {
		this.studentNm = studentNm;
	}

	public double getPoint1() {
		return point1;
	}

	public void setPoint1(double point1) {
		this.point1 = point1;
	}

	public double getPoint2() {
		return point2;
	}

	public void setPoint2(double point2) {
		this.point2 = point2;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Grade [studentNo=" + studentNo + ", studentNm=" + studentNm + ", point1=" + point1 + ", point2="
				+ point2 + ", avg=" + avg + ", rate=" + rate + "]";
	}

	
	
}
