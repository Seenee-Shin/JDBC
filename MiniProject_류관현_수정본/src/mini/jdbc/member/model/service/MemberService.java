package mini.jdbc.member.model.service;

import static mini.jdbc.common.JDBCMiniProjectTemplate.*;

import java.sql.Connection;
import java.util.List;

import mini.jdbc.member.model.dao.MemberDAO;
import mini.jdbc.member.model.vo.Member;
import mini.jdbc.view.MainView;


public class MemberService {

	private MemberDAO dao = new MemberDAO();



	/**
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int checkMember(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.checkMember(memberNo, conn);
		
		close(conn);
		return result;
	}
	
	/** 학생 정보 수정
	 * @param memberDn 학과
	 * @param memberNm 학생 이름
	 * @param adr 주소
	 * @return result2
	 * @throws Exception 뷰로 던짐.
	 */
	public int updateMember(Member member) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateMember(member, conn);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	/** 학생 정보 삭제
	 * @param memberNo
	 * @return result
	 * @throws Exception 뷰로 던짐.
	 */
	public int deleteMember(int memberNo) throws Exception{
		
		Connection conn = getConnection();

		int result = dao.deleteMember(memberNo, conn);

		if (result > 0) {
			commit(conn);

		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}
	


	/** 학생 정보 등록 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int insertMember(Member member) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertMember(member, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	

	/** 학생 정보 상세 조회
	 * @param memberNo
	 * @return member
	 * @throws Exception
	 */
	public Member selectMember(int sequenceNo) throws Exception{
		Connection conn = getConnection();
		Member member = dao.selectMember(sequenceNo, conn);
		
		if(member != null) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		close(conn);
		
		return member;
	}
	

	/** 학생 정보 리스트 조회
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> selectMemberList() throws Exception{
		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectMemberList(conn);
		
		close(conn);
		
		return memberList;
	}
	
}
