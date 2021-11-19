package edu.kh.jdbc.view;

import java.util.Scanner;

import edu.kh.jdbc.member.model.service.MemberService;
import edu.kh.jdbc.member.model.vo.Member;

public class MemberView {
	//필드 
	private Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();
	
	
	// 1.login
	public void login() {
		System.out.println("[로그인]");
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String memberPw = sc.nextLine();
		
		//login service 호출, 결과 반환 
		try {
			Member member = service.login(memberId, memberPw);
			
			if(member == null) {
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
			}else {
				System.out.println(member.getMemberNm() + "님 환영합니다.");
				
				//static 
				MainView.loginMember = member;
				
				
			}
		} catch (Exception e) {
			System.out.println("로그인 과정에서 문제가 발생했습니다");
			e.printStackTrace();
		}
		
	}
	// 2. signUp
	public void signUp() {
		
		try {
			String inputId = null;
			
			while(true) {
				
				int result = 0;
				
				System.out.print("ID : ");
				inputId = sc.nextLine();
				
				// 중복검사 후 
				// 중복 -> 아이디 다시 입력
				result = service.idDupCheck(inputId);
				
				if(result > 0) {
					System.out.println("이미 사용 중인 아이디 입니다.");
				}else {
					System.out.println("사용 가능한 아이디입니다.");
					break; //while문 종료
				}
			}
			// 중복 x -> 가입할 회원정보 입력 
			System.out.print("비밀번호 : ");
			String inputPw = sc.nextLine();
			
			System.out.print("이름 : ");
			String inputNm = sc.nextLine();
			
			System.out.print("전화 번호 : ");
			String inputPhone = sc.nextLine();
			
			// 매개변수 저장 생성자 
			Member member = new Member(inputId, inputPw, inputNm, inputPhone);
			
			// SignUp Service 
			int signResult = service.signUp(member);
			
			
			if(signResult > 0) {
				System.out.println("회원가입이 완료 되었습니다.");
			}else {
				System.out.println("회원가입에 실패하였습니다.");
			}
			
			
		} catch (Exception e) {
			System.out.println("회원가입 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
	// [login menu] case1 : myInfo
	public void myInfo() {
		System.out.println("[내정보 조회]");
		// 로그인한 상태의 아이디가 있기 때문에 loginMember에서 가져온다.
		System.out.print("아이디 : " + MainView.loginMember.getMemberId());
		System.out.print("이름 : " + MainView.loginMember.getMemberNo());
		System.out.print("전화번호 : " + MainView.loginMember.getPhone());
	}
	
	// [login menu] case2 : updateInfo
	public void updateInfo() {
		System.out.println("[내 정보 수정]");
		
		//비밀번호 중복 확인 
		String inputPw = null;
		try {
		
		while(true) {
			System.out.print("비밀번호 : ");
			inputPw = sc.nextLine();
			int checkResult = service.pwDupCheck(inputPw);
			
			if(checkResult == 0) {
				System.out.println("비밀번호를 잘못 입력 하셨습니다.");
			}else {
				System.out.println("[수정할 정보 입력]");
				break; //while문 종료
			}
		}
		
		//회원번호 수정
		System.out.print("수정할 비밀번호 : ");
		String newPw = sc.nextLine();

		System.out.print("전화 번호 : ");
		String newPhone = sc.nextLine();
		
		int result = service.updateInfo(newPw, newPhone);
		if(result>0) {
			System.out.println("회원 정보가 수정되었습니다.");
			
		}else {
			System.out.println("회원 정보 수정에 실패 하였습니다.");
		}
		
		}catch(Exception e) {
			System.out.println();
			e.printStackTrace();
		}
		
	}
	public void deleteMember() {
		System.out.println("[회원 탈퇴]");
		
		String inputPw = null;
		
		try {
			while(true) {
				
				System.out.print("비밀번호 : ");
				inputPw = sc.nextLine();
				int checkResult = service.pwDupCheck(inputPw);
				
				if(checkResult == 0) {
					System.out.println("비밀번호를 잘못 입력 하셨습니다.");
				}else {
					break; //while문 종료
				}
			}
			
			// 탈퇴 
			System.out.print("정말로 탈퇴 하시겠습니까? (y/n) : ");
			char input = sc.nextLine().toLowerCase().charAt(0);
			//.toLowerCase() / .toUpperCase() 대,소문자 자동 변환 
			
			while(true) {
				if(input == 'y') {
					
					int result = service.deleteMember(inputPw);
					
					if(result>0) {
					System.out.println("회원 탈퇴가 정상적으로 되었습니다.");
					MainView.loginMember = null; 
					}else {
						System.out.println("회원 탈퇴에 실패 하였습니다.");
					}
					
					break;
					
				}else if(input == 'n'){
					System.out.println("회원 탈퇴를 취소하였습니다.");
					break;
					
				}else { 
					
					System.out.println("잘못 입력하셨습니다.");
					
					}
			}
			}catch(Exception e){
				System.out.println("회원 탈퇴에 실패 하였습니다.");
			e.printStackTrace();
		}
	}
}
