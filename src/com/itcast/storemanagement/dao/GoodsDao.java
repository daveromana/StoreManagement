package com.itcast.storemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.storemanagement.daoimpl.GoodsDaoImpl;
import com.itcast.storemanagement.po.Goods;

public class GoodsDao implements GoodsDaoImpl {

	BaseDao bd = null;
	
	public GoodsDao() {
		bd = new BaseDao();
	}

	@Override
	public boolean GoodsAdd(Goods goods) {
		String sql = "insert into t_dc_goods (goods_name,goods_type,goods_address) values ('"
				+ goods.getGoodsName() + "','"+goods.getGoodsType()+"','"+goods.getGoodsAddress()+"')";
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean deleteOneGoods(String id) {
		String sql = "delete from t_dc_goods where goods_id = " + id;
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean updateOneGoods(Goods goods) {
		String sql = "update t_dc_goods set goods_name = '"
				+ goods.getGoodsName() + "',"+"goods_type = '"+goods.getGoodsType()+"',goods_address = '"+goods.getGoodsAddress()+"' where goods_id = " + goods.getId();
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public Goods getOneGoodsById(String id) throws SQLException {
		String sql = "select * from t_dc_goods where goods_id = "+id;
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
	}

	@Override
	public List<Goods> getAllGoods() throws SQLException {
		String sql = "select * from t_dc_goods";
		ResultSet rs = bd.executeQuery(sql);
		List<Goods> goodsList = new ArrayList<Goods>();
		while(rs.next()){
			goodsList.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		return goodsList;
	}
	@Override
	public List<Goods> getAllGoods(String goodsName) throws SQLException {
		String sql = "select * from t_dc_goods where goods_name like '%"+goodsName+"%'";
		ResultSet rs = bd.executeQuery(sql);
		List<Goods> goodsList = new ArrayList<Goods>();
		while(rs.next()){
			goodsList.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		return goodsList;
	}

	@Override
	public List<Goods> getAllGoods(int pageSize, int current_page)
			throws SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select * from t_dc_goods limit " + size + ","
				+ pageSize;
		ResultSet rs = bd.executeQuery(sql);
		List<Goods> goodsList = new ArrayList<Goods>();
		while(rs.next()){
			goodsList.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		return goodsList;
	}

	@Override
	public int getAllGoodsCount() throws SQLException {
		String sql = "select count(goods_id) from t_dc_goods";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}


	@Override
	public Goods getOneGoodsByName(String name) throws SQLException {
		String sql = "select * from t_dc_goods where goods_name = '"+name+"'";
		ResultSet rs = bd.executeQuery(sql);
		if(rs.next()){
		}
		else{
			Goods g = new Goods();
			g.setGoodsName(name);
			if(this.GoodsAdd(g)){
				rs = bd.executeQuery(sql);
				rs.next();
			}else{
				return null;
			}
		}
		return new Goods(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));
	}

	@Override
	public Goods getOneGoodsByCondition(String name) throws SQLException {
		String sql = "select * from t_dc_goods where goods_name = '"+name+"'";
		ResultSet rs = bd.executeQuery(sql);
		if(rs.next()){
		}
		else{
			Goods g = new Goods();
			g.setGoodsName(name);
			if(this.GoodsAdd(g)){
				rs = bd.executeQuery(sql);
				rs.next();
			}else{
				return null;
			}
		}
		return new Goods(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));
	}

	@Override
	public Goods getOneGoodsByCondition(String name, String type)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Goods getOneGoodsByCondition(String name, String type, String address)
			throws SQLException {
		String sql = "select * from t_dc_goods where goods_name = '"+name+"' and goods_type = '"+type+"' and goods_address = '"+address+"'";
		ResultSet rs = bd.executeQuery(sql);
		if(rs.next()){
		}
		else{
			Goods g = new Goods();
			g.setGoodsName(name);
			g.setGoodsAddress(address);
			g.setGoodsType(type);
			if(this.GoodsAdd(g)){
				rs = bd.executeQuery(sql);
				rs.next();
			}else{
				return null;
			}
		}
		return new Goods(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));
	}
	
	@Override
	public ResultSet getAllGoodsExcle() {
		String sql = "select * from t_dc_goods";
		ResultSet rs = bd.executeQuery(sql);
		return rs;
	}

}
