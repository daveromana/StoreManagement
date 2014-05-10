package com.itcast.storemanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.dao.MenuDao;
import com.itcast.storemanagement.po.Menu;
import com.itcast.storemanagement.serviceimpl.MenuServiceImpl;

public class MenuService implements MenuServiceImpl{

	MenuDao md = null;
	
	public MenuService() {
		md = new MenuDao();
	}

	@Override
	public boolean MenuAdd(Menu menu) {
		return md.MenuAdd(menu);
	}

	@Override
	public boolean deleteOneMenu(String id) {
		return md.deleteOneMenu(id);
	}

	@Override
	public boolean updateOneMenu(Menu menu) {
		return md.updateOneMenu(menu);
	}

	@Override
	public Menu getOneMenu(String id) throws SQLException {
		return md.getOneMenu(id);
	}

	@Override
	public List<Menu> getAllMenu() throws SQLException {
		return md.getAllMenu();
	}

	@Override
	public List<Menu> getAllMenu(int pageSize, int current_page)
			throws SQLException {
		return md.getAllMenu(pageSize, current_page);
	}

	@Override
	public int getAllMenuCount() throws SQLException {
		return md.getAllMenuCount();
	}

	@Override
	public List<Menu> getAllMenu(String menuName) throws SQLException {
		return md.getAllMenu(menuName);
	}

	

}
