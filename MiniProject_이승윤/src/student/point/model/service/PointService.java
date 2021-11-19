package student.point.model.service;

import static student.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import student.model.vo.Student;
import student.point.model.dao.PointDAO;
import student.point.model.vo.Point;

public class PointService {
	
	private PointDAO dao = new PointDAO();

	/** 학생 성적 기입 Service
	 * @param point
	 * @return result (성공 1, 실패 0)
	 * @throws Exception
	 */
	public int insertPoint(Point point) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertPoint(point, conn);
		
		if(result >0) close	  (conn);
		else		  rollback(conn);
				
		return result;
	}
	
	/** 학생 성적 수정 Service
	 * @param studentNo
	 * @param termNo
	 * @param point
	 * @return result (성공 1, 실패 0)
	 * @throws Exception
	 */
	public int updatePoint(int studentNo, int termNo, double point) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updatePoint(studentNo, termNo, point, conn);
		
		if(result > 0) commit  (conn);
		else		   rollback(conn);
		
		close(conn);
		return result;
	}

	/** 학생 성적 전체 조회 Service
	 * @return list
	 * @throws Excepiton
	 */
	public List<Point> selectAllPoint() throws Exception {

		Connection conn = getConnection();
		
		List<Point> list = dao.selectAllPoint(conn);
		
		close(conn);
		
		return list;
	}

	/** 학생 성적 상세 조회 Service
	 * @param studentNo
	 * @return list
	 * @throws Exception
	 */
	public List<Point> selectOne(int studentNo) throws Exception {
		Connection conn = getConnection();
		
		List<Point> list = dao.selectOne(studentNo, conn);
		
		close(conn);
		
		return list;
	}
	
	/** 학생 성적 삭제 Service
	 * @param point
	 * @return result(성공 1, 실패 0)
	 * @throws Exception
	 */
	public int deletePoint(Point point) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deletePoint(point, conn);
		
		if(result > 0) commit  (conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	

}
