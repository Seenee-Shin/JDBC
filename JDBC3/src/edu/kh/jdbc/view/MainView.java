package edu.kh.jdbc.view;

import java.util.Scanner;

import edu.kh.jdbc.member.model.vo.Member;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	private MemberView memberView = new MemberView();
	private BoardView boardView = new BoardView();
	
	public static Member loginMember = null;
	//로그인 회원정보 변수 선언 (static : 프로젝트 내 공유 )
	
	public void displayMenu() {
		
		int sel = -1; //메뉴 번호 저장 변수 
		
		do {
			try {
				
				if(loginMember == null) {//로그인 안된경우 메뉴
					System.out.println("-- 메인 메뉴 --");
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 종료");
					System.out.println();
					
					System.out.print("메뉴 선택 : ");
					sel = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(sel) {
					
					case 1: memberView.login(); break;
					case 2: memberView.signUp(); break;
					case 0: System.out.println("프로그램을 종료합니다."); break;
					default : System.out.println("메뉴 번호를 잘못 입력하셨습니다.");
					}
					
				// 로그인 후 메뉴 
				}else{					
					System.out.println();
					
					System.out.println("-- 회원 메뉴 --");
					System.out.println("1. 내 정보 조회");
					System.out.println("2. 내 정보 수정");
					System.out.println("3. 회원 탈퇴");
					
					System.out.println("4. 게시글 작성");
					System.out.println("5. 게시글 수정");
					//게시글 번호 입력 -> 확인
					// 본인게시글만 수정 가능 (if)
					// update 
					System.out.println("6. 게시글 삭제");
					System.out.println("7. 게시글 목록 조회");
					System.out.println("8. 게시글 상세");
					System.out.println("9. 게시글 상세 검색");
					
					System.out.println("10. 로그아웃");
					
					System.out.print("메뉴 선택 >> ");
					sel = sc.nextInt();
					sc.nextLine();
					
					switch(sel) {
					case 1 : memberView.myInfo();  break;
					case 2 : memberView.updateInfo(); break;
					case 3 : memberView.deleteMember(); break;
					case 4 : boardView.insertBoard(); break;
					case 5 : boardView.updateBoard();break;
					case 6 : boardView.deleteBoard();break;
					case 7 : boardView.selectBoardList();break;
					case 8 : boardView.selectBoard(); break;
					case 9 : boardView.searchBoard(); break;
					
					case 10 : System.out.println("로그아웃 되었습니다.");
							  loginMember = null; //login정보 없앰 (null로 바뀌면 메인메뉴로 돌아감 -> if문 조건일치)
							  break;
					
					default : System.out.println("메뉴 번호를 다시 입력해 주세요");
					
					}
				}
					
				
			}catch(Exception e) {
				System.out.println("메뉴 번호만 입력해 주세요");
				sc.nextLine();
				e.printStackTrace();
			}
			
		}while(sel !=0);
		
	}
}
