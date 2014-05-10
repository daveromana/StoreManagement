package com.itcast.storemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.storemanagement.daoimpl.UserDaoImpl;
import com.itcast.storemanagement.vo.User;
import com.itcast.storemanagement.vo.UserInfo;

public class UserDao implements UserDaoImpl {

	BaseDao bd = null;

	public UserDao() {
		bd = new BaseDao();
	}

	@Override
	public boolean userAdd(User user, String[] role) {
		String[] sqls = new String[role.length + 1];
		sqls[0] = "insert into t_dc_user (user_userName,user_sex,user_phoneNUM,user_email,user_comment,user_job) values ('"
				+ user.getUserName()
				+ "','"
				+ user.getSex()
				+ "','"
				+ user.getPhoneNUM_()
				+ "','"
				+ user.getEmail()
				+ "','"
				+ user.getComment() + "','" + user.getJob() + "')";
		for (int i = 1; i < role.length + 1; i++) {
			sqls[i] = "insert into t_dc_user_role_mapping(user_id,role_id) values((select user_id from t_dc_user where user_userName = '"
					+ user.getUserName()
					+ "' and user_sex = '"
					+ user.getSex()
					+ "' and user_phoneNum = '"
					+ user.getPhoneNUM_()
					+ "'),"
					+ role[i - 1] + ")";
		}
		return bd.dataUpdate(sqls);
	}

	@Override
	public boolean deleteOneUser(String id) {
		String[] sqls = new String[3];
		sqls[0] = "delete from t_dc_user_role_mapping where user_id = " + id;
		sqls[1] = "delete from t_dc_user_login_mapping where user_id = " + id;
		sqls[2] = "delete from t_dc_user where user_id = " + id;
		return bd.dataUpdate(sqls);
	}

	@Override
	public boolean updateOneUser(User user, String[] role) {
		String[] sqls = new String[role.length + 2];
		sqls[0] = "delete from t_dc_user_role_mapping where user_id = "
				+ user.getId();
		for (int i = 1; i < role.length + 1; i++) {
			sqls[i] = "insert into t_dc_user_role_mapping(user_id,role_id) values("
					+ user.getId() + "," + role[i - 1] + ")";
		}
		sqls[role.length + 1] = "update t_dc_user set user_userName = '"
				+ user.getUserName() + "',user_sex = '" + user.getSex()
				+ "',user_phoneNUM = " + user.getPhoneNUM_()
				+ ",user_email = '" + user.getEmail() + "',user_comment = '"
				+ user.getComment() + "',user_job = '" + user.getJob()
				+ "' where user_id = " + user.getId();
		return bd.dataUpdate(sqls);
	}

	@Override
	public User getOneUser(String id) throws SQLException {
		String sql = "select u.user_id, u.user_userName, u.user_sex, u.user_phoneNum ,u.user_email,u.user_comment,u.user_job,r.role_name "
				+ "from t_dc_user u "
				+ "inner join t_dc_user_role_mapping urm on u.user_id = urm.user_id "
				+ "inner join t_dc_role r on urm.role_id = r.role_id "
				+ "where u.user_id =" + id;
		ResultSet rs = bd.executeQuery(sql);
		User user = new User();
		boolean flag = true;
		while (rs.next()) {
			if (flag) {
				flag = false;
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setSex(rs.getString(3));
				user.setPhoneNUM_(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setComment(rs.getString(6));
				user.setJob(rs.getString(7));
			}
			user.getRole().add(rs.getString(8));
		}
		return user;
	}

	@Override
	public List<User> getAllUser() throws SQLException {
		String sql = "select u.user_id, u.user_userName, u.user_sex, u.user_phoneNum ,u.user_email,u.user_comment,u.user_job,r.role_name "
				+ "from t_dc_user u "
				+ "inner join t_dc_user_role_mapping urm on u.user_id = urm.user_id "
				+ "inner join t_dc_role r on urm.role_id = r.role_id "
				+ "order by u.user_id ";
		ResultSet rs = bd.executeQuery(sql);
		List<User> userList = new ArrayList<User>();
		User u = new User();
		while (rs.next()) {
			if (rs.getString("user_userName").equals(u.getUserName())
					&& rs.getString("user_sex").equals(u.getSex())
					&& rs.getString("user_phoneNum").equals(u.getPhoneNUM_())
					&& rs.getString("user_job").equals(u.getJob())) {
				userList.get(userList.size() - 1).getRole()
						.add(rs.getString("role_name"));
			} else {
				List list = new ArrayList<>();
				list.add(rs.getString(8));
				User user = new User(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), list);
				userList.add(user);
				u = user;
			}
		}
		return userList;
	}

