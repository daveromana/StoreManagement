package com.itcast.storemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.storemanagement.daoimpl.BuyDaoImpl;
import com.itcast.storemanagement.po.GoodsBuyOrder;

public class BuyDao implements BuyDaoImpl {

	BaseDao bd = null;
	GoodsHavingDao ghd = null;

	public BuyDao() {
		ghd = new GoodsHavingDao();
		bd = new BaseDao();
	}

	@Override
	public boolean buyOrderAdd(GoodsBuyOrder goodsBuyOrder) throws SQLException {
		String[] sqls = new String[3];
		// 操作采购单
		sqls[0] = "insert into t_dc_goodsbuy (goods_id,user_id,goodsbuy_price,goodsbuy_num,goodsbuy_person,goodsbuy_date,goodsbuy_status) values ("
				+ goodsBuyOrder.getGoodsId()
				+ ","
				+ goodsBuyOrder.getUserId()
				+ ","
				+ goodsBuyOrder.getGoodsBuy_price()
				+ ","
				+ goodsBuyOrder.getGoodsBuy_num()
				+ ",'"
				+ goodsBuyOrder.getGoodsBuy_person()
				+ "','"
				+ goodsBuyOrder.getGoodsBuy_date()
				+ "','"
				+ goodsBuyOrder.getGoodsBuy_status() + "')";
		if ("success".equals(goodsBuyOrder.getGoodsBuy_status())) {
			// 操作入库单
			sqls[1] = "insert into t_dc_goodsin (goods_id, user_id,goodsin_price,goodsin_num,goodsin_person,goodsin_date) values("
					+ goodsBuyOrder.getGoodsId()
					+ ","
					+ goodsBuyOrder.getUserId()
					+ ","
					+ goodsBuyOrder.getGoodsBuy_price()
					+ ","
					+ goodsBuyOrder.getGoodsBuy_num()
					+ ",'"
					+ goodsBuyOrder.getGoodsBuy_person()
					+ "','"
					+ goodsBuyOrder.getGoodsBuy_date() + "')";
			if (ghd.getOneGoodsStatus(goodsBuyOrder.getGoodsId().toString())) {
				ResultSet rs = bd
						.executeQuery("select goodshaving_num from t_dc_goodshaving where goods_id = "
								+ goodsBuyOrder.getGoodsId());
				rs.next();
				Float goodsNum = (float) (goodsBuyOrder.getGoodsBuy_num() + rs
						.getInt(1));
				// 操作在库货物单，货物存在修改数量
				sqls[2] = "update t_dc_goodshaving set goodshaving_num = "
						+ goodsNum + " where goods_id = "
						+ goodsBuyOrder.getGoodsId();
			} else {
				// 操作在库货物单 或者增加在库货物
				sqls[2] = "insert into t_dc_goodshaving (goods_id,goodshaving_num,goodshaving_price,goodshaving_customer_name,goodshaving_customer_tel,goodshaving_customer_comment,goodshaving_comment,goodshaving_update_person) values ("
						+ goodsBuyOrder.getGoodsId()
						+ ","
						+ goodsBuyOrder.getGoodsBuy_num()
						+ ","
						+ goodsBuyOrder.getGoodsBuy_price()
						+ ","
						+ "'"
						+ goodsBuyOrder.getCustomerName()
						+ "','"
						+ goodsBuyOrder.getCustomerTel()
						+ "','"
						+ goodsBuyOrder.getCustomerComment()
						+ "','"
						+ goodsBuyOrder.getGoodsComment()
						+ "','"
						+ goodsBuyOrder.getUpdatePerson() + "')";
				;
			}

		}
		return bd.dataUpdate(sqls);
	}

