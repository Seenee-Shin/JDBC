package edu.kh.student.view;

import java.util.List;
import java.util.Scanner;

import edu.kh.student.model.service.StudentService;
import edu.kh.student.model.vo.Student;

public class StudentView {

	private Scanner sc = new Scanner(System.in);
	private StudentService service = new StudentService();
	
	
	
	// 1. 전체 학생 조회
	public void selectAll() {
		System.out.println("[전체 학생 조회]");
		
		try {
			List<Student> list = service.selectAll();
			
			if(list == null) {
				System.out.println("오류가 발생하여 조회에 실패했습니다.");
			
			}else if( list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			
			}else {
				for(Student student : list) {
//					System.out.println(student);
					System.out.printf(" | %d | 학번 : %10d | 학년 : %d | 이름 : %5s | 주민등록번호 : %15s | 주소 : %40s  | 학과명 : %15s | 교수번호 : %3d | \n", 
							 student.getSequenceNo(), 
							 student.getStudentNo(), 
							 student.getStudentGrade(),
							 student.getStudentName(),
							 student.getStudentSSN(),
							 student.getStudentAddress(),
							 student.getDepartmentName(),
							 student.getProfessorNo());
				}
			}
			
			
			
		}catch(Exception e) {
			System.out.println("전체 학생 조회 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
	}

	
	
	// 2. 학생 등록 추가
	public void insertStudent() {
		System.out.println("[학생 등록 추가]");
		
		System.out.print("등록할 학생 번호 : ");
		int studentNo = sc.nextInt();
		
		System.out.print("등록할 학생 학년 : ");
		int studentGrade = sc.nextInt();
		
		System.out.print("등록할 학생 이름 : ");
		String studentName = sc.next();
		sc.nextLine();
		
		System.out.print("등록할 학생 주민번호 : ");
		String studentSSN = sc.nextLine();
		
		System.out.print("등록할 학생 주소 : ");
		String studentAddress = sc.nextLine();
		
		System.out.print("등록할 학생 학과명 : ");
		String departmentName = sc.nextLine();
		
		System.out.print("등록할 학생 담당교수번호 : ");
		int professorNo = sc.nextInt();
		
		Student student = new Student(studentNo, studentGrade, studentName, studentSSN, studentAddress, departmentName, professorNo);
		
		try {
			int result = service.insertStudent(student);
			
			if(result > 0) {
				System.out.println("*** 학생 등록 추가 성공 ! ***");
			}else {
				System.out.println("*** 학생 등록 추가 실패 ! ***");
			}
		}catch(Exception e) {
			System.out.println("학생 등록 과정에서 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
	}


	
	// 3. 학생 정보 수정(학과명, 교수번호만)
	public void updateStudent() {
		System.out.println("[학생 정보 수정]");
		
		System.out.print("수정할 학생의 학번 : ");
		int studentNo = sc.nextInt();
		
/*		System.out.print("학년 : ");
		int studentGrade = sc.nextInt();
		
		System.out.print("이름 : ");
		String studentName = sc.nextLine();
		
		System.out.print("주민등록번호 : ");
		String studentSSN = sc.nextLine();
		
		System.out.print("주소 : ");
		String studentAddress = sc.nextLine();
*/		
		System.out.print("학과명 : ");
		String departmentName = sc.next();
		sc.nextLine();
		
		System.out.print("교수번호 : ");
		int professorNo = sc.nextInt();
		sc.nextLine();
		
		Student student = new Student(studentNo, departmentName, professorNo);
		
		
		try {
			
			int result = service.updateStudent(student);
		
			if(result > 0) {
				System.out.println("*** 학생 정보 수정 성공 ! ***");
			}else {
				System.out.println("*** 학생 정보 수정 실패 ! ***");
			}
		
		} catch (Exception e) {
			System.out.println("정보 수정 중 오류가 발생했습니다 ㅠ_ㅠ");
			e.printStackTrace();
		}
		
	}




	// 학번을 입력받아 일치하는 학생 정보 삭제
	//4. 학생 정보 삭제
	public void deleteStudent() {
		System.out.println("[학생 정보 삭제]");
		
		System.out.print("삭제할 학생의 학번 : ");
		int studentNo = sc.nextInt();
		sc.nextLine();
		
		try {
			int result = service.deleteStudent(studentNo);
			
			if(result > 0) {
				System.out.println("*** 학생 정보 삭제 성공 ! ***");
			}else {
				System.out.println("*** 학생 정보 수정 실패 ! ***");
			}
		}catch(Exception e) {
			System.out.println("정보 삭제 중 오류가 발생했습니다 ㅠ_ㅠ");
			e.printStackTrace();
		}
	}


	
	
	
	// 5. 학번으로 학생 정보 조회
	public void selectOne() {
		System.out.println("[학번으로 학생 정보 조회]");
		
		System.out.print("조회할 학생의 학번 : ");
		int studentNo = sc.nextInt();
		sc.nextLine();
		
		try {
			 Student student = service.selectOne(studentNo);
			 
			 if(student == null) {
				 System.out.println("일치하는 학번의 학생이 없습니다.");
			 }else {
//				 System.out.println(student);
				 System.out.printf(" | %d | 학번 : %10d | 학년 : %d | 이름 : %5s | 주민등록번호 : %15s | 주소 : %40s  | 학과명 : %15s | 교수번호 : %3d | \n", 
						 student.getSequenceNo(), 
						 student.getStudentNo(), 
						 student.getStudentGrade(),
						 student.getStudentName(),
						 student.getStudentSSN(),
						 student.getStudentAddress(),
						 student.getDepartmentName(),
						 student.getProfessorNo());
			 }
			 
		}catch(Exception e) {
			System.out.println("정보 조회 중 오류가 발생했습니다 ..!");
		}
	}

	
	
/*	// 6,7번에서 사용될 목록 출력 메소드  ( >> 무시해주세요~!)
	private void printList(List<Student> list) {
		
		if( !list.isEmpty() ) { // list가 비어있지 않음 == true == 결과 출력
			for(Student student : list) {
				System.out.printf("[구분] | %d | 학번 : %10d | 학년 : %d | 이름 : %5s | 주민등록번호 : %15s | 주소 : %40s  | 학과명 : %15s | 교수번호 : %3d | \n", 
					 student.getSequenceNo(), 
					 student.getStudentNo(), 
					 student.getStudentGrade(),
					 student.getStudentName(),
					 student.getStudentSSN(),
					 student.getStudentAddress(),
					 student.getDepartmentName(),
					 student.getProfessorNo());
			}
		}else {
			System.out.println("조회 결과가 없습니다.");
		}
	}
*/

	
	
	// 학과명 or 주소지를 검색하여 해당하는 학생(들) 정보 조회
	// 6. 학생 검색 
	public void searchStudent() {
		System.out.println("[학생 검색]");
		
		int searchKey = 0;
		while(true) {
			
			System.out.println("1. 학과명 | 2. 주소지");
			System.out.print("검색 카테고리 선택 : ");
			searchKey = sc.nextInt();
			sc.nextLine();
			
			if(searchKey == 1 || searchKey == 2) {
				break;
			}else {
				System.out.println("숫자 1 또는 2만 입력해주세요.");
			}
			
		}
		
		
		System.out.print("검색어 입력 : ");
		String searchValue = sc.next();
		sc.nextLine();
		
		try {
			List<Student> list = service.list(searchKey, searchValue);
		
			if( !list.isEmpty() ) { // list가 비어있지 않음 == true == 결과 출력
				for(Student student : list) {
					System.out.printf(" | %d | 학번 : %10d | 학년 : %d | 이름 : %5s | 주민등록번호 : %15s | 주소 : %40s  | 학과명 : %15s | 교수번호 : %3d | \n", 
						 student.getSequenceNo(), 
						 student.getStudentNo(), 
						 student.getStudentGrade(),
						 student.getStudentName(),
						 student.getStudentSSN(),
						 student.getStudentAddress(),
						 student.getDepartmentName(),
						 student.getProfessorNo());
				}
			}else {
				System.out.println("조회 결과가 없습니다.");
			}
			
		}catch(Exception e) {
			System.out.println("검색 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
	}


	
	
	
	// 7. 학번,이름을 입력받아 일치하는 학생의 담당 교수명 조회
	public void selectPFName() {
		System.out.println("[학번,이름을 입력받아 일치하는 학생의 담당 교수명 조회]");
		
		System.out.print("조회할 학생의 학번 : ");
		int studentNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("조회할 학생의 이름 : ");
		String studentName = sc.nextLine();
		
		try {
			Student student = service.selectPFName(studentNo, studentName);
			
			if(student == null) {
//*?*			이 부분에서 학번이 존재하지 않는 경우, 이름이 존재하지 않는경우 나누는 법
				System.out.println("일치하는 학번 또는 이름이 존재하지 않습니다.");
			}else {
				System.out.println("[조회 결과]");
				
				System.out.printf(" | %d | 학번 : %10d | 이름 : %5s | 담당교수명 : %5s | \n",
							student.getSequenceNo(), 
							student.getStudentNo(), 
							student.getStudentName(),
							student.getPfName());
			}
		}catch(Exception e) {
			System.out.println("담당 교수님 조회 중 오류가 발생했습니다..!");
			e.printStackTrace();
		}
	}
	
	
}
