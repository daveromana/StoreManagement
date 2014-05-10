package com.itcast.storemanagement.vo;

import java.util.ArrayList;
import java.util.List;

public class Role {

	private Integer id; //��ɫID
	private String roleName; // ��ɫ��
	private String comment; // ��ɫ����
	private List power = new ArrayList<>(); // ��ɫȨ��

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		comment = comment;
	}

	public List getPower() {
		return power;
	}

	public void setPower(List power) {
		this.power = power;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role() {

	}

	public Role(Integer id, String roleName, String comment, List power) {

		this.id = id;
		this.roleName = roleName;
		this.comment = comment;
		this.power = power;
	}

}
