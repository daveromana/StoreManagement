package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.service.UserService;
import com.itcast.storemanagement.util.SystemFinalVar;
import com.itcast.storemanagement.vo.User;

public class CustomerDatagridServlet extends HttpServlet {

	UserService us = null;

	public CustomerDatagridServlet() {
		us = new UserService();
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
		if ("delete".equals(methodType)) {
			this.delete(request, response);
		} else if ("query".equals(methodType)) {
			this.query(request, response);
		} else {
			try {
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List userList = us.getAllCustomer(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("userList", userList);
				// 计算总页数，Session存储总页数
				maxCount = us.getAllCustomerCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				request.getSession().setAttribute("maxCount", maxCount);
				request.getSession().setAttribute("queryUserName", "");
				// 跳转页面
				response.sendRedirect("khgl/customer.jsp?current_page="
						+ current_page);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int current_page = 1;
		String userName = request.getParameter("queryusername");
		try {
			//如果不为空
			if (!"".equals(userName)) {
				List userList = us.getAllCustomer(userName);
				request.getSession().setAttribute("userList", userList);
				request.getSession().setAttribute("queryUserName", userName);
				response.sendRedirect("khgl/customer.jsp?current_page="
						+ current_page);
			} else {
				List userList = us.getAllCustomer(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("userList", userList);
				request.getSession().setAttribute("queryUserName", "");
				response.sendRedirect("khgl/customer.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		if (us.deleteOneUser(id)) {
			try {
				int current_page = 1;
				int maxCount = 1;
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List userList = us.getAllCustomer(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("userList", userList);
				// 计算总页数，Session存储总页数
				maxCount = us.getAllCustomerCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				request.getSession().setAttribute("maxCount", maxCount);
				// 跳转页面
				response.sendRedirect("khgl/customer.jsp?result=success&current_page="
						+ current_page);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("khgl/customer.jsp?result=failed");
		}
	}

}
