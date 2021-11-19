package edu.kh.jdbc.board.model.vo;

import java.sql.Date;

public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private Date createDt;
	private int readCount;
	private int WriterNo;
	private String WriterNm;
	
	public Board() {}
	
	

	public Board(int boardNo, String boardTitle, Date createDt, int readCount, String writerNm) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.createDt = createDt;
		this.readCount = readCount;
		WriterNm = writerNm;
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

	public int getWriterNo() {
		return WriterNo;
	}

	public void setWriterNo(int writerNo) {
		WriterNo = writerNo;
	}

	public String getWriterNm() {
		return WriterNm;
	}

	public void setWriterNm(String writerNm) {
		WriterNm = writerNm;
	}

	@Override
	public String toString() {
		return "Borad [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", createDt=" + createDt + ", readCount=" + readCount + ", WriterNo=" + WriterNo + ", WriterNm="
				+ WriterNm + "]";
	}
	
	

}
