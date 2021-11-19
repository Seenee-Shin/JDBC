package edu.kh.board.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	// 공통된 내용을 추출하여 템플릿 작성 
	// connection, 트랜잭션 제어
	// close(conn, stmt, pstmt, rs) 등  
	
	//static 객체를 생성하지 않아도 메모리에 변수 선언
	// 외부로 부터 직접접근 x (static 끼리 공유)
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		//close 되지 않은 conn있으면 재활용/ closed or 없으면 conn 생성
		// 접속 인수 당 1 개의 커넥션만 사용하기 위하여 
		
		try {
			
			if(conn == null || conn.isClosed() ) {		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String userName = "board";
			String password = "board1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			conn.setAutoCommit(false);// auto commit off
			
			// true : sql 수행 후 자동으로 commit
			// connclose 수행 시 자동 commit 수행
			
			}else {	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//객체 반환 메소드 
	// preparedStatement = Statement 의 하위 클래스 
	public static void close(Statement stmt) {
		try {
			
			if(stmt != null && !stmt.isClosed()) {stmt.close();}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//
	public static void close(ResultSet rs) {
		try {
			
			if(rs != null && !rs.isClosed()) {rs.close();}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			
			if(conn != null && !conn.isClosed()) {conn.close();}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//commit
	public static void commit(Connection conn) {
		try {
			
			if(conn != null && !conn.isClosed()) {conn.commit();}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//rollback
	public static void rollback(Connection conn) {
		try {
			
			if(conn != null && !conn.isClosed()) {conn.rollback();}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
