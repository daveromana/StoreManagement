package com.itcast.storemanagement.po;

public class Image {
	private Integer id;
	private String imgUrl;
	private String uploadDate;
	private String imgUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getImgUser() {
		return imgUser;
	}
	public void setImgUser(String imgUser) {
		this.imgUser = imgUser;
	}
	public Image(Integer id, String imgUrl, String uploadDate, String imgUser) {
		super();
		this.id = id;
		this.imgUrl = imgUrl;
		this.uploadDate = uploadDate;
		this.imgUser = imgUser;
	}
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
