package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.vo.Board;

public class BoardService {
	
	private BoardDAO dao = new BoardDAO();

	/**insertBoard
	 * @param boardTitle
	 * @param boardContent
	 * @return result (0 || 1)
	 * @throws Exception
	 */
	public int insertBoard(String boardTitle, String boardContent) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertBoard(boardTitle,boardContent,conn);
		
		if(result > 0) commit(conn); else rollback(conn);
		
		close(conn);
		
		return result;
	}

	//case 5,6, checking method
	/** checkBoard
	 * @param inputNo
	 * @return result 
	 * @throws Exception
	 */
	public int checkBoard(int inputNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.checkBoard(inputNo, conn);
		
		close(conn);
		return result;
	}

	public int updateBoard(Board board) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateBoard(board,conn);
		
		if(result > 0) commit(conn); else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** deleteBoard
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.deleteBoard(boardNo,conn);

		if(result > 0) commit(conn); else rollback(conn);
		close(conn);
		
		return result;
	}

	/**selectBoard
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		Board board = dao.selectBoard(boardNo,conn);
		
		//조회수 증가 
		if(board != null) {
			int result = dao.increaseReadCount(boardNo, conn);
			
			if(result > 0) { 
				
				commit(conn);
				
				board.setReadCount(board.getReadCount()+1);
				
			}else {
				
			}
		}
		close(conn);
		
		return board;
	}

	public List<Board> selectBoardList() throws Exception {
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectBoardList(conn);
		
		close(conn);
		return boardList;
	}

	public List<Board> searchBoard(int searchKey, String searchValue) throws Exception{
		Connection conn = getConnection();
		
		List<Board> searchList = dao.searchBoard(searchKey, searchValue, conn);
		
		close(conn);
		return searchList;
	}
		
	

}
