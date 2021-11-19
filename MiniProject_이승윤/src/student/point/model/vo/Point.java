package student.point.model.vo;

public class Point {
	private int studentNo;
	private int termNo;
	private double point;
	
	public Point() {
	}
	
	
	public Point(int studentNo, int termNo, double point) {
		super();
		this.studentNo = studentNo;
		this.termNo = termNo;
		this.point = point;
	}


	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public int getTermNo() {
		return termNo;
	}

	public void setTermNo(int termNo) {
		this.termNo = termNo;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Point [studentNo=" + studentNo + ", termNo=" + termNo + ", point=" + point + "]";
	}
	
}
