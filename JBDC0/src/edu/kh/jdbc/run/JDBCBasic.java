package edu.kh.jdbc.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCBasic {

	public static void main(String[] args) {
		//JDBC (Java Database Connectivity) : db에 접근할 수 있게 해 주는 java프로그래밍 api
		//Java에서 db와 연결하기 위한 인터페이스, 클래스 제공 (java,sql)
		
		//ojdbc6.jar : Oracle JDBC드라이버 포함, 
		//오라클에서 제공하는 java와 Oracle DBMS 연결을 위한 라이브러리
		// 프로젝트에 등록 (필수)
		
		//JDBC 객체선언
		Connection conn = null;
		//DB의 연결 정보를 담은 객체 (java와 db의 연결통로)
		// -> 연결정보 : id, pw, 주소(ip), 접속방식, DB이름
		
		Statement stmt = null;
		//connection 객체를 통해 sql문 전달 후 실행,결과 반환 
		
		ResultSet rs = null;
		// SELECT문 반환값 저장 (성공시)
		
		try {
			//connection 얻어오기
			//1. Oracle JDBC 드라이버 준비(메모리에 로드 : )
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//작성하지 않아도 자동로드됨 
			
			//2.connection 객체 생성 (DriverManager)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","kh","kh");
												// db주소 , 이름, 타입, 아이디, 비밀번호 작성
			
			//jdbc:oracle:thin : jdbc드라이버 타입 = thin 타입(기본)
			//@127.0.0.1 : 루프백 아이피 / 컴퓨터주소
			// 1521 : 포트번호 (외부와의 통신을 위한 포트) -> Oracle DBMS 기본포트 1521
			// xe : Express/ DB 이름
			
			
			
			// 중간확인 : 커넥션이 정상적으로 생성되었는지 확인 
			// 오류 발생 x -> db연결 성공 
			System.out.println(conn);
			
			//SQL작성
			String sql = "SELECT EMP_NAME, SALARY FROM EMPLOYEE";
			
			//Statement 생성 (sql 전달, 반환)
			stmt = conn.createStatement();
			//커넥션 안쪽에 스테이트먼트 생성 
			
			//Statement에 sql 담아 전달 후 수행, 결과(result set)반환 
			rs = stmt.executeQuery(sql);
			//executeQuery(sql) select문 수행 후 result set을 반환 하는 메소드
			
			// 행에 순서대로 접근 (커서 이용)
			while(rs.next()) {
				//조회결과 행을 커서를 이용해 순차적으로 접근
				
			//rs.get[type]("컬럼면") 메소드를 이용해 컬럼 값 가져오기
			//	[type] : 컬럼 값의 자료형 (java ver.)
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				
				//출력
				System.out.printf("이름: %s / 급여 : %d\n",empName, salary);
			}
			
			
		}catch(Exception e) {
			//SQLException : SQL,DB관련 예외의 최상위클래스
			e.printStackTrace();
		}
		finally {//사용자원 반환
			//사용한 객체의 역순으로 close() (생성된 역순으로 close 생성)
			// null여부를 검사하여 사용하였는지 확인 
			
			try {
				if(rs != null) { rs.close(); }
				if(stmt != null) { stmt.close();}
				if(conn != null) { conn.close();}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		
	}

}
