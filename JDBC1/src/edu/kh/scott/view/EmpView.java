package edu.kh.scott.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.scott.model.service.EmpService;
import edu.kh.scott.model.vo.Emp;

public class EmpView {
	
	//View 클래스 : 사용자에게 보여지는 부분 (입,출력 관련)
	// 입출력 코드 작성 예정 

	private Scanner sc = new Scanner(System.in);
	
	private EmpService service = new EmpService();
	// 뷰 -> 서비스
	
	public void displayMenu() {
		int sel = 0; //메뉴 번호를 입력 받을 변수 
		
		do {
			try {
	            System.out.println("==================================");
	            System.out.println("[Main Menu]");
	            System.out.println("1. 전체 사원 정보 조회");
	            System.out.println("2. 사번으로 사원 정보 조회");
	            System.out.println("3. 새로운 사원 정보 삽입"); //DML - insert 
	            System.out.println("4. 사번으로 사원 정보 수정");
	            // 사번, 직책, 급여, 커미션 입력 받아
	            // 사번이 일치하는 사원의 직책, 급여, 커미션을 수정

	            System.out.println("5. 사번으로 사원 정보 삭제");
	            // 사번을 입력 받아 일치하는 사번을 가진 사원 정보 삭제

	            System.out.println("6. 사번, 이름이 모두 일치하는 사원 정보 조회");
	            System.out.println("7. 부서 번호로 사원정보 조회");
	            System.out.println("0. 프로그램 종료");
	            System.out.println("==================================");
	            
	            System.out.println();
	            
	            System.out.print("메뉴 선택 >>> ");
	            sel = sc.nextInt();
	            sc.nextLine(); // 개행문자 제거 
	            
	            System.out.println();
	            
	            
	            switch(sel) {
	            
	            case 1 : selectAll(); break; //사원정보조회 메소드 
	            case 2 : selectOne(); break;
	            case 3 : insertEmp(); break;
	            case 4 : updateEmp(); break;
	            case 5 : deleteEmp(); break;
	            case 6 : selectDeptNo_EmpName(); break;
	            case 7 : selectDeptNo(); break;
	            case 0 : System.out.println("프로그램이 종료 됩니다."); break;
	            
	            default : System.out.println("메뉴 번호를 잘못 입력 하였습니다.\n 다시 입력해 주세요");
	            }
				
			}catch(InputMismatchException e ) {
				System.out.println("메뉴 번호만 입력해 주세요");
				sc.nextLine(); // 입력버퍼에 남아있는 값 제거 
				sel = -1; //오류 발생시 메뉴 종료 막기위해 더미값 지정
			}
			
		}while(sel !=0 );
		
	}
	
	//case 1 메소드
	private void selectAll() {
		//emp table 모든 사원 조회 후 출력 
		// empService 클래스의 select all 메소드 호출 
		List<Emp> empList = service.selectAll(); //return empList 
		
		//print emplist considering amplist statement 
		
		if(empList == null) {
			System.out.println("오류 발생 조회실패 ");
		}else if(empList.isEmpty()){
			System.out.println("조회결과 없음");
		}else {
			for(Emp emp : empList) {
				System.out.println(emp);
			}
		}
		
	}
	
	//case 2 : 사번으로 사원정보 조회
	private void selectOne(){
		//input empNo
		System.out.print("조회할 사번 입력");
		int input = sc.nextInt();
		sc.nextLine();
		
		// send int input ->selectOne in EmpService 
		Emp emp = service.selectOne(input);
		
		//출력
		if(emp == null) {
			
			System.out.println("일치하는 사번의 사원이 없습니다.");
		}else {
			System.out.println(emp);
		}
	}
	
	//case 7
	private void selectDeptNo() {
		System.out.println("조회할 부서 번호 입력");
		int inputNo = sc.nextInt();
		sc.nextLine();
		
		List<Emp> empList = service.selectDeptNo(inputNo); 
		
		if(empList == null) {
			System.out.println("오류 발생 조회실패 ");
		}else if(empList.isEmpty()){
			System.out.println("조회결과 없음");
		}else {
			for(Emp emp : empList) {
				System.out.println(emp);
			}
			
		}
	}
	// case 3
	private void insertEmp() {
		// 새 사원정보 입력받기 (단, 사원번호 = 시퀀스 / 입사일 = sysdate)
		System.out.print("이름 : ");
		String eName = sc.next();
		System.out.print("직책 : ");
		String job = sc.next();
		System.out.print("멘토 이름 : ");
		int mgr = sc.nextInt();
		System.out.print("급여 : ");
		int sal = sc.nextInt();
		System.out.print("커미션 : ");
		int comm = sc.nextInt();
		System.out.print("부서번호 : ");
		int deptNo = sc.nextInt();
		sc.nextLine();
		
		//객체로 입력받은 정보 저장 
		
		Emp emp = new Emp(eName, job, mgr, sal, comm, deptNo);
		
		int result = service.insertEmp(emp);
		
		if(result > 0) {
			System.out.println("사원 정보 입력 성공");
		}else {
			System.out.println("사원 정보 입력 실패");
		}
	}
	
	//case 4  
	private void updateEmp() {
		
		System.out.print("사원 사번 입력 : ");
		int empNo = sc.nextInt();
		
		System.out.println("== 수정할 정보 입력 ==");
		System.out.print("직책 : ");
		String job = sc.next();
		System.out.print("급여 : ");
		int sal = sc.nextInt();
		System.out.print("커미션 : ");
		int comm = sc.nextInt();
		sc.nextLine();
		
		
		Emp emp = new Emp(empNo, job, sal, comm);
		
		int result = service.updateEmp(emp);
		
		if(result > 0) {
			System.out.println("사원 정보 입력 성공");
		}else {
			System.out.println("사원 정보 입력 실패");
		}
		
	}
	//case5 
	private void deleteEmp() {
		System.out.print("삭제할 사원 사번 입력 : ");
		int input = sc.nextInt();
		sc.nextLine();
		
		int result = service.deleteEmp(input);
		
		if(result > 0 ) {
			System.out.println("사원 정보 삭제");
		}else {
			System.out.println("사원 정보 삭제 실패 ");
		}
		
	}
	//case6 
	private void selectDeptNo_EmpName() {
		System.out.print("조회할 사원 사번 입력 : ");
		int inputNo = sc.nextInt();
		
		System.out.print("조회할 사원 이름 입력 : ");
		String inputName = sc.nextLine();
		sc.nextLine();
		
		Emp emp = new Emp(inputNo, inputName);
		
		Emp result = service.selectDeptNo_EmpName(emp); 
		
		
		if(result == null) {
			System.out.println("일치하는 사원이 없습니다.");
		}else {
			System.out.println(result);
		}
		
		
	}
}
