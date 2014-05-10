package com.itcast.storemanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.vo.Role;

public interface RoleDaoImpl {

	/**
	 * 增加一个角色
	 * @param power
	 * @return
	 */
	public boolean roleAdd(Role role, String[] power);
	
	/**
	 * 删除一个角色
	 * @param id
	 * @return
	 */
	public boolean deleteOneRole(String id);
	
	/**
	 * 修改角色方法
	 * @param power
	 * @return
	 */
	public boolean updateOneRole(Role role, String[] power);
	
	/**
	 * 得到一项角色的信息，便于修改
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Role getOneRole(String id) throws SQLException;
	
	
	/**
	 * 获得所有角色列表
	 * @return
	 * @throws SQLException 
	 */
	public List<Role> getAllRole() throws SQLException;
	
	/**
	 * 查询所有角色分页 pageSize
	 * @param current_page
	 * @return
	 * @throws SQLException
	 */
	public List<Role> getAllRole(int pageSize, int current_page) throws SQLException;
	
	/**
	 * 获取角色总记录数
	 * @return
	 * @throws SQLException
	 */
	public int getAllRoleCount() throws SQLException;

	/**
	 * 模糊查询角色
	 * @param roleName
	 * @return
	 * @throws SQLException
	 */
	public List<Role> getAllRole(String roleName) throws SQLException;
}
