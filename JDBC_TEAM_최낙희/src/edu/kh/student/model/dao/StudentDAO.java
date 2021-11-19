package edu.kh.student.model.dao;

import static edu.kh.student.common.StudentTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.student.model.vo.Student;

public class StudentDAO {

	//필드
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public StudentDAO() {
		
		try {
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("student-sql.xml"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 1. 전체 학생 조회 
	/** 전체 학생 조회 DAO
	 * @return list(학생정보)
	 * @throws Exception
	 */
	public List<Student> selectAll(Connection conn) throws Exception {
		
		List<Student> list = new ArrayList<Student>();
		
		try {
			
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int sequenceNo = rs.getInt("SEQUENCE_NO");
				int studentNo = rs.getInt("STUDENT_NO");
				int studentGrade = rs.getInt("STUDENT_GRADE");
				String studentName = rs.getString("STUDENT_NAME");
				String studentSSN = rs.getString("STUDENT_SSN");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				int professorNo = rs.getInt("PROFESSOR_NO");
				
				Student student = new Student(sequenceNo, studentNo, studentGrade, studentName, studentSSN, studentAddress, departmentName, professorNo);
				
				list.add(student);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}

		return list;
	}


	
	//2. 학생 등록 추가
	/** 학생 등록 추가 DAO
	 * @param student
	 * @param conn
	 * @return result (추가 성공: 1, 실패: 0)
	 * @throws Exception
	 */
	public int insertStudent(Student student, Connection conn) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertStudent");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, student.getStudentNo());
			pstmt.setInt(2, student.getStudentGrade());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getStudentSSN());
			pstmt.setString(5, student.getStudentAddress());
			pstmt.setString(6, student.getDepartmentName());
			pstmt.setInt(7, student.getProfessorNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			
			close(pstmt);
		}
		
		return result;
	}


	
	
	// 3. 학생 정보 수정(학과명, 교수번호만)
	/** 학생 정보 수정(학과명, 교수번호만) DAO
	 * @param student
	 * @param conn
	 * @return result(수정 성공:1 , 실패: 0)
	 * @throws Exception
	 */
	public int updateStudent(Student student, Connection conn) throws Exception{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateStudent");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, student.getDepartmentName());
			pstmt.setInt(2, student.getProfessorNo());
			pstmt.setInt(3, student.getStudentNo());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	
	// 4. 학생 정보 삭제 
	/** 학생 정보 삭제 DAO
	 * @param studentNo
	 * @param conn
	 * @return result(수정 성공:1 , 실패: 0)
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

	
	
	
	// 5. 학번으로 학생 정보 조회
	/** 학번으로 학생 정보 조회 DAO
	 * @param studentNo
	 * @param conn
	 * @return student(학생정보)
	 * @throws Exception
	 */
	public Student selectOne(int studentNo, Connection conn) throws Exception {
		
		Student student = null;
		
		try {
			
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studentNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int sequenceNo = rs.getInt("SEQUENCE_NO");
				int studentNo2 = rs.getInt("STUDENT_NO");
				int studentGrade = rs.getInt("STUDENT_GRADE");
				String studentName = rs.getString("STUDENT_NAME");
				String studentSSN = rs.getString("STUDENT_SSN");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				int professorNo = rs.getInt("PROFESSOR_NO");
				
				student = new Student(sequenceNo, studentNo2, studentGrade, studentName, studentSSN, studentAddress, departmentName, professorNo);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return student;
	}


	
	
	
	// 6. 학생 검색
	/** 학생 검색 DAO
	 * @param departmentName
	 * @param conn
	 * @return list(학생 정보)
	 * @throws Exception
	 */
	public List<Student> list(int searchKey, String searchValue, Connection conn) throws Exception {
		
		List<Student> list = new ArrayList<>(); //타입추론
		
		try {
			//SELECT절 + FROM절 부분
			String sql1 = prop.getProperty("list");

			String sql2 = null;
			
			switch(searchKey) {
			case 1: sql2 = prop.getProperty("searchDeptName"); break;
			case 2: sql2 = prop.getProperty("searchAddress"); break;
			}
			
			String sql = sql1 + sql2;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchValue);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int sequenceNo = rs.getInt("SEQUENCE_NO");
				int studentNo = rs.getInt("STUDENT_NO");
				int studentGrade = rs.getInt("STUDENT_GRADE");
				String studentName = rs.getString("STUDENT_NAME");
				String studentSSN = rs.getString("STUDENT_SSN");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				int professorNo = rs.getInt("PROFESSOR_NO");
				
				Student student = new Student(sequenceNo, studentNo, studentGrade, studentName, studentSSN, studentAddress, departmentName, professorNo);
				
				list.add(student);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}


	
	
	// 7. 학번,이름을 입력받아 일치하는 학생의 담당 교수명 조회
	/** 학번,이름을 입력받아 일치하는 학생의 담당 교수명 조회
	 * @param studentNo
	 * @param studentName
	 * @param conn
	 * @return student(학생, 교수명 정보)
	 * @throws Exception
	 */
	public Student selectPFName(int studentNo, String studentName, Connection conn) throws Exception{
		
		Student student = null;
		
		try {
			
			String sql = prop.getProperty("selectPFName");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studentNo);
			pstmt.setString(2, studentName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				student = new Student();
				
				student.setSequenceNo(rs.getInt("SEQUENCE_NO"));
				student.setStudentNo(rs.getInt("STUDENT_NO"));
				student.setStudentName(rs.getString("STUDENT_NAME"));
				student.setPfName(rs.getString("PF_NAME"));
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return student;
	}


	
	

}
