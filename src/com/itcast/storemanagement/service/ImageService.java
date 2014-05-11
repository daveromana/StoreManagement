package com.itcast.storemanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.dao.ImageDao;
import com.itcast.storemanagement.po.Image;
import com.itcast.storemanagement.serviceimpl.ImageServiceImpl;

public class ImageService implements ImageServiceImpl {

	ImageDao id = null;
	
	public ImageService() {
		super();
		id = new ImageDao();
}

	@Override
	public boolean ImageAdd(Image image) {
		return id.ImageAdd(image);
	}

	@Override
	public boolean deleteOneImage(String id) {
		return this.id.deleteOneImage(id);
	}

	@Override
	public boolean updateOneImage(Image image) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Image getOneImageById(String id) throws SQLException {
		return this.id.getOneImageById(id);
	}

	@Override
	public List<Image> getAllImage() throws SQLException {
		return id.getAllImage();
	}

	@Override
	public List<Image> getAllImage(String ImageName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> getAllImage(int pageSize, int current_page)
			throws SQLException {
		return id.getAllImage(pageSize, current_page);
	}

	@Override
	public int getAllImageCount() throws SQLException {
		return id.getAllImageCount();
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
