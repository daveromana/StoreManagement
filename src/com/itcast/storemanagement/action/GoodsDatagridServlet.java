package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.service.GoodsService;
import com.itcast.storemanagement.util.SystemFinalVar;

public class GoodsDatagridServlet extends HttpServlet {

	GoodsService gs = null;

	public GoodsDatagridServlet() {
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
			}else if ("query".equals(methodType)) {
				this.query(request, response);
			}  else {
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List goodsList = gs.getAllGoods(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("goodsList", goodsList);
				// 计算总页数，Session存储总页数
				maxCount = gs.getAllGoodsCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				request.getSession().setAttribute("queryGoodsName", "");
				response.sendRedirect("jcxxgl/hwgl/goods.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int current_page = 1;
		String queryGoodsName = request.getParameter("querygoodsname");
			//如果不为空
			try {
				if (!"".equals(queryGoodsName)) {
					List goodsList = gs.getAllGoods(queryGoodsName);
					request.getSession().setAttribute("goodsList", goodsList);
					request.getSession().setAttribute("queryGoodsName", queryGoodsName);
					response.sendRedirect("jcxxgl/hwgl/goods.jsp?current_page="
							+ current_page);
				} else {
					List goodsList = gs.getAllGoods(
							SystemFinalVar.INDEX_POWER_LENGTH, current_page);
					request.getSession().setAttribute("goodsList", goodsList);
					request.getSession().setAttribute("queryGoodsName", "");
					response.sendRedirect("jcxxgl/hwgl/goods.jsp?current_page="
							+ current_page);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		if(gs.deleteOneGoods(id)){
			try {
				int current_page = 1;
				int maxCount = 1;
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List goodsList = gs.getAllGoods(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("goodsList", goodsList);
				// 计算总页数，Session存储总页数
				maxCount = gs.getAllGoodsCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				response.sendRedirect("jcxxgl/hwgl/goods.jsp?result=success&current_page="
						+ current_page);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			response.sendRedirect("jcxxgl/hwgl/goods.jsp?result=failed");
		}

	}

}
