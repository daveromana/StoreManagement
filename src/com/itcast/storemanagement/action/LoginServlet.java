package com.itcast.storemanagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itcast.storemanagement.po.Account;
import com.itcast.storemanagement.service.AccountService;

public class LoginServlet extends HttpServlet {

	AccountService as = null;

	public LoginServlet() {
		as = new AccountService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String loginName = request.getParameter("username");
		String loginPassword = request.getParameter("userpassword");
		Account account = new Account();
		account.setLoginName(loginName);
		account.setLoginPassword(loginPassword);
		try {
			List accountList = as.select(account);
			if (accountList.size() > 0) {
				initHomeData(request, response, accountList);
				out.print("0");// 存在账户
			} else {
				out.print("1");// 不存在账户
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initHomeData(HttpServletRequest request, HttpServletResponse response, List accountList) {
		HttpSession session = request.getSession();
		Account account = (Account)accountList.get(0);
		List homeData = new ArrayList<>();
		try {
			homeData = as.getHomeInfo(account);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("homeData", homeData);
	}

}
