package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.GoodsBuyOrder;
import com.itcast.storemanagement.service.BuyService;
import com.itcast.storemanagement.service.GoodsHavingService;
import com.itcast.storemanagement.service.GoodsService;
import com.itcast.storemanagement.service.UserService;
import com.itcast.storemanagement.util.Now;
import com.itcast.storemanagement.vo.User;

public class BuyAddServlet extends HttpServlet {

	BuyService bs = null;
	GoodsService gs = null;

	public BuyAddServlet() {
		bs = new BuyService();
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
		} else {
			response.sendRedirect("gmxsgl/gmgl/goodsbuyAdd.jsp");
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
			GoodsBuyOrder gbo = new GoodsBuyOrder();
			gbo.setGoodsBuy_date(goodsBuyDate);
			gbo.setGoodsBuy_num(Integer.parseInt(goodsBuyNum));
			gbo.setGoodsBuy_person(goodsBuyPerson);
			gbo.setUserId(userId);
			gbo.setGoodsId(Integer.parseInt(goods.getId()));
			gbo.setGoodsBuy_status(goodsBuyStatus);
			gbo.setGoodsBuy_price(goodsBuyPrice);
			gbo.setCustomerComment(customerComment);
			gbo.setCustomerName(customerName);
			gbo.setCustomerTel(customerTel);
			gbo.setGoodsComment(goodsComment);
			gbo.setUpdatePerson(user);
			if (bs.buyOrderAdd(gbo)) {
				response.sendRedirect("gmxsgl/gmgl/goodsbuyAdd.jsp?result=success");
			} else {
				response.sendRedirect("gmxsgl/gmgl/goodsbuyAdd.jsp?result=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
