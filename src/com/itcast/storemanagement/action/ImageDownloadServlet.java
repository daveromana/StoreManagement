package com.itcast.storemanagement.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Image;
import com.itcast.storemanagement.service.ImageService;

public class ImageDownloadServlet extends HttpServlet {

	ImageService is = null;
	public ImageDownloadServlet() {
		is = new ImageService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			Image image = is.getOneImageById(id);
			response.setContentType("application/octet-stream");
			String filePath = super.getServletContext().getRealPath("/")
					+ "upload" + File.separator;
			String fileName = image.getImgUrl().substring(7, image.getImgUrl().length());
			File file = new File(filePath, fileName);
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			InputStream io = new FileInputStream(file);
			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[2048];
			while (io.read(buffer) > 0) {
				outputStream.write(buffer);
			}
			outputStream.flush();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
