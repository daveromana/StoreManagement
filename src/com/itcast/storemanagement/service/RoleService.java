package com.itcast.storemanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.dao.RoleDao;
import com.itcast.storemanagement.serviceimpl.RoleServiceImpl;
import com.itcast.storemanagement.vo.Role;

public class RoleService implements RoleServiceImpl{

	RoleDao rd =null;
	
	public RoleService() {
		rd = new RoleDao();
	}

	@Override
	public boolean roleAdd(Role role, String[] power) {
		return rd.roleAdd(role, power);
	}

	@Override
	public boolean deleteOneRole(String id) {	
		return rd.deleteOneRole(id);
	}

	@Override
	public boolean updateOneRole(Role role, String[] power) {
		return rd.updateOneRole(role, power);
	}

	@Override
	public Role getOneRole(String id) throws SQLException {
		return rd.getOneRole(id);
	}

	@Override
	public List<Role> getAllRole() throws SQLException {
		return rd.getAllRole();
	}

	@Override
	public List<Role> getAllRole(int pageSize, int current_page)
			throws SQLException {
		return rd.getAllRole(pageSize, current_page);
	}

	@Override
	public int getAllRoleCount() throws SQLException {
		return rd.getAllRoleCount();
	}

	@Override
	public List<Role> getAllRole(String roleName) throws SQLException {
		return rd.getAllRole(roleName);
	}

}
