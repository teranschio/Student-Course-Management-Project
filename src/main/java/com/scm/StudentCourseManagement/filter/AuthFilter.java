package com.scm.StudentCourseManagement.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")   // intercept ALL requests
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();

        // Allow these without login
        boolean allowed =
                uri.endsWith("index.html") ||
                uri.contains("images") ||
                uri.endsWith("login");

        if (allowed) {
            chain.doFilter(request, response);
            return;
        }

        // Check login
        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("index.html"); // redirect to login
        }
    }
}