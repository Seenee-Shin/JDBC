package mini.jdbc.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import mini.jdbc.member.model.vo.Member;

public class MainView {

	private Scanner sc = new Scanner(System.in);

	private MemberView memberView = new MemberView();
	
	
	public void mainMenu() {
		
		int sel = -1;
		
		do {
			
			try {
					System.out.println("-----------------");
					System.out.println();
					System.out.println("@ 학생 전용 메뉴 @");
					System.out.println("1. 전체 학생리스트 조회");
					System.out.println("2. 학생 정보 수정");
					System.out.println("3. 학생 등록 추가");
					System.out.println("4. 퇴학생 삭제(학생 정보 삭제)");
					System.out.println("5. 학생 정보 상세 조회");
					System.out.println();
					System.out.println("0. 프로그램 종료");
					System.out.println();
					
					System.out.println("-----------------");
					System.out.println();
					System.out.println();
					
					System.out.println("메뉴 번호 선택 >> ");
					sel = sc.nextInt();
					sc.nextLine();
					
					System.out.println();
					
					switch (sel) {
					case 1:
						memberView.selectMemerList(); // 전체 학생리스트 조회
						break;
					case 2:
						memberView.updateMember(); // 학생 정보 수정
						break;
					case 3:
						memberView.insertMember(); // 학생 정보 삽입
						break;
					case 4:
						memberView.deleteMember(); // 학생 정보 삭제 -> null == 퇴학생
						break;
					case 5:
						memberView.selectMember(); // 학생 정보 상세 조회
						break;	
					
						
					case 0:
						System.out.println("프로그램이 정상적으로 종료되었습니다.");
						break;
						
					default:
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
						break;
					}
				
			} catch (InputMismatchException e) {
				System.out.println("메뉴에 있는 숫자만 입력");
				sc.nextLine();
			}
			
			
		} while (sel != 0);
		
	}
	
}
