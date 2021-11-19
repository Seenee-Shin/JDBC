package view;

import java.util.List;
import java.util.Scanner;

import model.service.GradeService;
import model.vo.Grade;



public class GradeView {

	private Scanner sc = new Scanner(System.in);
	private GradeService service = new GradeService();
	
	
	// 성적 조회
	public void gradeSelect() {
		int menu = -1;
		System.out.println("[성적 조회]");
		System.out.println("1. 전체 성적 조회");
		System.out.println("2. 학생 성적 검색");
		System.out.println("3. 장학금 대상자 조회");
		System.out.println("0. 뒤로가기");
		System.out.print("메뉴 선택 => ");
		menu = sc.nextInt();
		sc.nextLine();
		
		System.out.println();
		switch(menu) {
		case 1: gradeAll(); break; 
		case 2: gradeSearch(); break;
		case 3: award(); break;
		case 0: break;
		default : System.out.println("메뉴에 있는 숫자만 입력해주세요!!");
		}
	
	}

	
	// 출력 메소드
	private void printGradeList(List<Grade> gradeList) {
		
		if(gradeList.isEmpty()) { 
			System.out.println("성적 조회 결과가 없습니다.");
		}else {
			
			System.out.printf("학번        이름     1학기   2학기  평균  등급\n"
							+ "------------------------------------------------\n");
			
			for(Grade grade : gradeList) {
				System.out.printf("%5d %5s    %.1f    %.1f    %.1f %5s\n",
						grade.getStudentNo(), grade.getStudentNm(), 
						grade.getPoint1(), grade.getPoint2(),
						grade.getAvg(), grade.getRate());
			}
		}
		
	}
	
	
	// 전체 성적 조회
	private void gradeAll() {
		try {
			System.out.println("[전체 성적 조회]");
			printGradeList(service.gradeAll());
			
			
		} catch (Exception e) {
			System.out.println("성적 조회 중 문제가 발생했습니다!!");
			e.printStackTrace();
		}
		
	}
	
	// 성적 검색
	private void gradeSearch() {
		System.out.println("[학생 성적 검색]");
		System.out.print("학번 입력 : ");
		int studentNo = sc.nextInt();
		sc.nextLine();
		
		try {
			Grade grade = service.gradeSearch(studentNo);
			
			if(grade == null) {
				System.out.println("학번의 학생 성적이 존재하지 않습니다.");
			}else {
				
				System.out.printf("학번        이름     1학기   2학기  평균  등급\n"
						+ "------------------------------------------------\n");
		
				System.out.printf("%5d %5s    %.1f    %.1f    %.1f %5s\n",
						grade.getStudentNo(), grade.getStudentNm(), 
						grade.getPoint1(), grade.getPoint2(),
						grade.getAvg(), grade.getRate());
				
			}
			
		} catch (Exception e) {
			System.out.println("성적 검색 중 문제가 발생했습니다!!");
			e.printStackTrace();
		}
		
	}
	
	// 장학금 대상자 찾기
	private void award() {
		System.out.println("등급 'A' 이상시 장학금 요건에 충족합니다!");
		System.out.println("-----------------------------------------");
		try {
			printGradeList(service.award());
			
			
		} catch (Exception e) {
			System.out.println("성적 조회 중 문제가 발생했습니다!!");
			e.printStackTrace();
		}
		
	}


	
	// 학번으로 학생 등록 확인
	private int checkNo() throws Exception{
		System.out.print("수정할 학번 입력 : ");
		int check = sc.nextInt();
		sc.nextLine();
		
		int result = service.checkNo(check);
		
		if(result > 0) { 
			System.out.println("확인 완료");
			System.out.println();
			return check;
			
		}else {
			return 0;
		}
		
	}
	
	
	
	
	public void gradeInsert() {
		System.out.println("[성적 등록]");
		
		System.out.print("학번(8자리) : ");
		int gdNo = sc.nextInt();
		sc.nextLine();

		System.out.print("이름 : ");
		String gdNm = sc.nextLine();
		
		System.out.print("1학기 평점 : ");
		double p1No = sc.nextDouble();
		sc.nextLine();
		
		System.out.print("2학기 평점 : ");
		double p2No = sc.nextDouble();
		sc.nextLine();
		
		double avg = (p1No + p2No)/2.0;

		String rate = null;
		if(avg >= 4.5) {
			rate = "A+";
		}else if(avg >= 4) {
			rate = "A";
		}else if(avg >= 3.5) {
			rate = "B+";
		}else if(avg >= 3) {
			rate = "B";
		}else if(avg >=2.5) {
			rate = "C+";
		}else if(avg >= 2) {
			rate = "C";
		}else {
			rate = "F";
		}
		
		Grade grade = new Grade(gdNo, gdNm, p1No, p2No, avg, rate);
		
		try {
			int result = service.gradeInsert(grade);
			
			if(result > 0) {
				System.out.println(gdNm + "학생 성적 추가 성공!!");
				
			}else {
				System.out.println("성적 추가 실패!!");

			}
			
		} catch (Exception e) {
			System.out.println();
			System.out.println("   성적 추가 도중 문제가 발생했습니다!");
			System.out.println(("(-> 이미 등록된 성적이 등록된 학번입니다.)"));
			e.printStackTrace();
		}
		
	}


	// 성적 수정
	public void gradeUpdate() {
		
		System.out.println("[성적 수정]");
		
		try {

			int check = checkNo();
			
			if( check == 0) {
				System.out.println("입력하신 학번의 학생이 존재하지 않습니다.");
			}else {
				
				System.out.println("[수정값 입력]");
				
				System.out.print("학번(8자리) : ");
				int gdNo = sc.nextInt();
				sc.nextLine();

				System.out.print("이름 : ");
				String gdNm = sc.nextLine();
				
				System.out.print("1학기 평점 : ");
				double p1No = sc.nextDouble();
				sc.nextLine();
				
				System.out.print("2학기 평점 : ");
				double p2No = sc.nextDouble();
				sc.nextLine();
				
				double avg = (p1No + p2No)/2.0;

				String rate = null;
				if(avg >= 4.5) {
					rate = "A+";
				}else if(avg >= 4) {
					rate = "A";
				}else if(avg >= 3.5) {
					rate = "B+";
				}else if(avg >= 3) {
					rate = "B";
				}else if(avg >=2.5) {
					rate = "C+";
				}else if(avg >= 2) {
					rate = "C";
				}else {
					rate = "F";
				}
				
				
				Grade grade = new Grade(gdNo, gdNm, p1No, p2No, avg, rate);
				
	
					int result = service.gradeUpdate(check, grade);
					
					if(result > 0) {
						System.out.println(gdNm + "학생 성적 수정 성공!!");
						
					}else {
						System.out.println("성적 수정 실패!!");
						
					}
			}
					
		}catch (Exception e) {
				System.out.println();
				System.out.println("성적 수정 도중 문제가 발생했습니다!");
				e.printStackTrace();
		}
			
	}
		
}
		

	
	
	
