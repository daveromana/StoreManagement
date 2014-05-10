package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.service.RoleService;
import com.itcast.storemanagement.util.SystemFinalVar;
import com.itcast.storemanagement.vo.Role;

public class RoleDatagridServlet extends HttpServlet {

	RoleService rs = null;

	public RoleDatagridServlet() {
		rs = new RoleService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String methodType = request.getParameter("methodType");
		int current_page = 1;
		int maxCount = 1;
		try {
			if ("delete".equals(methodType)) {
				this.delete(request, response);
			} else if ("query".equals(methodType)) {
				this.query(request, response);
			} else {
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List roleList = rs.getAllRole(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("roleList", roleList);
				// 计算总页数，Session存储总页数
				maxCount = rs.getAllRoleCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				request.getSession().setAttribute("queryRoleName", "");
				response.sendRedirect("xtgl/jsgl/role.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int current_page = 1;
		String queryRoleName = request.getParameter("queryrolename");
		try {
			//如果不为空
			if (!"".equals(queryRoleName)) {
				List roleList = rs.getAllRole(queryRoleName);
				request.getSession().setAttribute("roleList", roleList);
				request.getSession().setAttribute("queryRoleName", queryRoleName);
				response.sendRedirect("xtgl/jsgl/role.jsp?current_page="
						+ current_page);
			} else {
				List roleList = rs.getAllRole(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("roleList", roleList);
				request.getSession().setAttribute("queryRoleName", "");
				response.sendRedirect("xtgl/jsgl/role.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String id = request.getParameter("id");
		if (rs.deleteOneRole(id)) {
			try {
				int current_page = 1;
				int maxCount = 1;
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List roleList = rs.getAllRole(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("powerlist", roleList);
				// 计算总页数，Session存储总页数
				maxCount = rs.getAllRoleCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				response.sendRedirect("xtgl/jsgl/role.jsp?result=success&current_page="
						+ current_page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("xtgl/jsgl/role.jsp?result=failed");
		}
	}

}
