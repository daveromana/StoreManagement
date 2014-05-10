package com.itcast.storemanagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.service.PowerService;
import com.itcast.storemanagement.util.SystemFinalVar;

public class PowerDatagridServlet extends HttpServlet {

	PowerService ps = null;

	public PowerDatagridServlet() {
		ps = new PowerService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int current_page = 1;
		int maxCount = 1;
		String methodType = request.getParameter("methodType");
		try {
			if ("delete".equals(methodType)) {
				this.delete(request, response);
			}else if ("query".equals(methodType)){
				this.query(request,response);
			} else {
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List powerList = ps.getAllPower(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("powerlist", powerList);
				// 计算总页数，Session存储总页数
				maxCount = ps.getAllPowerCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				request.getSession().setAttribute("queryPowerName", "");
				response.sendRedirect("xtgl/qxgl/power.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int current_page = 1;
		String queryPowerName = request.getParameter("querypowername");
		try {
			//如果不为空
			if (!"".equals(queryPowerName)) {
				List powerlist = ps.getAllPower(queryPowerName);
				request.getSession().setAttribute("powerlist", powerlist);
				request.getSession().setAttribute("queryPowerName", queryPowerName);
				response.sendRedirect("xtgl/qxgl/power.jsp?current_page="
						+ current_page);
			} else {
				List powerlist = ps.getAllPower(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("powerlist", powerlist);
				request.getSession().setAttribute("queryPowerName", "");
				response.sendRedirect("xtgl/qxgl/power.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String id = request.getParameter("id");
		if (ps.deleteOnePower(id)) {
			try {
				int current_page = 1;
				int maxCount = 1;
				// 重新获得权限列表
				List powerList = ps.getAllPower(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("powerlist", powerList);
				// 计算总页数，Session存储总页数
				maxCount = ps.getAllPowerCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				request.getSession().setAttribute("maxCount", maxCount);
				// 跳转页面
				response.sendRedirect("xtgl/qxgl/power.jsp?result=success&current_page="
						+ current_page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			response.sendRedirect("power.jsp?result=failed");

	}

}
