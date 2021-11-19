package mini.jdbc.view;

import java.util.List;
import java.util.Scanner;

import mini.jdbc.member.model.service.MemberService;
import mini.jdbc.member.model.vo.Member;

public class MemberView {

	private Scanner sc = new Scanner(System.in);

	private MemberService service = new MemberService();

	// 학생 정보 수정
	public void updateMember() {
		System.out.println("@ 학생 정보 수정 @");
		System.out.println();

		System.out.println("본인 학번 입력 : ");
		int memberNo = sc.nextInt();
		sc.nextLine();

		try {
			int result = service.checkMember(memberNo);

			if (result == 0) {
				System.out.println("조회된 학번이 없습니다.");
			} else {

				System.out.println("수정할 학과 : ");
				String dName = sc.nextLine();

				System.out.println("수정할 이름 : ");
				String memberNm = sc.nextLine();

				System.out.println("수정할 주소 (end 입력 시 종료) : ");
				String adr = "";
				System.out.println();

				while (true) {
					String temp = sc.nextLine();

					if (temp.equals("end")) {
						break;
					} else {
						adr += temp + "\n";
					}
				}
				// 입력받은 값을 Member 객체에 저장
				Member member = new Member(memberNo, dName, memberNm, adr);

				int result2 = service.updateMember(member);

				if (result2 > 0) { // 수정 성공 상태 == 1
					System.out.println("@ 학생 정보 수정 성공 @");

				} else {
					System.out.println("@ 학생 정보 수정 실패 @");
				}
			}
		} catch (Exception e) {
			System.out.println("학생 정보 수정중 문제 발생");
			e.printStackTrace();
		}
	}

	// 학생 정보 삭제
	public void deleteMember() {

		System.out.println("@ 학생 정보 삭제 @");

		System.out.println("학번 입력 : ");
		int memberNo = sc.nextInt();
		sc.nextLine();

		try {
			int result = service.checkMember(memberNo);

			while (true) {
				if (result == 0) {
					System.out.println();
				}else {
				System.out.println("정말로 삭제 하시겠습니까? (Y / N) : ");
				char yn = sc.nextLine().toUpperCase().charAt(0);

				if (yn == 'Y') {
					try {
						int result3 = service.deleteMember(memberNo);
						if (result3 > 0) {
							System.out.println("삭제 성공");
						} else {
							System.out.println("학번이 일치하지 않습니다.");
						}
					} catch (Exception e) {
						System.out.println("삭제 중 문제가 발생했습니다. ");
						e.printStackTrace();
					}
					break;

				} else if (yn == 'N') {
					System.out.println("삭제를 취소합니다.");

					break;
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 학생 정보 등록
	public void insertMember() {
		System.out.println("@ 학생 정보 등록 @");

		System.out.print("학번 : ");
		int memberNo = sc.nextInt();

		System.out.print("학년 : ");
		String memberGr = sc.next();
		System.out.print("이름 : ");
		String memberNm = sc.next();
		System.out.println(" - 포함 13자리를 입력해주세요");
		System.out.print("주민등록번호 : ");
		String memberSsn = sc.next();
		System.out.print("학과 : ");
		String dName = sc.next();
		System.out.print("주소(종료 시 end 입력 후 엔터) : ");
		String adr = "";

		while (true) {
			String temp = sc.nextLine();

			if (temp.equals("end")) {
				break;
			} else {
				adr += temp + " "; //코드 리뷰 중 수정 
			}
		}

		// 입력받은 값을 Member 객체에 저장
		Member member = new Member(memberNo, memberGr, memberNm, memberSsn, adr, dName);

		try {

			int result = service.insertMember(member);

			if (result > 0) {
				System.out.println("회원 정보 등록 성공");
			} else {
				System.out.println("회원 정보 등록 실패");
			}

		} catch (Exception e) {
			System.out.println("회원 정보 등록 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}

	

	private void printMemberList(List<Member> memberList) {

		if (memberList.isEmpty()) {
			System.out.println("조회 결과가 없습니다.");
		} else {
			System.out.printf("번호	  학번		 학년	  학과		    이름	 주민등록번호(2자리)	 주소\n"
					+ "-----------------------------------------------------------------------------------------------\n");
			for (Member member : memberList) {
				System.out.printf("%2d %15d %10s %10s %10s %15s %15s \n", member.getSequenceNo(), member.getMemberNo(),
						member.getMemberGr(), member.getdName(), member.getMemberNm(), member.getMemberSsn(),
						member.getAdr());
			}

		}
	}

	// 학생 정보 리스트 조회
	public void selectMemerList() {
		System.out.println("@ 학생리스트 조회 @");
		System.out.println();

		try {
			List<Member> MemberList = service.selectMemberList();
			printMemberList(MemberList);

		} catch (Exception e) {
			System.out.println("게시글 목록 조회 중 문제 발생");
			e.printStackTrace();
		}

	}

	// 학생 정보 상세 조회
	public void selectMember() {
		System.out.println("@ 학생 정보 상세 조회 @");

		System.out.println("등록된 번호 입력 : ");
		int sequenceNo = sc.nextInt();
		sc.nextLine();

		try {
			Member member = service.selectMember(sequenceNo);

			if (member != null) {
				System.out.printf("%2d %15s %10s %10s %10s %15s \n",
									member.getMemberNo(), member.getMemberGr(),
									member.getMemberNm(), member.getMemberSsn(),
									member.getAdr(), member.getdName());
			} else {
				System.out.println("조회한 학번의 정보가 없습니다.");
			}

		} catch (Exception e) {
			System.out.println("학생 정보 상세 조회 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}

}
