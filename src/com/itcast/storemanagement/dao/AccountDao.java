package com.itcast.storemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.storemanagement.daoimpl.AccountDaoImpl;
import com.itcast.storemanagement.po.Account;
import com.itcast.storemanagement.vo.Home;

public class AccountDao implements AccountDaoImpl {

	BaseDao bd = null;

	public AccountDao() {
		bd = new BaseDao();
	}

	@Override
	public boolean addAccount(Account account) {
		String sql = "insert into t_dc_login (login_loginName,login_loginPassword) values ('"
				+ account.getLoginName()
				+ "','"
				+ account.getLoginPassword()
				+ "')";
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean deleteAccpunt(String id) {
		String[] sqls = new String[2];
		sqls[0] = "delete from t_dc_user_login_mapping where login_id = " + id;
		sqls[1] = "delete from t_dc_login where login_id = " + id;
		return bd.dataUpdate(sqls);
	}

	@Override
	public boolean updateAccount(Account account) {
		String sqls[] = new String[3];
		sqls[0] = "update t_dc_login set login_loginName = '"
				+ account.getLoginName() + "',login_loginPassword = '"
				+ account.getLoginPassword() + "' where login_id = "
				+ account.getId();
		sqls[1] = "delete from t_dc_user_login_mapping where login_id = "
				+ account.getId();
		sqls[2] = "insert into t_dc_user_login_mapping values ((select user_id from t_dc_user where user_userName = '"
				+ account.getUserName() + "'), " + account.getId() + ")";
		return bd.dataUpdate(sqls);
	}

	@Override
	public List<Account> getAccountList() throws NumberFormatException,
			SQLException {
		String sql = "select l.login_id,l.login_loginName,l.login_loginPassword,u.user_userName from t_dc_login l left join t_dc_user_login_mapping ulm on l.login_id = ulm.login_id left join t_dc_user u on ulm.user_id = u.user_id";
		ResultSet rs = bd.executeQuery(sql);
		List<Account> accountList = new ArrayList<Account>();
		while (rs.next()) {
			accountList.add(new Account(rs.getInt(1), rs.getString(2), rs
					.getString(3), rs.getString(4)));
		}
		return accountList;
	}
	@Override
	public List<Account> getAccountList(String loginName) throws NumberFormatException,
	SQLException {
		String sql = "select l.login_id,l.login_loginName,l.login_loginPassword,u.user_userName from t_dc_login l left join t_dc_user_login_mapping ulm on l.login_id = ulm.login_id left join t_dc_user u on ulm.user_id = u.user_id where l.login_loginName like '%"+loginName+"%'";
		ResultSet rs = bd.executeQuery(sql);
		List<Account> accountList = new ArrayList<Account>();
		while (rs.next()) {
			accountList.add(new Account(rs.getInt(1), rs.getString(2), rs
					.getString(3), rs.getString(4)));
		}
		return accountList;
	}
	@Override
	public List<Account> getAccountList(int pageSize, int current_page) throws NumberFormatException,
	SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select l.login_id,l.login_loginName,l.login_loginPassword,u.user_userName from t_dc_login l "
				+"left join t_dc_user_login_mapping ulm on l.login_id = ulm.login_id "
				+"left join t_dc_user u on ulm.user_id = u.user_id "
				+"limit " + size + "," + pageSize;
		ResultSet rs = bd.executeQuery(sql);
		List<Account> accountList = new ArrayList<Account>();
		while (rs.next()) {
			accountList.add(new Account(rs.getInt(1), rs.getString(2), rs
					.getString(3), rs.getString(4)));
		}
		return accountList;
	}

	@Override
	public List<Account> select(Account account) throws NumberFormatException,
			SQLException {
		String loginName = account.getLoginName();
		String loginPassword = account.getLoginPassword();
		String sql = "select * from t_dc_login where 1=1 and login_loginName = '"
				+ loginName
				+ "' and login_LoginPassword = '"
				+ loginPassword
				+ "'";
		ResultSet rs = bd.executeQuery(sql);
		List<Account> accountList = new ArrayList<Account>();
		while (rs.next()) {
			Account a = new Account();
			a.setId(rs.getInt(1));
			a.setLoginName(rs.getString(2));
			a.setLoginPassword(rs.getString(3));
			accountList.add(a);
		}
		return accountList;
	}

	@Override
	public List<Home> getHomeInfo(Account account)
			throws NumberFormatException, SQLException {
		String sql = "select distinct "
				+ "u.user_userName, p.power_name, p.power_uri, m.powerColumn_name, m.powerColumn_supermenu, u.user_id "
				+ "from t_dc_login l "
				+ "inner join t_dc_user_login_mapping ulmap on l.login_id = ulmap.login_id "
				+ "inner join t_dc_user u on ulmap.user_id = u.user_id "
				+ "inner join t_dc_user_role_mapping urmap on u.user_id = urmap.user_id "
				+ "inner join t_dc_role r on urmap.role_id = r.role_id "
				+ "inner join t_dc_power_role_mapping prmap on r.role_id = prmap.role_id "
				+ "inner join t_dc_power p on prmap.power_id = p.power_id "
				+ "inner join t_dc_power_menu_mapping pmmap on p.power_id = pmmap.power_id "
				+ "inner join t_dc_menu m on pmmap.powerColumn_id = m.powerColumn_id "
				+ "where l.login_loginName = '" + account.getLoginName() + "'";
		ResultSet rs = bd.executeQuery(sql);
		List<Home> list = new ArrayList<Home>();
		while (rs.next()) {
			list.add(new Home(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
		}
		return list;
	}

	@Override
	public Account getOneAccount(String id) throws SQLException {
		String sql = "select l.login_id,l.login_loginName,l.login_loginPassword,u.user_userName from t_dc_login l left join t_dc_user_login_mapping ulm on l.login_id = ulm.login_id left join t_dc_user u on ulm.user_id = u.user_id where l.login_id = "
				+ id;
		ResultSet rs = bd.executeQuery(sql);
		Account account = new Account();
		while (rs.next()) {
			account.setId(rs.getInt(1));
			account.setLoginName(rs.getString(2));
			account.setLoginPassword(rs.getString(3));
			account.setUserName(rs.getString(4));
		}
		return account;
	}
	
	@Override
	public int getAllAccountCount() throws SQLException {
		String sql = "select count(l.login_id) from t_dc_login l left join t_dc_user_login_mapping ulm on l.login_id = ulm.login_id left join t_dc_user u on ulm.user_id = u.user_id ";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}
}
