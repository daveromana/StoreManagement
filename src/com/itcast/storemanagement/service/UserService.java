package com.itcast.storemanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.dao.UserDao;
import com.itcast.storemanagement.serviceimpl.UserServiceImpl;
import com.itcast.storemanagement.vo.Role;
import com.itcast.storemanagement.vo.User;
import com.itcast.storemanagement.vo.UserInfo;

public class UserService implements UserServiceImpl{

	UserDao ud = null;
	
	public UserService() {
		ud = new UserDao();
	}

	@Override
	public boolean userAdd(User user, String[] role) {
		return ud.userAdd(user, role);
	}

	@Override
	public boolean deleteOneUser(String id) {
		return ud.deleteOneUser(id);
	}

	@Override
	public boolean updateOneUser(User user, String[] role) {
		return ud.updateOneUser(user, role);
	}

	@Override
	public User getOneUser(String id) throws SQLException {
		return ud.getOneUser(id);
	}

	@Override
	public List<User> getAllUser() throws SQLException {
		return ud.getAllUser();
	}

	@Override
	public List<User> getAllUser(int pageSize, int current_page)
			throws SQLException {
		return ud.getAllUser(pageSize, current_page);
	}

	@Override
	public int getAllUserCount() throws SQLException {
		return ud.getAllUserCount();
	}

	@Override
	public List<User> getAllCustomer() throws SQLException {
		return ud.getAllCustomer();
	}

	@Override
	public User getOneUserByName(String userName) throws SQLException {
		return ud.getOneUserByName(userName);
	}
	@Override
	public UserInfo getUserInfo(String id) throws SQLException {
		return ud.getUserInfo(id);
	}
	@Override
	public boolean updateUserInfo(UserInfo userInfo) {
		return ud.updateUserInfo(userInfo);
	}

	@Override
	public List<User> getAllCustomer(int pageSize, int current_page)
			throws SQLException {
		return ud.getAllCustomer(pageSize, current_page);
	}

	@Override
	public int getAllCustomerCount() throws SQLException {
		return ud.getAllCustomerCount();
	}
	
	@Override
	public List<User> getAllUser(String userName) throws SQLException {
		return ud.getAllUser(userName);
	}
	@Override
	public List getAllCustomer(String userName) throws SQLException {
		// TODO Auto-generated method stub
		return ud.getAllCustomer(userName);
	}

}
