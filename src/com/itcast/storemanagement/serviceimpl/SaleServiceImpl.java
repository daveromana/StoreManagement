package com.itcast.storemanagement.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.po.GoodsSaleOrder;

public interface SaleServiceImpl {

	/**
	 * 增加销售订单
	 * 
	 * @param goodsSaleOrder
	 * @return
	 * @throws SQLException 
	 */
	public boolean saleOrderAdd(GoodsSaleOrder goodsSaleOrder) throws SQLException;

	/**
	 * 删除一个订单
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteOneSaleOrder(String id);

	/**
	 * 修改订单方法
	 * 
	 * @param goodsSaleOrder
	 * @return
	 * @throws SQLException 
	 */
	public boolean updateOneSaleOrder(GoodsSaleOrder goodsSaleOrder) throws SQLException;

	/**
	 * 得到一项订单的信息，便于修改
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public GoodsSaleOrder getOneSaleOrder(String id) throws SQLException;

	/**
	 * 获得所有订单列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<GoodsSaleOrder> getAllSaleOrder() throws SQLException;
	/**
	 * 模糊查询销售单，逗号分隔
	 * @param goodsId
	 * @return
	 * @throws SQLException
	 */
	public List<GoodsSaleOrder> getAllSaleOrder(String goodsId) throws SQLException;

	/**
	 * 查询所有销售订单分页 pageSize
	 * 
	 * @param current_page
	 * @return
	 * @throws SQLException
	 */
	public List<GoodsSaleOrder> getAllSaleOrder(int pageSize, int current_page)
			throws SQLException;

	/**
	 * 获取销售单总记录数
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getAllSaleOrderCount() throws SQLException;

}
