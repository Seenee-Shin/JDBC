package model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import model.dao.DepartmentDAO;
import model.vo.Department;

public class DepartmentService {
	
	DepartmentDAO dao = new DepartmentDAO();

	/** 학과 조회
	 * @return list
	 * @throws Exception
	 */
	public List<Department> DepartmentSearch() throws Exception{
		
		Connection conn = getConnection();
		
		List<Department> list = dao.DepartmentSearch(conn);
		
		close(conn);
		
		return list;
	}

}
