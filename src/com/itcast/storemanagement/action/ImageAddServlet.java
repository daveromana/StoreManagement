package com.itcast.storemanagement.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.itcast.storemanagement.po.Image;
import com.itcast.storemanagement.service.ImageService;
import com.itcast.storemanagement.util.Now;

public class ImageAddServlet extends HttpServlet {

	ImageService is = null;
	
	public ImageAddServlet() {
		super();
		is = new ImageService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String methodType=request.getParameter("methodType");
		if("add".equals(methodType)){
			this.add(request,response);
		}else{
			response.sendRedirect("image/imageAdd.jsp");
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		DiskFileItemFactory dfi = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(dfi);
		String user = request.getParameter("user");
		try {
			List list = upload.parseRequest(request);
			for (int i = 0; i < list.size(); i++) {
				FileItem fileItem = (FileItem) list.get(i);
				if(fileItem.isFormField()){
					//是表单元素  code
				}else{
					String filepath = super.getServletContext().getRealPath("/")+File.separator+"upload"+File.separator;
					File file = new File(filepath + fileItem.getName());
					PrintWriter out = response.getWriter();
					fileItem.write(file);
					Image image = new Image();
					image.setImgUrl("upload/"+fileItem.getName());
					image.setUploadDate(Now.time());
					image.setImgUser(user);
					if(is.ImageAdd(image)){
						response.sendRedirect("image/imageAdd.jsp?result=success");
					}
					else{
						response.sendRedirect("image/imageAdd.jsp?result=failed");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
