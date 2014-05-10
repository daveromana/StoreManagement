package com.itcast.storemanagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.GoodsHaving;
import com.itcast.storemanagement.service.GoodsHavingService;
import com.itcast.storemanagement.service.GoodsService;

public class GoodshavingUpdateServlet extends HttpServlet {

	GoodsHavingService ghs = null;
	GoodsService gs = null;

	public GoodshavingUpdateServlet() {
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
		if ("update".equals(methodType)) {
			this.update(request, response);
		} else {
			String id = request.getParameter("id");
			try {
				GoodsHaving gh = ghs.getOneGoods(id);
				Goods g = gs.getOneGoodsById(gh.getGoodsId().toString());
				gh.setGoodsName(g.getGoodsName());
				gh.setGoodsType(g.getGoodsType());
				gh.setGoodsAddress(g.getGoodsAddress());
				request.getSession().setAttribute("goodsHaving", gh);
				response.sendRedirect("kcgl/goodshavingUpdate.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String id = request.getParameter("id");
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
		gh.setId(Integer.parseInt(id));
		gh.setCustomerComment(customerComment);
		gh.setCustomerName(customerName);
		gh.setCustomerTel(customerTel);
		gh.setGoodsComment(goodsComment);
		gh.setGoodsName(goodsName);
		gh.setGoodsNum(Integer.parseInt(goodsNum));
		gh.setGoodsPrice(goodsPrice);
		gh.setUpdatePerson(user);
		Goods g = new Goods();
		try {
			g = gs.getOneGoodsByCondition(goodsName,goodsType,goodsAddress);
			gh.setGoodsId(Integer.parseInt(g.getId()));
			if (ghs.updateOneGoods(gh)) {
				request.getSession().setAttribute("goodsHaving", gh);
				response.sendRedirect("kcgl/goodshavingUpdate.jsp?result=success");
			} else {
				response.sendRedirect("kcgl/goodshavingUpdate.jsp?result=failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
