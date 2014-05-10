package com.itcast.storemanagement.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.service.GoodsService;

public class GoodsAddServlet extends HttpServlet {

	GoodsService gs = null;
	
	public GoodsAddServlet() {
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
		if("add".equals(methodType)){
			this.add(request,response);
		}else{
			response.sendRedirect("jcxxgl/hwgl/goodsAdd.jsp");
		}
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String goodsName = request.getParameter("goodsname");
		String goodsType = request.getParameter("goodstype");
		String goodsAddress = request.getParameter("goodsaddress");
		Goods goods = new Goods();
		goods.setGoodsName(goodsName);
		goods.setGoodsAddress(goodsAddress);
		goods.setGoodsType(goodsType);
		if(gs.GoodsAdd(goods)){
			response.sendRedirect("jcxxgl/hwgl/goodsAdd.jsp?result=success");
		}else{
			response.sendRedirect("jcxxgl/hwgl/goodsAdd.jsp?result=failed");
		}
	}

}
