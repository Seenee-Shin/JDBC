package edu.kh.jdbc.project.view;

import java.util.Scanner;

import edu.kh.jdbc.project.professor.model.service.ProfessorService;
import edu.kh.jdbc.project.professor.model.vo.Professor;

public class ProfessorView {
	private Scanner sc =new Scanner(System.in);
	private ProfessorService service = new ProfessorService();
	
	public void officeInfo() {
		System.out.println("[학과 사무실 조회]");
	
		System.out.print("학과 입력 : ");
		String input = sc.nextLine();
		
		try {
			Professor office = service.officeInfo(input);
			
			if(office == null) {
				System.out.println("학과 사무실이 조회되지 않습니다.");
			}else {
				System.out.println(input + " 사무실 : " + office.getDeptOffice());
			}
			
		}catch(Exception e) {
			System.out.println("사무실 조회 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		
		
	}

	public void deanInfo() {
		System.out.println("[학과장 정보 조회]");
		
		System.out.print("학과 입력 : ");
		String input = sc.nextLine();
		
		try {
			
			Professor dean = service.deanInfo(input);
			
			if(dean == null) {
				System.out.println("학과 사무실이 조회되지 않습니다.");
			}else {
				System.out.printf("%s 학과장 : %s | 교수사무실 : %7s | 전화번호 : %s \n",
						input, dean.getpName(), dean.getpOffice(), dean.getpPhone());
			}
			
		}catch(Exception e) {
			System.out.println("학과장 조회 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		
		
	}

	public void updateInfo() {
		String inputName = null;
		String inputCode = null;
		int searchKey = 0;
		
		System.out.println("[교수 정보 수정]");
		
		try {
			while(true){
				
				System.out.print("수정 할 교수의 이름 :");
				inputName = sc.nextLine();
				
				System.out.print("수정 할 교수 번호 :");
				inputCode = sc.nextLine();
				
				
				int checkProfessor = service.checkProfessor(inputCode,inputName);
				
				if(checkProfessor == 0) {
					System.out.println("교수 정보를 잘못 입력 하셨습니다.");
				}else {
					System.out.println("[교수 정보 수정 메뉴]");
					break; //while문 종료
				}
			}
			while(true) {
				
				System.out.println("1. 전화번호 수정 | 2. 교수 사무실 주소 수정");
				System.out.print("수정 카테고리 선택 :");
				searchKey = sc.nextInt();
				sc.nextLine();
				
				if(searchKey > 0 && searchKey < 3) {
					break;
				}else {
					System.out.println("메뉴번호만 입력해주세요");
				}
			}
			
			System.out.print("[수정할 정보 입력]");
			
			if(searchKey == 1) {
				System.out.print("변경할 전화번호 입력 : ");
			}else {
				System.out.print("변경 할 주소 입력 : ");
			}
			String searchValue = sc.nextLine();
			
			int result = service.updateProfessor(inputName, inputCode, searchKey,searchValue);
			
			if(result>0) {
				System.out.println("교수 정보가 수정되었습니다.");
				
			}else {
				System.out.println("교수 정보 수정에 실패 하였습니다.");
			}
			
			
		}catch(Exception e) {
			System.out.println("수정 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}

	public void deleteProfessor() {
		int result = 0;
		String inputName = null;
		String inputCode = null;
		try {		
			
			while(true){
			
			System.out.print("삭제할 교수의 이름 :");
			inputName = sc.nextLine();
			
			System.out.print("삭제할 교수 번호 :");
			inputCode = sc.nextLine();
			
			
			int checkProfessor = service.checkProfessor(inputCode,inputName);
			
			if(checkProfessor == 0) {
				System.out.println("교수 정보를 잘못 입력 하셨습니다.");
			}else {
				System.out.println(inputName +"교수의 정보를 삭제 하시겠습니까? (y/n)");
				break; //while문 종료
			}
		}
		
		char input = sc.nextLine().toLowerCase().charAt(0);
		
		while(true) {
			if(input == 'y') {
				result = service.deletePorfessor(inputCode,inputName);
				
				if(result > 0) {
					System.out.println("정상적으로 교수 정보가 삭제되었습니다.");
				}else {
					System.out.println("교수 정보 삭제에 실패하였습니다.");
				}
				break;
			}else if(input == 'n') {
				System.out.println("교수 정보 삭제를 취소하였습니다.");
				break;
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}	
		}
		
		}catch(Exception e) {
			System.out.println("수정 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}

}
