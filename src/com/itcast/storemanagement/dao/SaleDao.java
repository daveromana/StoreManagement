package com.itcast.storemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.storemanagement.daoimpl.SaleDaoImpl;
import com.itcast.storemanagement.po.GoodsBuyOrder;
import com.itcast.storemanagement.po.GoodsSaleOrder;

public class SaleDao implements SaleDaoImpl{

	BaseDao bd = null;
	GoodsHavingDao ghd = null;
	
	public SaleDao() {
		bd = new BaseDao();
		ghd = new GoodsHavingDao();
	}

	@Override
	public boolean saleOrderAdd(GoodsSaleOrder goodsSaleOrder) throws SQLException {
		String[] sqls = new String[3];
		// 操作采购单
		sqls[0] = "insert into t_dc_goodssale (goods_id,user_id,goodssale_saleprice,goodssale_num,goodssale_person,goodssale_date,goodssale_status) values ("
				+ goodsSaleOrder.getGoodsId()
				+ ","
				+ goodsSaleOrder.getUserId()
				+ ","
				+ goodsSaleOrder.getGoodsSale_salePrice()
				+ ","
				+ goodsSaleOrder.getGoodsSale_num()
				+ ",'"
				+ goodsSaleOrder.getGoodsSale_person()
				+ "','"
				+ goodsSaleOrder.getGoodsSale_date()
				+ "','"
				+ goodsSaleOrder.getGoodsSale_status() + "')";
		if ("success".equals(goodsSaleOrder.getGoodsSale_status())) {
			// 操作入库单
			sqls[1] = "insert into t_dc_goodsout (goods_id, user_id,goodsout_saleprice,goodsout_num,goodsout_person,goodsout_date) values("
					+ goodsSaleOrder.getGoodsId()
					+ ","
					+ goodsSaleOrder.getUserId()
					+ ","
					+ goodsSaleOrder.getGoodsSale_salePrice()
					+ ","
					+ goodsSaleOrder.getGoodsSale_num()
					+ ",'"
					+ goodsSaleOrder.getGoodsSale_person()
					+ "','"
					+ goodsSaleOrder.getGoodsSale_date() + "')";
			if (ghd.getOneGoodsStatus(goodsSaleOrder.getGoodsId().toString())) {
				ResultSet rs = bd
						.executeQuery("select goodshaving_num from t_dc_goodshaving where goods_id = "
								+ goodsSaleOrder.getGoodsId());
				rs.next();
				Float goodsNum = (float) rs.getInt(1) - (goodsSaleOrder.getGoodsSale_num());
				// 操作在库货物单，货物存在修改数量
				sqls[2] = "update t_dc_goodshaving set goodshaving_num = "
						+ goodsNum + " where goods_id = "
						+ goodsSaleOrder.getGoodsId();
			}

		}
		return bd.dataUpdate(sqls);
	}

