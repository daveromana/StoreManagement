package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.GoodsSaleOrder;
import com.itcast.storemanagement.service.GoodsHavingService;
import com.itcast.storemanagement.service.GoodsService;
import com.itcast.storemanagement.service.SaleService;
import com.itcast.storemanagement.util.Now;

public class SaleAddServlet extends HttpServlet {

	SaleService ss = null;
	GoodsService gs = null;
	GoodsHavingService ghs = null;

	public SaleAddServlet() {
		ss = new SaleService();
		gs = new GoodsService();
		ghs = new GoodsHavingService();
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
		} else if ("check".equals(methodType)) {
			this.check(request, response);
		} else {
			response.sendRedirect("gmxsgl/xsgl/goodssaleAdd.jsp");
		}
	}

	/**
	 * AJAX检查货物在在库货物表里是否已存在
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void check(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String goodsName = request.getParameter("goodsname");
		String goodsType = request.getParameter("goodstype");
		String goodsAddress = request.getParameter("goodsaddress");
		try {
			Goods goods = gs.getOneGoodsByCondition(goodsName, goodsType,
					goodsAddress);
			if (!ghs.getOneGoodsStatus(goods.getId())) {
				// 存在
				response.getWriter().print(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String userId = request.getParameter("userid");
		String goodsName = request.getParameter("goodsname");
		String goodsBuyNum = request.getParameter("goodsbuynum");
		String goodsBuyPrice = request.getParameter("goodsbuyprice");
		String goodsBuyType = request.getParameter("goodsbuytype");
		String goodsBuyAddress = request.getParameter("goodsbuyaddress");
		String goodsBuyPerson = request.getParameter("goodsbuyperson");
		String goodsBuyStatus = request.getParameter("goodsbuystatus");
		String customerName = request.getParameter("customer");
		String customerTel = request.getParameter("customertel");
		String customerComment = request.getParameter("customercomment");
		String goodsComment = request.getParameter("goodscomment");
		String user = request.getParameter("user");
		String goodsBuyDate = Now.time();
		try {
			Goods goods = gs.getOneGoodsByCondition(goodsName, goodsBuyType,
					goodsBuyAddress);
			GoodsSaleOrder gso = new GoodsSaleOrder();
			gso.setGoodsSale_date(goodsBuyDate);
			gso.setGoodsSale_num(Integer.parseInt(goodsBuyNum));
			gso.setGoodsSale_person(goodsBuyPerson);
			gso.setUserId(userId);
			gso.setGoodsId(Integer.parseInt(goods.getId()));
			gso.setGoodsSale_status(goodsBuyStatus);
			gso.setGoodsSale_salePrice(goodsBuyPrice);
			gso.setCustomerComment(customerComment);
			gso.setCustomerName(customerName);
			gso.setCustomerTel(customerTel);
			gso.setGoodsComment(goodsComment);
			gso.setUpdatePerson(user);
			// 得到商品不为空，则商品存在库中，则修改数量
			if (!ghs.getOneGoodsStatus(goods.getId())) {
				response.sendRedirect("gmxsgl/xsgl/goodssaleAdd.jsp?result=retry");
			} else if (ss.saleOrderAdd(gso)) {
				response.sendRedirect("gmxsgl/xsgl/goodssaleAdd.jsp?result=success");
			} else {
				response.sendRedirect("gmxsgl/xsgl/goodssaleAdd.jsp?result=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
