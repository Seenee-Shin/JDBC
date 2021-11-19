package model.dao;

import static common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.vo.Grade;

public class GradeDAO {
	
	// 필드
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public GradeDAO() {

		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("grade-sql.xml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/** 학생 성적 검색
	 * @param studentNo
	 * @param conn
	 * @return grade(검색된 학생 성적)
	 * @throws Exception
	 */
	public Grade gradeSearch(int studentNo, Connection conn) throws Exception{

		Grade grade = null;
		
		try {
			String sql = prop.getProperty("gradeSearch");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studentNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int studentNo2 = rs.getInt("STUDENT_NO");
				String studentNm = rs.getString("STUDENT_NAME");
				double pointG1 = rs.getDouble("POINT_G1");
				double pointG2 = rs.getDouble("POINT_G2");
				double pointAvg = rs.getDouble("POINT_AVG");
				String rate = rs.getString("RATE");
				
				grade = new Grade(studentNo2, studentNm, pointG1, pointG2, pointAvg, rate);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return grade;
	}

	
	/** 성적 전체 조회
	 * @param conn
	 * @return gradeList
	 * @throws Exception
	 */
	public List<Grade> gradeAll(Connection conn) throws Exception{
		
		List<Grade> gradeList = new ArrayList<Grade>();
		
		try {
			String sql = prop.getProperty("gradeSearchALL");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int studentNo2 = rs.getInt("STUDENT_NO");
				String studentNm = rs.getString("STUDENT_NAME");
				double pointG1 = rs.getDouble("POINT_G1");
				double pointG2 = rs.getDouble("POINT_G2");
				double pointAvg = rs.getDouble("POINT_AVG");
				String rate = rs.getString("RATE");
			
				Grade grade = new Grade(studentNo2, studentNm, pointG1, pointG2, pointAvg, rate);
				
				gradeList.add(grade);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return gradeList;
	}

	/** 장학금 대상자
	 * @param conn
	 * @return gradeList
	 * @throws Exception
	 */
	public List<Grade> award(Connection conn) throws Exception {
		
		List<Grade> gradeList = new ArrayList<Grade>();
		
		try {
			String sql = prop.getProperty("gradeAward");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int studentNo2 = rs.getInt("STUDENT_NO");
				String studentNm = rs.getString("STUDENT_NAME");
				double pointG1 = rs.getDouble("POINT_G1");
				double pointG2 = rs.getDouble("POINT_G2");
				double pointAvg = rs.getDouble("POINT_AVG");
				String rate = rs.getString("RATE");
			
				Grade grade = new Grade(studentNo2, studentNm, pointG1, pointG2, pointAvg, rate);
				
				gradeList.add(grade);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return gradeList;
	}

	/** 성적 추가
	 * @param grade
	 * @param conn
	 * @return result(1성공)
	 * @throws Exception
	 */
	public int gradeInsert(Grade grade, Connection conn) throws Exception{
		
		int result = 0;
		
		try {	
			String sql = prop.getProperty("gradeInsert");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, grade.getStudentNo());
			pstmt.setString(2, grade.getStudentNm());
			pstmt.setDouble(3, grade.getPoint1());
			pstmt.setDouble(4, grade.getPoint2());
			pstmt.setDouble(5, grade.getAvg());
			pstmt.setString(6, grade.getRate());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}

		return result;
	}

	
	/** 입력한 학번 정보로 학생 확인
	 * @param check
	 * @param conn
	 * @return (1성공)
	 * @throws Exception
	 */
	public int checkNo(int check, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("checkNo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, check);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	/** 성적 수정
	 * @param grade
	 * @param conn
	 * @return result(1성공)
	 * @throws Exception
	 */
	public int gradeUpdate(int check, Grade grade, Connection conn) throws Exception{
		
		int result = 0;
		
		try {	
			String sql = prop.getProperty("gradeUpdate");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, grade.getStudentNo());
			pstmt.setString(2, grade.getStudentNm());
			pstmt.setDouble(3, grade.getPoint1());
			pstmt.setDouble(4, grade.getPoint2());
			pstmt.setDouble(5, grade.getAvg());
			pstmt.setString(6, grade.getRate());
			pstmt.setInt(7, check);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}

		return result;
	}
	
	

}
