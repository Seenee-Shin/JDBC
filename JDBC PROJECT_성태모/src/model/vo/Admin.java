package model.vo;

public class Admin {
	
	private String adminId; // 관리자 아이디
	private String adminPw; // 관리자 비밀번호
	private String adminNm; // 관리자 아이디
	
	public Admin() {}
	
	
	public Admin(String adminId, String adminPw, String adminNm) {
		super();
		this.adminId = adminId;
		this.adminPw = adminPw;
		this.adminNm = adminNm;
	}


	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	public String getAdminNm() {
		return adminNm;
	}


	public void setAdminNm(String adminNm) {
		this.adminNm = adminNm;
	}


	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminPw=" + adminPw + ", adminNm=" + adminNm + "]";
	}


	

}
