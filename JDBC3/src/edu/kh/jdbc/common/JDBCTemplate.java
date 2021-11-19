package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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
			//xml파일 읽어오기 (properties 객체 생성)
			Properties prop = new Properties();
			
			//diver.xml 저장 
			prop.loadFromXML(new FileInputStream("driver.xml"));
			
			Class.forName(prop.getProperty("driver"));
			String url = prop.getProperty("url");
			String userName = prop.getProperty("userName");
			String password = prop.getProperty("password");
			//DB연결정도 xml파일로 별도 작성후 불러오기 -> 컴파일 속도 향상 
			
			// properties : map의 후손클래스 
			// 파일 입출력에 특화 
			
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
