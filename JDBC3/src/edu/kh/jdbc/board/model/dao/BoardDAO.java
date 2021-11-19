package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.view.MainView;

public class BoardDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public BoardDAO() {
	try {
		prop = new Properties();
		prop.loadFromXML(new FileInputStream("board-sql.xml"));
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	/** insertBoard
	 * @param boardTitle
	 * @param boardContent
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(String boardTitle, String boardContent, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, MainView.loginMember.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**checkBoard
	 * @param inputNo
	 * @param conn 
	 * @return result
	 * @throws Exception
	 */
	public int checkBoard(int inputNo, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("checkBoard");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,inputNo);
			pstmt.setInt(2,MainView.loginMember.getMemberNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return result;
	}

	public int updateBoard(Board board, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			
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

	
	public int deleteBoard(int boardNo, Connection conn) throws Exception{
		int result = 0;
		
		try { 
			String sql = prop.getProperty("deleteBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
	
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo, Connection conn) throws Exception {
		
		Board board = null;
		
		try {
			String sql = prop.getProperty("selectBoard");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setCreateDt(rs.getDate("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setWriterNm(rs.getString("MEMBER_NM"));

			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}

	public int increaseReadCount(int boardNo, Connection conn) throws Exception {
		int result = 0;
		try {
			
			String sql = prop.getProperty("increaseReadCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
			
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Board> selectBoardList(Connection conn) throws Exception {
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			stmt = conn.createStatement();
			String sql = prop.getProperty("selectBoardList");
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int BoardNo = rs.getInt("BOARD_NO");
				String BoardTitle=rs.getString("BOARD_TITLE");
				Date createDate = rs.getDate("CREATE_DT");
				int ReadCount = rs.getInt("READ_COUNT");
				String WriterNm = rs.getString("MEMBER_NM");
				
				Board board = new Board(BoardNo, BoardTitle, createDate, ReadCount, WriterNm);
				
				boardList.add(board);
			}
			
			
		}finally {
			close(rs);
			close(stmt);
			
		}
		
		return boardList;
	}

	public List<Board> searchBoard(int searchKey, String searchValue, Connection conn) throws Exception {
		List<Board> searchList = new ArrayList<Board>();
		try {
			String sql1 = prop.getProperty("searchList");
			String sql2 = null;
			
			switch(searchKey){
			case 1 : sql2 = prop.getProperty("title"); break;
			case 2 : sql2 = prop.getProperty("content"); break;
			case 3 : sql2 = prop.getProperty("title_content"); break;
			case 4 : sql2 = prop.getProperty("w"); break;
			}
			
			String sql = sql1 + sql2;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,searchValue);
			
			if(searchKey == 3) {
				pstmt.setString(2, searchValue);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int BoardNo = rs.getInt("BOARD_NO");
				String BoardTitle=rs.getString("BOARD_TITLE");
				Date createDate = rs.getDate("CREATE_DT");
				int ReadCount = rs.getInt("READ_COUNT");
				String WriterNm = rs.getString("MEMBER_NM");
				
				Board board = new Board(BoardNo, BoardTitle, createDate, ReadCount, WriterNm);
				
				searchList.add(board);
			}
			
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return searchList;
	}

}
