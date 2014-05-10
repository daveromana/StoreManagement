package com.itcast.storemanagement.vo;

import java.util.ArrayList;
import java.util.List;

public class User {

	private Integer id; // 用户ID
	private String userName; // 用户姓名
	private String sex; // 性别
	private String phoneNUM_; // 电话号码
	private String email; // E-mail地址
	private String comment; // 简要描述
	private String job; // 岗位
	private List role = new ArrayList<Role>();// 用户角色

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNUM_() {
		return phoneNUM_;
	}

	public void setPhoneNUM_(String phoneNUM_) {
		this.phoneNUM_ = phoneNUM_;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public List getRole() {
		return role;
	}

	public void setRole(List role) {
		this.role = role;
	}

	public User() {

	}

	public User(Integer id, String userName, String sex, String phoneNUM_,
			String email, String comment, String job, List role) {
		this.id = id;
		this.userName = userName;
		this.sex = sex;
		this.phoneNUM_ = phoneNUM_;
		this.email = email;
		this.comment = comment;
		this.job = job;
		this.role = role;
	}

}
