package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Power;
import com.itcast.storemanagement.service.MenuService;
import com.itcast.storemanagement.service.PowerService;

public class PowerUpdateServlet extends HttpServlet {

	PowerService ps = null;
	MenuService ms = null;

	public PowerUpdateServlet() {
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
		String id = request.getParameter("id");
		Power p = null;
			if ("update".equals(methodType)) {
				this.update(request, response);
			} else {
				try {
					List menuList = ms.getAllMenu();
					p = ps.getOnePower(id);
					request.getSession().setAttribute("power", p);
					request.getSession().setAttribute("menuList", menuList);
					response.sendRedirect("xtgl/qxgl/powerUpdate.jsp");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} 

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		String powerName = request.getParameter("powername");
		String powerUri = request.getParameter("poweruri");
		String powerComment = request.getParameter("powercomment");
		String powerSuperMenu = request.getParameter("powersupermenu");
		Power power = new Power();
		power.setId(Integer.parseInt(id));
		power.setPowerName(powerName);
		power.setPowerComment(powerComment);
		power.setPowerSuperMenu(powerSuperMenu);
		power.setPowerUri(powerUri);
		if(ps.updateOnePower(power)){
			request.getSession().setAttribute("power", power);
			response.sendRedirect("xtgl/qxgl/powerUpdate.jsp?result=success");
		}else{
			response.sendRedirect("xtgl/qxgl/powerUpdate.jsp?result=failed");
		}
	}

}
