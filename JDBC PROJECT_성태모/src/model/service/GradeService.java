package model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import model.dao.GradeDAO;
import model.vo.Grade;

public class GradeService {
	
	GradeDAO dao = new GradeDAO();

	
	/** 학생 성적 검색
	 * @param studentNo
	 * @return grade(검색된 학생)
	 * @throws Exception
	 */
	public Grade gradeSearch(int studentNo) throws Exception{
		
		Connection conn = getConnection();
		 
		Grade grade = dao.gradeSearch(studentNo, conn);
		
		close(conn);
		
		return grade;
	}


	/** 전체 성적 조회
	 * @return gradeList;
	 * @throws Exception
	 */
	public List<Grade> gradeAll() throws Exception{
		
		Connection conn = getConnection();
		 
		List<Grade> gradeList = dao.gradeAll(conn);
		
		close(conn);
		
		return gradeList;
	}


	/** 장학금 대상자
	 * @return gradeList
	 */
	public List<Grade> award() throws Exception {
		
		Connection conn = getConnection();
		 
		List<Grade> gradeList = dao.award(conn);
		
		close(conn);
		
		return gradeList;
	}


	/** 성적 추가
	 * @param grade
	 * @return result(1성공)
	 * @throws Exception
	 */
	public int gradeInsert(Grade grade) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.gradeInsert(grade, conn);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 입력한 학번으로 학생 정보 확인
	 * @param check
	 * @return result(1존재)
	 * @throws Exception
	 */
	public int checkNo(int check) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.checkNo(check, conn);
		
		close(conn);
		
		return result;
	}


	/** 성적 수정
	 * @param grade
	 * @return result(1성공)
	 * @throws Exception
	 */
	public int gradeUpdate(int check, Grade grade) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.gradeUpdate(check, grade, conn);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

}
