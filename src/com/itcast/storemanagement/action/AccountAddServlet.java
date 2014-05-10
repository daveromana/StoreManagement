package com.itcast.storemanagement.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Account;
import com.itcast.storemanagement.service.AccountService;

public class AccountAddServlet extends HttpServlet {

	AccountService as = null;

	public AccountAddServlet() {
		as = new AccountService();
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
			this.add(request,response);
		} else {
			response.sendRedirect("xtgl/zhgl/accountAdd.jsp");
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String loginName = request.getParameter("loginname");
		String loginPassWord = request.getParameter("loginpassword");
		Account account = new Account();
		account.setLoginName(loginName);
		account.setLoginPassword(loginPassWord);
		if(as.addAccount(account)){
			response.sendRedirect("xtgl/zhgl/accountAdd.jsp?result=success");
		}else{
			response.sendRedirect("xtgl/zhgl/accountAdd.jsp?result=failed");
		}
	}

}
