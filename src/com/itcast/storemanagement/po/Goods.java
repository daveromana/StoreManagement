package com.itcast.storemanagement.po;

public class Goods {
	
	private String id;
	private String goodsName;
	private String goodsType;
	private String goodsAddress;
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsAddress() {
		return goodsAddress;
	}
	public void setGoodsAddress(String goodsAddress) {
		this.goodsAddress = goodsAddress;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Goods(String id, String goodsName, String goodsType,
			String goodsAddress) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.goodsType = goodsType;
		this.goodsAddress = goodsAddress;
	}
	public Goods() {
		
	}
	
}
