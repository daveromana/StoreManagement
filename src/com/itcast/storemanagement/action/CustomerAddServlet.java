package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.service.RoleService;
import com.itcast.storemanagement.service.UserService;
import com.itcast.storemanagement.vo.Role;
import com.itcast.storemanagement.vo.User;

public class CustomerAddServlet extends HttpServlet {

	UserService us = null;
	RoleService rs = null;

	public CustomerAddServlet() {
		us = new UserService();
		rs = new RoleService();
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
			try {
				List<Role> roleList = rs.getAllRole();
				request.getSession().setAttribute("roleList", roleList);
				response.sendRedirect("khgl/customerAdd.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userName = request.getParameter("username");
		String userSex = request.getParameter("usersex");
		String userPhoneNUM_ = request.getParameter("userphonenum");
		String userEmail = request.getParameter("useremail");
		String userComment = request.getParameter("usercomment");
		String userJob = request.getParameter("userjob");
		String[] role = request.getParameterValues("userrole");
		User user = new User();
		user.setComment(userComment);
		user.setEmail(userEmail);
		user.setJob(userJob);
		user.setPhoneNUM_(userPhoneNUM_);
		user.setSex(userSex);
		user.setUserName(userName);
		if (us.userAdd(user, role)) {
			try {
				List<Role> roleList = rs.getAllRole();
				request.getSession().setAttribute("roleList", roleList);
				response.sendRedirect("khgl/customerAdd.jsp?result=success");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("khgl/customerAdd.jsp?result=failed");
		}
	}

}