	@Override
	public List<User> getAllUser(int pageSize, int current_page)
			throws SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select u.user_id, u.user_userName, u.user_sex, u.user_phoneNum ,u.user_email,u.user_comment,u.user_job,r.role_name "
				+ "from t_dc_user u "
				+ "left join t_dc_user_role_mapping urm on u.user_id = urm.user_id "
				+ "left join t_dc_role r on urm.role_id = r.role_id "
				+ "inner join (select user_id from t_dc_user limit "+size+","+pageSize+") sl on u.user_id = sl.user_id "
				+ "order by u.user_id";
		ResultSet rs = bd.executeQuery(sql);
		List<User> userList = new ArrayList<User>();
		User u = new User();
		while (rs.next()) {
			if (rs.getString("user_userName").equals(u.getUserName())
					&& rs.getString("user_sex").equals(u.getSex())
					&& rs.getString("user_phoneNum").equals(u.getPhoneNUM_())
					&& rs.getString("user_job").equals(u.getJob())) {
				userList.get(userList.size() - 1).getRole()
						.add(rs.getString("role_name"));
			} else {
				List list = new ArrayList<>();
				list.add(rs.getString(8));
				User user = new User(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), list);
				userList.add(user);
				u = user;
			}
		}
		return userList;
	}

	@Override
	public int getAllUserCount() throws SQLException {
		String sql = "select count(user_id) "
				+ "from t_dc_user u ";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}

	@Override
	public List<User> getAllCustomer() throws SQLException {
		String sql = "select u.user_id, u.user_userName, u.user_sex, u.user_phoneNum ,u.user_email,u.user_comment,u.user_job,r.role_name "
				+ "from t_dc_user u "
				+ "inner join t_dc_user_role_mapping urm on u.user_id = urm.user_id "
				+ "inner join t_dc_role r on urm.role_id = r.role_id "
				+ "where r.role_name = '客户' order by u.user_id ";
		ResultSet rs = bd.executeQuery(sql);
		List<User> userList = new ArrayList<User>();
		User u = new User();
		while (rs.next()) {
			if (rs.getString("user_userName").equals(u.getUserName())
					&& rs.getString("user_sex").equals(u.getSex())
					&& rs.getString("user_phoneNum").equals(u.getPhoneNUM_())
					&& rs.getString("user_job").equals(u.getJob())) {
				userList.get(userList.size() - 1).getRole()
						.add(rs.getString("role_name"));
			} else {
				List list = new ArrayList<>();
				list.add(rs.getString(8));
				User user = new User(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), list);
				userList.add(user);
				u = user;
			}
		}
		return userList;
	}
	@Override
	public List<User> getAllCustomer(int pageSize, int current_page) throws SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select u.user_id, u.user_userName, u.user_sex, u.user_phoneNum ,u.user_email,u.user_comment,u.user_job,r.role_name "
				+ "from t_dc_user u "
				+ "left join t_dc_user_role_mapping urm on u.user_id = urm.user_id "
				+ "left join t_dc_role r on urm.role_id = r.role_id "
				+ "inner join (select user_id from t_dc_user limit "+size+","+pageSize+") sl on u.user_id = sl.user_id "
				+ "where r.role_name = '客户' order by u.user_id";
		ResultSet rs = bd.executeQuery(sql);
		List<User> userList = new ArrayList<User>();
		User u = new User();
		while (rs.next()) {
			if (rs.getString("user_userName").equals(u.getUserName())
					&& rs.getString("user_sex").equals(u.getSex())
					&& rs.getString("user_phoneNum").equals(u.getPhoneNUM_())
					&& rs.getString("user_job").equals(u.getJob())) {
				userList.get(userList.size() - 1).getRole()
				.add(rs.getString("role_name"));
			} else {
				List list = new ArrayList<>();
				list.add(rs.getString(8));
				User user = new User(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), list);
				userList.add(user);
				u = user;
			}
		}
		return userList;
	}
	@Override
	public int getAllCustomerCount() throws SQLException {
		String sql = "select count(u.user_id) "
				+ "from t_dc_user u "
				+ "where u.user_job = '客户' order by u.user_id";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}

	@Override
	public User getOneUserByName(String userName) {
		String sql = "select * from t_dc_user where user_userName = "
				+ userName;
		return null;
	}

	@Override
	public UserInfo getUserInfo(String id) throws SQLException {
		String sql = "select u.user_id,u.user_userName,u.user_sex,u.user_phoneNum,u.user_email,l.login_id,l.login_loginName,l.login_loginPassword from t_dc_user u "
				+ "inner join t_dc_user_login_mapping ulm on u.user_id = ulm.user_id "
				+ "inner join t_dc_login l on ulm.login_id = l.login_id where u.user_id = "
				+ id;
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return new UserInfo(rs.getString(1), rs.getString(2), rs.getString(3),
				rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7),
				rs.getString(8));
	}

	@Override
	public boolean updateUserInfo(UserInfo userInfo) {
		String sqls[] = new String[2];
		sqls[0] = "update t_dc_user set user_userName = '"
				+userInfo.getUserName()+"', user_sex = '"
				+userInfo.getUserSex()+"', user_email = '"
				+userInfo.getUserEmail()+"', user_phoneNum = '"
				+userInfo.getPhoneNum()+"' where user_id = "+userInfo.getId();
		sqls[1] = "update t_dc_login set login_loginName = '"
				+userInfo.getLoginName()+"', login_loginPassword = '"
				+userInfo.getLoginPassword()+"' where login_id = "+userInfo.getLoginId();
		return bd.dataUpdate(sqls);
	}

	@Override
	public List<User> getAllUser(String userName) throws SQLException {
		String sql = "select u.user_id, u.user_userName, u.user_sex, u.user_phoneNum ,u.user_email,u.user_comment,u.user_job,r.role_name "
				+ "from t_dc_user u "
				+ "inner join t_dc_user_role_mapping urm on u.user_id = urm.user_id "
				+ "inner join t_dc_role r on urm.role_id = r.role_id where u.user_userName like '%"+userName+"%' "
				+ "order by u.user_id ";
		ResultSet rs = bd.executeQuery(sql);
		List<User> userList = new ArrayList<User>();
		User u = new User();
		while (rs.next()) {
			if (rs.getString("user_userName").equals(u.getUserName())
					&& rs.getString("user_sex").equals(u.getSex())
					&& rs.getString("user_phoneNum").equals(u.getPhoneNUM_())
					&& rs.getString("user_job").equals(u.getJob())) {
				userList.get(userList.size() - 1).getRole()
						.add(rs.getString("role_name"));
			} else {
				List list = new ArrayList<>();
				list.add(rs.getString(8));
				User user = new User(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), list);
				userList.add(user);
				u = user;
			}
		}
		return userList;
	}
	@Override
	public List getAllCustomer(String userName) throws SQLException {
		String sql = "select u.user_id, u.user_userName, u.user_sex, u.user_phoneNum ,u.user_email,u.user_comment,u.user_job,r.role_name "
				+ "from t_dc_user u "
				+ "inner join t_dc_user_role_mapping urm on u.user_id = urm.user_id "
				+ "inner join t_dc_role r on urm.role_id = r.role_id where r.role_name = '客户' and u.user_userName like '%"+userName+"%' "
				+ "order by u.user_id ";
		ResultSet rs = bd.executeQuery(sql);
		List<User> userList = new ArrayList<User>();
		User u = new User();
		while (rs.next()) {
			if (rs.getString("user_userName").equals(u.getUserName())
					&& rs.getString("user_sex").equals(u.getSex())
					&& rs.getString("user_phoneNum").equals(u.getPhoneNUM_())
					&& rs.getString("user_job").equals(u.getJob())) {
				userList.get(userList.size() - 1).getRole()
						.add(rs.getString("role_name"));
			} else {
				List list = new ArrayList<>();
				list.add(rs.getString(8));
				User user = new User(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), list);
				userList.add(user);
				u = user;
			}
		}
		return userList;
	}

}
