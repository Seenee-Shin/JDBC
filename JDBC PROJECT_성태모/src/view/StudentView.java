package view;

import java.util.List;
import java.util.Scanner;

import model.service.StudentService;
import model.vo.Admin;
import model.vo.Student;

public class StudentView {
	
	private Scanner sc = new Scanner(System.in);
	private StudentService service = new StudentService();

	// 관리자 로그인
	public void adminLogin() {
		System.out.println("[관리자 계정 로그인]");
		System.out.print("ID : ");
		String adminId = sc.nextLine();
		
		System.out.print("PW : ");
		String adminPw = sc.nextLine();
		
		try {
			Admin admin = service.adminLogin(adminId, adminPw);
			
			if(admin == null) { // 로그인 실패
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				
			}else { // 로그인 성공
				System.out.println(admin.getAdminNm() + "관리자님 로그인 성공.");
				MainView.loginAdmin = admin;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// 학생 조회 메뉴 선택
	public void studentSelect() {
		int menu = -1;
		System.out.println("[학생 정보 조회]");
		System.out.println("1. 전체 학생 목록");
		System.out.println("2. 학과별 학생 목록");
		System.out.println("3. 학생 검색");
		System.out.println("0. 뒤로가기");
		System.out.print("메뉴 선택 => ");
		menu = sc.nextInt();
		sc.nextLine();
		
		System.out.println();
		switch(menu) {
		case 1: studentAllSelect(); break; 
		case 2: studentDpSelect(); break;
		case 3: studentSearch(); break;
		case 0: break;
		default : System.out.println("메뉴에 있는 숫자만 입력해주세요!!");
		}
		
	}


	
	// 일치하는 학생이 있는지 조회하는 출력 메소드
	private void printStudentList(List<Student> studentList) {
		
		if(studentList.isEmpty()) { // 조회 결과가 없을때
			System.out.println("학생 조회 결과가 없습니다.");
		}else {
			
			System.out.printf("학번     학년       학과           이름           주민번호               주소           \n"
							+ "----------------------------------------------------------------------------------------\n");
			
			for(Student list : studentList) {
				System.out.printf("%3d %3d %10s %8s %20s %10s\n",
									list.getStudentNO(), list.getStudentGrade(),
									list.getStudentDepartment(), list.getStudentName(), 
									list.getStudentSsn(), list.getStudentAddress());
				
			}
		}
		
	}
	
	
	// 학생 전체 목록 조회
	public void studentAllSelect() {
		
		System.out.println("[학생 전체 목록]");
		
		try {
			printStudentList(service.studentAllSelect());
			
		}catch(Exception e) {
			System.out.println("전체 학생 목록 조회 중 문제가 발생했습니다.");
		}


	}

	
	// 학과별 학생 조회
	public void studentDpSelect() {
		
		String dpName = null;
	
		System.out.println("[학과별 학생 조회]");
		System.out.println("1.기계       | 2.컴퓨터공학 | 3.전자공학\n"
				+ "4.국어국문학 | 5.사회복지학 | 6.법학");
		System.out.print("메뉴 선택 => ");
		int dpSelect = sc.nextInt();
		sc.nextLine();
		
		switch(dpSelect) {
		case 1: dpName = "기계과"; break;
		case 2: dpName = "컴퓨터공학과"; break;
		case 3: dpName = "전자공학과"; break;
		case 4: dpName = "국어국문학과"; break;
		case 5: dpName = "사회복지학과"; break;
		case 6: dpName = "법학과"; break;			
		default: System.out.println("숫자 1~6만 입력해주세요.");
		}
	
		try {
			// List<Student> student = service.studentDpSelect(dpName);
			printStudentList(service.studentDpSelect(dpName));
			
			
		} catch (Exception e) {
			System.out.println("학과별 학생 조회중 문제 발생!!");
			e.printStackTrace();
		} 
		
	}

	
	// 학생 검색
	public void studentSearch() {
		
			System.out.println("[학생 검색]");
			System.out.println("1. 학번으로 검색");
			System.out.println("2. 이름으로 검색");
			System.out.print("메뉴 선택 => ");
			char select = sc.nextLine().charAt(0);
			
			System.out.println();

				if(select == '1') {
					System.out.print("검색할 학생 학번 입력 : ");
					int search = sc.nextInt();
					sc.nextLine();
					
					System.out.println();
					
					try {
						Student student = service.studentSearch(search);
						
						System.out.printf("학번     학년       학과           이름           주민번호               주소           \n"
										+ "----------------------------------------------------------------------------------------\n");

						System.out.printf("%3d %3d %10s %8s %20s %10s\n",
									student.getStudentNO(), student.getStudentGrade(),
									student.getStudentDepartment(), student.getStudentName(), 
									student.getStudentSsn(), student.getStudentAddress());
						
					} catch (Exception e) {
						System.out.println("학생 검색 중 문제가 발생했습니다!");
					}
					
				}else if(select == '2') {
					System.out.print("검색할 학생 이름 입력 : ");
					String search2 = sc.nextLine();
					
					System.out.println();
					
					try {
						printStudentList(service.studentSearch2(search2));
						
					} catch (Exception e) {
						System.out.println("학생 검색 중 문제가 발생했습니다!");
						
					}
					
				}else {
					System.out.println("숫자 1 또는 2 만 입력해주세요!!");
					System.out.println();
				}


	}


	// 학생 정보 등록
	public void studentInsert() {
		System.out.println("[학생 정보 등록]");
		
		System.out.print("학번(8자리) : ");
		int stNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("학년 : ");
		int stGd = sc.nextInt();
		sc.nextLine();
		
		System.out.print("이름 : ");
		String stNm = sc.nextLine();
		
		System.out.print("주민등록번호(******-*******) : ");
		String stSs = sc.nextLine();
		
		System.out.print("주소 : ");
		String stAd = sc.nextLine();
		
		System.out.print("학과 : ");
		String stDp = sc.nextLine();
		
		
		Student student = new Student(stNo, stGd, stNm, stSs, stAd, stDp);
		
		try {
			int result = service.studentInsert(student);
			
			if(result > 0) {
				System.out.println("학생 추가에 성공하였습니다.");
				
			}else {
				System.out.println("학생 추가 실패!!");

			}
			
		} catch (Exception e) {
			System.out.println();
			System.out.println("   학생 추가 도중 문제가 발생했습니다!");
			System.out.println(("(-> 학번, 주민번호는 중복 될 수 없습니다.)"));
			e.printStackTrace();
		}
		
	}

	// 학생 정보 수정
	public void studentUpdate() {
		
		try {
			
			String stdName = null;
			
			while(true) { // 이름이 일치하는 학생이 있는지 검사
				System.out.println("[학생 정보 수정]");
				System.out.print("수정할 학생 이름 : ");
				stdName = sc.nextLine();
				
				int result2 = service.nameCheck(stdName);
				
				if(result2 > 0) {
					System.out.println();
					break;
					
				}else {
					System.out.println("일치하는 학생이 없습니다! 다시 입력해주세요.");

				}
				
			}
			
			System.out.println("[수정 정보 입력]");
			
			System.out.print("학번(8자리) : ");
			int stNo = sc.nextInt();
			sc.nextLine();
			
			System.out.print("학년 : ");
			int stGd = sc.nextInt();
			sc.nextLine();
			
			System.out.print("이름 : ");
			String stNm = sc.nextLine();
			
			System.out.print("주민등록번호(******-*******) : ");
			String stSs = sc.nextLine();
			
			System.out.print("주소 : ");
			String stAd = sc.nextLine();
			
			System.out.print("학과 : ");
			String stDp = sc.nextLine();
			
			Student student = new Student(stNo, stGd, stNm, stSs, stAd, stDp);
			
			int result = service.studentUpdate(student, stdName);
			
			if(result > 0) {
				System.out.println("학생 정보 수정 성공!");
			}else {
				System.out.println("학생 정보 수정 실패!");
			}
			
		}catch(Exception e) {
			System.out.println("학생 정보 수정 중 문제 발생!!");
			e.printStackTrace();
		}

	}

	// 학생 정보 삭제
	public void studentDelete() {
		
		String stdName = null;
		
		try {
			while(true) { // 이름이 일치하는 학생이 있는지 검사
				System.out.println("[학생 정보 삭제]");
				System.out.print("삭제할 학생 이름 : ");
				stdName = sc.nextLine();
				
				int result = service.nameCheck(stdName);
				
				if(result > 0) {
					System.out.println();
					break;
					
				}else {
					System.out.println("일치하는 학생이 없습니다! 다시 입력해주세요.");
	
				}
				
			}
			
			while(true) {
				System.out.println("정말로 삭제하시겠습니까?(Y/N) : ");
				char answer = sc.next().toUpperCase().charAt(0);
				
				if(answer == 'Y') {
					service.studentDelete(stdName);
					System.out.println(stdName + " 학생 정보가 삭제 되었습니다.");
					sc.nextLine();
					break;
					
				}else if(answer == 'N') {
					System.out.println("삭제를 취소하였습니다.");
					sc.nextLine();
					break;
				}else {
					System.out.println("'Y/N' 중 하나를 입력해주세요");
					sc.nextLine();
				}
			}
			
		}catch(Exception e) {
			System.out.println("학생 정보 삭제중 문제 발생!!");
			e.printStackTrace();
		}

	}





}
