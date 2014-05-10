package com.itcast.storemanagement.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.po.Account;
import com.itcast.storemanagement.vo.Home;

public interface AccountServiceImpl {
	
	/**
	 * 增加登陆账户
	 * @param account
	 * @return
	 */
	public boolean addAccount(Account account);
	/**
	 * 删除登陆账户
	 * @param id
	 * @return
	 */
	public boolean deleteAccpunt(String id);
	/**
	 * 更新登陆账户
	 * @param account
	 * @return
	 */
	public boolean updateAccount(Account account);
	/**
	 * 获得一个账户，便于修改
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Account getOneAccount(String id) throws SQLException;
	/**
	 * 获得全部登陆账号列表
	 * @return	全部登陆账号列表
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public List<Account> getAccountList() throws NumberFormatException, SQLException;
	/**
	 * 模糊查询登陆账号
	 * @param loginName
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public List<Account> getAccountList(String loginName) throws NumberFormatException,
			SQLException;
	/**
	 * 查询某一个登陆账号
	 * @param account
	 * @return	某一个登陆账号列表
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public List<Account> select(Account account) throws NumberFormatException, SQLException;
	/**
	 * 初始化首页信息
	 * @param account
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public List<Home> getHomeInfo(Account account) throws NumberFormatException, SQLException;
	/**
	 * 查询所有登陆账号分页
	 * @param pageSize
	 * @param current_page
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public List<Account> getAccountList(int pageSize, int current_page)
			throws NumberFormatException, SQLException;
	/**
	 * 查询账号总数
	 * @return
	 * @throws SQLException
	 */
	public int getAllAccountCount() throws SQLException;
}
