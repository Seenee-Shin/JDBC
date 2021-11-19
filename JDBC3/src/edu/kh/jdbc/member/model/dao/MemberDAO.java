package edu.kh.jdbc.member.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.member.model.vo.Member;
import edu.kh.jdbc.view.MainView;

public class MemberDAO {
	
	//필드 선언 
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	//기초생성자 생성 (객체 생성 메소드)
	//코드의 중복을 막기위해
	// 클래스 바로 아래에 작성될 수 있는 코드는 필드, 메소드 뿐이기 때문에 prop를 기본생성자에 생성 
	public MemberDAO() {
		try {
			//member sql.xml을 불러와 prop에 저장 
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** login DAO
	 * @param memberId
	 * @param memberPw
	 * @param conn
	 * @param memberId 
	 * @return member (select member information)
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw, Connection conn) throws Exception {
		
		Member member = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int memberNo = rs.getInt("MEMBER_NO");
				String memberNm = rs.getString("MEMBER_NM");
				String phone = rs.getString("PHONE");
				
				member = new Member(memberNo, memberId, memberNm, phone);
			}
			
		}finally{
			close(rs);
			close(pstmt);
		}
		return member;
	}

	/** idDupCheck DAO
	 * @param inputId
	 * @param conn
	 * @return int result (1 || 0)
	 * @throws Exception
	 */
	public int idDupCheck(String inputId, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("idDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputId);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				//.next를 수행하지 않으면 rs에 접근 불가 
				result = rs.getInt(1); //컬럼순번으로 결과 반환하기 
				
			}
			
			
		}finally { // 만든 역순 
			close(rs);
			close(pstmt);
		}
		
		
		return result;
	}

	/**
	 * @param member
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member, Connection conn) throws Exception {
		int result = 0; 
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberNm());
			pstmt.setString(4, member.getPhone());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
			
		}
		return result ;
	}

	/**pwDupCheck
	 * @param inputPw
	 * @param conn
	 * @return result 
	 * @throws Exception
	 */
	public int pwDupCheck(String inputPw, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("pwDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MainView.loginMember.getMemberId());
			pstmt.setString(2, inputPw);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				//.next를 수행하지 않으면 rs에 접근 불가 
				result = rs.getInt(1); //컬럼순번으로 결과 반환하기 
			}
			
			
		}finally { // 만든 역순 
			close(rs);
			close(pstmt);
		}
		return result;
	}

	/**updateInfo
	 * @param newPw
	 * @param newPhone
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updateInfo(String newPw, String newPhone, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateInfo");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, newPhone);
			pstmt.setInt(3,MainView.loginMember.getMemberNo());

			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
			
		}
		
		return result;
	}

	/** deleteMember
	 * @param inputPw
	 * @param conn
	 * @return result (0 || 1)
	 * @throws Exception
	 */
	public int deleteMember(String inputPw, Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteMember");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,MainView.loginMember.getMemberNo());
			pstmt.setString(2, inputPw);

			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
