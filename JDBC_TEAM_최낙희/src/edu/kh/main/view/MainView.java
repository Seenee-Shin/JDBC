package edu.kh.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.student.view.StudentView;

public class MainView {

	// 필드
	private Scanner sc = new Scanner(System.in);
	
	private StudentView studentView = new StudentView();
	
	
	public void displayMenu() {
		
		int sel = -1;
		
		do {
			
			try {
				System.out.println("===========================================");
				// 공통 기능
				System.out.println("1. 전체 학생 조회");
				System.out.println("2. 학생 등록 추가");
				System.out.println("3. 학생 정보 수정"); //(학과명, 교수번호만)
				System.out.println("4. 학생 정보 삭제"); // 학번을 입력받아 일치하는 학생 정보 삭제
				// 추가 기능
				System.out.println("5. 학번으로 학생 정보 조회");
				System.out.println("6. 학생 검색(학과명 or 주소지)"); // 학과명 or 주소지를 입력받아 일치하는 학생(들) 정보 조회
									// 주소지로 검색 시) "경기도"만 검색했을 경우 "경기도"에 사는 학생 모두 조회
				System.out.println("7. 학번,이름을 입력받아 일치하는 학생의 담당 교수명 조회"); 
			
				System.out.println("0. 프로그램 종료");
				System.out.println("===========================================");
				
				System.out.print("메뉴 번호 선택 >> ");
				sel = sc.nextInt();
				sc.nextLine(); // 개행 문자 제거
				
				System.out.println(); // 줄바꿈
				
				
				switch (sel) {
					case 1: studentView.selectAll(); break; // 1. 전체 학생 조회 
					case 2: studentView.insertStudent(); break; // 2. 학생 등록 추가
					case 3: studentView.updateStudent(); break; // 3. 학생 정보 수정(학과명, 교수번호만)
					case 4: studentView.deleteStudent(); break; // 4. 학생 정보 삭제 // 학번을 입력받아 일치하는 학생 정보 삭제
					case 5: studentView.selectOne(); break; // 5. 학번으로 학생 정보 조회
					case 6: studentView.searchStudent(); break; // 6. 학생 검색 (학과명 or 주소지를 검색하여 해당하는 학생(들) 정보 조회)
					case 7: studentView.selectPFName(); break; // 7. 학번,이름을 입력받아 일치하는 학생의 담당 교수명 조회
					
					case 0: System.out.println("프로그램을 종료합니다.."); break;
					
					default: System.out.println("메뉴에 있는 번호만 입력해주세요.");
				}
				
				
			}catch(InputMismatchException e) {
				System.out.println("메뉴에 있는 숫자만 입력해주세요.");
				sc.nextLine();
			}
			
			
		}while(sel != 0);
	}
	
	
}
