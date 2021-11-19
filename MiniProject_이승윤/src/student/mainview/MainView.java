package student.mainview;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import student.model.service.StudentService;
import student.model.vo.Student;
import student.point.model.service.PointService;
import student.point.model.vo.Point;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	private StudentService service = new StudentService(); 
	private PointService service2 = new PointService();
	
	public void diplayMenu() {
		int sel = -1; 
		
		do {
			try {			
				System.out.println("=======");
				System.out.println("1. 학생 정보 추가");
				System.out.println("2. 학생 정보 수정");
				System.out.println("3. 학생 정보 전체 조회");
				System.out.println("4. 학생 정보 상세 조회");
				System.out.println("5. 학생 정보 검색");
				System.out.println("6. 학생 정보 삭제");
				System.out.println("7. 학생 성적 기입");
				System.out.println("8. 학생 성적 수정");
				System.out.println("9. 학생 성적 전체 조회");
				System.out.println("10. 학생 성적 상세 조회");
				System.out.println("11. 학생 성적 삭제");
				System.out.println("0. 프로그램 종료");
				System.out.println("=======");
				
				System.out.print("메뉴 번호 선택 >>");
				sel = sc.nextInt();
				sc.nextLine(); 
				System.out.println();
				
				switch(sel) {
				case 1 : insertStudent();  break; 
				case 2 : updateStudent(); break; 
				case 3 : selectAll();  break; 
				case 4 : selectOne();  break; 
				case 5 : searchStudent();  break; 
				case 6 : deleteStudent();  break; 
				case 7 : insertPoint();  break; 
				case 8 : updatePoint();  break; 
				case 9 : selectAllPoint();  break; 
				case 10 : selectOnePoint();  break; 
				case 11 : deletePoint();  break; 
				case 0 : System.out.println("프로그램을 종료합니다.");break;
				default : System.out.println("메뉴에 있는 번호만 입력해주세요.");
				}
								
			}catch(InputMismatchException e){
				System.out.println("메뉴에 있는 번호만 입력해주세요.");
				sc.nextLine(); 
			}		
		}while(sel != 0);
	}
	private void insertStudent() {
		System.out.println("학생 정보 추가");
		
		System.out.print("학번 : ");
		int studentNo = sc.nextInt();
		
		System.out.print("학년 : ");
		int studentGrade = sc.nextInt();
		sc.nextLine();
		
		System.out.print("이름 : ");
		String studentNm = sc.nextLine();
		
		System.out.print("주민번호 : ");
		String studentSSN = sc.nextLine();
		
		System.out.print("주소 : ");
		String studentAddress = sc.nextLine();
		
		System.out.print("학과 : ");
		String departmentNm = sc.nextLine();
		
		Student student = new Student(studentNo, studentGrade, studentNm, studentSSN, studentAddress, departmentNm);
		try {
		int result = service.insertStudent(student);
		
		if( result >0) {
			System.out.println("학생 정보를 추가했습니다.");
		}else {
			System.out.println("학생 정보 추가를 실패했습니다.");
		}
		}catch(Exception e){
			System.out.println("학생 정보 추가 도중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	private void updateStudent() {
		System.out.println("학생 정보 수정");
		
		System.out.print("수정 할 학생의 학번 입력 : ");
		int studentNo = sc.nextInt();

		System.out.println("1. 학년, 2. 이름, 3. 주민번호, 4. 주소 5. 학과");
		System.out.print("수정 할 번호 : ");
		int sel = sc.nextInt();
		sc.nextLine();
		
		String input1 = "";
		switch(sel){
		case 1 : input1 = "STUDENT_GRADE"; break;
		case 2 : input1 = "STUDENT_NAME"; break;
		case 3 : input1 = "STUDENT_SSN"; break;
		case 4 : input1 = "STUDENT_ADDRESS"; break;
		case 5 : input1 = "DEPARTMENT_NAME"; break;
		default : System.out.println("위의 번호만 입력해주세요.");
		}
		
		System.out.print("수정 할 내용 : ");
		String input2 = sc.nextLine();

		try {
			int result = service.updateStudent(studentNo, input1, input2);
			
			if(result>0) {
				System.out.println("학생 정보 수정 성공");
			}else {
				System.out.println("학생 정보 수정 실패, 입력한 학번을 확인해주세요.");
			}
		} catch (Exception e) {
			System.out.println("학생 정보 수정 과정에서 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
	private void printList(List<Student> list) {
		
		if(!list.isEmpty()) {

		for(Student student: list) {
			System.out.println(student);
		}
		} else {
			System.out.println("조회 결과가 없습니다.");
		}
		
	}
	private void selectAll() {
		System.out.println("학생 정보 전체 조회");
		
		try {
			List<Student> list = service.selectAll();
			
			printList(list);
			
		} catch (Exception e) {
			System.out.println("조회 중 문제가 발생했습니다.");			
			e.printStackTrace();
		}	
	}
	private void selectOne() {
		System.out.println("학생 정보 상세 조회");
		
		System.out.print("조회할 학생의 학번 : ");
		int studentNo = sc.nextInt();
		sc.nextLine();
		
		try {
			Student student = service.selectOne(studentNo);
			
			if(student != null) {
				System.out.println(student);
			}else {
				System.out.println("조회할 학생의 학번이 잘못되었습니다.");
			}
		} catch (Exception e) {
			System.out.println("조회 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	private void searchStudent() {
		System.out.println("학생 정보 검색");
		
		int searchKey = 0;
		while(true) {
		
			System.out.println("1. 학번 | 2. 학년 | 3. 이름 | 4. 주소 | 5. 학과");
			System.out.print("검색 카테고리 검색 : ");
			searchKey = sc.nextInt();
			sc.nextLine();
			
			if(searchKey > 0 && searchKey < 6) {
				break;
			}else {
				System.out.println("1~5 사이 번호만 입력해주세요.");
			}
		}
		
		System.out.print("검색어 : ");
		String searchValue = sc.nextLine();
		
		try {
			List<Student> searchList = service.searchStudent(searchKey, searchValue);
			

			printList(searchList);
		
		} catch (Exception e) {

			System.out.println("게시글 검색 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
	private void deleteStudent() {
		System.out.println("학생 정보 삭제");
		
		System.out.print("삭제할 학생의 학번 : ");
		int studentNo = sc.nextInt();
		sc.nextLine();
			
		try {
			int result = service.deleteStudent(studentNo);
			
			if(result > 0 ) {
				System.out.println("삭제가 완료되었습니다.");
			}else{
				System.out.println("삭제할 학생의 학번이 잘못되었습니다.");
			}
			
		}catch(Exception e) {
			System.out.println("학생 정보 삭제 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	private void insertPoint() {
		System.out.println("학생 성적 기입");
		try {
			System.out.print("학번 : ");
			int studentNo = sc.nextInt();
			
			System.out.print("해당 학기 : ");
			int termNo = sc.nextInt();
			
			System.out.print("학점 : ");
			double inputPoint = sc.nextDouble();
			sc.nextLine();
			
			Point point = new Point(studentNo, termNo, inputPoint);
		
			int result = service2.insertPoint(point);
			
			if(result >0) {
				System.out.println("성적 기입 성공");
			}else {
				System.out.println("성적 기입 실패");
			}
		}catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("해당 학번이 없습니다.");
		}catch (Exception e) {
			System.out.println("학생 성적 기입 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
		
	}
	private void updatePoint() {
		System.out.println("학생 성적 수정");
		
		System.out.println("수정 할 학생의 학번과 학기 입력");
		System.out.print("학번 : ");
		int studentNo = sc.nextInt();

		System.out.print("학기 : ");
		int termNo = sc.nextInt();
		
		System.out.print("학점 수정 값 : ");
		double point = sc.nextDouble();
		sc.nextLine();
		
		try {
			int result = service2.updatePoint(studentNo, termNo, point);
			
			if(result>0) {
				System.out.println("학생 성적 수정 성공");
			}else {
				System.out.println("학생 성적 수정 실패, 입력한 학번을 확인해주세요.");
			}
		} catch (Exception e) {
			System.out.println("학생 성적 수정 과정에서 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	private void printListPoint(List<Point> list) {
		
		if(!list.isEmpty()) {

		for(Point point: list) {
			System.out.println(point);
		}
		} else {
			System.out.println("조회 결과가 없습니다.");
		}
		
	}
	private void selectAllPoint() {
		System.out.println("학생 성적 전체 조회");
		
		try {
			List<Point> list = service2.selectAllPoint();
			
			printListPoint(list);
			
		} catch (Exception e) {
			System.out.println("조회 중 문제가 발생했습니다.");			
			e.printStackTrace();
		}
		
	}
	private void selectOnePoint() {
		System.out.println("학생 성적 상세 조회");
		
		System.out.print("조회할 학생의 학번 : ");
		int studentNo = sc.nextInt();
		sc.nextLine();
		
		try {
			List<Point> list = service2.selectOne(studentNo);
			
			printListPoint(list);
			
		} catch (Exception e) {
			System.out.println("조회 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	private void deletePoint() {
		System.out.println("학생 성적 정보 삭제");
		
		System.out.print("삭제할 성적의 학번 : ");
		int studentNo = sc.nextInt();

		System.out.print("삭제할 성적의 학기 : ");
		int termNo = sc.nextInt();
		sc.nextLine();
			
		try {
			Point point = new Point();
			point.setStudentNo(studentNo);
			point.setTermNo(termNo);
			
			int result = service2.deletePoint(point);
			
			if(result > 0 ) {
				System.out.println("삭제가 완료되었습니다.");
			}else{
				System.out.println("삭제할 학생의 학번 또는 학기가 잘못되었습니다.");
			}			
		}catch(Exception e) {
			System.out.println("학생 성적 삭제 중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
}
