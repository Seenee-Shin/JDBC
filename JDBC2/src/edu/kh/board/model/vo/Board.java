package edu.kh.board.model.vo;

import java.sql.Date;

public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardPw;
	private Date createDt;
	private int readCount;
	
	public Board() {
		
	}

	public Board(String boardTitle, String boardContent, String boardWriter, String boardPw) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.boardPw = boardPw;
	}

	public Board(int boardNo, String boardTitle, String boardContent) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
	}
	

	public Board(int boardNo, String boardTitle, String boardWriter, Date createDt, int readCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.createDt = createDt;
		this.readCount = readCount;
	}
	

	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, String boardPw, Date createDt,
			int readCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.boardPw = boardPw;
		this.createDt = createDt;
		this.readCount = readCount;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardPw() {
		return boardPw;
	}

	public void setBoardPw(String boardPw) {
		this.boardPw = boardPw;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", boardPw=" + boardPw + ", createDt=" + createDt + ", readCount="
				+ readCount + "]";
	}
	
}
