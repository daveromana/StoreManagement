package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Account;
import com.itcast.storemanagement.service.AccountService;
import com.itcast.storemanagement.service.UserService;
import com.itcast.storemanagement.vo.User;

public class AccountUpdateServlet extends HttpServlet {


	AccountService as = null;
	UserService us = null;
	
	public AccountUpdateServlet() {
		as = new AccountService();
		us = new UserService();
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
				this.update(response,request);
			}else{
				Account account = as.getOneAccount(id);
				request.getSession().setAttribute("account", account);
				List<User> userList = us.getAllUser();
				request.getSession().setAttribute("userList", userList);
				response.sendRedirect("xtgl/zhgl/accountUpdate.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void update(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String id = request.getParameter("id");
		String loginName = request.getParameter("loginname");
		String loginPassword = request.getParameter("loginpassword");
		String userName = request.getParameter("username");
		Account a = new Account(Integer.parseInt(id), loginName, loginPassword, userName);
		try {
			if(as.updateAccount(a)){
				request.getSession().setAttribute("account", a);
				List<User> userList = us.getAllUser();
				request.getSession().setAttribute("userList", userList);
				response.sendRedirect("xtgl/zhgl/accountUpdate.jsp?result=success");
			}else{
				response.sendRedirect("xtgl/zhgl/accountUpdate.jsp?result=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
