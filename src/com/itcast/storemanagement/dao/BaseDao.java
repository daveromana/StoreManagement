package com.itcast.storemanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * jdbcTemplate  ������ݿ��ģ����
 * @author Administrator
 *
 */
public class BaseDao {

	Connection  conn;
	Statement  st;
	ResultSet  rs;
	
	PreparedStatement  ps;
	
	static String  driver = "com.mysql.jdbc.Driver";
	String  url = "jdbc:mysql://localhost:3306/store?useUnicode=true&characterEncoding=utf-8";
	String  username = "root";
	String  userps = "admin";
	
	/**
	 * 加载驱动
	 */
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("加载驱动失败");
		}
	}
	
	
	/**
	 * ͨ打开数据库连接
	 */
	public  Connection  getConnection(){
		try {
		   conn  = 	DriverManager.getConnection(url,username,userps);
		   conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获得连接失败");
		}
		return conn;
	}
	/**
	 *创建statement通道
	 * @return
	 */
	private    void  createStatment(){
		 this.getConnection();
		 try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建statement通道失败");
		}
	}
	/**
	 * 执行多条sql语句
	 * @param sqls
	 * @return
	 */
	public  boolean  dataUpdate(String sqls[]){
		this.createStatment();
		boolean isok = false;
			try {
				for (int i = 0; i < sqls.length; i++) {
					st.addBatch(sqls[i]);
				}
			    st.executeBatch();
			    this.myCommit();
			    isok = true;
			} catch (SQLException e) {
				e.printStackTrace();
				this.myRollback();
			}
		return isok;
	}
	public void  myCommit(){
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public  void  myRollback(){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ͨ通用查询方法
	 * @param  sql 
	 * @return
	 */
	public  ResultSet   executeQuery(String sql){
		 this.createStatment();
		 try {
			rs =  st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("通用查询失败");
		}
		return rs;
	}
	/**
	 * 通用增删改方法
	 * @param sql
	 * @return
	 */
	public  int  dataUpdate(String sql){
		this.createStatment();
		int i = 0;
		try {
			i= st.executeUpdate(sql);
			this.myCommit();
		} catch (SQLException e) {
			this.myRollback();
			e.printStackTrace();
			System.out.println("通用增删改失败");
		}
		return i;
	}
	/**
	 * 创建Pstatement通道
	 * @param sql
	 */
	private  void  createPstatement(String sql){
		 this.getConnection();
		 try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建Pstatement通道失败");
		}
	}
	/**
	 * 通用查询方法
	 * @param sql  
	 * @param String  params  []  
	 * @return 
	 */
	public  ResultSet  executeQuery(String sql,String param[]){
		this.createPstatement(sql);
		try {
			if(param!= null && param.length>0){
				for (int i = 0; i < param.length; i++) {
					ps.setString(i+1,param[i]);
				}
			}
		
			rs = ps.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("通用查询方法失败");
		}
		return rs;
	}
	/**
	 * 通用增删改方法
	 * @param sql  insert   update   delete  ������
	 * @param params  String  []  ???
	 * @return
	 */
	public  int  dataUpdate(String sql,String params[]){
		this.createPstatement(sql);
		int result = 0;
		try {
			if(params != null && params.length>0){
				for (int i = 0; i < params.length; i++) {
					ps.setString(i+1, params[i]);
				}
			}
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			 System.out.println("通用增删改方法失败");
		}
		return result;
	}
	
	/**
	 * 关闭连接
	 */
	public   void  closeAll(){
		 try {
	    	 // 从尾到头
	    	if(rs!= null){
	    		rs.close();
	    	}
	    	if(st!= null){
	    		st.close();
	    	}
	    	if(ps != null){
	    		ps.close();
	    	}
	    	if(conn != null){
	    		conn.close();
	    	}
	    	
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
}
