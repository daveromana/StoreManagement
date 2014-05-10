package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Menu;
import com.itcast.storemanagement.service.MenuService;

public class MenuUpdateServlet extends HttpServlet {

	MenuService ms = null;

	public MenuUpdateServlet() {
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
		String id = request.getParameter("id");
		try {
			if ("update".equals(methodType)) {
				this.update(request, response);
			} else {
				Menu menu = ms.getOneMenu(id);
				request.getSession().setAttribute("menu", menu);
				response.sendRedirect("xtgl/cdgl/menuUpdate.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String menuName = request.getParameter("menuname");
		String superMenu = request.getParameter("supermenu");
		Menu m = new Menu(Integer.parseInt(id), menuName, superMenu);
		if(ms.updateOneMenu(m)){
			try {
				Menu menu = ms.getOneMenu(id);
				request.getSession().setAttribute("menu", menu);
				response.sendRedirect("xtgl/cdgl/menuUpdate.jsp?result=success");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			response.sendRedirect("xtgl/cdgl/menuUpdate.jsp?result=failed");
		}
	}

}
