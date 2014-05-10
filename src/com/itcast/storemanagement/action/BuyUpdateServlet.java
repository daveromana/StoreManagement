package com.itcast.storemanagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.po.GoodsBuyOrder;
import com.itcast.storemanagement.service.BuyService;
import com.itcast.storemanagement.service.GoodsHavingService;
import com.itcast.storemanagement.service.GoodsService;
import com.itcast.storemanagement.util.Now;
import com.itcast.storemanagement.vo.User;

public class BuyUpdateServlet extends HttpServlet {

	BuyService bs = null;
	GoodsService gs = null;
	GoodsHavingService ghs = null;
	
	public BuyUpdateServlet() {
		bs = new BuyService();
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
		String id = request.getParameter("id");
		try {
			if("update".equals(methodType)){
				this.update(request,response);
			}else{
				GoodsBuyOrder goodsBuyOrder = bs.getOneBuyOrder(id);
				List<Goods> goodsList = new ArrayList<Goods>();
				goodsList = gs.getAllGoods();
				request.getSession().setAttribute("goodsList", goodsList);
				request.getSession().setAttribute("goodsBuyOrder", goodsBuyOrder);
				response.sendRedirect("gmxsgl/gmgl/goodsbuyUpdate.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String userId = request.getParameter("userid");
		String goodsName = request.getParameter("goodsname");
		String goodsBuyNum = request.getParameter("goodsbuynum");
		String goodsBuyPrice = request.getParameter("goodsbuyprice");
		String goodsType = request.getParameter("goodstype");
		String goodsAddress = request.getParameter("goodsaddress");
		String goodsBuyPerson = request.getParameter("goodsbuyperson");
		String goodsBuyStatus = request.getParameter("goodsbuystatus");
		String customerName = request.getParameter("customer");
		String customerTel = request.getParameter("customertel");
		String customerComment = request.getParameter("customercomment");
		String goodsComment = request.getParameter("goodscomment");
		String user = request.getParameter("user");
		String goodsBuyDate = Now.time();
		try {
			Goods goods = gs.getOneGoodsByCondition(goodsName, goodsType,
					goodsAddress);
			GoodsBuyOrder gbo = new GoodsBuyOrder();
			gbo.setId(Integer.parseInt(id));
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
			if (bs.updateOneBuyOrder((gbo))) {
				request.getSession().setAttribute("goodsBuyOrder", gbo);
				response.sendRedirect("gmxsgl/gmgl/goodsbuyUpdate.jsp?result=success");
			} else {
				response.sendRedirect("gmxsgl/gmgl/goodsbuyUpdate.jsp?result=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
