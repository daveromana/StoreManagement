package com.itcast.storemanagement.po;

public class GoodsBuyOrder {

	private Integer id;
	private Integer goodsId;
	private String userId;
	private String goodsBuy_price;
	private Integer goodsBuy_num;
	private String goodsBuy_person;
	private String goodsBuy_date;
	private String goodsBuy_status;
	private String customerName;
	private String customerTel;
	private String customerComment;
	private String goodsComment;
	private String updatePerson;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGoodsBuy_price() {
		return goodsBuy_price;
	}

	public void setGoodsBuy_price(String goodsBuy_price) {
		this.goodsBuy_price = goodsBuy_price;
	}

	public Integer getGoodsBuy_num() {
		return goodsBuy_num;
	}

	public void setGoodsBuy_num(Integer goodsBuy_num) {
		this.goodsBuy_num = goodsBuy_num;
	}

	public String getGoodsBuy_person() {
		return goodsBuy_person;
	}

	public void setGoodsBuy_person(String goodsBuy_person) {
		this.goodsBuy_person = goodsBuy_person;
	}

	public String getGoodsBuy_date() {
		return goodsBuy_date;
	}

	public void setGoodsBuy_date(String goodsBuy_date) {
		this.goodsBuy_date = goodsBuy_date;
	}

	public String getGoodsBuy_status() {
		return goodsBuy_status;
	}

	public void setGoodsBuy_status(String goodsBuy_status) {
		this.goodsBuy_status = goodsBuy_status;
	}

	public GoodsBuyOrder(Integer id, Integer goodsId, String userId,
			String goodsBuy_price, Integer goodsBuy_num, String goodsBuy_person,
			String goodsBuy_date, String goodsBuy_status, String customerName,
			String customerTel, String customerComment, String goodsComment,
			String updatePerson) {
		this.id = id;
		this.goodsId = goodsId;
		this.userId = userId;
		this.goodsBuy_price = goodsBuy_price;
		this.goodsBuy_num = goodsBuy_num;
		this.goodsBuy_person = goodsBuy_person;
		this.goodsBuy_date = goodsBuy_date;
		this.goodsBuy_status = goodsBuy_status;
		this.customerName = customerName;
		this.customerTel = customerTel;
		this.customerComment = customerComment;
		this.goodsComment = goodsComment;
		this.updatePerson = updatePerson;
	}

	public GoodsBuyOrder() {

	}

}
