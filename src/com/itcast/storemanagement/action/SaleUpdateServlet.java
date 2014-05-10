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
import com.itcast.storemanagement.service.GoodsHavingService;
import com.itcast.storemanagement.service.GoodsService;
import com.itcast.storemanagement.service.SaleService;
import com.itcast.storemanagement.util.Now;

public class SaleUpdateServlet extends HttpServlet {

	SaleService ss = null;
	GoodsService gs = null;
	GoodsHavingService ghs = null;
	
	public SaleUpdateServlet() {
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
		String id = request.getParameter("id");
		try {
			if("update".equals(methodType)){
				this.update(request,response);
			}else{
				GoodsSaleOrder goodsSaleOrder = ss.getOneSaleOrder(id);
				List<Goods> goodsList = new ArrayList<Goods>();
				goodsList = gs.getAllGoods();
				request.getSession().setAttribute("goodsList", goodsList);
				request.getSession().setAttribute("goodsSaleOrder", goodsSaleOrder);
				response.sendRedirect("gmxsgl/xsgl/goodssaleUpdate.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String userId = request.getParameter("userid");
		String goodsName = request.getParameter("goodsname");
		String goodsSaleNum = request.getParameter("goodssalenum");
		String goodsSalePrice = request.getParameter("goodssaleprice");
		String goodsType = request.getParameter("goodstype");
		String goodsAddress = request.getParameter("goodsaddress");
		String goodsSalePerson = request.getParameter("goodssaleperson");
		String goodsSaleStatus = request.getParameter("goodssalestatus");
		String customerName = request.getParameter("customer");
		String customerTel = request.getParameter("customertel");
		String customerComment = request.getParameter("customercomment");
		String goodsComment = request.getParameter("goodscomment");
		String user = request.getParameter("user");
		String goodsBuyDate = Now.time();
		try {
			Goods goods = gs.getOneGoodsByCondition(goodsName, goodsType,
					goodsAddress);
			GoodsSaleOrder gso = new GoodsSaleOrder();
			gso.setId(Integer.parseInt(id));
			gso.setGoodsSale_date(goodsBuyDate);
			gso.setGoodsSale_num(Integer.parseInt(goodsSaleNum));
			gso.setGoodsSale_person(goodsSalePerson);
			gso.setUserId(userId);
			gso.setGoodsId(Integer.parseInt(goods.getId()));
			gso.setGoodsSale_status(goodsSaleStatus);
			gso.setGoodsSale_salePrice(goodsSalePrice);
			gso.setCustomerComment(customerComment);
			gso.setCustomerName(customerName);
			gso.setCustomerTel(customerTel);
			gso.setGoodsComment(goodsComment);
			gso.setUpdatePerson(user);
			if (ss.updateOneSaleOrder((gso))) {
				request.getSession().setAttribute("goodsSaleOrder", gso);
				response.sendRedirect("gmxsgl/xsgl/goodssaleUpdate.jsp?result=success");
			} else {
				response.sendRedirect("gmxsgl/xsgl/goodssaleUpdate.jsp?result=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
