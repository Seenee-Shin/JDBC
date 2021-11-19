package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.vo.Admin;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	private StudentView studentView = new StudentView();
	private DepartmentView departmentView = new DepartmentView();
	private GradeView gradeView = new GradeView();
	
	public static Admin loginAdmin = null;
	
	public void displayMenu() {
		
		boolean flag = true; // 프로그램 종료 역할의 깃발
		int menu = -1; // 메뉴 입력용 변수
		int menu2 = -1; // 메뉴2 입력용 변수
		
		do {
			
			try {
				
				while(flag == true) { // 프로그램 종료 입력까지 반복
					
					if(loginAdmin == null) { // 관리자 로그인 x
						
						System.out.println();
						
						System.out.println("[KH대학 학생 관리 시스템]");
						System.out.println("1. 관리자 계정 로그인");
						System.out.println("0. 프로그램 종료");
						
						System.out.print("메뉴 선택 => ");
						menu = sc.nextInt();
						sc.nextLine();
						
						System.out.println();
						
						switch(menu) {
						case 1 : studentView.adminLogin(); break;
						case 0 : 
							System.out.println("프로그램을 종료합니다."); 
							flag = false;
							break;
						default : System.out.println("메뉴에 있는 숫자만 입력해주세요!!");
						}
						
					}else { // 관리자 로그인 o
						
						System.out.println();
						System.out.println("[KH대학 학생 관리 시스템]");
						System.out.println("[접속 계정 : " + loginAdmin.getAdminNm() + "]");
						
						System.out.println("1. 학생 관리");
						System.out.println("2. 성적 관리");
						System.out.println("3. 학과 정보");
						System.out.println("0. 로그아웃");
						
						System.out.print("메뉴 선택 => ");
						menu2 = sc.nextInt();
						sc.nextLine();
						
						System.out.println();
						
						if(menu2 == 1) { // 학생 관리
							
							System.out.println("[학생 관리]");
							System.out.println("1. 학생 정보 조회");
							System.out.println("2. 학생 정보 등록");
							System.out.println("3. 학생 정보 수정");
							System.out.println("4. 학생 정보 삭제");
							System.out.println("0. 뒤로가기");
							
							System.out.print("메뉴 선택 => ");
							int select = sc.nextInt();
							sc.nextLine();
							
							System.out.println();
							
							switch(select){
							case 1: studentView.studentSelect(); break; // 학생 정보 조회
							case 2: studentView.studentInsert(); break; // 학생 정보 등록
							case 3: studentView.studentUpdate(); break;// 학생 정보 수정
							case 4: studentView.studentDelete(); break; // 학생 정보 삭제
							case 0: break;
							default : System.out.println("메뉴에 있는 숫자만 입력해주세요!!");

						}
							
						}else if(menu2 == 2){ // 성적 관리
							System.out.println("[성적 관리]");
							System.out.println("1. 성적 조회");
							System.out.println("2. 성적 입력");
							System.out.println("3. 성적 수정");
							System.out.println("0. 뒤로가기");
							
							System.out.print("메뉴 선택 => ");
							int select = sc.nextInt();
							sc.nextLine();
							
							System.out.println();
							
							switch(select){
							case 1: gradeView.gradeSelect(); break; // 성적 조회
							case 2: gradeView.gradeInsert(); break; // 성적 등록
							case 3: gradeView.gradeUpdate(); break;// 성적 수정
							case 0: break;
							default : System.out.println("메뉴에 있는 숫자만 입력해주세요!!");
							}
							
						}else if(menu2 == 3){
							departmentView.departmentSearch();
						}else if(menu2 == 0){
							System.out.println("로그아웃 되었습니다..");
							System.out.println();
							loginAdmin = null;
						}else {
							System.out.println("메뉴에 있는 숫자만 입력해주세요!!");
						}
						
					}
				}
				
			}catch(InputMismatchException e) {
					System.out.println("메뉴에 있는 숫자만 입력해주세요!!");
					sc.nextLine();
			}
			
		}while(menu != 0);
		
	}

}
