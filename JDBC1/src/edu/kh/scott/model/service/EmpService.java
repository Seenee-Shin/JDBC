package edu.kh.scott.model.service;

import java.util.List;
import edu.kh.scott.model.dao.EmpDAO;
import edu.kh.scott.model.vo.Emp;

public class EmpService {
	//Service : 비지니스 로직 처리 (데이터 가공, 트랜잭션 제어 )
	
	private EmpDAO dao = new EmpDAO();
	
	//case 1 : 전체 사원정보조회
	public List<Emp> selectAll() {
		//조건 없음. 전체조회, 데이터가공 처리 x 
		// DAO의 selectAll() 호출  
		List<Emp> empList = dao.selectAll(); // return empList 
		
		return empList;
		
	}
	// case 2 : 사번으로 사원정보조회 
	public Emp selectOne(int input) {
		
		// dao 호출 & input 전달
		Emp emp = dao.selectOne(input);
		
		return emp;
	}
	
	//case 7 : 부서별 사원조회
	public  List<Emp> selectDeptNo(int inputNo){
		
		List<Emp> empList = dao.selectDeptNo(inputNo);
		return empList;
		
	}
	// case 3
	public int insertEmp(Emp emp) {
		
		emp.setSal(emp.getSal()+200);
		
		int result = dao.insertEmp(emp);
		
		return result;
	}
	
	// case4 
	public int updateEmp(Emp emp) {
		
		int result = dao.updateEmp(emp);
		
		return result;
	}
	
	//case 5 
	public int deleteEmp(int input) {
		int result = dao.deleteEmp(input);
		
		return result;
		
	}
	
	public Emp selectDeptNo_EmpName(Emp emp) {
		
		Emp result = dao.selectDeptNo_EmpName(emp);
		
		
		return emp;
	}
	
}
