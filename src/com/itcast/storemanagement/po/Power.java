package com.itcast.storemanagement.po;

public class Power {

	private Integer id;// Ȩ��id
	private String powerName;// Ȩ�����
	private String powerComment;// Ȩ������
	private String powerUri;// Ȩ�޵�
	private String powerSuperMenu;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPowerName() {
		return powerName;
	}
	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}
	public String getPowerComment() {
		return powerComment;
	}
	public void setPowerComment(String powerComment) {
		this.powerComment = powerComment;
	}
	public String getPowerUri() {
		return powerUri;
	}
	public void setPowerUri(String powerUri) {
		this.powerUri = powerUri;
	}
	public Power() {

	}
	public String getPowerSuperMenu() {
		return powerSuperMenu;
	}
	public void setPowerSuperMenu(String powerSuperMenu) {
		this.powerSuperMenu = powerSuperMenu;
	}
	public Power(Integer id, String powerName, String powerComment,
			String powerUri, String powerSuperMenu) {
		this.id = id;
		this.powerName = powerName;
		this.powerComment = powerComment;
		this.powerUri = powerUri;
		this.powerSuperMenu = powerSuperMenu;
	}
	

	
}
