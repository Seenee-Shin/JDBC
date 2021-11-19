package edu.kh.jdbc.member.model.vo;

public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberNm;
	private String phone;
	
	public Member() {}
	

	public Member(int memberNo, String memberId, String memberNm, String phone) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberNm = memberNm;
		this.phone = phone;
	}

   //Signup
	public Member(String memberId, String memberPw, String memberNm, String phone) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberNm = memberNm;
		this.phone = phone;
	}


	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMember(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberNm() {
		return memberNm;
	}

	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "Member [membeNo=" + memberNo + ", member=" + memberId + ", memberPw=" + memberPw + ", memberNm=" + memberNm
				+ ", phone=" + phone + "]";
	}
	}
