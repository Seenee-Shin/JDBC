package edu.kh.board.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.board.model.service.BoardService;
import edu.kh.board.model.vo.Board;

public class BoardView {
	
	private BoardService service = new BoardService();
	private Scanner sc = new Scanner(System.in);
	
	public void displayMenu() {
		int sel = -1;
		
		do {
			try {
				System.out.println("===========================");
				System.out.println("1.게시글 작성 ");
				System.out.println("2.게시글 수정(비밀번호 x) ");
				System.out.println("3.게시글 수정 ");
				System.out.println("4.게시글 삭제 ");
				System.out.println("5.게시글 목록 조회 ");
				System.out.println("6.게시글 제목 검색 ");
				System.out.println("7.게시글 상세 조회 ");
				System.out.println("0.프로그램 종료");
				System.out.println("===========================");				
				System.out.print("메뉴 선택 : ");
				sel = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(sel) {
				case 1: insertBoard(); break;
				case 2: updateBoardPwx(); break;
				case 3: updateBoard(); break;
				case 4: deleteBoard(); break;
				case 5: boardList(); break;
				case 6: serachTilte(); break;
				case 7: selectBoard(); break;
				case 0: System.out.println("프로그램 종료");break;
				default : System.out.println("메뉴 번호만 입력해주세요");
				}
				
			}catch(InputMismatchException e) {
				System.out.println("메뉴 번호만 입력해주세요");
				sc.nextLine();
			}
			
		}while(sel != 0);
		
	}
	
	//case 1 
	private void insertBoard() {
		System.out.println("-----게시글 작성 -----");
		
		System.out.print("작성자 입력 :");
		String boardWriter = sc.nextLine();
		
		System.out.print("비밀번호 입력 :");
		String boardPw = sc.nextLine();
		
		System.out.print("제목 입력 :");
		String boardTilte = sc.nextLine();
		
		System.out.println("내용 작성 (입력종료시 !q 작성 후 엔터) ");
		String boardContent = "";
		while(true) {
			//내용입력
			String temp = sc.nextLine();
			
			if(temp.equals("!q")) {
				break;
			}else {
				boardContent += temp+ "\n";
			}
		}
		Board board = new Board(boardTilte, boardContent, boardWriter, boardPw);
		
		try {
			int result = service.insertBoard(board);
			
			//다중행, 서브쿼리 insert시 0이 나올 가능성 o
			if(result > 0) {
				System.out.println("게시글 작성 성공");
			}else {
				System.out.println("게시글 작성 실패");
			}
			
		} catch (Exception e) {
			System.out.println("게시물 작성 과정에서 문제가 발생했습니다.");
			e.printStackTrace();
		}
		// 작성 1개 성공 : 성공한 행의 갯수
	}
	
