package student.model.service;

import static student.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;


import student.model.dao.StudentDAO;
import student.model.vo.Student;
import student.point.model.vo.Point;

public class StudentService {
	
	private StudentDAO dao = new StudentDAO();
	
	/** 학생 정보 추가 Service
	 * @return result (성공 1, 실패 0)
	 * @throws Excepiton
	 */
	public int insertStudent(Student student) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.insertStudent(student, conn);
		
		if(result > 0) commit  (conn);
		else		   rollback(conn);
		
		close(conn);
		return result;
	}

	/** 학생 정보 수정 Service
	 * @param input
	 * @return result (성공 1, 실패 0)
	 * @throws Excepiton
	 */
	public int updateStudent(int studentNo, String input1, String input2) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateStudent(studentNo, input1, input2, conn);
		
		if(result > 0) commit  (conn);
		else		   rollback(conn);
		
		close(conn);
		return result;
		
	}

	/** 학생 정보 전체 조회 Service
	 * @return list
	 * @throws Excepiton
	 */
	public List<Student> selectAll() throws Exception {

		Connection conn = getConnection();
		
		List<Student> list = dao.selectAll(conn);
		
		close(conn);
		
		return list;
	}

	/** 학생 정보 상세 조회 Service
	 * @param studentNo
	 * @return student
	 * @throws Exception
	 */
	public Student selectOne(int studentNo) throws Exception{
		
		Connection conn = getConnection();
		
		Student student = dao.selectOne(studentNo, conn);
		
		close(conn);
		
		return student;
	}

	/** 학생 정보 검색
	 * @param searchKey
	 * @param searchValue
	 * @return list
	 * @throws Exception
	 */
	public List<Student> searchStudent(int searchKey, String searchValue) throws Exception {
		
		Connection conn = getConnection();
		
		List<Student> list = dao.searchStudent(searchKey, searchValue, conn);
		
		close(conn);
		
		return list;
	}

	/** 학생 정보 삭제 Service
	 * @param studentNo
	 * @return result (성공 1, 실패 0)
	 * @throws Exception
	 */
	public int deleteStudent(int studentNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.deleteStudent(studentNo, conn);
		
		if(result > 0) commit  (conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	
}
