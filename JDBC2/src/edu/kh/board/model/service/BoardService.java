package edu.kh.board.model.service;

import static edu.kh.board.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import edu.kh.board.model.dao.BoardDAO;
import edu.kh.board.model.vo.Board;

/**
 * @author heeyeon Shin
 *
 */
public class BoardService {
	// 트랜잭션 제어, 데이터가공 클래스 
	
	// connection객체가 있어야 commit,rollback가눙
	private BoardDAO dao = new BoardDAO();
	
	
	//클래스 or 메소드 설명 주석
	/** 게시글 작성 Service
	 * @param board
	 * @return result
	 */

	//case 1
	public int insertBoard(Board board) throws Exception {
		// connection 불러오기
		Connection conn = getConnection();
		
		//게시글 정보 삽입을 수행하는 dao메소드 호출 후 반환 
		int result = dao.insertBoard(board, conn);
		
		//dao 수행 후 
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 게시글 수정 (비밀번호 x)
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardPwx(Board board) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateBoardPwx(board, conn);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);

		return result;
	}


	/** 게시글 조회 서비스
	 * @param boardNo
	 * @param boardPw
	 * @return int result (0 : 조회결과 x)
	 * @throws Exception
	 */
	public int searchBoard(int boardNo, String boardPw) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.searchBoard(boardNo,boardPw, conn);
		
		return result;
		
	}


	/** 게시글 삭제 서비스 
	 * @param boardNo
	 * @param boardPw
	 * @return result 
	 */
	public int deleteBoard(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteBoard(boardNo, conn);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/**
	 * @return
	 * @throws Exception
	 */
	public List<Board> boardList() throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> list = dao.boardList(conn);
		
		close(conn);
		
		return list;
	}
	
	public List<Board> searchTitle(String input) throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> list = dao.searchTitle(input, conn);
		
		close(conn);
		
		return list;
	}


	public Board selectBoard(int inputNo) throws Exception{
		
		Connection conn = getConnection();
		
		Board board = dao.selectBoard(inputNo,conn);
		if(board != null) {
			int result = dao.increaseReadCount(inputNo,conn);
			
			if(result > 0) {
				commit(conn);
				
				board.setReadCount(board.getReadCount()+1);
			}else {
				
				rollback(conn);
			}
		}
		return board;
	}

}
