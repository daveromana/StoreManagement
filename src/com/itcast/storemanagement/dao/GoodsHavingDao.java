package com.itcast.storemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.storemanagement.daoimpl.GoodsHavingDaoImpl;
import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.GoodsHaving;

public class GoodsHavingDao implements GoodsHavingDaoImpl {

	BaseDao bd = null;

	public GoodsHavingDao() {
		bd = new BaseDao();
	}

	@Override
	public boolean GoodsAdd(GoodsHaving goodsHaving) {
		String sql = "insert into t_dc_goodshaving (goods_id,goodshaving_num,goodshaving_price,goodshaving_customer_name,goodshaving_customer_tel,goodshaving_customer_comment,goodshaving_comment,goodshaving_update_person) values ("
				+ goodsHaving.getGoodsId()
				+ ","
				+ goodsHaving.getGoodsNum()
				+ ","
				+ goodsHaving.getGoodsPrice()
				+ ","
				+ "'"
				+ goodsHaving.getCustomerName()
				+ "','"
				+ goodsHaving.getCustomerTel()
				+ "','"
				+ goodsHaving.getCustomerComment()
				+ "','"
				+ goodsHaving.getGoodsComment()
				+ "','"
				+ goodsHaving.getUpdatePerson() + "')";
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean deleteOneGoods(String id) {
		String sql = "delete from t_dc_goodshaving where goodshaving_id = "
				+ id;
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean updateOneGoods(GoodsHaving goodsHaving) {
		String sql = "update t_dc_goodshaving set " + "goods_id = "
				+ goodsHaving.getGoodsId() + ", goodshaving_num = "
				+ goodsHaving.getGoodsNum() + ", goodshaving_price = "
				+ goodsHaving.getGoodsPrice() + ","
				+ "goodshaving_customer_name = '"
				+ goodsHaving.getCustomerName() + "',"
				+ "goodshaving_customer_tel = '" + goodsHaving.getCustomerTel()
				+ "'," + " goodshaving_customer_comment = '"
				+ goodsHaving.getCustomerComment() + "',"
				+ " goodshaving_comment = '" + goodsHaving.getGoodsComment()
				+ "'," + " goodshaving_update_person = '"
				+ goodsHaving.getUpdatePerson() + "' where goodshaving_id = "
				+ goodsHaving.getId();
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean getOneGoodsStatus(String id) throws SQLException {
		String sql = "select * from t_dc_goodshaving where goods_id = " + id;
		ResultSet rs = bd.executeQuery(sql);
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<GoodsHaving> getAllGoodsHaving() throws SQLException {
		String sql = "select gh.*,g.goods_name from t_dc_goodshaving gh left join t_dc_goods g on gh.goods_id = g.goods_id";
		ResultSet rs = bd.executeQuery(sql);
		List<GoodsHaving> goodsHavingList = new ArrayList<GoodsHaving>();
		while (rs.next()) {
			goodsHavingList.add(new GoodsHaving(rs.getInt(1), rs.getInt(2), rs
					.getInt(3), rs.getString("goods_name"), rs.getString(4), rs
					.getString(5), rs.getString(6), rs.getString(7), rs
					.getString(8), rs.getString(9)));
		}
		return goodsHavingList;
	}
	@Override
	public List<GoodsHaving> getAllGoodsHaving(String goodsName) throws SQLException {
		String sql = "select gh.*,g.goods_name from t_dc_goodshaving gh left join t_dc_goods g on gh.goods_id = g.goods_id where g.goods_name like '%"+goodsName+"%'";
		ResultSet rs = bd.executeQuery(sql);
		List<GoodsHaving> goodsHavingList = new ArrayList<GoodsHaving>();
		while (rs.next()) {
			goodsHavingList.add(new GoodsHaving(rs.getInt(1), rs.getInt(2), rs
					.getInt(3), rs.getString("goods_name"), rs.getString(4), rs
					.getString(5), rs.getString(6), rs.getString(7), rs
					.getString(8), rs.getString(9)));
		}
		return goodsHavingList;
	}

	@Override
	public List<GoodsHaving> getAllGoodsHaving(int pageSize, int current_page)
			throws SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select gh.*,g.goods_name from t_dc_goodshaving gh "
				+"left join t_dc_goods g on gh.goods_id = g.goods_id "
				+"limit " + size + "," + pageSize;
		ResultSet rs = bd.executeQuery(sql);
		List<GoodsHaving> goodsHavingList = new ArrayList<GoodsHaving>();
		while (rs.next()) {
			goodsHavingList.add(new GoodsHaving(rs.getInt(1), rs.getInt(2), rs
					.getInt(3), rs.getString("goods_name"), rs.getString(4), rs
					.getString(5), rs.getString(6), rs.getString(7), rs
					.getString(8), rs.getString(9)));
		}
		return goodsHavingList;
	}

	@Override
	public int getAllGoodsHavingCount() throws SQLException {
		String sql = "select count(goodshaving_id) from t_dc_goodshaving gh left join t_dc_goods g on gh.goods_id = g.goods_id";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}

	@Override
	public GoodsHaving getOneGoods(String id) throws SQLException {
		String sql = "select * from t_dc_goodshaving where goodshaving_id = "
				+ id;
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		GoodsHaving gh = new GoodsHaving(rs.getInt(1), rs.getInt(2),
				rs.getInt(3), "", rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8),
				rs.getString(9));
		return gh;
	}

}
