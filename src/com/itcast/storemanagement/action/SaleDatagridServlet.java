package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.GoodsBuyOrder;
import com.itcast.storemanagement.po.GoodsSaleOrder;
import com.itcast.storemanagement.service.GoodsService;
import com.itcast.storemanagement.service.SaleService;
import com.itcast.storemanagement.service.UserService;
import com.itcast.storemanagement.util.SystemFinalVar;
import com.itcast.storemanagement.vo.User;

public class SaleDatagridServlet extends HttpServlet {

	SaleService ss = null;
	GoodsService gs = null;
	UserService us = null;
	
	public SaleDatagridServlet() {
		ss = new SaleService();
		gs = new GoodsService();
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
		try {
			if ("delete".equals(methodType)) {
				this.delete(request, response);
			}else if ("query".equals(methodType)) {
				this.query(request, response);
			} else {
				List<Goods> goodsList = new ArrayList<Goods>();
				List<User> userList = new ArrayList<User>();
				goodsList = gs.getAllGoods();
				userList = us.getAllUser();
				request.getSession().setAttribute("goodsList", goodsList);
				request.getSession().setAttribute("userList", userList);
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List goodsSaleList = ss.getAllSaleOrder(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("goodsSaleList", goodsSaleList);
				// 计算总页数，Session存储总页数
				maxCount = ss.getAllSaleOrderCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				request.getSession().setAttribute("queryGoodsName", "");
				response.sendRedirect("gmxsgl/xsgl/goodssale.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int current_page = 1;
		String queryGoodsName = request.getParameter("querygoodsname");
		String goodsId = "0";
		try {
			//得到商品 id 一个或多个
			List goodsList = gs.getAllGoods(queryGoodsName);
			for (int i = 0; i < goodsList.size(); i++) {
				Goods g = (Goods) goodsList.get(i);
				goodsId = goodsId + "," + g.getId();
			}
			//如果不为空
			if (!"".equals(queryGoodsName)) {
				List goodsSaleList = ss.getAllSaleOrder(goodsId);
				request.getSession().setAttribute("goodsSaleList", goodsSaleList);
				request.getSession().setAttribute("queryGoodsName", queryGoodsName);
				response.sendRedirect("gmxsgl/xsgl/goodssale.jsp?current_page="
						+ current_page);
			} else {
				List goodsSaleList = ss.getAllSaleOrder(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("goodsSaleList", goodsSaleList);
				request.getSession().setAttribute("queryGoodsName", "");
				response.sendRedirect("gmxsgl/xsgl/goodssale.jsp?current_page="
						+ current_page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String id = request.getParameter("id");
			if(ss.deleteOneSaleOrder(id)){
				int current_page = 1;
				int maxCount = 1;
				List<Goods> goodsList = new ArrayList<Goods>();
				List<User> userList = new ArrayList<User>();
				goodsList = gs.getAllGoods();
				userList = us.getAllUser();
				request.getSession().setAttribute("goodsList", goodsList);
				request.getSession().setAttribute("userList", userList);
				// 判断当前页数
				String page = request.getParameter("current_page");
				if (page != "" && page != null) {
					current_page = Integer.parseInt(page);
				}
				// 获得权限列表
				List goodsSaleList = ss.getAllSaleOrder(
						SystemFinalVar.INDEX_POWER_LENGTH, current_page);
				request.getSession().setAttribute("goodsSaleList", goodsSaleList);
				// 计算总页数，Session存储总页数
				maxCount = ss.getAllSaleOrderCount();
				if (maxCount % SystemFinalVar.INDEX_POWER_LENGTH == 0)
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH;
				else
					maxCount = maxCount / SystemFinalVar.INDEX_POWER_LENGTH + 1;
				// 跳转页面
				request.getSession().setAttribute("maxCount", maxCount);
				response.sendRedirect("gmxsgl/xsgl/goodssale.jsp?result=success&current_page="
						+ current_page);
			}else{
				response.sendRedirect("gmxsgl/xsgl/goodssale.jsp?result=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
