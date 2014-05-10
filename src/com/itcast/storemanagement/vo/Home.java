package com.itcast.storemanagement.vo;

/**
 * 用户首页要显示的字段信息
 * 
 * @author Tai
 * 
 */
public class Home {
	
	private String user_id;
	private String user_Name;
	private String power_name;
	private String power_uri;
	private String powerColumn_name;
	private String powerColumn_supermenu;
	public Home() {	}
	public Home(String user_Name, String power_name, String power_uri,
			String powerColumn_name, String powerColumn_supermenu, String user_id) {
		this.user_Name = user_Name;
		this.power_name = power_name;
		this.power_uri = power_uri;
		this.powerColumn_name = powerColumn_name;
		this.powerColumn_supermenu = powerColumn_supermenu;
		this.user_id = user_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getPower_name() {
		return power_name;
	}
	public void setPower_name(String power_name) {
		this.power_name = power_name;
	}
	public String getPower_uri() {
		return power_uri;
	}
	public void setPower_uri(String power_uri) {
		this.power_uri = power_uri;
	}
	public String getPowerColumn_name() {
		return powerColumn_name;
	}
	public void setPowerColumn_name(String powerColumn_name) {
		this.powerColumn_name = powerColumn_name;
	}
	public String getPowerColumn_supermenu() {
		return powerColumn_supermenu;
	}
	public void setPowerColumn_supermenu(String powerColumn_supermenu) {
		this.powerColumn_supermenu = powerColumn_supermenu;
	}
	
	
	
}
