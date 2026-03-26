package com.scm.StudentCourseManagement.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession session = req.getSession(false);
		
		if(session != null && session.getAttribute("user") != null) {
			res.sendRedirect("dashboard.jsp");
		}else {
			res.sendRedirect("index.html");
		}
	}
}
