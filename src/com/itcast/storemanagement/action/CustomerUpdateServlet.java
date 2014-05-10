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

public class CustomerUpdateServlet extends HttpServlet {

	RoleService rs = null;
	UserService us = null;
	
	public CustomerUpdateServlet() {
		rs = new RoleService();
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
		if("update".equals(methodType)){
			this.update(request,response);
		}else{
			try {
				User user = us.getOneUser(id);
				List<Role> roleList = rs.getAllRole();
				request.getSession().setAttribute("roleList", roleList);
				request.getSession().setAttribute("user", user);
				response.sendRedirect("xtgl/yhgl/userUpdate.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	}


	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String userName = request.getParameter("username");
		String userSex = request.getParameter("usersex");
		String userPhoneNUM_ = request.getParameter("userphonenum");
		String userEmail = request.getParameter("useremail");
		String userComment = request.getParameter("usercomment");
		String userJob = request.getParameter("userjob");
		String[] role = request.getParameterValues("userrole");
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setComment(userComment);
		user.setEmail(userEmail);
		user.setJob(userJob);
		user.setPhoneNUM_(userPhoneNUM_);
		user.setSex(userSex);
		user.setUserName(userName);
		if (us.updateOneUser(user, role)) {
			try {
				List<Role> roleList = rs.getAllRole();
				request.getSession().setAttribute("roleList", roleList);
				response.sendRedirect("xtgl/yhgl/customerUpdate.jsp?result=success");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("xtgl/yhgl/customerUpdate.jsp?result=failed");
		}
	}

}
