package edu.kh.jdbc.project.student.model.serviece;

import static edu.kh.jdbc.project.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.project.student.model.dao.StudentDAO;
import edu.kh.jdbc.project.student.model.vo.Student;

public class StudentService {
	
	private StudentDAO dao = new StudentDAO();

	public List<Student> studentInfo(int searchKey, String searchValue) throws Exception {
		
		Connection conn = getConnection();
		
		List<Student> list = dao.studentInfo(searchKey, searchValue, conn);
		
		close(conn);
		return list;
	}

	/** 학생 확인 service 메소드
	 * @param inputNo
	 * @param inputName
	 * @return result
	 * @throws Exception
	 */
	public int checkStudent(int inputNo, String inputName) throws Exception {
		int result = 0;
		Connection conn = getConnection();
		result = dao.checkStudent(inputNo,inputName,conn);
		
		close(conn);
		return result;
	}

	/** 학생 정보 수정 service
	 * @param inputNo
	 * @param inputName
	 * @param searchKey
	 * @param searchValue
	 * @return result
	 * @throws Exception
	 */
	public int updateStudent(int inputNo, String inputName, int searchKey, String searchValue) throws Exception{
		int result = 0;
		Connection conn = getConnection();
		
		
		try {
			result = dao.updateStudent(inputNo,inputName,searchKey,searchValue,conn);
			
			if(result> 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}finally {
			close(conn);
		}
		return result;
	}

	/**신/편입생 정보 추가
	 * @param student
	 * @return result
	 * @throws Exception
	 */
	public int addStudent(Student student) throws Exception {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = dao.addStudent(conn,student);
			
			if(result> 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}finally {
			close(conn);
		}
		return result;
	}

	/** 퇴학생 정보 삭제
	 * @param inputNo
	 * @param inputName
	 * @return result
	 * @throws Exception
	 */
	public int deleteStudent(int inputNo, String inputName) throws Exception {
		int result = 0;
		Connection conn = getConnection();
		try {
			
			result = dao.deleteStudent(conn,inputNo,inputName);
			
			if(result> 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}finally {
			close(conn);
		}
		return result;
	}
}
