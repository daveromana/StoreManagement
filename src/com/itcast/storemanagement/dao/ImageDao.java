package com.itcast.storemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.storemanagement.daoimpl.ImageDaoImpl;
import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.Image;
import com.itcast.storemanagement.util.Now;

public class ImageDao implements ImageDaoImpl {
	BaseDao bd = null;

	public ImageDao() {
		bd = new BaseDao();
	}

	@Override
	public boolean ImageAdd(Image image) {
		String sql = "insert into t_dc_image (image_url,image_date,image_user) values ('"
				+ image.getImgUrl()
				+ "','"
				+ image.getUploadDate()
				+ "','"
				+ image.getImgUser() + "')";
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean deleteOneImage(String id) {
		String sql = "delete from t_dc_image where image_id = " + id;
		return bd.dataUpdate(sql) > 0 ? true : false;
	}

	@Override
	public boolean updateOneImage(Image image) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Image getOneImageById(String id) throws SQLException {
		String sql = "select * from t_dc_image where image_id = "+id;
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		Image image = new Image(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		return image;
	}

	@Override
	public List<Image> getAllImage() throws SQLException {
		String sql = "select * from t_dc_image";
		ResultSet rs = bd.executeQuery(sql);
		List<Image> imageList = new ArrayList<>();
		while(rs.next()){
			imageList.add(new Image(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		return imageList;
	}

	@Override
	public List<Image> getAllImage(String ImageName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> getAllImage(int pageSize, int current_page)
			throws SQLException {
		int size = pageSize * (current_page - 1);
		String sql = "select * from t_dc_image limit " + size + ","
				+ pageSize;
		ResultSet rs = bd.executeQuery(sql);
		List<Image> imageList = new ArrayList<Image>();
		while(rs.next()){
			imageList.add(new Image(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		return imageList;
	}

	@Override
	public int getAllImageCount() throws SQLException {
		String sql = "select count(image_id) from t_dc_image";
		ResultSet rs = bd.executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}

	@Override
	public Image getOneImageByCondition(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getOneImageByCondition(String name, String type)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getOneImageByCondition(String name, String type, String address)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