	@Override
	public boolean deleteOneSaleOrder(String id) {
		String sql = "delete from t_dc_goodssale where goodssale_id = " + id;
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean updateOneSaleOrder(GoodsSaleOrder goodsSaleOrder) throws SQLException {
		String[] sqls = new String[3];
		// 操作采购单
		sqls[0] = " update t_dc_goodssale set goods_id = "+goodsSaleOrder.getGoodsId()
				+", user_id = "+goodsSaleOrder.getUserId()
				+", goodssale_saleprice = "+ goodsSaleOrder.getGoodsSale_salePrice()
				+", goodssale_num = "+goodsSaleOrder.getGoodsSale_num()
				+", goodssale_person = '"+goodsSaleOrder.getGoodsSale_person()
				+"', goodssale_date = '"+goodsSaleOrder.getGoodsSale_date()
				+"', goodssale_status = '"+goodsSaleOrder.getGoodsSale_status()+"' where goodssale_id = "+goodsSaleOrder.getId();
		if ("success".equals(goodsSaleOrder.getGoodsSale_status())) {
			// 操作入库单
			sqls[1] = "insert into t_dc_goodsout (goods_id, user_id,goodsout_saleprice,goodsout_num,goodsout_person,goodsout_date) values("
					+ goodsSaleOrder.getGoodsId()
					+ ","
					+ goodsSaleOrder.getUserId()
					+ ","
					+ goodsSaleOrder.getGoodsSale_salePrice()
					+ ","
					+ goodsSaleOrder.getGoodsSale_num()
					+ ",'"
					+ goodsSaleOrder.getGoodsSale_person()
					+ "','"
					+ goodsSaleOrder.getGoodsSale_date() + "')";
			if (ghd.getOneGoodsStatus(goodsSaleOrder.getGoodsId().toString())) {
				ResultSet rs = bd
						.executeQuery("select goodshaving_num from t_dc_goodshaving where goods_id = "
								+ goodsSaleOrder.getGoodsId());
				rs.next();
				Float goodsNum = (float) rs.getInt(1) - (goodsSaleOrder.getGoodsSale_num());
				// 操作在库货物单，货物存在修改数量
				sqls[2] = "update t_dc_goodshaving set goodshaving_num = "
						+ goodsNum + " where goods_id = "
						+ goodsSaleOrder.getGoodsId();
			}

		}
		return bd.dataUpdate(sqls);
	}

	@Override
	public GoodsSaleOrder getOneSaleOrder(String id) throws SQLException {
		String sql = "select * from t_dc_goodssale where goodssale_id = "+id;
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		GoodsSaleOrder gso = new GoodsSaleOrder();
		gso.setId(rs.getInt(1));
		gso.setUserId(rs.getString(2));
		gso.setGoodsId(rs.getInt(3));
		gso.setGoodsSale_salePrice(rs.getString(5));
		gso.setGoodsSale_num(rs.getInt(6));
		gso.setGoodsSale_person(rs.getString(7));
		gso.setGoodsSale_date(rs.getString(8));
		gso.setGoodsSale_status(rs.getString(9));
		return gso;
	}

	@Override
	public List<GoodsSaleOrder> getAllSaleOrder() throws SQLException {
		String sql = "select * from t_dc_goodssale";
		ResultSet rs = bd.executeQuery(sql);
		List<GoodsSaleOrder> goodsSaleList = new ArrayList<GoodsSaleOrder>();
		while (rs.next()) {
			GoodsSaleOrder gso = new GoodsSaleOrder();
			gso.setId(rs.getInt(1));
			gso.setUserId(rs.getString(2));
			gso.setGoodsId(rs.getInt(3));
			gso.setGoodsSale_salePrice(rs.getString(5));
			gso.setGoodsSale_num(rs.getInt(6));
			gso.setGoodsSale_person(rs.getString(7));
			gso.setGoodsSale_date(rs.getString(8));
			gso.setGoodsSale_status(rs.getString(9));
			goodsSaleList.add(gso);
		}
		return goodsSaleList;
	}
	@Override
	public List<GoodsSaleOrder> getAllSaleOrder(String goodsId) throws SQLException {
		String sql = "select * from t_dc_goodssale where goods_id in ("+goodsId+")";
		ResultSet rs = bd.executeQuery(sql);
		List<GoodsSaleOrder> goodsSaleList = new ArrayList<GoodsSaleOrder>();
		while (rs.next()) {
			GoodsSaleOrder gso = new GoodsSaleOrder();
			gso.setId(rs.getInt(1));
			gso.setUserId(rs.getString(2));
			gso.setGoodsId(rs.getInt(3));
			gso.setGoodsSale_salePrice(rs.getString(5));
			gso.setGoodsSale_num(rs.getInt(6));
			gso.setGoodsSale_person(rs.getString(7));
			gso.setGoodsSale_date(rs.getString(8));
			gso.setGoodsSale_status(rs.getString(9));
			goodsSaleList.add(gso);
		}
		return goodsSaleList;
	}

	@Override
	public List<GoodsSaleOrder> getAllSaleOrder(int pageSize, int current_page)
			throws SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select * from t_dc_goodssale limit " + size + ","
				+ pageSize;
		ResultSet rs = bd.executeQuery(sql);
		List<GoodsSaleOrder> goodsSaleList = new ArrayList<GoodsSaleOrder>();
		while (rs.next()) {
			GoodsSaleOrder gso = new GoodsSaleOrder();
			gso.setId(rs.getInt(1));
			gso.setUserId(rs.getString(2));
			gso.setGoodsId(rs.getInt(3));
			gso.setGoodsSale_salePrice(rs.getString(5));
			gso.setGoodsSale_num(rs.getInt(6));
			gso.setGoodsSale_person(rs.getString(7));
			gso.setGoodsSale_date(rs.getString(8));
			gso.setGoodsSale_status(rs.getString(9));
			goodsSaleList.add(gso);
		}
		return goodsSaleList;
	}

	@Override
	public int getAllSaleOrderCount() throws SQLException {
		String sql = "select count(goodssale_id) from t_dc_goodssale";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}

}
