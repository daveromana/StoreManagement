package com.itcast.storemanagement.po;

public class Menu {
	
	private Integer id;
	private String powerColumnName;
	private String powerColumnSuperMenu;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPowerColumnName() {
		return powerColumnName;
	}
	public void setPowerColumnName(String powerColumnName) {
		this.powerColumnName = powerColumnName;
	}
	public String getPowerColumnSuperMenu() {
		return powerColumnSuperMenu;
	}
	public void setPowerColumnSuperMenu(String powerColumnSuperMenu) {
		this.powerColumnSuperMenu = powerColumnSuperMenu;
	}
	public Menu(Integer id, String powerColumnName, String powerColumnSuperMenu) {
		this.id = id;
		this.powerColumnName = powerColumnName;
		this.powerColumnSuperMenu = powerColumnSuperMenu;
	}
	public Menu() {
	}
	
	

}
