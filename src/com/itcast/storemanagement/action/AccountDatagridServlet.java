package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Account;
import com.itcast.storemanagement.service.AccountService;
import com.itcast.storemanagement.util.SystemFinalVar;

public class AccountDatagridServlet extends HttpServlet {

	AccountService as = null;

	public AccountDatagridServlet() {
		as = new AccountService();
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
				List accountList = as.getAccountList(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("accountList", accountList);
				// 计算总页数，Session存储总页数
				maxCount = as.getAllAccountCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				request.getSession().setAttribute("queryAccountName", "");
				response.sendRedirect("xtgl/zhgl/account.jsp?current_page="
						+ current_page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int current_page = 1;
		String queryAccountName = request.getParameter("queryaccountname");
		try {
			//如果不为空
			if (!"".equals(queryAccountName)) {
				List accountList = as.getAccountList(queryAccountName);
				request.getSession().setAttribute("accountList", accountList);
				request.getSession().setAttribute("queryAccountName", queryAccountName);
				response.sendRedirect("xtgl/zhgl/account.jsp?current_page="
						+ current_page);
			} else {
				List accountList = as.getAccountList(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("accountList", accountList);
				request.getSession().setAttribute("queryAccountName", "");
				response.sendRedirect("xtgl/zhgl/account.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String id = request.getParameter("id");
		if (as.deleteAccpunt(id)) {
			try {
				int current_page = 1;
				int maxCount = 1;
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List accountList = as.getAccountList(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("accountList", accountList);
				// 计算总页数，Session存储总页数
				maxCount = as.getAllAccountCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				response.sendRedirect("xtgl/zhgl/account.jsp?result=success&current_page="
						+ current_page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("xtgl/zhgl/account.jsp?result=failed");
		}

	}

}
