package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.service.PowerService;
import com.itcast.storemanagement.service.RoleService;
import com.itcast.storemanagement.vo.Role;

public class RoleAddServlet extends HttpServlet {

	RoleService rs = null;
	PowerService ps = null;

	public RoleAddServlet() {
		rs = new RoleService();
		ps = new PowerService();
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
			this.add(request, response);
		} else {
			try {
				List powerList = ps.getAllPower();
				request.getSession().setAttribute("powerList", powerList);
				response.sendRedirect("xtgl/jsgl/roleAdd.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] power = request.getParameterValues("rolepower");
		String roleName = request.getParameter("rolename");
		System.out.println(roleName);
		String roleComment = request.getParameter("rolecomment");
		Role role = new Role();
		role.setRoleName(roleName);
		role.setComment(roleComment);
		if (rs.roleAdd(role, power)) {
			try {
				List powerList = ps.getAllPower();
				request.getSession().setAttribute("powerList", powerList);
				response.sendRedirect("xtgl/jsgl/roleAdd.jsp?result=success");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("xtgl/jsgl/roleAdd.jsp?result=failed");
		}
	}

}
