package com.itcast.storemanagement.vo;

public class UserInfo {
	/*
	 * select
	 * u.user_userName,u.user_sex,u.user_phoneNum,u.user_email,l.login_loginName
	 * ,l.login_loginPassword from t_dc_user u inner join
	 * t_dc_user_login_mapping ulm on u.user_id = ulm.user_id inner join
	 * t_dc_login l on ulm.login_id = l.login_id where u.user_id = 1;
	 */
	private String id;
	private String userName;
	private String userSex;
	private String phoneNum;
	private String userEmail;
	private String loginId;
	private String loginName;
	private String loginPassword;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public UserInfo(String id, String userName, String userSex,
			String phoneNum, String userEmail, String loginId, String loginName,
			String loginPassword) {
		this.id = id;
		this.userName = userName;
		this.userSex = userSex;
		this.phoneNum = phoneNum;
		this.userEmail = userEmail;
		this.loginId = loginId;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
	}

	public UserInfo() {

	}

}
