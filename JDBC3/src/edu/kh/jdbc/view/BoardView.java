package edu.kh.jdbc.view;

import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.board.model.vo.Board;

public class BoardView {
	private Scanner sc =new Scanner(System.in);
	private BoardService service = new BoardService();
	
	
	//case 4
	public void insertBoard() {
		System.out.println("[게시글 작성]");
		
		System.out.print("제목 : ");
		String boardTitle = sc.nextLine();
		
		System.out.print("내용 입력 (종료시 !p) :");
		
		String boardContent = "";
		
		while(true) {
			String temp = sc.nextLine();
			
			if(temp.equals("!q")) {
				break;
			}else {
				boardContent += temp + "\n";
			}
		}
		//게시글 삽입 서비스 호출, 결과 반환 
		try {
			int result = service.insertBoard(boardTitle,boardContent);
			
			if(result > 0) {
				System.out.println("게시글이 작성되었습니다.");
				
			}else {
				System.out.println("게시글 작성 실패");
			}
			
		}catch(Exception e) {
			System.out.println("오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}
	
	private int checkBoard() throws Exception {
		
	System.out.print("게시글 번호 : ");
	int inputNo = sc.nextInt();
	sc.nextLine();
	
	int result = service.checkBoard(inputNo);
	
	if(result > 0) {
		System.out.println("확인 완료");
		return inputNo;
	}else {
		System.out.println("");
		return 0;
	}
	
	}
	
	//case 5
	public void updateBoard() {
		System.out.println("[게시글 수정]");
		
		try {
			int boardNo = checkBoard();
			
			if(boardNo == 0) {
				System.out.println("본인의 글만 수정 가능합니다.");
			}else {
				System.out.print("수정할 제목 : ");
				String boardTitle = sc.nextLine();
				
				System.out.print("수정 내용 입력 (종료시 !p) :");
				
				String boardContent = "";
				
				while(true) {
					String temp = sc.nextLine();
					
					if(temp.equals("!q")) {
						break;
						
					}else {
						boardContent += temp + "\n";
					}
				}
				Board board = new Board();
				board.setBoardTitle(boardTitle);
				board.setBoardContent(boardContent);
				board.setBoardNo(boardNo);
				
				int result = service.updateBoard(board);
				
				if(result > 0) {
					System.out.println("게시글이 수정되었습니다.");
					
				}else {
					System.out.println("게시글 작성 실패");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//case 6 
	public void deleteBoard() {
		System.out.println("[게시글 삭제]");
		
		try {
			int boardNo = checkBoard();
			
			if(boardNo == 0) {
				System.out.println("삭제할 게시글이 존재하지 않습니다.");
			}else {
				
				while(true) {
					System.out.println("정말 삭제하시겠습니까 ? (y/n)");
					char input = sc.nextLine().toLowerCase().charAt(0);
					
					if(input == 'y') {
						
						int result = service.deleteBoard(boardNo);
						 if(result > 0){
							 System.out.println("게시글이 삭제되었습니다.");
						 }else {
							 System.out.println("게시글 삭제 실패");
						 }
						break;
						
					}else if (input == 'n') {
						
						System.out.println("삭제를 취소하였습니다.");
						break;
						
					}else {
						System.out.println("다시 입력해 주세요");
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//login menu 8
	public void selectBoard() {
		System.out.println("[게시글 상세 조회]");
		
		System.out.print("게시글 번호 입력 :");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		try {
			Board board = service.selectBoard(boardNo);
			
			if(board != null) {
				System.out.println(board);
			}else {
				System.out.println("조회 결과가 존재하지 않습니다.");
			}
		}catch(Exception e){
			System.out.println("조회 중 문제가 발생했습니다.");
			e.printStackTrace();
			
		}
		
	}
	
	private void printBoardList(List<Board> boardList) throws Exception {
		if(boardList.isEmpty()) {
			System.out.println("조회결과 없음 ");
			
		}else {
			for(Board board : boardList) {
				
				System.out.printf("%2d %15s %s %s\n",
						board.getBoardNo(), board.getBoardTitle(), board.getReadCount(), board.getWriterNm(), board.getCreateDt());
				
			}
		}
	}
	

	public void selectBoardList() {
		System.out.println("게시 목록 조회");
		try {
			
			List<Board> boardList = service.selectBoardList();
			printBoardList(boardList);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("문제발생");
		}
	}

	public void searchBoard() {
		//제목 || 제목 + 내용 || 작성자 검색 카테고리 
		int searchKey = 0;
		
		while(true) {
			
			System.out.println("1.제목 | 2.내용 | 3. 제목+내용 | 4.작성자");
			System.out.print("검색카테고리 선택 :");
			searchKey = sc.nextInt();
			sc.nextLine();
			
			if(searchKey > 0 && searchKey < 5) {
				break;
			}else {
				System.out.println("메뉴번호만 입력해주세요");
			}
		}
		
		System.out.println("검색 입력 : ");
		String searchValue = sc.nextLine();
		
		try {
			List<Board> searchList = service.searchBoard(searchKey ,searchValue);
			
			printBoardList(searchList);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("문제발생");
		}
		
	}

}
