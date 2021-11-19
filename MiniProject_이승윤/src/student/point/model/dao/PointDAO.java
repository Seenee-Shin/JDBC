package student.point.model.dao;

import static student.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import student.model.vo.Student;
import student.point.model.vo.Point;

public class PointDAO {
	
	private Properties prop = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public PointDAO() {
		prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream("point.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 학생 성적 기입 DAO
	 * @param point
	 * @param conn
	 * @return result(성공 1, 실패 0)
	 * @throws Exception
	 */
	public int insertPoint(Point point, Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertPoint");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, point.getStudentNo());
			pstmt.setInt(2, point.getTermNo());
			pstmt.setDouble(3, point.getPoint());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	/** 학생 성적 수정 DAO
	 * @param studentNo
	 * @param termNo
	 * @param point
	 * @param conn
	 * @return result (성공 1, 실패 0)
	 * @throws Exception
	 */
	public int updatePoint(int studentNo, int termNo, double point, Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePoint");
			
			pstmt  = conn.prepareStatement(sql);
			pstmt.setDouble(1, point);
			pstmt.setInt(2, termNo);
			pstmt.setInt(3, studentNo);
			result = pstmt.executeUpdate();
		
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	/** 학생 성적 전체 조회
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Point> selectAllPoint(Connection conn) throws Exception {
		List<Point> list = new ArrayList();
		
		try{
			String sql = prop.getProperty("selectAllPoint");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int studentNo = rs.getInt(1);
				int termNo = rs.getInt(2);
				double point1 = rs.getDouble(3);
				
				Point point = new Point(studentNo, termNo, point1);
				
				list.add(point);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}
	/** 학생 성적 상세 조회
	 * @param studentNo
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Point> selectOne(int studentNo, Connection conn) throws Exception {
		List<Point> list = new ArrayList();
		
		try{
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studentNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int studentNo2 = rs.getInt(1);
				int termNo = rs.getInt(2);
				double point1 = rs.getDouble(3);
				
				Point point = new Point(studentNo2, termNo, point1);
				
				list.add(point);
	
			}			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}
	/** 학생 성적 삭제 DAO
	 * @param point
	 * @param conn
	 * @return result(성공 1, 실패 0)
	 * @throws Exception
	 */
	public int deletePoint(Point point, Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deletePoint");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, point.getStudentNo());
			pstmt.setInt(2, point.getTermNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	

}
