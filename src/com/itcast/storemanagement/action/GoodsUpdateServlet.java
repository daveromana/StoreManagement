package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Goods;
import com.itcast.storemanagement.service.GoodsService;

public class GoodsUpdateServlet extends HttpServlet {

	GoodsService gs = null;
	public GoodsUpdateServlet() {
		gs = new GoodsService();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String methodType = request.getParameter("methodType");
		try {
			if("update".equals(methodType)){
				this.update(request,response);
			}else{
				Goods goods = gs.getOneGoodsById(id);
				request.getSession().setAttribute("goods", goods);
				response.sendRedirect("jcxxgl/hwgl/goodsUpdate.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String goodsName = request.getParameter("goodsname");
		String goodsType = request.getParameter("goodstype");
		String goodsAddress = request.getParameter("goodsaddress");
		Goods goods = new Goods(id, goodsName,goodsType,goodsAddress);
		if(gs.updateOneGoods(goods)){
			request.getSession().setAttribute("goods", goods);
			response.sendRedirect("jcxxgl/hwgl/goodsUpdate.jsp?result=success");
		}else{
			response.sendRedirect("jcxxgl/hwgl/goodsUpdate.jsp?result=failed");
		}
		
	}

}
