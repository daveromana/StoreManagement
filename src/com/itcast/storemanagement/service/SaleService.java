package com.itcast.storemanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.dao.SaleDao;
import com.itcast.storemanagement.po.GoodsSaleOrder;
import com.itcast.storemanagement.serviceimpl.SaleServiceImpl;

public class SaleService implements SaleServiceImpl{
	
	SaleDao sd = null;
	public SaleService() {
		sd = new SaleDao();
	}

	@Override
	public boolean saleOrderAdd(GoodsSaleOrder goodsSaleOrder) throws SQLException {
		return sd.saleOrderAdd(goodsSaleOrder);
	}

	@Override
	public boolean deleteOneSaleOrder(String id) {
		return sd.deleteOneSaleOrder(id);
	}

	@Override
	public boolean updateOneSaleOrder(GoodsSaleOrder goodsSaleOrder) throws SQLException {
		return sd.updateOneSaleOrder(goodsSaleOrder);
	}

	@Override
	public GoodsSaleOrder getOneSaleOrder(String id) throws SQLException {
		return sd.getOneSaleOrder(id);
	}

	@Override
	public List<GoodsSaleOrder> getAllSaleOrder() throws SQLException {
		return sd.getAllSaleOrder();
	}

	@Override
	public List<GoodsSaleOrder> getAllSaleOrder(int pageSize, int current_page)
			throws SQLException {
		return sd.getAllSaleOrder(pageSize, current_page);
	}

	@Override
	public int getAllSaleOrderCount() throws SQLException {
		return sd.getAllSaleOrderCount();
	}

	@Override
	public List<GoodsSaleOrder> getAllSaleOrder(String goodsId)
			throws SQLException {
		return sd.getAllSaleOrder(goodsId);
	}

}
