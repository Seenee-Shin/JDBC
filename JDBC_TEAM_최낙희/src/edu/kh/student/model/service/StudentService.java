package edu.kh.student.model.service;

import static edu.kh.student.common.StudentTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.student.model.dao.StudentDAO;
import edu.kh.student.model.vo.Student;

public class StudentService {

	// 필드
	private StudentDAO dao = new StudentDAO();
	
	// 1. 전체 학생 조회
	/** 전체 학생 조회 Service
	 * @return list (학생정보)
	 * @throws Exception
	 */
	public List<Student> selectAll() throws Exception {
		
		Connection conn = getConnection();
		
		List<Student> list = dao.selectAll(conn);
		
		close(conn);
		
		return list;
	}

	
	// 2. 학생 등록 추가
	/** 학생 등록 추가 Service
	 * @param student
	 * @return result(추가 성공: 1, 실패: 0)
	 * @throws Exception
	 */
	public int insertStudent(Student student) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.insertStudent(student, conn);
		
		close(conn);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);
		
		return result;
	}


	
	// 3. 학생 정보 수정(학과명, 교수번호만)
	/** 학생 정보 수정(학과명, 교수번호만) Service
	 * @param student
	 * @return result(수정 성공:1 , 실패: 0)
	 * @throws Exception
	 */
	public int updateStudent(Student student) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateStudent(student, conn);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);
		
		close(conn);
		
		return result;
	}


	
	
	// 4. 학생 정보 삭제
	/** 학생 정보 삭제 Service
	 * @param studentNo
	 * @return result(수정 성공:1 , 실패: 0)
	 * @throws Exception
	 */
	public int deleteStudent(int studentNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.deleteStudent(studentNo, conn);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);
		
		close(conn);
		
		return result;
	}


	
	
	// 5. 학번으로 학생 정보 조회
	/** 학번으로 학생 정보 조회 Service
	 * @param studentNo
	 * @return student(학생정보)
	 * @throws Exception
	 */
	public Student selectOne(int studentNo) throws Exception {
		Connection conn = getConnection();
		
		Student student = dao.selectOne(studentNo, conn);
		
		close(conn);
		
		return student;
	}


	
	// 6. 학생 검색
	/** 학생 검색 Service
	 * @param departmentName
	 * @return list(학생 정보)
	 * @throws Exception
	 */
	public List<Student> list(int searchKey, String searchValue) throws Exception{
		Connection conn = getConnection();
		
		List<Student> list = dao.list(searchKey, searchValue, conn);
		
		close(conn);
		
		return list;
	}


	
	
	// 7. 학번,이름을 입력받아 일치하는 학생의 담당 교수명 조회
	/** 학번,이름을 입력받아 일치하는 학생의 담당 교수명 조회
	 * @param studentNo
	 * @param studentName
	 * @return student(학생, 교수명 정보)
	 * @throws Exception
	 */
	public Student selectPFName(int studentNo, String studentName) throws Exception {
		Connection conn = getConnection();
		
		Student student = dao.selectPFName(studentNo, studentName, conn);
		
		close(conn);
		
		return student;
	}


	
	
	
}
