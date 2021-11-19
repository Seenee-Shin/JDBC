package edu.kh.jdbc.project.student.model.dao;

import static edu.kh.jdbc.project.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.project.student.model.vo.Student;

public class StudentDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public StudentDAO() {
	try {
		prop = new Properties();
		prop.loadFromXML(new FileInputStream("student-sql.xml"));
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	/**
	 * @param searchKey
	 * @param searchValue
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Student> studentInfo(int searchKey, String searchValue, Connection conn) throws Exception{
		
		List<Student> list =  new ArrayList<>();
		try {
			String sql1 = prop.getProperty("studentInfo");
			String sql2 = null;
			
			switch(searchKey){
			case 1 : sql2 = prop.getProperty("dept"); break;
			case 2 : sql2 = prop.getProperty("grade"); break;
			case 3 : sql2 = prop.getProperty("studentNo"); break;
			}
			String sql = sql1 + sql2;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,searchValue);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int studentNo = rs.getInt("STUDENT_NO");
				String grade = rs.getString("STUDENT_GRADE");
				String studentName = rs.getString("STUDENT_NAME");
				String studentSSN = rs.getString("STUDENT_SSN");
				String studentAddress =rs.getString("STUDENT_ADDRESS");
				String sDeptName =rs.getString("DEPARTMENT_NAME");
				
				Student student = new Student(studentNo, grade, studentName, studentSSN, studentAddress, sDeptName);
				
				list.add(student);
			
			
		}
		}finally {
			close(rs);
			close(pstmt);	
		}
		return list;
	}
	
	/**학생 정보 확인용
	 * @param inputNo
	 * @param inputName
	 * @param conn 
	 * @return result
	 */
	public int checkStudent(int inputNo, String inputName, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql =  prop.getProperty("studentInfo")+ prop.getProperty("checkStudent");
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,inputNo);
			pstmt.setString(2,inputName);
			
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

	/** 학생 정보 수정 dao
	 * @param inputNo
	 * @param inputName
	 * @param searchKey
	 * @param searchValue
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updateStudent(int inputNo, String inputName, int searchKey, String searchValue, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql1 = null;
			String sql2 = prop.getProperty("checkStudent");
			
			switch(searchKey){
			case 1 : sql1 = prop.getProperty("changeDept"); break;
			case 2 : sql1 = prop.getProperty("changeAddress"); break;
			}
			String sql = sql1 + sql2;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, inputNo);
			pstmt.setString(3, inputName);
		
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 신/편입생 정보 추가
	 * @param conn
	 * @param student
	 * @return result
	 * @throws Exception
	 */
	public int addStudent(Connection conn, Student student) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("addStudent");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, student.getGrade());
			pstmt.setString(2, student.getStudentName());
			pstmt.setString(3, student.getStudentSSN());
			pstmt.setString(4, student.getStudentAddress());
			pstmt.setString(5, student.getsDeptName());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/**퇴학생 정보 삭제 
	 * @param conn
	 * @param inputNo
	 * @param inputName
	 * @return result 
	 * @throws Exception
	 */
	public int deleteStudent(Connection conn, int inputNo, String inputName) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("deleteStudent") + prop.getProperty("checkStudent");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, inputNo);
			pstmt.setString(2,inputName);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}


}
