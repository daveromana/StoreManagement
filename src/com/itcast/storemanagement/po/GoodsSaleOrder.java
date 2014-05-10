package com.itcast.storemanagement.po;

public class GoodsSaleOrder {

	private Integer id;
	private Integer goodsId;
	private String  userId;
	private String goodsSale_buyPrice;
	private String goodsSale_salePrice;
	private String goodsSale_type;
	private String goodsSale_address;
	private Integer goodsSale_num;
	private String goodsSale_person;
	private String goodsSale_date;
	private String goodsSale_status;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGoodsSale_buyPrice() {
		return goodsSale_buyPrice;
	}

	public void setGoodsSale_buyPrice(String goodsSale_buyPrice) {
		this.goodsSale_buyPrice = goodsSale_buyPrice;
	}

	public String getGoodsSale_salePrice() {
		return goodsSale_salePrice;
	}

	public void setGoodsSale_salePrice(String goodsSale_salePrice) {
		this.goodsSale_salePrice = goodsSale_salePrice;
	}

	public String getGoodsSale_type() {
		return goodsSale_type;
	}

	public void setGoodsSale_type(String goodsSale_type) {
		this.goodsSale_type = goodsSale_type;
	}

	public String getGoodsSale_address() {
		return goodsSale_address;
	}

	public void setGoodsSale_address(String goodsSale_address) {
		this.goodsSale_address = goodsSale_address;
	}

	public Integer getGoodsSale_num() {
		return goodsSale_num;
	}

	public void setGoodsSale_num(Integer goodsSale_num) {
		this.goodsSale_num = goodsSale_num;
	}

	public String getGoodsSale_person() {
		return goodsSale_person;
	}

	public void setGoodsSale_person(String goodsSale_person) {
		this.goodsSale_person = goodsSale_person;
	}

	public String getGoodsSale_date() {
		return goodsSale_date;
	}

	public void setGoodsSale_date(String goodsSale_date) {
		this.goodsSale_date = goodsSale_date;
	}

	public String getGoodsSale_status() {
		return goodsSale_status;
	}

	public void setGoodsSale_status(String goodsSale_status) {
		this.goodsSale_status = goodsSale_status;
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

	public GoodsSaleOrder(Integer id, Integer goodsId, String userId,
			String goodsSale_buyPrice, String goodsSale_salePrice,
			String goodsSale_type, String goodsSale_address,
			Integer goodsSale_num, String goodsSale_person,
			String goodsSale_date, String goodsSale_status,
			String customerName, String customerTel, String customerComment,
			String goodsComment, String updatePerson) {
		this.id = id;
		this.goodsId = goodsId;
		this.userId = userId;
		this.goodsSale_buyPrice = goodsSale_buyPrice;
		this.goodsSale_salePrice = goodsSale_salePrice;
		this.goodsSale_type = goodsSale_type;
		this.goodsSale_address = goodsSale_address;
		this.goodsSale_num = goodsSale_num;
		this.goodsSale_person = goodsSale_person;
		this.goodsSale_date = goodsSale_date;
		this.goodsSale_status = goodsSale_status;
		this.customerName = customerName;
		this.customerTel = customerTel;
		this.customerComment = customerComment;
		this.goodsComment = goodsComment;
		this.updatePerson = updatePerson;
	}

	public GoodsSaleOrder() {

	}

}