	@Override
	public boolean deleteOneBuyOrder(String id) {
		String sql = "delete from t_dc_goodsbuy where goodsbuy_id = " + id;
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean updateOneBuyOrder(GoodsBuyOrder goodsBuyOrder) throws SQLException {
		String[] sqls = new String[3];
		// 操作采购单
		sqls[0] = " update t_dc_goodsbuy set goods_id = "+goodsBuyOrder.getGoodsId()
				+", user_id = "+goodsBuyOrder.getUserId()
				+", goodsbuy_price = "+ goodsBuyOrder.getGoodsBuy_price()
				+", goodsbuy_num = "+goodsBuyOrder.getGoodsBuy_num()
				+", goodsbuy_person = '"+goodsBuyOrder.getGoodsBuy_person()
				+"', goodsbuy_date = '"+goodsBuyOrder.getGoodsBuy_date()
				+"', goodsbuy_status = '"+goodsBuyOrder.getGoodsBuy_status()+"' where goodsbuy_id = "+goodsBuyOrder.getId();
		if ("success".equals(goodsBuyOrder.getGoodsBuy_status())) {
			// 操作入库单
			sqls[1] = "insert into t_dc_goodsin (goods_id, user_id,goodsin_price,goodsin_num,goodsin_person,goodsin_date) values("
					+ goodsBuyOrder.getGoodsId()
					+ ","
					+ goodsBuyOrder.getUserId()
					+ ","
					+ goodsBuyOrder.getGoodsBuy_price()
					+ ","
					+ goodsBuyOrder.getGoodsBuy_num()
					+ ",'"
					+ goodsBuyOrder.getGoodsBuy_person()
					+ "','"
					+ goodsBuyOrder.getGoodsBuy_date() + "')";
			if (ghd.getOneGoodsStatus(goodsBuyOrder.getGoodsId().toString())) {
				ResultSet rs = bd
						.executeQuery("select goodshaving_num from t_dc_goodshaving where goods_id = "
								+ goodsBuyOrder.getGoodsId());
				rs.next();
				Float goodsNum = (float) (goodsBuyOrder.getGoodsBuy_num() + rs
						.getInt(1));
				// 操作在库货物单，货物存在修改数量
				sqls[2] = "update t_dc_goodshaving set goodshaving_num = "
						+ goodsNum + " where goods_id = "
						+ goodsBuyOrder.getGoodsId();
			} else {
				// 操作在库货物单 或者增加在库货物
				sqls[2] = "insert into t_dc_goodshaving (goods_id,goodshaving_num,goodshaving_price,goodshaving_customer_name,goodshaving_customer_tel,goodshaving_customer_comment,goodshaving_comment,goodshaving_update_person) values ("
						+ goodsBuyOrder.getGoodsId()
						+ ","
						+ goodsBuyOrder.getGoodsBuy_num()
						+ ","
						+ goodsBuyOrder.getGoodsBuy_price()
						+ ","
						+ "'"
						+ goodsBuyOrder.getCustomerName()
						+ "','"
						+ goodsBuyOrder.getCustomerTel()
						+ "','"
						+ goodsBuyOrder.getCustomerComment()
						+ "','"
						+ goodsBuyOrder.getGoodsComment()
						+ "','"
						+ goodsBuyOrder.getUpdatePerson() + "')";
			}

		}
		return bd.dataUpdate(sqls);
	}

	@Override
	public GoodsBuyOrder getOneBuyOrder(String id) throws SQLException {
		String sql = "select * from t_dc_goodsbuy where goodsbuy_id = "+id;
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		GoodsBuyOrder gbo = new GoodsBuyOrder();
		gbo.setId(rs.getInt(1));
		gbo.setGoodsId(rs.getInt(2));
		gbo.setUserId(rs.getString(3));
		gbo.setGoodsBuy_price(rs.getString(4));
		gbo.setGoodsBuy_num(rs.getInt(5));
		gbo.setGoodsBuy_person(rs.getString(6));
		gbo.setGoodsBuy_date(rs.getString(7));
		gbo.setGoodsBuy_status(rs.getString(8));
		return gbo;
	}

	@Override
	public List<GoodsBuyOrder> getAllBuyOrder() throws SQLException {
		String sql = "select * from t_dc_goodsbuy";
		ResultSet rs = bd.executeQuery(sql);
		List<GoodsBuyOrder> goodsBuyList = new ArrayList<GoodsBuyOrder>();
		while (rs.next()) {
			GoodsBuyOrder gbo = new GoodsBuyOrder();
			gbo.setId(rs.getInt(1));
			gbo.setGoodsId(rs.getInt(2));
			gbo.setUserId(rs.getString(3));
			gbo.setGoodsBuy_price(rs.getString(4));
			gbo.setGoodsBuy_num(rs.getInt(5));
			gbo.setGoodsBuy_person(rs.getString(6));
			gbo.setGoodsBuy_date(rs.getString(7));
			gbo.setGoodsBuy_status(rs.getString(8));
			goodsBuyList.add(gbo);
		}
		return goodsBuyList;
	}
	
	@Override
	public List<GoodsBuyOrder> getAllBuyOrder(String goodsId) throws SQLException {
		String sql = "select * from t_dc_goodsbuy where goods_id in ("+ goodsId+")";
		ResultSet rs = bd.executeQuery(sql);
		List<GoodsBuyOrder> goodsBuyList = new ArrayList<GoodsBuyOrder>();
		while (rs.next()) {
			GoodsBuyOrder gbo = new GoodsBuyOrder();
			gbo.setId(rs.getInt(1));
			gbo.setGoodsId(rs.getInt(2));
			gbo.setUserId(rs.getString(3));
			gbo.setGoodsBuy_price(rs.getString(4));
			gbo.setGoodsBuy_num(rs.getInt(5));
			gbo.setGoodsBuy_person(rs.getString(6));
			gbo.setGoodsBuy_date(rs.getString(7));
			gbo.setGoodsBuy_status(rs.getString(8));
			goodsBuyList.add(gbo);
		}
		return goodsBuyList;
	}

	@Override
	public List<GoodsBuyOrder> getAllBuyOrder(int pageSize, int current_page)
			throws SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select * from t_dc_goodsbuy limit " + size + ","
				+ pageSize;
		ResultSet rs = bd.executeQuery(sql);
		List<GoodsBuyOrder> goodsBuyList = new ArrayList<GoodsBuyOrder>();
		while (rs.next()) {
			GoodsBuyOrder gbo = new GoodsBuyOrder();
			gbo.setId(rs.getInt(1));
			gbo.setGoodsId(rs.getInt(2));
			gbo.setUserId(rs.getString(3));
			gbo.setGoodsBuy_price(rs.getString(4));
			gbo.setGoodsBuy_num(rs.getInt(5));
			gbo.setGoodsBuy_person(rs.getString(6));
			gbo.setGoodsBuy_date(rs.getString(7));
			gbo.setGoodsBuy_status(rs.getString(8));
			goodsBuyList.add(gbo);
		}
		return goodsBuyList;
	}

	@Override
	public int getAllBuyOrderCount() throws SQLException {
		String sql = "select count(goodsbuy_id) from t_dc_goodsbuy";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}

}
