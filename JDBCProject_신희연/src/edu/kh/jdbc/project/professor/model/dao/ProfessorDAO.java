package edu.kh.jdbc.project.professor.model.dao;
import static edu.kh.jdbc.project.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.project.professor.model.vo.Professor;

public class ProfessorDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public ProfessorDAO() {
	try {
		prop = new Properties();
		prop.loadFromXML(new FileInputStream("professor-sql.xml"));
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	/** 과사무실 조회 
	 * @param conn
	 * @param input
	 * @return office
	 * @throws Exception
	 */
	public Professor officeInfo(Connection conn, String input) throws Exception{
		Professor office = null;
		try {
			String sql = prop.getProperty("deptOffice");
			
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String deptOffice = rs.getString("DEPT_OFFICE");
				office = new Professor(deptOffice);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return office;
	}

	/** 학과장 정보 조회 
	 * @param conn
	 * @param input
	 * @return dean
	 * @throws Exception
	 */
	public Professor deanInfo(Connection conn, String input) throws Exception{
		Professor dean = null;
		try {
			String sql = prop.getProperty("dean");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String pName = rs.getString("P_NAME");
				String pPhone = rs.getString("P_PHONE");
				String pOffice = rs.getString("P_OFFICE");
				
				dean = new Professor(pName, pOffice, pPhone);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return dean;
	}

	/** 교수 확인 메소드 
	 * @param inputDept
	 * @param inputName
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int checkProfessor(String inputCode, String inputName, Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("checkProfessor");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputCode);
			pstmt.setString(2, inputName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	/** 교수 정보 수정 
	 * @param inputName
	 * @param inputDept
	 * @param searchKey
	 * @param searchValue
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updateProfessor(String inputName, String inputCode, int searchKey, String searchValue, Connection conn) throws Exception {
		int result = 0 ;
		try {
			String sql1 = null;
			String sql2 = prop.getProperty("where");
			
			switch(searchKey) {
			case 1 : sql1 = prop.getProperty("updatePhone");
			case 2 : sql1 = prop.getProperty("updateAddress");
			}
			
			String sql = sql1 + sql2; 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,searchValue); 
			pstmt.setString(2,inputCode); 
			pstmt.setString(3,inputName); 
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		
		}
		return result;
	}

	/** 교수 정보 삭제
	 * @param inputCode
	 * @param inputName
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int deleteProfessor(String inputCode, String inputName, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("deleteProfessor") + prop.getProperty("where");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,inputCode); 
			pstmt.setString(2,inputName); 
			
		}finally {
			close(pstmt);
		}
		return result;
	}

}
