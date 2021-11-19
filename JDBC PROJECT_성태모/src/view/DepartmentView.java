package view;

import java.util.List;

import model.service.DepartmentService;
import model.vo.Department;




public class DepartmentView {
	
	private DepartmentService service = new DepartmentService();

	
	
	// 출력 메소드
	private void printDepartmentList(List<Department> list) {
		
		if(list.isEmpty()) { 
			System.out.println("학과 조회 결과가 없습니다.");
		}else {
			
			System.out.printf("학과번호      학과명       계열명\n"
							+ "---------------------------------------\n");
			
			for(Department dplist : list) {
				System.out.printf("%5d %10s %10s\n",
									dplist.getDepartmentNo(), dplist.getDepartmentNm(), dplist.getCategory());
		
	}
		}
		
	}
	
	
	
	// 학과 정보 조회
	public void departmentSearch() {
		
		try {
			printDepartmentList(service.DepartmentSearch());
			
		} catch (Exception e) {
			System.out.println("학과 정보 조회중 문제가 발생했습니다!");
			e.printStackTrace();
		}
		
		
	}
	

}
