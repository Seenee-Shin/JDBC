package student.model.dao;

import static student.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import student.model.vo.Student;

public class StudentDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = new Properties();
	public StudentDAO() {
		try {
			prop.loadFromXML(new FileInputStream("student.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 학생 정보 추가
	 * @param student
	 * @param conn
	 * @return result (성공 1, 실패 0)
	 * @throws Exception
	 */
	public int insertStudent(Student student, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertStudent");
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, student.getStudentNo());
			pstmt.setInt(2, student.getStudentGrade());
			pstmt.setString(3, student.getStudentNm());
			pstmt.setString(4, student.getStudentSSN());
			pstmt.setString(5, student.getStudentAddress());
			pstmt.setString(6, student.getDepartmentNm());
			
			result = pstmt.executeUpdate();
			
		}finally{
			close(pstmt);
		}
		
		return result;
	}
	/** 학생 정보 수정 DAO
	 * @param input
	 * @param conn
	 * @return result(성공 1, 실패 0)
	 * @throws Exception
	 */
	public int updateStudent(int studentNo, String input1, String input2, Connection conn) throws Exception {
		
		int result = 0;
				
		try {
			String sql = "UPDATE MN_STUDENT SET "+input1+" = '"+input2+"' WHERE STUDENT_NO = "+studentNo;
			
			stmt  = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
		}finally {
			close(stmt);
		}
		
		return result;
	}
	/** 학생 정보 전체 조회 DAO
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Student> selectAll(Connection conn) throws Exception {

		List<Student> list = new ArrayList();
		
		try{
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int sequenceNo = rs.getInt(1);
				int studentNo = rs.getInt(2);
				int studentGrade = rs.getInt(3);
				String studentNm = rs.getString(4);
				String studentSSN = rs.getString(5);
				String studentAddress = rs.getString(6);
				String departmentNm = rs.getString(7);
				
				Student student = new Student(sequenceNo, studentNo, studentGrade, studentNm, studentSSN, studentAddress, departmentNm);
				
				list.add(student);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}
	/** 학생 정보 상세 조회 DAO
	 * @param studentNo
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public Student selectOne(int studentNo, Connection conn) throws Exception {
		
		Student student = null;
		
		try{
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studentNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int sequenceNo = rs.getInt(1);
				int studentNo1 = rs.getInt(2);
				int studentGrade = rs.getInt(3);
				String studentNm = rs.getString(4);
				String studentSSN = rs.getString(5);
				String studentAddress = rs.getString(6);
				String departmentNm = rs.getString(7);
				
				student = new Student(sequenceNo, studentNo1, studentGrade, studentNm, studentSSN, studentAddress, departmentNm);
				
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return student;
	}
	
	/** 학생 정보 검색
	 * @param searchKey
	 * @param searchValue
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Student> searchStudent(int searchKey, String searchValue, Connection conn) throws Exception{
		
		List<Student> list = new ArrayList();
		
		try {
			String sql1= prop.getProperty("searchStudent");
		
			String sql2= null;
			
			switch(searchKey) {
			case 1 : sql2 = prop.getProperty("No"); break;
			case 2 : sql2 = prop.getProperty("Grade"); break;
			case 3 : sql2 = prop.getProperty("Name"); break;
			case 4 : sql2 = prop.getProperty("Address"); break;
			case 5 : sql2 = prop.getProperty("Department"); break;
			}
			
			String sql = sql1+sql2;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchValue);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int sequenceNo = rs.getInt(1);
				int studentNo1 = rs.getInt(2);
				int studentGrade = rs.getInt(3);
				String studentNm = rs.getString(4);
				String studentSSN = rs.getString(5);
				String studentAddress = rs.getString(6);
				String departmentNm = rs.getString(7);
				
				Student student = new Student(sequenceNo, studentNo1, studentGrade, studentNm, studentSSN, studentAddress, departmentNm);
				
				list.add(student);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	/** 학생 정보 삭제 DAO
	 * @param studentNo
	 * @param conn
	 * @return result (성공 1, 삭제 0)
	 * @throws Exception
	 */
	public int deleteStudent(int studentNo, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteStudent");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studentNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
