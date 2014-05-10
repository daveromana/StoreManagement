package com.itcast.storemanagement.po;

public class Account {

	private Integer id;
	private String loginName;
	private String loginPassword;
	private String userName;

	public Integer getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Account() {
	}

	public Account(Integer id, String loginName, String loginPassword,
			String userName) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.userName = userName;
	}

	

}
