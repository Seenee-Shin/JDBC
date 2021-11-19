package model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import model.dao.StudentDAO;
import model.vo.Admin;
import model.vo.Student;

public class StudentService {

	private StudentDAO dao = new StudentDAO();
	
	
	/** 관리자 계정 로그인
	 * @param adminId
	 * @param adminPw
	 * @return admin(값이 있으면 로그인 성공)
	 * @throws Exception
	 */
	public Admin adminLogin(String adminId, String adminPw) throws Exception {
		
		Connection conn = getConnection();
		
		Admin admin = dao.adminLogin(adminId, adminPw, conn);

		close(conn);
		
		return admin;
	}

	
	/** 학생 전체 목록 조회
	 * @return studentList(학생목록)
	 * @throws Exception
	 */
	public List<Student> studentAllSelect() throws Exception {
		
		Connection conn = getConnection();
		
		List<Student> studentList = dao.studentAllSelect(conn);
		
		close(conn);
		
		return studentList;
		
	}
	

	/** 학번으로 학생 검색
	 * @param search
	 * @return student(검색된 학생)
	 * @throws Exception
	 */
	public Student studentSearch(int search) throws Exception{
		
		Connection conn = getConnection();
		
		Student student = dao.studentSearch(search, conn);

		close(conn);
		
		return student;
	}
	
	
	/** 이름으로 학생 검색
	 * @param search2
	 * @return student(검색된 학생들)
	 * @throws Exception
	 */
	public List<Student> studentSearch2(String search2) throws Exception {
		
		Connection conn = getConnection();
		
		List<Student> student = dao.studentSearch2(search2, conn);
		
		close(conn);
		
		return student;
	}
	
	
	/** 학과별 학생 검색
	 * @param dpName
	 * @return student(검색된 학생들)
	 * @throws Exception
	 */
	public List<Student> studentDpSelect(String dpName) throws Exception {
		
		Connection conn = getConnection();
		
		List<Student> student = dao.studentDpSelect(dpName, conn);
		
		close(conn);
		
		return student;
	}
	

	/** 학생 정보 등록
	 * @param student
	 * @return result (1 성공)
	 * @throws Exception
	 */
	public int studentInsert(Student student) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.studentInsert(student, conn);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	/** 학생 정보 수정
	 * @param student
	 * @return result(1 성공)
	 * @throws Exception
	 */
	public int studentUpdate(Student student, String stdName) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.studentUpdate(student, stdName, conn);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	
	/** 학생 존재여부 검사
	 * @param stdName
	 * @return result(1존재)
	 * @throws Exception
	 */
	public int nameCheck(String stdName) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.nameCheck(stdName, conn);
		
		close(conn);
		
		return result;
	}

	
	/** 학생 정보 삭제
	 * @param stdName
	 * @return result(1성공)
	 * @throws Exception
	 */
	public int studentDelete(String stdName) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.studentDelete(stdName, conn);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}




}
