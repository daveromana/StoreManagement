package com.itcast.storemanagement.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.storemanagement.po.Power;
import com.itcast.storemanagement.service.PowerService;
import com.itcast.storemanagement.service.RoleService;
import com.itcast.storemanagement.vo.Role;

public class RoleUpdateServlet extends HttpServlet {

	RoleService rs = null;
	PowerService ps = null;
	
	public RoleUpdateServlet() {
		rs = new RoleService();
		ps = new PowerService();
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
		if("update".equals(methodType)){
			this.update(request,response);
		}else{
			try {
				Role role = rs.getOneRole(id);
				List<Power> powerList = ps.getAllPower();
				request.getSession().setAttribute("role", role);
				request.getSession().setAttribute("powerList", powerList);
				response.sendRedirect("xtgl/jsgl/roleUpdate.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String roleName = request.getParameter("rolename");
		String roleComment = request.getParameter("rolecomment");
		String[] rolePower = request.getParameterValues("rolepower");
		Role r = new Role();
		r.setId(Integer.parseInt(id));
		r.setRoleName(roleName);
		r.setComment(roleComment);
		if(rs.updateOneRole(r, rolePower)){
			try {
				Role role = rs.getOneRole(id);
				List<Power> powerList = ps.getAllPower();
				request.getSession().setAttribute("role", role);
				request.getSession().setAttribute("powerList", powerList);
				response.sendRedirect("xtgl/jsgl/roleUpdate.jsp?result=success");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			response.sendRedirect("xtgl/jsgl/roleUpdate.jsp?result=failed");
		}
		
	}

}
