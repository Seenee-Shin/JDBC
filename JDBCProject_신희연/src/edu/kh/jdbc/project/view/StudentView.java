package edu.kh.jdbc.project.view;

import java.util.List;
import java.util.Scanner;


import edu.kh.jdbc.project.student.model.serviece.StudentService;
import edu.kh.jdbc.project.student.model.vo.Student;

public class StudentView {
	private Scanner sc =new Scanner(System.in);
	private StudentService service = new StudentService();
	

	/** 조회 결과 출력용 메소드 
	 * @param list
	 * @throws Exception
	 */
	private void printResult(List<Student> list) throws Exception {
		if(list.isEmpty()) {
			System.out.println("조회결과 없음 ");
			
		}else {
			for(Student student : list) {
				
				System.out.printf("학번 : %s | 학년 : %s  | 학과 : %s | 이름 : %s | 주민번호 : %s | 주소 : %s \n",
						student.getStudentNo(),student.getGrade(), student.getsDeptName(),student.getStudentName(),
						student.getStudentSSN(), student.getStudentAddress());
				
			}
		}
	}
	
	/**
	 * 학생 조회 메소드
	 */
	public void studentInfo() {
		int searchKey = 0;
		
		while(true) {
			
			System.out.println("1.학과별 학생 조회 | 2. 학년별 학생 조회 | 3. 학번으로 학생 조회");
			System.out.print("검색카테고리 선택 :");
			searchKey = sc.nextInt();
			sc.nextLine();
			
			if(searchKey > 0 && searchKey < 4) {
				break;
			}else {
				System.out.println("메뉴번호만 입력해주세요");
			}
		}
		
		System.out.println("검색 입력 : ");
		String searchValue = sc.nextLine();
		
		try {
			List<Student> list = service.studentInfo(searchKey ,searchValue);
			
			if(list.isEmpty()) {
				System.out.println("학생 정보가 없습니다.");
			}else {
				printResult(list);	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("문제발생");
		}
		
	}

	/**
	 * 학생 정보 수정 
	 */
	public void updateInfo() {
		int inputNo = 0;
		String inputName = null;;
		int searchKey = 0;
		
		try {
			//학생정보 확인
			while(true) {
				System.out.print("수정할 학생 학번 입력 : ");
				inputNo = sc.nextInt();
				sc.nextLine();
				
				System.out.print("수정할 학생 이름 입력 : ");
				inputName = sc.nextLine();
				int checkStudent = service.checkStudent(inputNo,inputName);
				
				
				if(checkStudent == 0) {
					System.out.println("학생 정보를 잘못 입력 하셨습니다.");
				}else {
					System.out.println("[재학생 정보 수정 메뉴]");
					break; //while문 종료
				}
			}
			//수정할 정보 선택
			
			while(true) {
				
				System.out.println("1. 전과 정보 입력 | 2. 학생 개인 정보 수정(주소)");
				System.out.print("검색카테고리 선택 :");
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
				System.out.print("전과 학과 입력 : ");
			}else {
				System.out.println("변경할 주소 입력 : ");
			}
			String searchValue = sc.nextLine();
			
			int result = service.updateStudent(inputNo, inputName, searchKey,searchValue);
			
			if(result>0) {
				System.out.println("학생 정보가 수정되었습니다.");
				
			}else {
				System.out.println("학생 정보 수정에 실패 하였습니다.");
			}
			
		}catch(Exception e){
			System.out.println("학생 정보 수정 실패");
			e.printStackTrace();
		}
	}

	public void addStudent() {
		int result = 0;
		System.out.println("[신/편입생 정보 입력]");
		System.out.print("신입생은 1, 편입생은 해당 학년을 입력하세요 :");
		String grade = sc.nextLine();
		
		System.out.print("학과 :");
		String dept = sc.nextLine();
		
		System.out.print("학생 이름:");
		String studentName = sc.nextLine();
		
		System.out.print("학생 주민번호:");
		String studentSSN = sc.nextLine();
		
		System.out.print("학생 주소:");
		String studentAddress = sc.nextLine();
		try {
			
			Student student = new Student(grade, studentName, studentSSN, studentAddress, dept);
		
			result = service.addStudent(student);
			
			if(result > 0) {
				System.out.println("신입생 입력 완료 하였습니다.");
			}else {
				System.out.println("신입생 입력에 실패 하였습니다.");
			}
		
		}catch(Exception e){
			System.err.println("학생 입력 중 오류가 발생했습니다.");
			e.printStackTrace();
			
		}
	}

	public void deleteStudent() {
		int result = 0;
		System.out.println("[퇴학생 정보 삭제]");
		int inputNo = 0;
		String inputName = null;;
		
		try {
			//학생정보 확인
			while(true) {
				System.out.print("삭제할 학생 학번 입력 : ");
				inputNo = sc.nextInt();
				sc.nextLine();
				
				System.out.print("삭제할 학생 이름 입력 : ");
				inputName = sc.nextLine();
				int checkStudent = service.checkStudent(inputNo,inputName);
				
				if(checkStudent == 0) {
					System.out.println("학생 정보를 잘못 입력 하셨습니다.");
				}else {
					System.out.println(inputName +"학생 정보를 삭제 하시겠습니까? (y/n)");
					break;
				}
			}
			char input = sc.nextLine().toLowerCase().charAt(0);
			
			while(true) {
				if(input == 'y') {
					result = service.deleteStudent(inputNo,inputName);
					
					if(result > 0) {
						System.out.println("정상적으로 학생 정보가 삭제되었습니다.");
					}else {
						System.out.println("학생 정보 삭제에 실패하였습니다.");
					}
					break;
				}else if(input == 'n') {
					System.out.println("학생 정보 삭제를 취소하였습니다.");
					break;
				}else {
					System.out.println("잘못 입력하셨습니다.");
				}	
			}
		}catch(Exception e) {
			System.out.println("학생 정보 삭제 중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}

}
