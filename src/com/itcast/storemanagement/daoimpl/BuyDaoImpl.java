package com.itcast.storemanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.po.GoodsBuyOrder;

public interface BuyDaoImpl {
	
	/**
	 * 增加采购订单
	 * @param goodsBuyOrder
	 * @return
	 * @throws SQLException 
	 */
	public boolean buyOrderAdd(GoodsBuyOrder goodsBuyOrder) throws SQLException;
	
	/**
	 * 删除一个订单
	 * @param id
	 * @return
	 */
	public boolean deleteOneBuyOrder(String id);
	
	/**
	 * 修改订单方法
	 * @param goodsBuyOrder
	 * @return
	 * @throws SQLException 
	 */
	public boolean updateOneBuyOrder(GoodsBuyOrder goodsBuyOrder) throws SQLException;
	
	/**
	 * 得到一项订单的信息，便于修改
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public GoodsBuyOrder getOneBuyOrder(String id) throws SQLException;
	
	
	/**
	 * 获得所有订单列表
	 * @return
	 * @throws SQLException 
	 */
	public List<GoodsBuyOrder> getAllBuyOrder() throws SQLException;
	
	/**
	 * 查询所有采购订单分页 pageSize
	 * @param current_page
	 * @return
	 * @throws SQLException
	 */
	public List<GoodsBuyOrder> getAllBuyOrder(int pageSize, int current_page) throws SQLException;
	
	/**
	 * 获取采购单总记录数
	 * @return
	 * @throws SQLException
	 */
	public int getAllBuyOrderCount() throws SQLException;
	
	/**
	 * 模糊查询采购订单 By 商品Id
	 * @param goodsId
	 * @return
	 * @throws SQLException
	 */
	public List<GoodsBuyOrder> getAllBuyOrder(String goodsId) throws SQLException;
	

}
