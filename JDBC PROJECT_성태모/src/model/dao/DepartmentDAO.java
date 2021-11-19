package model.dao;

import static common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.vo.Department;

public class DepartmentDAO {
	
	// 필드
	private Statement stmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public DepartmentDAO() {

		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("department-sql.xml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	/** 학과 조회
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Department> DepartmentSearch(Connection conn) throws Exception{
		
		List<Department> list = new ArrayList<Department>();
		
		try {
			String sql = prop.getProperty("departmentSearch");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {

				int departmentNo = rs.getInt("DEPARTMENT_NO");
				String departmentNm = rs.getString("DEPARTMENT_NAME");
				String category = rs.getString("CATEGORY");
				
				Department departMent = new Department(departmentNo, departmentNm, category);
				
				list.add(departMent);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}



}
