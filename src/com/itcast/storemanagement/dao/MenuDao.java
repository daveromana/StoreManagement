package com.itcast.storemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.storemanagement.daoimpl.MenuDaoImpl;
import com.itcast.storemanagement.po.Menu;

public class MenuDao implements MenuDaoImpl {

	BaseDao bd = null;

	public MenuDao() {
		bd = new BaseDao();
	}

	@Override
	public boolean MenuAdd(Menu menu) {
		String sql = "insert into t_dc_menu (powerColumn_name,powerColumn_supermenu) values ('"
				+ menu.getPowerColumnName()
				+ "','"
				+ menu.getPowerColumnSuperMenu() + "')";
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean deleteOneMenu(String id) {
		String[] sqls = new String[2];
		sqls[0] = "delete from t_dc_power_menu_mapping where powerColumn_id = "
				+ id;
		sqls[1] = "delete from t_dc_menu where powerColumn_id = " + id;
		return bd.dataUpdate(sqls);
	}

	@Override
	public boolean updateOneMenu(Menu menu) {
		String sql = "update t_dc_menu set powerColumn_name = '"
				+ menu.getPowerColumnName() + "',powerColumn_supermenu = '"
				+ menu.getPowerColumnSuperMenu() + "' where powerColumn_id = "
				+ menu.getId();
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public Menu getOneMenu(String id) throws SQLException {
		String sql = "select * from t_dc_menu where powerColumn_id = " + id;
		ResultSet rs = bd.executeQuery(sql);
		Menu menu = null;
		while (rs.next()) {
			menu = new Menu(rs.getInt(1), rs.getString(2), rs.getString(3));
		}
		return menu;
	}

	@Override
	public List<Menu> getAllMenu() throws SQLException {
		String sql = "select * from t_dc_menu";
		ResultSet rs = bd.executeQuery(sql);
		List<Menu> menuList = new ArrayList<Menu>();
		while (rs.next()) {
			menuList.add(new Menu(rs.getInt(1), rs.getString(2), rs
					.getString(3)));
		}
		return menuList;
	}
	@Override
	public List<Menu> getAllMenu(String menuName) throws SQLException {
		String sql = "select * from t_dc_menu where powerColumn_name like '%"+menuName+"%'";
		ResultSet rs = bd.executeQuery(sql);
		List<Menu> menuList = new ArrayList<Menu>();
		while (rs.next()) {
			menuList.add(new Menu(rs.getInt(1), rs.getString(2), rs
					.getString(3)));
		}
		return menuList;
	}

	@Override
	public List<Menu> getAllMenu(int pageSize, int current_page)
			throws SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select * from t_dc_menu limit " + size + ","
				+ pageSize;
		ResultSet rs = bd.executeQuery(sql);
		List<Menu> menuList = new ArrayList<Menu>();
		while (rs.next()) {
			menuList.add(new Menu(rs.getInt(1), rs.getString(2), rs
					.getString(3)));
		}
		return menuList;
	}

	@Override
	public int getAllMenuCount() throws SQLException {
		String sql = "select count(powerColumn_id) from t_dc_menu";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}

}
