package com.itcast.storemanagement.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.po.Goods;

public interface GoodsServiceImpl {
	
	/**
	 * 增加一个商品
	 * @param goods
	 * @return
	 */
	public boolean GoodsAdd(Goods goods);
	
	/**
	 * 删除一个货物
	 * @param id
	 * @return
	 */
	public boolean deleteOneGoods(String id);
	
	/**
	 * 修改货物方法
	 * @param goods
	 * @return
	 */
	public boolean updateOneGoods(Goods goods);
	
	/**
	 * 得到一项货物的信息，便于修改
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Goods getOneGoodsById(String id) throws SQLException;

	
	
	/**
	 * 获得所有货物列表
	 * @return
	 * @throws SQLException 
	 */
	public List<Goods> getAllGoods() throws SQLException;
	/**
	 * 模糊查询货物名称
	 * @param goodsName
	 * @return
	 * @throws SQLException
	 */
	public List<Goods> getAllGoods(String goodsName) throws SQLException;
	
	/**
	 * 查询所有货物分页 pageSize
	 * @param current_page
	 * @return
	 * @throws SQLException
	 */
	public List<Goods> getAllGoods(int pageSize, int current_page) throws SQLException;
	
	/**
	 * 获取菜单总记录数
	 * @return
	 * @throws SQLException
	 */
	public int getAllGoodsCount() throws SQLException;
	
	/**
	 * 得到一项货物的信息，便于修改
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public Goods getOneGoodsByCondition(String name) throws SQLException;
	
	/**
	 * 得到一项货物的信息，便于修改
	 * @param name
	 * @param type
	 * @return
	 * @throws SQLException 
	 */
	public Goods getOneGoodsByCondition(String name,String type) throws SQLException;
	/**
	 * 得到一项货物的信息，便于修改
	 * @param name
	 * @param type
	 * @param address
	 * @return
	 * @throws SQLException 
	 */
	public Goods getOneGoodsByCondition(String name,String type,String address) throws SQLException;

}
