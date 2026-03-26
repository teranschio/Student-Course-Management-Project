package com.scm.StudentCourseManagement.servlet;

import com.scm.StudentCourseManagement.dao.UserDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDAO user = new UserDAO();

        HttpSession session = req.getSession();
        
        try {
			if(user.validateUser(username, password)){
				session.setAttribute("user", username);
			    res.sendRedirect("dashboard");
			}else{
			    res.sendRedirect("index.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}