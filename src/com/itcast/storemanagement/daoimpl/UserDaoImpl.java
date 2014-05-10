package com.itcast.storemanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.vo.User;
import com.itcast.storemanagement.vo.UserInfo;

public interface UserDaoImpl {
	
	/**
	 * 增加一个用户
	 * @param power
	 * @return
	 */
	public boolean userAdd(User user,String[] power);
	
	/**
	 * 删除一个用户
	 * @param id
	 * @return
	 */
	public boolean deleteOneUser(String id);
	
	/**
	 * 修改用户方法
	 * @param power
	 * @return
	 */
	public boolean updateOneUser(User user, String[] role);
	
	/**
	 * 得到一项用户的信息，便于修改
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public User getOneUser(String id) throws SQLException;
	/**
	 * 得到一项用户的信息，便于修改
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public User getOneUserByName(String userName) throws SQLException;
	
	/**
	 * 获得所有用户列表
	 * @return
	 * @throws SQLException 
	 */
	public List<User> getAllUser() throws SQLException;
	
	/**
	 * 查询所有用户分页 pageSize
	 * @param current_page
	 * @return
	 * @throws SQLException
	 */
	public List<User> getAllUser(int pageSize, int current_page) throws SQLException;
	
	/**
	 * 获取角色总记录数
	 * @return
	 * @throws SQLException
	 */
	public int getAllUserCount() throws SQLException;
	
	/**
	 * 获得所有客户列表
	 * @return
	 * @throws SQLException
	 */
	public List<User> getAllCustomer() throws SQLException;

	/**
	 * 获得用户一般信息
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public UserInfo getUserInfo(String id) throws SQLException;

	/**
	 * 修改用户一般信息
	 * @param userInfo
	 * @return
	 */
	public boolean updateUserInfo(UserInfo userInfo);
	
	/**
	 * 获得所有客户列表分页
	 * @param pageSize
	 * @param current_page
	 * @return
	 * @throws SQLException
	 */
	public List<User> getAllCustomer(int pageSize, int current_page)
			throws SQLException;

	/**
	 * 获得所有客户数
	 * @return
	 * @throws SQLException
	 */
	public int getAllCustomerCount() throws SQLException;
	/**
	 * 模糊查询用户姓名
	 * @param userName
	 * @return
	 * @throws SQLException 
	 */
	public List<User> getAllUser(String userName) throws SQLException;
	/**
	 * 模糊查询用户姓名
	 * @param userName
	 * @return
	 * @throws SQLException 
	 */
	public List getAllCustomer(String userName) throws SQLException;

}
