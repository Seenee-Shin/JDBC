package mini.jdbc.member.model.vo;

public class Member {
	
	private int sequenceNo;
	private int memberNo;
	private String memberGr;
	private String memberNm;
	private String memberSsn;
	private String adr;
	private String dName;
	
	public Member() {}


	
	public Member(int memberNo, String memberNm, String adr, String dName) {
		super();
		this.memberNo = memberNo;
		this.memberNm = memberNm;
		this.adr = adr;
		this.dName = dName;
	}



	// 학생 정보 삭제
	public Member(int memberNo) {
		super();
		this.memberNo = memberNo;
	}


	// 학생 정보 등록
	public Member(int memberNo, String memberGr, String memberNm, String memberSsn, String adr, String dName) {
		super();
		this.memberNo = memberNo;
		this.memberGr = memberGr;
		this.memberNm = memberNm;
		this.memberSsn = memberSsn;
		this.adr = adr;
		this.dName = dName;
	}

	// 학생 수정 

	public Member(String dName, String memberNm, String adr ) {
		super();
		this.dName = dName;
		this.memberNm = memberNm;
		this.adr = adr;
	}

	// 전체 학생리스트
	public Member(int sequenceNo, int memberNo, String memberGr, String memberNm, String memberSsn, String adr,
			String dName) {
		super();
		this.sequenceNo = sequenceNo;
		this.memberNo = memberNo;
		this.memberGr = memberGr;
		this.memberNm = memberNm;
		this.memberSsn = memberSsn;
		this.adr = adr;
		this.dName = dName;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}


	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getMemberGr() {
		return memberGr;
	}


	public void setMemberGr(String memberGr) {
		this.memberGr = memberGr;
	}


	public String getMemberNm() {
		return memberNm;
	}


	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}


	public String getMemberSsn() {
		return memberSsn;
	}


	public void setMemberSsn(String memberSsn) {
		this.memberSsn = memberSsn;
	}


	public String getAdr() {
		return adr;
	}


	public void setAdr(String adr) {
		this.adr = adr;
	}


	public String getdName() {
		return dName;
	}


	public void setdName(String dName) {
		this.dName = dName;
	}


	@Override
	public String toString() {
		return "Member [sequenceNo=" + sequenceNo + ", memberNo=" + memberNo + ", memberGr=" + memberGr + ", memberNm="
				+ memberNm + ", memberSsn=" + memberSsn + ", adr=" + adr + ", dName=" + dName + "]";
	}
	
	
	
	
	
}
