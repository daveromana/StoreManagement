package com.itcast.storemanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.po.Menu;

public interface MenuDaoImpl {
	
	/**
	 * 增加一个菜单
	 * @param power
	 * @return
	 */
	public boolean MenuAdd(Menu menu);
	
	/**
	 * 删除一个菜单
	 * @param id
	 * @return
	 */
	public boolean deleteOneMenu(String id);
	
	/**
	 * 修改菜单方法
	 * @param power
	 * @return
	 */
	public boolean updateOneMenu(Menu menu);
	
	/**
	 * 得到一项菜单的信息，便于修改
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Menu getOneMenu(String id) throws SQLException;
	
	
	/**
	 * 获得所有菜单列表
	 * @return
	 * @throws SQLException 
	 */
	public List<Menu> getAllMenu() throws SQLException;
	
	/**
	 * 查询所有角色分页 pageSize
	 * @param current_page
	 * @return
	 * @throws SQLException
	 */
	public List<Menu> getAllMenu(int pageSize, int current_page) throws SQLException;
	
	/**
	 * 获取菜单总记录数
	 * @return
	 * @throws SQLException
	 */
	public int getAllMenuCount() throws SQLException;

	/**
	 * 模糊查询菜单
	 * @param menuName
	 * @return
	 * @throws SQLException
	 */
	public List<Menu> getAllMenu(String menuName) throws SQLException;

}
