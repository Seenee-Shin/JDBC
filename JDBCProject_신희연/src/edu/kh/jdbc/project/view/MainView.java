                             package edu.kh.jdbc.project.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	private ProfessorView professorView = new ProfessorView();
	private StudentView studentView = new StudentView();
	
	public void displayMenu() {
		
		int sel = -1;
		
		do {
			try {
				System.out.println("=======학생 관리 프로그램========");
				System.out.println("1. 학생 관리");
				System.out.println("2. 학과 & 교수 관리 ");
				System.out.println("0. 프로그램 종료 ");
				System.out.println("=================================");				
				System.out.print("메뉴 선택 : ");
				sel = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
					switch(sel) {
					
					//메뉴 1 이면 학생관리메뉴 실행 
					//메뉴 2 이면 학과.교수관리 메뉴 실행
					case 1: 
						
						System.out.println("-- 학생 관리 메뉴 --");
						System.out.println("1. 학생 정보 조회");
						System.out.println("2. 학생 정보 수정");
						System.out.println("3. 학생 정보 추가");
						System.out.println("4. 퇴학생 정보 삭제");
						System.out.println("0. 프로그램 종료");
						System.out.print("메뉴 선택 : ");
						sel = sc.nextInt();
						sc.nextLine();
						System.out.println();
						
						switch(sel) {
						case 1 : studentView.studentInfo(); break;
						case 2 : studentView.updateInfo(); break;
						case 3 : studentView.addStudent(); break;
						case 4 : studentView.deleteStudent(); break;
						case 0: System.out.println("프로그램을 종료합니다."); break;
						default : System.out.println("메뉴 번호를 잘못 입력하셨습니다.")
						;
						}
						break;
						
					case 2:
						
						System.out.println("-- 학과 & 교수 관리 메뉴 --");
						System.out.println("1. 학과사무실 조회");
						System.out.println("2. 학과장 조회");						
						System.out.println("3. 교수 정보 수정");
						System.out.println("4. 퇴임 교수 정보 삭제");
						System.out.println("0. 프로그램 종료");
						System.out.print("메뉴 선택 : ");
						sel = sc.nextInt();
						sc.nextLine();
						System.out.println();
						
						switch(sel) {
						case 1 : professorView.officeInfo(); break;
						case 2 : professorView.deanInfo(); break;
						case 3 : professorView.updateInfo(); break;
						case 4 : professorView.deleteProfessor(); break;
						case 0: System.out.println("프로그램을 종료합니다."); break;
						default : System.out.println("메뉴 번호를 잘못 입력하셨습니다.");
						}
						
						break;
	
					case 0: System.out.println("프로그램을 종료합니다."); break;
					default : System.out.println("메뉴 번호를 잘못 입력하셨습니다.");
					}
				}
				
				catch(InputMismatchException e) {
					System.out.println("메뉴 번호만 입력해주세요");
					sc.nextLine();
				}
			}
		while(sel !=0);
	}
}
