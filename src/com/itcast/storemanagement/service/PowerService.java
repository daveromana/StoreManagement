package com.itcast.storemanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.dao.PowerDao;
import com.itcast.storemanagement.po.Power;
import com.itcast.storemanagement.serviceimpl.PowerServiceImpl;

public class PowerService implements PowerServiceImpl{
	
	PowerDao pd = null;
	
	public PowerService() {
		pd = new PowerDao();
	}

	@Override
	public boolean powerAdd(Power power) {
		return pd.powerAdd(power);
	}

	@Override
	public List<Power> getAllPower() throws SQLException {
		return pd.getAllPower();
	}

	@Override
	public List<Power> getAllPower(int pageSize, int current_page)
			throws SQLException {
		return pd. getAllPower(pageSize,current_page);
	}

	@Override
	public int getAllPowerCount() throws SQLException {
		return pd.getAllPowerCount();
	}

	@Override
	public Power getOnePower(String id) throws SQLException {
		return pd.getOnePower(id);
	}

	@Override
	public boolean updateOnePower(Power power) {
		return pd.updateOnePower(power);
	}

	@Override
	public boolean deleteOnePower(String id) {
		return pd.deleteOnePower(id);
	}

	@Override
	public List<Power> getAllPower(String powerName) throws SQLException {
		return pd.getAllPower(powerName);
	}

	

}
