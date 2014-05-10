package com.itcast.storemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.storemanagement.daoimpl.PowerDaoImpl;
import com.itcast.storemanagement.po.Power;

public class PowerDao implements PowerDaoImpl {

	BaseDao bd = null;

	public PowerDao() {
		bd = new BaseDao();
	}

	@Override
	public boolean powerAdd(Power power) {
		String[] sqls = new String[2];
		sqls[0] = "insert into t_dc_power (power_name,power_comment,power_uri,power_supermenu) values ('"
				+ power.getPowerName()
				+ "','"
				+ power.getPowerComment()
				+ "','"
				+ power.getPowerUri()
				+ "','"
				+ power.getPowerSuperMenu() + "')";
		sqls[1] = "insert into t_dc_power_menu_mapping (powerColumn_id,power_id) values ("
				+ "(select powerColumn_id from t_dc_menu where powerColumn_name = '"
				+ power.getPowerSuperMenu()
				+ "'),"
				+ "(select power_id from t_dc_power where power_name = '"
				+ power.getPowerName() + "')" + ")";
		return bd.dataUpdate(sqls);
	}

	@Override
	public List<Power> getAllPower() throws SQLException {
		String sql = "select * from t_dc_power";
		ResultSet rs = bd.executeQuery(sql);
		List powerlist = new ArrayList<>();
		while (rs.next()) {
			powerlist.add(new Power(rs.getInt(1), rs.getString(2), rs
					.getString(3), rs.getString(4), rs.getString(5)));
		}
		return powerlist;
	}
	@Override
	public List<Power> getAllPower(String powerName) throws SQLException {
		String sql = "select * from t_dc_power where power_name like '%"+powerName+"%'";
		ResultSet rs = bd.executeQuery(sql);
		List powerlist = new ArrayList<>();
		while (rs.next()) {
			powerlist.add(new Power(rs.getInt(1), rs.getString(2), rs
					.getString(3), rs.getString(4), rs.getString(5)));
		}
		return powerlist;
	}

	@Override
	public List<Power> getAllPower(int pageSize, int current_page)
			throws SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select" + " * from t_dc_power limit " + size + ","
				+ pageSize;
		ResultSet rs = bd.executeQuery(sql);
		List powerlist = new ArrayList<>();
		while (rs.next()) {
			powerlist.add(new Power(rs.getInt(1), rs.getString(2), rs
					.getString(3), rs.getString(4), rs.getString(5)));
		}
		return powerlist;
	}

	@Override
	public int getAllPowerCount() throws SQLException {
		String sql = "select count(power_id) from t_dc_power";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}

	@Override
	public Power getOnePower(String id) throws SQLException {
		String sql = "select * from t_dc_power where power_id = " + id;
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		Power p = new Power(rs.getInt(1), rs.getString(2), rs.getString(3),
				rs.getString(4), rs.getString(5));
		return p;
	}

	@Override
	public boolean updateOnePower(Power power) {
		String[] sqls = new String[2];
		sqls[0] = "update t_dc_power_menu_mapping set powerColumn_id = (select powerColumn_id from t_dc_menu where powerColumn_name = '"
				+ power.getPowerSuperMenu()
				+ "') where power_id ="
				+ power.getId();
		sqls[1] = "update t_dc_power set power_name = '" + power.getPowerName()
				+ "',power_comment = '" + power.getPowerComment()
				+ "',power_uri = '" + power.getPowerUri()
				+ "',power_supermenu = '" + power.getPowerSuperMenu()
				+ "' where power_id = " + power.getId();
		return bd.dataUpdate(sqls);
	}

	@Override
	public boolean deleteOnePower(String id) {
		String[] sqls = new String[3];
		sqls[0] = "delete from t_dc_power_menu_mapping where power_id = "+id;
		sqls[1] = "delete from t_dc_power_role_mapping where power_id = "+id;
		sqls[2] = "delete from t_dc_power where power_id = "+id;
		return bd.dataUpdate(sqls);
	}

}
