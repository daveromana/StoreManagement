package com.itcast.storemanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.GoodsHaving;

public interface GoodsHavingDaoImpl {
	
	/**
	 * 增加库存货物
	 * @param goodsHaving
	 * @return
	 */
	public boolean GoodsAdd(GoodsHaving goodsHaving);
	
	/**
	 * 删除一个货物
	 * @param id
	 * @return
	 */
	public boolean deleteOneGoods(String id);
	
	/**
	 * 修改货物方法
	 * @param goodsHaving
	 * @return
	 */
	public boolean updateOneGoods(GoodsHaving goodsHaving);
	
	/**
	 * 得到一项货物的信息，便于修改
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public GoodsHaving getOneGoods(String id) throws SQLException;
	
	
	/**
	 * 获得所有在库货物列表
	 * @return
	 * @throws SQLException 
	 */
	public List<GoodsHaving> getAllGoodsHaving() throws SQLException;
	
	/**
	 * 查询所有货物分页 pageSize
	 * @param current_page
	 * @return
	 * @throws SQLException
	 */
	public List<GoodsHaving> getAllGoodsHaving(int pageSize, int current_page) throws SQLException;
	
	/**
	 * 获取在库货物总记录数
	 * @return
	 * @throws SQLException
	 */
	public int getAllGoodsHavingCount() throws SQLException;

	/**
	 * 查看一项商品在库状态
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean getOneGoodsStatus(String id) throws SQLException;

	/**
	 * 模糊查询在库货物
	 * @param goodsName
	 * @return
	 * @throws SQLException
	 */
	public List<GoodsHaving> getAllGoodsHaving(String goodsName) throws SQLException;

}
