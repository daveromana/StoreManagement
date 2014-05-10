package com.itcast.storemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.storemanagement.daoimpl.RoleDaoImpl;
import com.itcast.storemanagement.po.Power;
import com.itcast.storemanagement.vo.Role;

public class RoleDao implements RoleDaoImpl {

	BaseDao bd = null;

	public RoleDao() {
		bd = new BaseDao();
	}

	@Override
	public boolean roleAdd(Role role, String[] power) {
		String[] sqls = new String[power.length + 1];
		sqls[0] = "insert into t_dc_role (role_name,role_comment) values ('"
				+ role.getRoleName() + "','" + role.getComment() + "')";
		for (int i = 1; i < power.length + 1; i++) {
			sqls[i] = "insert into t_dc_power_role_mapping(role_id,power_id) values((select role_id from t_dc_role where role_name = '"
					+ role.getRoleName() + "')," + power[i - 1] + ")";
		}
		for (int i = 0; i < sqls.length; i++) {
			System.out.println(sqls[i]);
		}
		return bd.dataUpdate(sqls);
	}

	@Override
	public boolean deleteOneRole(String id) {
		String[] sqls = new String[3];
		sqls[0] = "delete from t_dc_power_role_mapping where role_id = " + id;
		sqls[1] = "delete from t_dc_user_role_mapping where role_id = " + id;
		sqls[2] = "delete from t_dc_role where role_id = " + id;
		return bd.dataUpdate(sqls);
	}

	@Override
	public boolean updateOneRole(Role role, String[] power) {
		String[] sqls = new String[power.length + 2];
		sqls[0] = "delete from t_dc_power_role_mapping where role_id = "+role.getId();
		for (int i = 1; i < power.length+1; i++) {
			sqls[i] = "insert into t_dc_power_role_mapping(role_id,power_id) values("+role.getId()+","+power[i-1]+")";
		}
		sqls[power.length+1] = "update t_dc_role set role_name = '"+role.getRoleName()+"',role_comment = '"+role.getComment()+"' where role_id = "+role.getId();
		for (int i = 0; i < sqls.length; i++) {
			System.out.println(sqls[i]);
		}
		return bd.dataUpdate(sqls);
	}

	@Override
	public Role getOneRole(String id) throws SQLException {
		String sql = "select r.role_id, r.role_name, r.role_comment, p.power_name "
				+ "from t_dc_role r "
				+ "inner join t_dc_power_role_mapping prm on r.role_id = prm.role_id "
				+ "inner join t_dc_power p on prm.power_id = p.power_id where r.role_id = "
				+ id;
		ResultSet rs = bd.executeQuery(sql);
		Role role = new Role();
		boolean flag = true;
		while (rs.next()) {
			if (flag) {
				flag = false;
				role.setId(rs.getInt(1));
				role.setRoleName(rs.getString(2));
				role.setComment(rs.getString(3));
			}
			role.getPower().add(rs.getString(4));
		}
		return role;
	}

	@Override
	public List<Role> getAllRole() throws SQLException {

		String sql = "select r.role_id, r.role_name, r.role_comment, p.power_name "
				+ "from t_dc_role r "
				+ "left join t_dc_power_role_mapping prm on r.role_id = prm.role_id "
				+ "left join t_dc_power p on prm.power_id = p.power_id order by r.role_id";
		ResultSet rs = bd.executeQuery(sql);
		List<Role> roleList = new ArrayList<Role>();
		Role r = new Role();
		while (rs.next()) {
			if (rs.getString("role_name").equals(r.getRoleName())) {
				roleList.get(roleList.size() - 1).getPower()
						.add(rs.getString("power_name"));
			} else {
				List list = new ArrayList<>();
				list.add(rs.getString(4));
				Role role = new Role(rs.getInt(1), rs.getString(2),
						rs.getString(3), list);
				roleList.add(role);
				r = role;
			}
		}
		return roleList;
	}
	@Override
	public List<Role> getAllRole(String roleName) throws SQLException {
		
		String sql = "select r.role_id, r.role_name, r.role_comment, p.power_name "
				+ "from t_dc_role r "
				+ "left join t_dc_power_role_mapping prm on r.role_id = prm.role_id "
				+ "left join t_dc_power p on prm.power_id = p.power_id where r.role_name like '%"+roleName+"%' order by r.role_id";
		ResultSet rs = bd.executeQuery(sql);
		List<Role> roleList = new ArrayList<Role>();
		Role r = new Role();
		while (rs.next()) {
			if (rs.getString("role_name").equals(r.getRoleName())) {
				roleList.get(roleList.size() - 1).getPower()
				.add(rs.getString("power_name"));
			} else {
				List list = new ArrayList<>();
				list.add(rs.getString(4));
				Role role = new Role(rs.getInt(1), rs.getString(2),
						rs.getString(3), list);
				roleList.add(role);
				r = role;
			}
		}
		return roleList;
	}

	@Override
	public List<Role> getAllRole(int pageSize, int current_page)
			throws SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select r.role_id, r.role_name, r.role_comment, p.power_name "
				+ "from t_dc_role r "
				+ "left join t_dc_power_role_mapping prm on r.role_id = prm.role_id "
				+ "left join t_dc_power p on prm.power_id = p.power_id "
				+ "inner join (select role_id from t_dc_role limit "+size+","+pageSize+") sl on r.role_id = sl.role_id "
				+ "order by r.role_id ";
		ResultSet rs = bd.executeQuery(sql);
		List<Role> roleList = new ArrayList<Role>();
		Role r = new Role();
		while (rs.next()) {
			if (rs.getString("role_name").equals(r.getRoleName())) {
				roleList.get(roleList.size() - 1).getPower()
						.add(rs.getString("power_name"));
			} else {
				List list = new ArrayList<>();
				list.add(rs.getString(4));
				Role role = new Role(rs.getInt(1), rs.getString(2),
						rs.getString(3), list);
				roleList.add(role);
				r = role;
			}
		}
		return roleList;
	}

	@Override
	public int getAllRoleCount() throws SQLException {
		String sql = "select count(role_id) "
				+ "from t_dc_role";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}

}
