
package edu.kh.scott.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.scott.model.vo.Emp;

public class EmpDAO {
	//DAO : Database Access Object 데이터 베이스 접근 객체 
	// 자바와 데이터베이스 연결을 위한 구문을 작성하는 클래스 
	
	//JDBC 객체선언
	private Connection conn = null; // 연결정보
	private Statement stmt = null; // 전달, 결과 반환 
	private ResultSet rs = null; // select문 결과 저장 
	
	private PreparedStatement pstmt = null;
	// SQL 준비가 완료 된 후 DB에 SQL을 전달하고 결과를 반환 받아오는 역할
	// 빈칸을 만들어 빈칸이 다채워진 후 별도의 SQL 전달 
	
	// ? : 위치 홀더. SQL구문에 들어갈 리터럴을 동적으로 작성하게 하는 부분
	// SQL구문을 자바변수와 합칠 때 용이
	// setString() 메소드 사용으로 ''생략가능 
	// but ! statement사용시 보다 코드길이가 길어짐 
 	
	private String driver = "oracle.jdbc.driver.OracleDriver"; //커넥션 생성 변수
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String userName = "scott";
	private String userPw = "tiger";

	
	//case 1 : 전체 사원 정보 조회
	public List<Emp> selectAll() {
		
		List<Emp> empList = null; // list instance object for saving & return rs
		
		try {
			//JDBC Driver
			Class.forName(driver);
			
			//get Connection 
			conn = DriverManager.getConnection(url, userName, userPw);
			
			// make Connection Statement Object 
			stmt = conn.createStatement();
			
			//write SQL 
			String sql = "SELECT * FROM EMP ORDER BY EMPNO";
			
			// result set 
			rs = stmt.executeQuery(sql);
			
			// DB와 관련된 동작이 끝난후 ArrayList 생성
			// DB 동작 중 오류 발생시 emplist 가 null(실패)로 반환되는 상황을 만들기 위해 
			// null = 실패 
			// 결과 없음
			// 결과 있음
			empList = new ArrayList<Emp>();
			
			//access to each tuple from rs
			while (rs.next()) {
				//re.next : if rs has next tuple : true
				 
				// put rs in list using List of collection 
				
				// get result from rs , save result at emp object
				int empNo = rs.getInt("EMPNO");
				String empName = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				Date hireDate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				int comm = rs.getInt("COMM");
				int deptNo = rs.getInt("DEPTNO");
				
				Emp emp = new Emp(empNo, empName, job, mgr, hireDate, sal, comm, deptNo);
				
				//add emp instance to empList
				empList.add(emp);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally { // close DB 
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		return empList; // return 'rs'
	}
	//case 2 : 사번으로 사원조회 
	public Emp selectOne(int input) {
		
		//조회결과 저장 변수선언
		Emp emp = null;
		
		try { 
			// driver road
			Class.forName(driver);
			
			//driverManager 
			conn = DriverManager.getConnection(url, userName, userPw);
			
			//Statement
			stmt = conn.createStatement();
			
			// sql + input
			String sql = "SELECT * FROM EMP WHERE EMPNO = " + input;
			
			rs = stmt.executeQuery(sql); //run query and save at rs
			
			//** access rs result -> 반환값이 1개 또는 0개 -> 검사1 번 
			// while(re.next()) : 다음 값이 없을 때까지 반복 -> 적합하지 않음 
			// if : 한번만 검사 -> 효율성 up
			if(rs.next()) {
				//컬럼 값 변수에 저장 후 객체생성 
				int empNo = rs.getInt("EMPNO");
				String empName = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				Date hireDate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				int comm = rs.getInt("COMM");
				int deptNo = rs.getInt("DEPTNO");
				
				emp = new Emp(empNo, empName, job, mgr, hireDate, sal, comm, deptNo);
				
			}
			///사번 일치 X -> emp 객체 생성 X -> emp == null;
			///사번 일치 O -> emp 객체 생성 O -> emp != null;
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally {
			//자원 close
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		} 
		return emp;
	}
	//case 7
	public List<Emp> selectDeptNo(int inputNo){
		List<Emp> empList = null;
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, userName, userPw);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM EMP WHERE DEPTNO = " + inputNo;
			
			rs = stmt.executeQuery(sql);
			
			empList = new ArrayList<Emp>();
			
			while(rs.next()) {
				
				int empNo = rs.getInt("EMPNO");
				String empName = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				Date hireDate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				int comm = rs.getInt("COMM");
				int deptNo = rs.getInt("DEPTNO");
				
				Emp emp = new Emp(empNo, empName, job, mgr, hireDate, sal, comm, deptNo);
				
				empList.add(emp);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return empList;
	}
	
	//case 3 
	public int insertEmp(Emp emp) {
		// DML 수행시 결과로 성공한 행의 개수 반환 
		//==> 정수형 변수 int로 dml수행 결과 저장 
		int result = 0;
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, userPw);
			
			//? 위치홀더를 사용하여 SQL작성 
			String sql = "INSERT INTO EMP VALUES(SEQ_EMPNO.NEXTVAL, ?,?,?, SYSDATE, ?,?,?)";
			
			// 위치홀더에 들어갈 값 입력
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.geteName());		
			pstmt.setString(2, emp.getJob());		
			pstmt.setInt(3, emp.getMgr());		
			pstmt.setInt(4, emp.getSal());			
			pstmt.setInt(5, emp.getComm());		
			pstmt.setInt(6, emp.getDeptNo());		
			
			// 결과 반환 
			result = pstmt.executeUpdate();
			//executeUpdate():insert,update,delete의 수행 성공 행의 개수 반환 
			
			//트랜잭션 제어
			if(result > 0 ) { //dml 수행 성공 
				conn.commit(); // 트랜잭션 커밋 
			}else {
				conn.rollback(); 
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result; 
	}
	
	public int updateEmp(Emp emp) {
		int result = 0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, userPw);
			
			String sql = "UPDATE EMP SET JOB = ?, SAL = ?, COMM= ?  WHERE EMPNO = ?";
			
			pstmt = conn.prepareStatement(sql);
		
			
			pstmt.setString(1,emp.getJob());
			pstmt.setInt(2,emp.getSal());
			pstmt.setInt(3,emp.getComm());
			pstmt.setInt(4,emp.getEmpNo());
			
			result = pstmt.executeUpdate();
			
			if(result > 0 ) { //dml 수행 성공 
				conn.commit(); // 트랜잭션 커밋 
			}else {
				conn.rollback(); 
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
			
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
		
		return result;
		
	}
	
	public int deleteEmp(int input) {
		int result = 0;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, userPw);
			
			String sql = "DELETE FROM EMP WHERE EMPNO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,input);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(conn != null) conn.close();
					if(pstmt != null)pstmt.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}	
		}
		return result;
	}
	
	//case 6 
	public Emp selectDeptNo_EmpName(Emp emp) {
		 Emp result = null;
		 
		 try {
			 Class.forName(driver);
			 conn = DriverManager.getConnection(url, userName, userPw);
			
			 String sql = "SELECT * FROM EMP WHERE EMPNO = ? AND ENAME = ?";
			 
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, emp.getEmpNo());
			 pstmt.setString(2, emp.geteName());
			
			 rs = pstmt.executeQuery();

				if(rs.next()) {
					
					int empNo = rs.getInt("EMPNO");
					String eName = rs.getString("ENAME");
					String job = rs.getString("JOB");
					int mgr = rs.getInt("MGR");
					Date hireDate = rs.getDate("HIREDATE");
					int sal = rs.getInt("SAL");
					int comm = rs.getInt("COMM");
					int deptNo = rs.getInt("DEPTNO");
					
					result = new Emp(empNo, eName, job, mgr, hireDate, sal, comm, deptNo);
					
				} 
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
			 try {
				 if(rs != null) rs.close();
				 if(pstmt != null) pstmt.close();
				 if(conn != null) conn.close();
			 }catch(SQLException e) {
				 e.printStackTrace();
			 }
			 
		 }
		 
		return result;
	}
}	
