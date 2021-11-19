package edu.kh.board.model.dao;

//static 변수,메소드호출시 클래스명 생략
import static edu.kh.board.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.board.model.vo.Board;

/**
 * @author heeye
 *
 */
public class BoardDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/** 게시글 작성 DAO
	 * @param board
	 * @param conn
	 * @return result
	 */
	public int insertBoard(Board board, Connection conn) throws Exception {
		// 결과 저장용 변수 
		int result = 0;
		
		try {
			//conn 이미 받아옴 
			String sql = "INSERT INTO TB_BOARD VALUES(SEQ_BOARD_NO.NEXTVAL, ?, ?, ?, ?, DEFAULT, DEFAULT )";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getBoardWriter());
			pstmt.setString(4, board.getBoardPw());
			
			result = pstmt.executeUpdate();
			
		}finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	
	/** 게시글 수정 (비밀번호 x) dao
	 * @param board
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int updateBoardPwx(Board board, Connection conn) throws Exception {

		int result = 0;
		try {
			String sql = "UPDATE TB_BOARD SET BOARD_TITLE= ?, BOARD_CONTENT =? WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}


	/**
	 * @param boardNo
	 * @param boardPw
	 * @param conn
	 * @return int result (0,1)
	 * @throws Exception
	 */
	public int searchBoard(int boardNo, String boardPw, Connection conn) throws Exception {
		
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) FROM TB_BOARD WHERE BOARD_NO = ? AND BOARD_PW = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, boardPw);
			
			rs = pstmt.executeQuery();
			// count 함수를 사용하면 행의 개수가 resultset으로 나온다 
			
			if(rs.next()) {
				result = rs.getInt(1);
				//컬럼값 얻어오기 : 컬럼명 or 컬럼순서(1행일때) 사용
			}
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return result;
	}


	/**
	 * @param boardNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = "DELETE FROM TB_BOARD WHERE BOARD_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally{
			close(pstmt);
		}
		return result;
	}


	/** 게시글 목록 조회
	 * @param conn
	 * @return list
	 */
	public List<Board> boardList(Connection conn) throws Exception {
		 List<Board> list = new ArrayList<Board>();
		 //예외를 throws로 처리했기 때문에 변수 선언과 초기화를 동시에 진행 
		 
		 try {
			 
			 String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, CREATE_DT, READ_COUNT FROM TB_BOARD WHERE BOARD_NO > 0 ORDER BY BOARD_NO DESC";
			 //인덱스 : 속도향상 / WHERE 절에 언급 
			 //위치홀더가 없을 경우 stmt 사용
			 
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(sql);
			 
			 while(rs.next()) {
				 int boardNo = rs.getInt("BOARD_NO");
				 String boardTitle = rs.getString("BOARD_TITLE");
				 String boardWriter = rs.getString("BOARD_WRITER");
				 Date creatDt = rs.getDate("CREATE_DT");
				 int readCount = rs.getInt("READ_COUNT");
				 
				 Board board = new Board(boardNo, boardTitle, boardWriter, creatDt, readCount);
				 
				 list.add(board);
			 }
			 
			 
		 }finally {
			 close(rs);
			 close(stmt);
			 
		 }
		 
		return list;
	}


	/**
	 * @param input
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Board> searchTitle(String input, Connection conn) throws Exception {
		 List<Board> list = new ArrayList<Board>();
		 //예외를 throws로 처리했기 때문에 변수 선언과 초기화를 동시에 진행 
		 
		 try {
			 
			 String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, CREATE_DT, READ_COUNT "
			 		+ "FROM TB_BOARD WHERE BOARD_NO > 0 "
			 		+ "AND BOARD_TITLE LIKE '%' || ? || '%' "
			 		+ "ORDER BY BOARD_NO DESC";
			 //%?% 연결연산자를 이용해 위치홀더 사용 
			 //위치홀더가 없을 경우 stmt 사용
			 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,input);
			rs = pstmt.executeQuery();
			
			 
			 while(rs.next()) {
				 int boardNo = rs.getInt("BOARD_NO");
				 String boardTitle = rs.getString("BOARD_TITLE");
				 String boardWriter = rs.getString("BOARD_WRITER");
				 Date creatDt = rs.getDate("CREATE_DT");
				 int readCount = rs.getInt("READ_COUNT");
				 
				 Board board = new Board(boardNo, boardTitle, boardWriter, creatDt, readCount);
				 
				 list.add(board);
			 }
			 
		 }finally {
			 close(rs);
			 close(pstmt);
			 
		 }
		
		return list;
	}


	public Board selectBoard(int inputNo, Connection conn) throws Exception {
		
		Board board = null;
		try {
			String sql = "SELECT * FROM TB_BOARD WHERE BOARD_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inputNo);
			
			rs = pstmt.executeQuery();		
					
			if(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardWriter = rs.getString("BOARD_WRITER");
				String boardContent = rs.getString("BOARD_CONTENT");
				String boardPw = rs.getString("BOARD_PW");
				Date creatDt = rs.getDate("CREATE_DT");
				int readCount = rs.getInt("READ_COUNT");
				
				board = new Board(boardNo, boardTitle, boardContent, boardWriter, boardPw, creatDt, readCount);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}


	/**
	 * @param inputNo
	 * @param conn
	 * @return
	 */
	public int increaseReadCount(int inputNo, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = "UPDATE TB_BOARD SET READ_COUNT = READ_COUNT + 1 WHERE BOARD_NO = ? ";
			// 입력받은 게시글 번호 조회수 +1
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inputNo);
			result = pstmt.executeUpdate();
			// 성공한 행의 개수 반환 
			
			
		}finally {
			close(pstmt);
		}
		return result;
	}
	
}
