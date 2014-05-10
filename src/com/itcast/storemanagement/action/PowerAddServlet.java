package com.itcast.storemanagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Power;
import com.itcast.storemanagement.service.MenuService;
import com.itcast.storemanagement.service.PowerService;

public class PowerAddServlet extends HttpServlet {

	PowerService ps = null;
	MenuService ms = null;

	public PowerAddServlet() {
		ps = new PowerService();
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
			this.powerAdd(request, response);
		} else {
			try {
				List menuList = ms.getAllMenu();
				request.getSession().setAttribute("menuList", menuList);
				response.sendRedirect("xtgl/qxgl/powerAdd.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void powerAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String powerName = request.getParameter("powername");
		String powerUri = request.getParameter("poweruri");
		String powerSuperMenu = request.getParameter("powersupermenu");
		String powerComment = request.getParameter("powercomment");
		Power power = new Power();
		power.setPowerName(powerName);
		power.setPowerUri(powerUri);
		power.setPowerSuperMenu(powerSuperMenu);
		power.setPowerComment(powerComment);
		if (ps.powerAdd(power)) {
			response.sendRedirect("xtgl/qxgl/powerAdd.jsp?result=success");
		} else {
			response.sendRedirect("xtgl/qxgl/powerAdd.jsp?result=failed");
		}
	}

}
