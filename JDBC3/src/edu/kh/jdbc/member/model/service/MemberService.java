package edu.kh.jdbc.member.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.vo.Member;
import edu.kh.jdbc.view.MainView;

public class MemberService {
	private MemberDAO dao = new MemberDAO();

	/** login Service 
	 * @param memberId
	 * @param memberPw
	 * @return member (login information)    
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw) throws Exception {
		
		Connection conn = getConnection();
		
		Member member = dao.login(memberId,memberPw,conn);
		
		close(conn);
		
		return member;
	}

	/**
	 * @param inputId
	 * @return int result (1 || 0)
	 * @throws Exception
	 */
	public int idDupCheck(String inputId) throws Exception{
		
		Connection conn = getConnection();
		int result =  dao.idDupCheck(inputId,conn);
		
		close(conn);
		
		return result;
	}

	/**signUp Service
	 * @param member
	 * @return  int result (0 || 1)
	 * @throws Exception
	 */
	public int signUp(Member member)throws Exception {
		
		Connection conn = getConnection();
		
		int result =  dao.signUp(member, conn);
		
		//트랜잭션 제어 
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int pwDupCheck(String inputPw) throws Exception {
		Connection conn = getConnection();
		
		int result =  dao.pwDupCheck(inputPw,conn);
		
		close(conn);
		
		return result;
	}

	public int updateInfo(String newPw, String newPhone) throws Exception {
		
		Connection conn = getConnection();
		
		int result =  dao.updateInfo(newPw,newPhone, conn);
		
		if(result > 0) {
			commit(conn);
			
			MainView.loginMember.setPhone(newPhone);
			
		}else {
			rollback(conn);
			}
		
		close(conn);
		
		return result;
	}

	/** deleteMember
	 * @param inputPw
	 * @return result ( 0 || 1 )
	 * @throws Exception
	 */
	public int deleteMember(String inputPw) throws Exception{
		Connection conn = getConnection();
		
		int result =  dao.deleteMember(inputPw,conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
			}
		
		close(conn);
		
		return result;
	}
	

}
