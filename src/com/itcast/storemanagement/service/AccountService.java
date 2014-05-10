package com.itcast.storemanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.dao.AccountDao;
import com.itcast.storemanagement.po.Account;
import com.itcast.storemanagement.serviceimpl.AccountServiceImpl;
import com.itcast.storemanagement.vo.Home;

public class AccountService implements AccountServiceImpl{
	
	AccountDao ad = null;

	public AccountService() {
		ad = new AccountDao();
	}

	@Override
	public boolean addAccount(Account account) {
		return ad.addAccount(account);
	}

	@Override
	public boolean deleteAccpunt(String id) {
		return ad.deleteAccpunt(id);
	}

	@Override
	public boolean updateAccount(Account account) {
		return ad.updateAccount(account);
	}

	@Override
	public List<Account> getAccountList() throws NumberFormatException,
			SQLException {
		return ad.getAccountList();
	}

	@Override
	public List<Account> select(Account account) throws NumberFormatException,
			SQLException {
		return ad.select(account);
	}

	@Override
	public List<Home> getHomeInfo(Account account)
			throws NumberFormatException, SQLException {
		return ad.getHomeInfo(account);
	}

	@Override
	public Account getOneAccount(String id) throws SQLException {
		return ad.getOneAccount(id);
	}

	@Override
	public List<Account> getAccountList(int pageSize, int current_page)
			throws NumberFormatException, SQLException {
		return ad.getAccountList(pageSize, current_page);
	}

	@Override
	public int getAllAccountCount() throws SQLException {
		return ad.getAllAccountCount();
	}

	@Override
	public List<Account> getAccountList(String loginName)
			throws NumberFormatException, SQLException {
		return ad.getAccountList(loginName);
	}

}
