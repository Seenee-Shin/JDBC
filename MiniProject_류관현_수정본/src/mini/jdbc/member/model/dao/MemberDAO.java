package mini.jdbc.member.model.dao;


import static mini.jdbc.common.JDBCMiniProjectTemplate.*;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mini.jdbc.member.model.vo.Member;
import mini.jdbc.view.MainView;
import oracle.net.aso.e;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public MemberDAO() {
		try {
			// 회원 관련 SQL 작성 xml 파일을 prop에 저장
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("Member-sql.xml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/** 학생 정보 일치 DAO
	 * @param memberNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int checkMember(int memberNo, Connection conn) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("checkMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
			
		return result;
	}


	/** 학생 정보 수정DAO
	 * @param dName
	 * @param memberNm
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member, Connection conn) throws Exception{
		// TODO Auto-generated method stub

		int result = 0;
		
		try {
			String sql = prop.getProperty("updateMember");
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, member.getdName());
			pstmt.setString(2, member.getMemberNm());
			pstmt.setString(3, member.getAdr());
			pstmt.setInt(4, member.getMemberNo());
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}

	/** 학생 정보 삭제 DAO
	 * @param memberNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int deleteMember(int memberNo, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 학생 정보 등록 DAO
	 * @param member
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int insertMember(Member member, Connection conn) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("insertMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, member.getMemberNo());
			pstmt.setString(2, member.getMemberGr());
			pstmt.setString(3, member.getMemberNm());
			pstmt.setString(4, member.getMemberSsn());
			pstmt.setString(5, member.getAdr());
			pstmt.setString(6, member.getdName());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	/** 학생 정보 상세 조회
	 * @param sequenceNo
	 * @param conn
	 * @return member
	 * @throws Exception
	 */
	public Member selectMember(int sequenceNo, Connection conn) throws Exception{

		Member member = null;
		
		try {
			String sql = prop.getProperty("selectMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sequenceNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				
				member.setMemberNo(rs.getInt("STUDENT_NO"));
				member.setMemberGr(rs.getString("STUDENT_GRADE"));
				member.setMemberNm(rs.getString("STUDENT_NAME"));
				member.setMemberSsn(rs.getString("STUDENT_SSN"));
				member.setAdr(rs.getString("STUDENT_ADDRESS"));
				member.setdName(rs.getString("DEPARTMENT_NAME"));
				
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return member;
	}

	/** 학생 정보 리스트 조회 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectMemberList(Connection conn) throws Exception{

		List<Member> memberList = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("selectMemberList");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int sequenceNo = rs.getInt("SEQUENCE_NO");
				int memberNo = rs.getInt("STUDENT_NO");
				String memberGr = rs.getString("STUDENT_GRADE");
				String memberNm = rs.getString("STUDENT_NAME");
				String memberSsn = rs.getString("STUDENT_SSN");
				String adr = rs.getString("STUDENT_ADDRESS");
				String dName = rs.getString("DEPARTMENT_NAME");
				
				Member member = new Member(sequenceNo, memberNo, memberGr, memberNm, memberSsn, adr, dName);
				
				memberList.add(member);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		return memberList;
	}


}
