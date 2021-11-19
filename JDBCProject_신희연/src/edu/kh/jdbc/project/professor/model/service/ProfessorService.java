package edu.kh.jdbc.project.professor.model.service;
import static edu.kh.jdbc.project.common.JDBCTemplate.*;
import java.sql.Connection;

import edu.kh.jdbc.project.professor.model.dao.ProfessorDAO;
import edu.kh.jdbc.project.professor.model.vo.Professor;

public class ProfessorService {
	
	private ProfessorDAO dao = new ProfessorDAO();

	/** 사무실 조회
	 * @param searchValue
	 * @return office
	 * @throws Exception
	 */
	public Professor officeInfo(String input) throws Exception{
		Professor office = null;
		Connection conn = getConnection();
		
		try {
			office = dao.officeInfo(conn, input);
			
		}finally {
			close(conn);
		}
		return office;
	}

	/** 학과장 조회
	 * @param input
	 * @return dean
	 * @throws Exception
	 */
	public Professor deanInfo(String input) throws Exception {
		Professor dean = null;
		Connection conn = getConnection();
		
		try {
			dean = dao. deanInfo(conn,input);
			
		}finally {
			close(conn);
		}
		return dean;
	}

	/**updateProfessor
	 * @param inputName
	 * @param inputCode
	 * @param searchKey
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public int updateProfessor(String inputName, String inputCode, int searchKey, String searchValue) throws Exception{
		int result = 0;
		Connection conn = getConnection();
		try {
			result = dao.updateProfessor(inputName,inputCode,searchKey,searchValue,conn);
			
			if(result > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}finally {
			close(conn);
		}
		return result;
	}

	public int checkProfessor(String inputCode, String inputName) throws Exception{
		int result = 0;
		Connection conn = getConnection();
		result = dao.checkProfessor(inputCode,inputName,conn);
		
		close(conn);
		
		return result;
	}

	/**deletePorfessor
	 * @param inputCode
	 * @param inputName
	 * @return result
	 * @throws Exception
	 */
	public int deletePorfessor(String inputCode, String inputName) throws Exception {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = dao.deleteProfessor(inputCode, inputName, conn);
			
			if(result > 0) {
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
