package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.GoodsHaving;
import com.itcast.storemanagement.service.GoodsHavingService;
import com.itcast.storemanagement.service.GoodsService;

public class GoodshavingAddServlet extends HttpServlet {

	GoodsHavingService ghs = null;
	GoodsService gs = null;

	public GoodshavingAddServlet() {
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
		if ("add".equals(methodType)) {
			this.add(request, response);
		} else if ("check".equals(methodType)){
			this.check(request,response);
		}else {
			response.sendRedirect("kcgl/goodshavingAdd.jsp");
		}
	}
	/**
	 * AJAX检查货物在在库货物表里是否已存在
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String goodsName = request.getParameter("goodsName");
		String goodsType = request.getParameter("goodsType");
		String goodsAddress = request.getParameter("goodsAddress");
		try {
			Goods goods = gs.getOneGoodsByCondition(goodsName,goodsType,goodsAddress);
			if(ghs.getOneGoodsStatus(goods.getId())){
				//存在
				response.getWriter().print(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 添加库存货物方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String goodsName = request.getParameter("goods");
		String goodsNum = request.getParameter("num");
		String goodsPrice = request.getParameter("price");
		String goodsType = request.getParameter("goodstype");
		String goodsAddress = request.getParameter("goodsaddress");
		String customerName = request.getParameter("customer");
		String customerTel = request.getParameter("customertel");
		String customerComment = request.getParameter("customercomment");
		String goodsComment = request.getParameter("goodscomment");
		String user = request.getParameter("user");
		GoodsHaving gh = new GoodsHaving();
		gh.setCustomerComment(customerComment);
		gh.setCustomerName(customerName);
		gh.setCustomerTel(customerTel);
		gh.setGoodsComment(goodsComment);
		gh.setGoodsNum(Integer.parseInt(goodsNum));
		gh.setGoodsPrice(goodsPrice);
		gh.setUpdatePerson(user);
		Goods g = new Goods();
		try {
			g = gs.getOneGoodsByCondition(goodsName,goodsType,goodsAddress);
			gh.setGoodsId(Integer.parseInt(g.getId()));
			// 得到商品不为空，则商品存在库中，则修改数量
			if (ghs.getOneGoodsStatus(g.getId())) {
				response.sendRedirect("kcgl/goodshavingAdd.jsp?result=retry");
			} else if (ghs.GoodsAdd(gh)) {
				gh.setGoodsId(Integer.parseInt(g.getId()));
				response.sendRedirect("kcgl/goodshavingAdd.jsp?result=success");
			} else {
				response.sendRedirect("kcgl/goodshavingAdd.jsp?result=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
