package com.itcast.storemanagement.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.dao.GoodsDao;
import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.serviceimpl.GoodsServiceImpl;

public class GoodsService implements GoodsServiceImpl{

	GoodsDao gd = null;
	
	public GoodsService() {
		gd = new GoodsDao();
	}

	@Override
	public boolean GoodsAdd(Goods goods) {
		return gd.GoodsAdd(goods);
	}

	@Override
	public boolean deleteOneGoods(String id) {
		return gd.deleteOneGoods(id);
	}

	@Override
	public boolean updateOneGoods(Goods goods) {
		return gd.updateOneGoods(goods);
	}

	@Override
	public Goods getOneGoodsById(String id) throws SQLException {
		return gd.getOneGoodsById(id);
	}

	@Override
	public List<Goods> getAllGoods() throws SQLException {
		return gd.getAllGoods();
	}
	
	@Override
	public ResultSet getAllGoodsExcle() throws SQLException {
		return gd.getAllGoodsExcle();
	}

	@Override
	public List<Goods> getAllGoods(int pageSize, int current_page)
			throws SQLException {
		return gd.getAllGoods(pageSize,current_page);
	}

	@Override
	public int getAllGoodsCount() throws SQLException {
		return gd.getAllGoodsCount();
	}

	@Override
	public Goods getOneGoodsByCondition(String name) throws SQLException {
		return gd.getOneGoodsByName(name);
	}

	@Override
	public Goods getOneGoodsByCondition(String name, String type)
			throws SQLException {
		return gd.getOneGoodsByCondition(name,type);
	}

	@Override
	public Goods getOneGoodsByCondition(String name, String type, String address)
			throws SQLException {
		return gd.getOneGoodsByCondition(name, type, address);
	}

	@Override
	public List<Goods> getAllGoods(String goodsName) throws SQLException {
		return gd.getAllGoods(goodsName);
	}

}
