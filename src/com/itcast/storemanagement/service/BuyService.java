package com.itcast.storemanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.dao.BuyDao;
import com.itcast.storemanagement.po.GoodsBuyOrder;
import com.itcast.storemanagement.serviceimpl.BuyServiceImpl;

public class BuyService implements BuyServiceImpl {

	BuyDao bd = null;

	public BuyService() {
		bd = new BuyDao();
	}

	@Override
	public boolean buyOrderAdd(GoodsBuyOrder goodsBuyOrder) throws SQLException {
		return bd.buyOrderAdd(goodsBuyOrder);
	}

	@Override
	public boolean deleteOneBuyOrder(String id) {
		return bd.deleteOneBuyOrder(id);
	}

	@Override
	public boolean updateOneBuyOrder(GoodsBuyOrder goodsBuyOrder) throws SQLException {
		return bd.updateOneBuyOrder(goodsBuyOrder);
	}

	@Override
	public GoodsBuyOrder getOneBuyOrder(String id) throws SQLException {
		return bd.getOneBuyOrder(id);
	}

	@Override
	public List<GoodsBuyOrder> getAllBuyOrder() throws SQLException {
		return bd.getAllBuyOrder();
	}

	@Override
	public List<GoodsBuyOrder> getAllBuyOrder(int pageSize, int current_page)
			throws SQLException {
		return bd.getAllBuyOrder(pageSize, current_page);
	}

	@Override
	public int getAllBuyOrderCount() throws SQLException {
		return bd.getAllBuyOrderCount();
	}

	@Override
	public List<GoodsBuyOrder> getAllBuyOrder(String goodsId)
			throws SQLException {
		return bd.getAllBuyOrder(goodsId);
	}

}
