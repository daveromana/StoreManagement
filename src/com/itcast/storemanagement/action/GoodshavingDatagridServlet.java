package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.GoodsHaving;
import com.itcast.storemanagement.service.GoodsHavingService;
import com.itcast.storemanagement.service.GoodsService;
import com.itcast.storemanagement.util.SystemFinalVar;

public class GoodshavingDatagridServlet extends HttpServlet {

	GoodsHavingService ghs = null;
	GoodsService gs = null;

	public GoodshavingDatagridServlet() {
		ghs = new GoodsHavingService();
		gs = new GoodsService();
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
				// 获得在库货物列表
				List<GoodsHaving> goodsHavingList = ghs.getAllGoodsHaving(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("goodsHavingList",
						goodsHavingList);
				// 计算总页数，Session存储总页数
				maxCount = ghs.getAllGoodsHavingCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				request.getSession().setAttribute("queryGoodsName", "");
				response.sendRedirect("kcgl/goodshaving.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int current_page = 1;
		String queryGoodsName = request.getParameter("querygoodsname");
		try {
			//如果不为空
			if (!"".equals(queryGoodsName)) {
				List goodsHavingList = ghs.getAllGoodsHaving(queryGoodsName);
				request.getSession().setAttribute("goodsHavingList", goodsHavingList);
				request.getSession().setAttribute("queryGoodsName", queryGoodsName);
				response.sendRedirect("kcgl/goodshaving.jsp?current_page="
						+ current_page);
			} else {
				List goodsHavingList = ghs.getAllGoodsHaving(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("goodsHavingList", goodsHavingList);
				request.getSession().setAttribute("queryGoodsName", "");
				response.sendRedirect("kcgl/goodshaving.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String id = request.getParameter("id");
		try {
			int current_page = 1;
			int maxCount = 1;
			if (ghs.deleteOneGoods(id)) {
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得在库货物列表
				List<GoodsHaving> goodsHavingList = ghs.getAllGoodsHaving(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("goodsHavingList",
						goodsHavingList);
				// 计算总页数，Session存储总页数
				maxCount = ghs.getAllGoodsHavingCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				response.sendRedirect("kcgl/goodshaving.jsp?result=success&current_page="
						+ current_page);
			} else {
				response.sendRedirect("kcgl/goodshaving.jsp?result=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
