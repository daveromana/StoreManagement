package com.itcast.storemanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;

import com.itcast.storemanagement.po.Image;

public interface ImageDaoImpl {
	
	/**
	 * 上传一个图片
	 * @param Image
	 * @return
	 */
	public boolean ImageAdd(Image image);
	
	/**
	 * 删除一张图片
	 * @param id
	 * @return
	 */
	public boolean deleteOneImage(String id);
	
	/**
	 * 修改图片方法
	 * @param Image
	 * @return
	 */
	public boolean updateOneImage(Image image);
	
	/**
	 * 得到一项图片的信息，便于修改
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Image getOneImageById(String id) throws SQLException;

	
	
	/**
	 * 获得所有图片列表
	 * @return
	 * @throws SQLException 
	 */
	public List<Image> getAllImage() throws SQLException;
	/**
	 * 模糊查询图片名称
	 * @param ImageName
	 * @return
	 * @throws SQLException
	 */
	public List<Image> getAllImage(String ImageName) throws SQLException;
	
	/**
	 * 查询所有图片分页 pageSize
	 * @param current_page
	 * @return
	 * @throws SQLException
	 */
	public List<Image> getAllImage(int pageSize, int current_page) throws SQLException;
	
	/**
	 * 获取图片总记录数
	 * @return
	 * @throws SQLException
	 */
	public int getAllImageCount() throws SQLException;
	
	/**
	 * 得到一项货物的信息，便于修改
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public Image getOneImageByCondition(String name) throws SQLException;
	
	/**
	 * 得到一项货物的信息，便于修改
	 * @param name
	 * @param type
	 * @return
	 * @throws SQLException 
	 */
	public Image getOneImageByCondition(String name,String type) throws SQLException;
	/**
	 * 得到一项货物的信息，便于修改
	 * @param name
	 * @param type
	 * @param address
	 * @return
	 * @throws SQLException 
	 */
	public Image getOneImageByCondition(String name,String type,String address) throws SQLException;

}
