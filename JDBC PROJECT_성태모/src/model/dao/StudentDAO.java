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

import model.vo.Admin;
import model.vo.Student;


public class StudentDAO {
	
	// 필드
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public StudentDAO() {

		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("student-sql.xml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/** 관리자 계정 로그인
	 * @param adminId
	 * @param adminPw
	 * @param conn
	 * @return admin(값이 있으면 로그인 성공)
	 * @throws Exception
	 */
	public Admin adminLogin(String adminId, String adminPw, Connection conn) throws Exception {
		
		Admin admin = null;
		
		try {

			String sql = prop.getProperty("adminlogin");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, adminId);
			pstmt.setString(2, adminPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String adminId2 = rs.getString("ADMIN_ID");
				String adminPw2 = rs.getString("ADMIN_PW");
				String adminNm = rs.getString("ADMIN_NAME");
				
				admin = new Admin(adminId2, adminPw2, adminNm); 
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return admin;
	}


	
	

	/** 학생 전체 목록 조회
	 * @return studentList(학생목록)
	 * @throws Exception
	 */
	public List<Student> studentAllSelect(Connection conn) throws Exception{
		
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			String sql = prop.getProperty("studentAllList");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int stNo = rs.getInt("STUDENT_NO");
				int stGd = rs.getInt("STUDENT_GRADE");
				String stNm = rs.getString("STUDENT_NAME");
				String stSs = rs.getString("STUDENT_SSN");
				String stAd = rs.getString("STUDENT_ADDRESS");
				String stDp = rs.getString("DEPARTMENT_NAME");
				
				Student stu = new Student(stNo, stGd, stNm, stSs, stAd, stDp);
				
				studentList.add(stu);
				
			}
			
			
		}finally {
			close(rs);
			close(stmt);
			
		}
		
		return studentList;
	}
	

	
	/** 학과별 학생 조회
	 * @param dpName
	 * @param conn
	 * @return student
	 * @throws Exception
	 */
	public List<Student> studentDpSelect(String dpName, Connection conn) throws Exception {

		List<Student> student = new ArrayList<Student>();
		
		try {
			String sql = prop.getProperty("studentDpSelect");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dpName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int stNo = rs.getInt("STUDENT_NO");
				int stGd = rs.getInt("STUDENT_GRADE");
				String stNm = rs.getString("STUDENT_NAME");
				String stSs = rs.getString("STUDENT_SSN");
				String stAd = rs.getString("STUDENT_ADDRESS");
				String stDp = rs.getString("DEPARTMENT_NAME");
				
				Student stu = new Student(stNo, stGd, stNm, stSs, stAd, stDp);
				
				student.add(stu);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return student;
	}
	
	
	/** 학번으로 학생 검색
	 * @param search
	 * @param conn
	 * @return student(검색된 학생)
	 * @throws Exception
	 */
	public Student studentSearch(int search, Connection conn) throws Exception{
		
		Student student = null;
		
		try {
			String sql = prop.getProperty("studentSearch");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, search);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int stNo = rs.getInt("STUDENT_NO");
				int stGd = rs.getInt("STUDENT_GRADE");
				String stNm = rs.getString("STUDENT_NAME");
				String stSs = rs.getString("STUDENT_SSN");
				String stAd = rs.getString("STUDENT_ADDRESS");
				String stDp = rs.getString("DEPARTMENT_NAME");
				
				student = new Student(stNo, stGd, stNm, stSs, stAd, stDp);

			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return student;
	}

	
	
	/** 이름으로 학생 검색
	 * @param search2
	 * @param conn
	 * @return student(검색된 학생들)
	 * @throws Exception
	 */
	public List<Student> studentSearch2(String search2, Connection conn) throws Exception{
		
		List<Student> student = new ArrayList<Student>();
		
		try {
			String sql = prop.getProperty("studentSearch2");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, search2);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int stNo = rs.getInt("STUDENT_NO");
				int stGd = rs.getInt("STUDENT_GRADE");
				String stNm = rs.getString("STUDENT_NAME");
				String stSs = rs.getString("STUDENT_SSN");
				String stAd = rs.getString("STUDENT_ADDRESS");
				String stDp = rs.getString("DEPARTMENT_NAME");
				
				Student stu = new Student(stNo, stGd, stNm, stSs, stAd, stDp);
				
				student.add(stu);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return student;
	}
	
	
	/** 학생 추가
	 * @param student
	 * @param conn
	 * @return result(1이면 성공)
	 * @throws Exception
	 */
	public int studentInsert(Student student, Connection conn) throws Exception{
		
		int result = 0;
		
		try {	
			String sql = prop.getProperty("studentInsert");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, student.getStudentNO());
			pstmt.setInt(2, student.getStudentGrade());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getStudentSsn());
			pstmt.setString(5, student.getStudentAddress());
			pstmt.setString(6, student.getStudentDepartment());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}

		return result;
		
	}
	
	
	/** 학생 정보 수정
	 * @param student
	 * @param conn
	 * @return result (1성공)
	 * @throws Exception
	 */
	public int studentUpdate(Student student, String stdName, Connection conn) throws Exception{
		int result = 0;
		
		try {	
			String sql = prop.getProperty("studentUpdate");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, student.getStudentNO());
			pstmt.setInt(2, student.getStudentGrade());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getStudentSsn());
			pstmt.setString(5, student.getStudentAddress());
			pstmt.setString(6, student.getStudentDepartment());
			pstmt.setString(7, stdName);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}

		return result;
	}
	
	
	/** 학생 존재여부 확인
	 * @param stdName
	 * @param conn
	 * @return result(1존재)
	 * @throws Exception
	 */
	public int nameCheck(String stdName, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("nameCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, stdName);
			
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

	/** 학생 정보 삭제
	 * @param stdName
	 * @param conn
	 * @return result(1성공)
	 * @throws Exception
	 */
	public int studentDelete(String stdName, Connection conn) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("studentDelete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, stdName);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	




}
