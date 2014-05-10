package com.itcast.storemanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.dao.GoodsHavingDao;
import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.GoodsHaving;
import com.itcast.storemanagement.serviceimpl.GoodsHavingServiceImpl;

public class GoodsHavingService implements GoodsHavingServiceImpl{
	
	GoodsHavingDao ghd = null;
	public GoodsHavingService() {
		ghd = new GoodsHavingDao();
	}

	@Override
	public boolean GoodsAdd(GoodsHaving goodsHaving) {
		return ghd.GoodsAdd(goodsHaving);
	}

	@Override
	public boolean deleteOneGoods(String id) {
		return ghd.deleteOneGoods(id);
	}

	@Override
	public boolean updateOneGoods(GoodsHaving goodsHaving) {
		return ghd.updateOneGoods(goodsHaving);
	}

	@Override
	public GoodsHaving getOneGoods(String id) throws SQLException {
		return ghd.getOneGoods(id);
	}

	@Override
	public List<GoodsHaving> getAllGoodsHaving() throws SQLException {
		return ghd.getAllGoodsHaving();
	}

	@Override
	public List<GoodsHaving> getAllGoodsHaving(int pageSize, int current_page)
			throws SQLException {
		return ghd.getAllGoodsHaving(pageSize, current_page);
	}

	@Override
	public int getAllGoodsHavingCount() throws SQLException {
		return ghd.getAllGoodsHavingCount();
	}

	public boolean getOneGoodsStatus(String id) throws SQLException {
		return ghd.getOneGoodsStatus(id);
	}

	@Override
	public List<GoodsHaving> getAllGoodsHaving(String goodsName)
			throws SQLException {
		return ghd.getAllGoodsHaving(goodsName);
	}

}
