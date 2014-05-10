package com.itcast.storemanagement.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Menu;
import com.itcast.storemanagement.service.MenuService;

public class MenuAddServlet extends HttpServlet {

	MenuService ms = null;
	
	public MenuAddServlet() {
		ms = new MenuService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String methodType = request.getParameter("methodType");
		if ("add".equals(methodType)) {
			this.add(request,response);
		} else
			response.sendRedirect("xtgl/cdgl/menuAdd.jsp");
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String menuName = request.getParameter("menuname");
		String superMenu = request.getParameter("supermenu");
		Menu menu = new Menu();
		menu.setPowerColumnName(menuName);
		menu.setPowerColumnSuperMenu(superMenu);
		if(ms.MenuAdd(menu)){
			response.sendRedirect("xtgl/cdgl/menuAdd.jsp?result=success");
		}else{
			response.sendRedirect("xtgl/cdgl/menuAdd.jsp?result=failed");
		}
	}

}