	private void updateBoardPwx() {
		// 게시글 번호입력 -> 번호의 게시글 제목,내용수정 
		
		System.out.println("*** 게시글 수정 (비밀번호 x )***");
		System.out.print("게시글 번호 입력 :");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("게시글 제목 입력 :");
		String boardTitle = sc.nextLine();
	
		System.out.print("게시글 내용 입력(종료 : !q):");
		String boardContent = "";
	
		
		
		while(true) {
			String temp = sc.nextLine();
			
			if(temp.equals("!q")){
				break;
			}else {
				boardContent += temp + "\n";
			}
		}
		
		//입력 받은 값 Board 객체에 저장 
		
		Board board = new Board(boardNo, boardTitle, boardContent);
		
		try {
			int result = service.updateBoardPwx(board);
			
			if (result >0) {
				System.out.println("게시글 수정이 완료되었습니다.");
			}else {
				System.out.println("입력한 번호의 게시글이 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("게시글 수정 과정에서 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
			
	}
	private void updateBoard(){
		//게시글 번호, 비밀번호 입력 후 게시글 일치 확인 
		System.out.print("수정할 게시글 번호 입력 ");
		int boardNo =sc.nextInt();
		sc.nextLine();
		
		System.out.print("수정할 게시글 비밀번호 입력 ");
		String boardPw = sc.nextLine();
		
		try {
			int result = service.searchBoard(boardNo,boardPw);
			//count로 조회 개수 반환 -> 반환형 int 
			
			if(result > 0) {
				
				System.out.println("게시글 수정");
				System.out.print("게시글 제목 입력 :");
				String boardTitle = sc.nextLine();
			
				System.out.print("게시글 내용 입력(종료 : !q):");
				String boardContent = "";
				
				while(true) {
					String temp = sc.nextLine();
					
					if(temp.equals("!q")){
						break;
					}else {
						boardContent += temp + "\n";
					}
				}
				
				//result 가 있으면 수정 내용 입력 후 borad객체에 저장 
				Board board = new Board(boardNo, boardTitle, boardContent);
				
				//case2 재사용
				int result2 = service.updateBoardPwx(board);
					
					if (result2 > 0) {
						System.out.println("게시글 수정이 완료되었습니다.");
					}else {
						System.out.println("입력한 번호의 게시글이 존재하지 않습니다.");
					}
				
				
			}else {
				System.out.println("게시글 번호가 일치하는 글이 없거나 비밀번호가 일치 하지 않습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void deleteBoard() {
		//번호, 비밀번호 입력
		// 삭제확인 문구 출력 후 y = 삭제수행 / n = 삭제취소
		
		System.out.print("삭제할 게시글 번호 : ");
		int boardNo =sc.nextInt();
		sc.nextLine();
		
		System.out.print("삭제할 게시글 비밀번호 : ");
		String boardPw = sc.nextLine();
		
		try {
			int result = service.searchBoard(boardNo,boardPw);
			
			if (result == 0) {
				System.out.println("게시글 번호가 일치하는 글이 없거나 비밀번호가 일치 하지 않습니다.");
			}else {
				
				System.out.print("게시글을 삭제하시겠습니까? (삭제 : Y / 삭제취소 : N) : ");
				char confirm = sc.next().charAt(0);
				
				if(confirm == 'Y'){
					
					int result2 = service.deleteBoard(boardNo);
					
					if(result2 > 0) {
						System.out.println("게시글 삭제가 완료되었습니다.");						
					}else {
						System.out.println("오류가 발생하였습니다.");
					}
					
				}else if(confirm == 'N') {
					System.out.println("게시글 삭제가 취소되었습니다.");
				}else {
					System.out.println("다시 입력해 주세요");
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//case 5,6 board list method
	private void printList(List<Board> list) {
		
		if(!list.isEmpty()) {
			for(Board board : list) {
				
				System.out.printf("%3d | %20s | %5s | %s | %3d\n",
						board.getBoardNo(),board.getBoardTitle(),board.getBoardWriter(),
						board.getCreateDt().toString(),board.getReadCount());	
			}
			
		}else { 
			
			System.out.println("조회 결과가 없습니다.");
		}
	}
	
	private void boardList() {
		
		System.out.println("게시글 조회");
		
		List<Board> list;
		try {
			list = service.boardList();
			
			printList(list);
			
		} catch (Exception e) {
			System.out.println("게시글 목록 조회 과정에서 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
		
	}
	
	private void serachTilte() {
		
		System.out.println("게시글 검색");
		System.out.print("검색 : ");
		String input = sc.nextLine();
		
		try {
			List<Board> list = service.searchTitle(input);
			
			printList(list);
			
		} catch (Exception e) {
			System.out.println("게시글 목록 조회 과정에서 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
	private void selectBoard() {
		System.out.println("게시글 상세조회");
		
		System.out.print("게시글 번호 검색 : ");
		int inputNo = sc.nextInt();
		sc.nextLine();
		
		try {
			Board board = service.selectBoard(inputNo);
			
			if(board == null) {
				System.out.println("조회 결과가 없습니다.");
				
			}else {
				System.out.println("------ 조회 결과 ------");
				System.out.printf("[번호 : %2d ] 제목 : %s \n" , board.getBoardNo(), board.getBoardTitle());
				System.out.println("--------------------------------------");
				System.out.printf("작성자 :%5s\n [작성일 : %s]\n 조회수 : %3d \n" , board.getBoardWriter(),board.getCreateDt().toString() , board.getReadCount());
				System.out.println("--------------------------------------");
				System.out.println(board.getBoardContent());
			}
		}catch (Exception e){
			System.out.println("게시글 조회 과정에서 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
		
	}
}
