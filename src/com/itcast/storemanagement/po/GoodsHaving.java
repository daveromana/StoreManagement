package com.itcast.storemanagement.po;

public class GoodsHaving {
	private Integer id;
	private Integer goodsId;
	private Integer goodsNum;
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

	private String goodsPrice;
	private String customerName;
	private String customerTel;
	private String customerComment;
	private String goodsComment;
	private String updatePerson;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getCustomerComment() {
		return customerComment;
	}

	public void setCustomerComment(String customerComment) {
		this.customerComment = customerComment;
	}

	public String getGoodsComment() {
		return goodsComment;
	}

	public void setGoodsComment(String goodsComment) {
		this.goodsComment = goodsComment;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public GoodsHaving(Integer id, Integer goodsId, Integer goodsNum,
			String goodsName, String goodsPrice, String customerName,
			String customerTel, String customerComment, String goodsComment,
			String updatePerson) {
		super();
		this.id = id;
		this.goodsId = goodsId;
		this.goodsNum = goodsNum;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.customerName = customerName;
		this.customerTel = customerTel;
		this.customerComment = customerComment;
		this.goodsComment = goodsComment;
		this.updatePerson = updatePerson;
	}

	public GoodsHaving() {

	}

}
